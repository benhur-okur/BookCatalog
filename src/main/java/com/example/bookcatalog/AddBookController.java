package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AddBookController {

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
    private ListView<String> listView;
    @FXML
    private ListView<String> listView2;

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
    private boolean isNull = false;
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> translators = new ArrayList<>();
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @FXML
    private ImageView imageView;

    @FXML
    private Button addButton;
    @FXML
    private Button addTranslator;

    public void setMainScreenController (MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png"),
                new FileChooser.ExtensionFilter("All Files", ".png", ".jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }
    public void addBook(ActionEvent event) throws InvocationTargetException, IOException {

        isTextNull(event);
        if (isNull) {
            NullAlert(event);
        } else {
            boolean hasError = false;
            int edition = 0;
            int rate = 0;
            try {
                edition = Integer.parseInt(t4.getText());
            } catch (Exception e) {
                hasError = true;
                t4.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a integer for Edition!");
                alert.showAndWait();
            }
            try {
                rate = Integer.parseInt(t5.getText());
            } catch (Exception e) {
                hasError = true;
                t5.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a integer for Rate!");
                alert.showAndWait();
            }

            if(!hasError){
                authors.addAll(listView.getItems());
                String title = t1.getText();
                String isbn = t2.getText(); // TODO: regex kullanarak isbn kontrolü yapılacak!!!
                String publisher = t3.getText();
                String coverType = t6.getText();
                if (checkSubtitle.isSelected()) {
                    subtitle = t7.getText();
                }
                if (checkTranslator.isSelected()) {
                    translators.addAll(listView2.getItems());
                }
                String language = t9.getText();
                book = new Book(title, isbn, publisher, edition, rate, coverType, subtitle, translators, language, authors);
                if (checkSubtitle.isSelected()) {
                    book.setHasSubtitle(true);
                }
                if (checkTranslator.isSelected()) {
                    book.setTranslation(true);
                }
                mainScreenController.getBookArrayList().add(book);
                mainScreenController.refreshBookList();
            }
        }
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
    public void NullAlert(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all the texts!");
        alert.showAndWait();

    }

    public void isTextNull(ActionEvent event){
        if(t1.getText().isBlank() || t2.getText().isBlank() ||t3.getText().isBlank()
                || t4.getText().isBlank() ||t5.getText().isBlank() ||t6.getText().isBlank()
                || checkSubtitle.isSelected() && t7.getText().isBlank()
                || t9.getText().isBlank() || listView.getItems().isEmpty() || listView2.getItems().isEmpty()){

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