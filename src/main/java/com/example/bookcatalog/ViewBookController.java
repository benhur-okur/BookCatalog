package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ViewBookController {

    @FXML
    private Label bookAuthorLabel;

    @FXML
    private Label bookCoverTypeLabel;

    @FXML
    private Label bookDateLabel;

    @FXML
    private Label bookEditionLabel;

    @FXML
    private ImageView bookImageView;

    @FXML
    private Label bookIsbnLabel;

    @FXML
    private Label bookLanguageLabel;

    @FXML
    private Label bookPublisherLabel;

    @FXML
    private Label bookRatingLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Button editButton;

    @FXML
    private Label titleLabel;
    private MainScreenController mainScreenController;

    /* TODO Melih'in yaptığı Netflix ekranındaki bir kitaba:
        - Çift tıklandığında
        - Seçildikten sonra MenuBar üzerinden "edit" seçeneği basıldığında
        Basılan kitabın özelliklerini al ve
     */
    @FXML
    void addBook(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }
    public void setMainScreenController (MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

}
