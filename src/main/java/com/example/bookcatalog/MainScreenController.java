package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {
    private ArrayList<Book> bookArrayList;


    private JSON json;


    @FXML
    private MenuItem save;

    @FXML
    public void setSave () {
        try {
            //bookArrayList.add(book);
            json = new JSON(AddBookController.books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        json.saveFile();
    }
    @FXML
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
}
