package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
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
    private Dialog<DialogPane> dialog = new Dialog<>();

    private Book book;
    private ArrayList<Book> books = new ArrayList<>();
    private String subtitle = null;
    private String translator = null;

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
   private Button addButton;
    public void addBook(ActionEvent event) throws InvocationTargetException {
        if(t1.getText().isBlank() || t2.getText().isBlank() || t3.getText().isBlank() || t4.getText().isEmpty()
                || t5.getText().isEmpty()|| t6.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the all texts!");
            alert.showAndWait();
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

        }

        //checkNullOrNot(event);

    }


    /*
    public void checkNullOrNot(ActionEvent event){
        if(t1.getText().isBlank() || t2.getText().isBlank() || t3.getText().isBlank() || t4.getText().isBlank()
                || t5.getText().isBlank() || t6.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the all texts!");
            alert.showAndWait();
        }

    }

     */

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








