package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditBookScreenController {
    @FXML
    private ChoiceBox<Integer> chooseRate = new ChoiceBox<>();
    private Integer[] rateNumbers = {1, 2, 3, 4, 5};
    private ViewBookController viewBookController;
    private int oldIsbn;
    private Book editedBook;
    @FXML
    private TextField t0;
    @FXML
    private TextField t1;
    @FXML
    private TextField t2;
    @FXML
    private TextField t3;
    @FXML
    private TextField t4;
    @FXML
    private TextField t5;
    @FXML
    private TextField t6;
    @FXML
    private TextField t7;
    @FXML
    private TextField t8;
    @FXML
    private TextField t9;
    @FXML
    private TextField t10;
    @FXML
    private TextField t11;
    @FXML
    private ListView<String> LW1;
    @FXML
    private ListView<String> LW2;
    @FXML
    private ListView<String> LW3;
    @FXML
    private ImageView imageView = new ImageView();
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;


    private boolean isNull;
    private final String dateFormat = "dd/MM/yyyy";
    private MainScreenController mainScreenController;


    public void setViewBookController (ViewBookController viewBookController, Book selectedBook) {
        this.viewBookController = viewBookController;
        this.editedBook = selectedBook;
        try {
            t0.setText(selectedBook.getTitle());
            t1.setText(String.valueOf(selectedBook.getIsbn()));
            t2.setText(selectedBook.getPublisher());
            t3.setText(String.valueOf(selectedBook.getEdition()));
            chooseRate.getItems().addAll(rateNumbers);
            chooseRate.setValue(selectedBook.getRate());
            t5.setText(selectedBook.getCoverType());
            t6.setText(selectedBook.getSubtitle());
            if (selectedBook.getTranslators() != null) {
                LW1.getItems().addAll((selectedBook.getTranslators()));
            }
            t8.setText(selectedBook.getLanguage());
            LW2.getItems().addAll(selectedBook.getAuthors());
            LW3.getItems().addAll(selectedBook.getTags());
            t10.setText(selectedBook.getDate());
            File file = new File(selectedBook.getImagePath());
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            oldIsbn = selectedBook.getIsbn();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void cancel () {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addTranslator(){
        if (t7.getText().isBlank()) return;
        LW1.getItems().add(t7.getText());
        t7.clear();
    }
    public void addAuthor(){
        if (t9.getText().isBlank()) return;
        LW2.getItems().add(t9.getText());
        t9.clear();
    }
    public void addTag() {
        if (t11.getText().isBlank()) return;
        LW3.getItems().add(t11.getText());
        t11.clear();
    }

    public void removeTranslator(){
        String selectedValue = LW1.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            LW1.getItems().remove(selectedValue);
        }
    }
    public void removeAuthor(){
        String selectedValue = LW2.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            LW2.getItems().remove(selectedValue);
        }
    }
    public void removeTag() {
        String selectedValue = LW3.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            LW3.getItems().remove(selectedValue);
        }
    }
    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png"),
                new FileChooser.ExtensionFilter("All Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // image seçildi
            String targetDirectory = "src/images/";
            File targetFolder = new File(targetDirectory);
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Path.of(targetDirectory + selectedFile.getName());
            try {
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            editedBook.setImagePath(targetPath.toString());
        }
        // else'i olmaması lazım öncekiyle yoladevam
    }

    public void NullAlert(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all the texts!");
        alert.showAndWait();

    }
    public void isTextNull(ActionEvent event){
        if(t0.getText().isBlank() || t1.getText().isBlank() || t2.getText().isBlank() ||t3.getText().isBlank() ||
                chooseRate.getValue() == null || t5.getText().isBlank() ||
                (editedBook.isHasSubtitle() && t6.getText().isBlank()) ||
                (editedBook.isTranslation() && LW1.getItems().isEmpty()) ||
                t8.getText().isBlank() || LW2.getItems().isEmpty() || t10.getText().isBlank()
                || LW3.getItems().isEmpty()){
            isNull = true;
            return;
        }
        isNull = false;
    }
    @FXML
    public void saveBookChanges (ActionEvent event) {

        isTextNull(event);
        if (isNull) {
            NullAlert(event);
        } else {
            l1.setVisible(false);
            l2.setVisible(false);
            l3.setVisible(false);
            boolean hasError = false;

            if (!t0.getText().isBlank()) {
                editedBook.setTitle(t0.getText());
            } else {
                editedBook.setTitle(null);
            }

            int isbn = 0;
            if (!t1.getText().isBlank()) {
                try {
                    isbn = Integer.parseInt(t1.getText());
                    editedBook.setIsbn(isbn);
                } catch (Exception e) {
                    hasError = true;
                    t1.clear();
                    l1.setVisible(true);
                }
            } else {
                hasError = true;
            }

            if (!t2.getText().isBlank()) {
                editedBook.setPublisher(t2.getText());
            } else {
                hasError = true;
            }

            int edition = 0;
            if (!t3.getText().isBlank()) {
                try {
                    edition = Integer.parseInt(t3.getText());
                    editedBook.setEdition(edition);
                    hasError = false;
                } catch (Exception e) {
                    hasError = true;
                    t3.clear();
                    l2.setVisible(true);
                }
            } else {
                hasError = true;
            }

            if (chooseRate.getValue() != null) {
                editedBook.setRate(chooseRate.getValue());
            } else {
                hasError = true;
            }

            if (!t5.getText().isBlank()) {
                editedBook.setCoverType(t5.getText());
            } else {
                hasError = true;
            }

            if (t6.getText() != null) {
                editedBook.setHasSubtitle(true);
                editedBook.setSubtitle(t6.getText());
            } else {
                editedBook.setHasSubtitle(false);
                editedBook.setSubtitle(null);
            }

            if (!LW1.getItems().isEmpty()) {
                editedBook.setTranslators(LW1.getItems().stream().toList());
                editedBook.setTranslation(true);
            } else {
                editedBook.setTranslators(null);
                editedBook.setTranslation(false);
            }


            if (!t8.getText().isBlank()) {
                editedBook.setLanguage(t8.getText());
            }else {
                hasError = true;
            }

            if (!LW2.getItems().isEmpty()) {
                editedBook.setAuthors(LW2.getItems().stream().toList());

            } else {
                editedBook.setAuthors(null);
                hasError = true;
            }

            // date uyuşuo mu ?
            String date = t10.getText();
            if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                l3.setVisible(true);
                hasError = true;
                t10.clear();
            }
            else {
                editedBook.setDate(date);
            }

            // TODO listeleri düzelt
            if (!LW3.getItems().isEmpty()) {
                editedBook.setTags(LW3.getItems().stream().toList());
            } else {
                editedBook.setTags(null);
                hasError = true;
            }


            if(!hasError){

                /*
                if (checkSubtitle.isSelected()) {
                    subtitle = t7.getText();
                }
                if (checkTranslator.isSelected()) {
                    translators.addAll(listView2.getItems());
                }

                 */

                //TODO

                for (int i = 0;i<viewBookController.getMainScreenController().getBookArrayList().size();i++) {
                    if (oldIsbn == viewBookController.getMainScreenController().getBookArrayList().get(i).getIsbn()) {
                        viewBookController.getMainScreenController().getBookArrayList().set(i, editedBook);
                        break;
                    }
                }

                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            }
        }

        /*
        Book editedBook = new Book();
        editedBook.setTitle(t0.getText());
        editedBook.setIsbn(Integer.parseInt(t1.getText()));
        editedBook.setPublisher(t2.getText());
        editedBook.getEdition(t3.getText());
        editedBook.setRate(chooseRate.getValue());
        editedBook.setTitle(t5.getText());
        editedBook.setTitle(t0.getText());
        editedBook.setTitle(t0.getText());
        editedBook.setTitle(t0.getText());
        editedBook.setTitle(t0.getText());
        editedBook.setTitle(t0.getText());

         */


    }

}
