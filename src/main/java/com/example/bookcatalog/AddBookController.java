package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private CheckBox checkSubtitle;
    @FXML
    private CheckBox checkTranslator;

    @FXML
    private DialogPane dialogPane;

    private JSON json;
    private Book book;
    public ArrayList<Book> books = new ArrayList<>();
    private MainScreenController mainScreenController;

    private String subtitle = null;
    private String translator = null;
    private boolean isNull = false;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @FXML
    private ImageView imageView;

    @FXML
    private Button addButton;

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
        if(isNull){
            NullAlert(event);
        }else {
            int edition = 0;
            int pageNumber = 0;
            String title = t1.getText();
            String isbn = t2.getText(); // TODO: regex kullanarak isbn kontrolü yapılacak!!!
            String publisher = t3.getText();
            try{
                edition = Integer.parseInt(t4.getText());
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a integer for Edition!");
                alert.showAndWait();
            }
            try{
                pageNumber = Integer.parseInt(t5.getText());
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a integer for Page Number!");
                alert.showAndWait();
            }
<<<<<<< HEAD
=======
            title = t1.getText();
            isbn = t2.getText(); //TODO isbn string girerse nolucak ??
            publisher = t3.getText();
            edition = Integer.parseInt(t4.getText());
            pageNumber = Integer.parseInt(t5.getText());
>>>>>>> f33defacad1b4e538a42b41b6b143af76fb1ff5d
            String coverType = t6.getText();
            if(checkSubtitle.isSelected()){
                subtitle = t7.getText();
            }
            if(checkTranslator.isSelected()){
                translator = t8.getText();
            }
            book = new Book(title, isbn, publisher, edition, pageNumber, coverType, subtitle, translator) ;
<<<<<<< HEAD
            mainScreenController.getBookArrayList().add(book);
            mainScreenController.refreshBookList();

=======
            books.add(book);
>>>>>>> f33defacad1b4e538a42b41b6b143af76fb1ff5d

        }
    }

<<<<<<< HEAD
=======
        /*
        if(isNull){
            NullAlert(event);
        }else {
            String title = t1.getText();
            String isbn = t2.getText();
            String publisher = t3.getText();
            int edition = Integer.parseInt(t4.getText());
            int pageNumber = Integer.parseInt(t5.getText());
            String coverType = t6.getText();
            if(checkSubtitle.isSelected()){
                subtitle = t7.getText();
            }
            if(checkTranslator.isSelected()){
                translator = t8.getText();
            }
            book = new Book(title, isbn, publisher, edition, pageNumber, coverType, subtitle, translator) ;
            books.add(book);
            dialogPane.getOnDragExited();
        }

        }

         */
>>>>>>> f33defacad1b4e538a42b41b6b143af76fb1ff5d


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
                || checkSubtitle.isSelected() && t7.getText().isBlank() || checkTranslator.isSelected() && t8.getText().isBlank()){

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
        } else {
            t8.setVisible(false);
        }
    }


    /*
    public void closeScreen(ActionEvent event){
        dialog.close();
    }
    */
}