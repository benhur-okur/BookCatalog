package com.example.bookcatalog;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBookController {
    private Book selectedBook;


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
    private Label bookSubtitleLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Button editButton;
    @FXML
    private ListView<String> authorsLW;
    @FXML
    private ListView<String> tagsLW;
    @FXML
    private ListView<String> translatorsLW;

    @FXML
    private Label bookTitleLabel;
    private MainScreenController mainScreenController;
    @FXML
    private Button backBT;
    @FXML
    private Button editBT;

    @FXML
    void back() {
        Stage stage = (Stage) backBT.getScene().getWindow();
        stage.close();

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
    public void setMainScreenController (MainScreenController mainScreenController, Book selectedBook) {
        this.mainScreenController = mainScreenController;
        this.selectedBook = selectedBook;
        try {
            titleLabel.setText(selectedBook.getTitle());
            bookIsbnLabel.setText(String.valueOf(selectedBook.getIsbn()));
            bookPublisherLabel.setText(selectedBook.getPublisher());
            bookEditionLabel.setText(String.valueOf(selectedBook.getEdition()));
            bookRatingLabel.setText(String.valueOf(selectedBook.getRate()));
            bookCoverTypeLabel.setText(selectedBook.getCoverType());
            bookSubtitleLabel.setText(selectedBook.getSubtitle());
            /*
            for (String translator : selectedBook.getTranslators()) {
                translatorsLW.setItems();
            }

             */
            translatorsLW.getItems().addAll((selectedBook.getTranslators()));
            authorsLW.getItems().addAll(selectedBook.getAuthors());
            tagsLW.getItems().addAll(selectedBook.getTags());
            bookLanguageLabel.setText(selectedBook.getLanguage());
            bookDateLabel.setText(selectedBook.getDate());
            File file = new File(selectedBook.getImagePath());
            Image image = new Image(file.toURI().toString());
            bookImageView.setImage(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void openEditBookScreen () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditBook.fxml"));
        Parent root = fxmlLoader.load();

        EditBookScreenController editBookScreenController = fxmlLoader.getController();
        editBookScreenController.setViewBookController(this, selectedBook);

        //yeni stage oluştur ve .fxml'i göster
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Book");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }
}
