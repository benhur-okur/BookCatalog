package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    private String subtitle = "";
    private String translator = "";

    public void addBook(ActionEvent event){
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
        book = new Book(title, isbn, publisher, edition, pageNumber, coverType, subtitle, translator);
        books.add(book);
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
    /*@FXML
    public void openAddBookScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBook.fxml"));
        Parent root = fxmlLoader.load();

        //yeni stage oluştur ve .fxml'i göster
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Book");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

     */

}








