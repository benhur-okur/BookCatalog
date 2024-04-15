package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewBookController {
    Book currentBook = null;
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
    private Label bookTitleLabel;
    private MainScreenController mainScreenController;

    /* TODO Melih'in yaptığı Netflix ekranındaki bir kitaba:
        - Çift tıklandığında
        - Seçildikten sonra MenuBar üzerinden "edit" seçeneği basıldığında
        Basılan kitabın özelliklerini al ve
     */
    public void showBookInfo(Book book) {
        currentBook = book;
        bookTitleLabel.setText(book.getTitle());
        bookIsbnLabel.setText(String.valueOf(book.getIsbn()));
        bookPublisherLabel.setText(book.getPublisher());
        bookEditionLabel.setText(String.valueOf(book.getEdition()));
        bookRatingLabel.setText(String.valueOf(book.getRate()));
        bookCoverTypeLabel.setText(book.getCoverType());
        bookLanguageLabel.setText(book.getLanguage());
        bookAuthorLabel.setText(String.valueOf(book.getAuthors())); // TODO Düzgün çalışıyor mu?
        bookDateLabel.setText(book.getDate()); // TODO date string olarak tutulmasa daha iyi olur (Tarihe göre aratma)
        bookImageView.setImage(new Image(book.getImagePath()));
    }
    @FXML
    void editBook(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBook.fxml"));
        Parent root = fxmlLoader.load();

        AddBookController addBookController = fxmlLoader.getController();
        addBookController.setMainScreenController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Book");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void deleteBook(ActionEvent event) {
        // todo Benhur'un yardımı lazım
    }
    public void setMainScreenController (MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

}
