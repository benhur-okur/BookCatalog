package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Files;
import java.nio.file.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

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
    private  TextField t11;

    @FXML
    private TextField t101;

    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listView2;
    @FXML
    private ListView<String> tagListView;

    @FXML
    private CheckBox checkSubtitle;
    @FXML
    private CheckBox checkTranslator;

    private MainScreenController mainScreenController;

    @FXML
    private DialogPane dialogPane;

    private JSON json;
    private Book book;

    private String subtitle = null;
    private String translator = null;
    private final String dateFormat = "dd/MM/yyyy";
    private boolean isNull = false;
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> translators = new ArrayList<>();
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    private String imagePath = "src\\images\\noimage.jpg";
    @FXML
    private ImageView imageView;

    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    private ArrayList<String> tags = new ArrayList<>();

    @FXML
    private Button addTranslator;
    @FXML
    private ChoiceBox<Integer> chooseRate;
    private Integer[] rateNumbers = {1, 2, 3, 4, 5};

    public void setMainScreenController (MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private void cancel () {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
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
            imagePath = targetPath.toString();
        } else {
            Image defaultImage = new Image("src\\images\\noimage.jpg");
            imageView.setImage(defaultImage);
            imagePath = "src\\images\\noimage.jpg";

        }
    }
    @FXML
    private void addBook(ActionEvent event) throws InvocationTargetException, IOException {

        isTextNull(event);
        if (isNull) {
            NullAlert(event);
        } else {
            l1.setVisible(false);
            l2.setVisible(false);
            l3.setVisible(false);
            l4.setVisible(false);
            boolean hasError = false;

            int edition = 0;
            int isbn = 0;
            String date = t11.getText();
            if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                l4.setVisible(true);
                hasError = true;
                t11.clear();
            }
            else {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            }

            try {
                isbn = Integer.parseInt(t2.getText());
            } catch (Exception e) {
                hasError = true;
                t2.clear();
                l1.setVisible(true);
            }

            try {
                edition = Integer.parseInt(t4.getText());
            } catch (Exception e) {
                hasError = true;
                t4.clear();
                l2.setVisible(true);
            }

            if(!hasError){
                authors.addAll(listView.getItems());
                String title = t1.getText();
                String publisher = t3.getText();
                String coverType = t6.getText();
                if (checkSubtitle.isSelected()) {
                    subtitle = t7.getText();
                }
                if (checkTranslator.isSelected()) {
                    translators.addAll(listView2.getItems());
                }
                int rate = chooseRate.getValue();
                String language = t9.getText();

                tags.addAll(tagListView.getItems());

                book = new Book(title, isbn, publisher, edition, rate, coverType, subtitle,
                        translators, language, authors, date, imagePath, tags);

                if (checkSubtitle.isSelected()) {
                    book.setHasSubtitle(true);
                }
                if (checkTranslator.isSelected()) {
                    book.setTranslation(true);
                }
                mainScreenController.getBookArrayList().add(book);
                mainScreenController.refreshBookList();

                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseRate.getItems().addAll(rateNumbers);
    }

    public void addAuthor(ActionEvent event){
        if (t10.getText().isBlank()) return;
        listView.getItems().add(t10.getText());
        t10.clear();
    }
    public void addTranslator(ActionEvent event){
        if (t8.getText().isBlank()) return;
        listView2.getItems().add(t8.getText());
        t8.clear();
    }

    public void addTag(ActionEvent event) {
        if (t101.getText().isBlank()) return;
        tagListView.getItems().add(t101.getText());
        t101.clear();
    }
    public void NullAlert(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all the texts!");
        alert.showAndWait();

    }

    public void isTextNull(ActionEvent event){
        if(t1.getText().isBlank() || t2.getText().isBlank() ||t3.getText().isBlank()
                || t4.getText().isBlank() ||chooseRate.getValue() == null || t6.getText().isBlank()
                || checkSubtitle.isSelected() && t7.getText().isBlank()
                || t9.getText().isBlank() || checkTranslator.isSelected() && listView2.getItems().isEmpty() || listView.getItems().isEmpty()){
            isNull = true;
            return;
        }
        isNull = false;
    }

    public void checkSubtitle(ActionEvent event) {
        if (checkSubtitle.isSelected()) {
            t7.setVisible(true);
        } else {
            t7.setVisible(false);
        }
    }

    public void checkTranslator(ActionEvent event) {
        if (checkTranslator.isSelected()) {
            t8.setVisible(true);
            listView2.setVisible(true);
            addTranslator.setVisible(true);

        } else {
            t8.setVisible(false);
            listView2.setVisible(false);
            addTranslator.setVisible(false);
        }
    }

    /*
    public void closeScreen(ActionEvent event){
        dialog.close();
    }
    */
}