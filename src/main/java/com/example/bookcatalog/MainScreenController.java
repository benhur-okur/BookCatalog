package com.example.bookcatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainScreenController {
    private ArrayList<Book> bookArrayList = new ArrayList<>();

    private JSON json;

    {
        try {
            json = new JSON(bookArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuItem save;
    @FXML
    private FlowPane flowPane;
    //private Map<String, List<Book>> booksByTag = new HashMap<>();
    @FXML
    public void setSave () {

        setRead();
        json.saveFile();
    }
    @FXML
    public void setRead() {
        json.readFile(bookArrayList);

    }

    @FXML
    public void openAddBookScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBook.fxml"));
        Parent root = fxmlLoader.load();

        AddBookController addBookController = fxmlLoader.getController();
        addBookController.setMainScreenController(this);

        //yeni stage oluştur ve .fxml'i göster
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Book");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void refreshBookList() {
        //TODO melih sende burası mainScreende
        // tableView listView ile alakalı güncellemeler olucak kitap eklendikten sonra sanırım
    }
    public void initialize () {
        showBooks(new ActionEvent());
    }

    private void showBooks (ActionEvent actionEvent) {
        setRead();
        int row = 0;
        int col = 0;

        for (Book book : bookArrayList) {
            Image image = new Image(book.getImagePath());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(190); // Resmi genişlik değerine sığacak şekilde ayarlayın
            imageView.setFitHeight(130); // Resmi yükseklik değerine sığacak şekilde ayarlayın

            // ImageView'i GridPane'e ekleyin
            gridPane.add(imageView, col, row);

            // Sütun numarasını bir artırın
            col++;

            // Eğer sütun numarası 4'e ulaşırsa, bir sonraki satıra geçin ve sütun numarasını sıfırlayın
            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }
    /*
    public void initialize() {
        loadBooks();
        displayBooks();
    }

     */
    /*
    private void loadBooks() {
        ArrayList<Book> allBooks =  json.readFile(bookArrayList);

        for (Book book : allBooks) {
            for (String tag : book.getTags()) {
                if (!booksByTag.containsKey(tag)) {
                    booksByTag.put(tag, new ArrayList<>());
                }
                booksByTag.get(tag).add(book);
            }
        }
    }

     */

    /*
    private void displayBooks() {
        for (Map.Entry<String, List<Book>> entry : booksByTag.entrySet()) {
            String tag = entry.getKey();
            List<Book> books = entry.getValue();

            // Add tag label
            Label tagLabel = new Label(tag);
            tagLabel.getStyleClass().add("tag-label");

            // Add books to the flow pane
            HBox tagBox = new HBox();
            tagBox.getChildren().addAll(tagLabel);
            flowPane.getChildren().add(tagBox);

            for (Book book : books) {
                Pane bookPane = createBookPane(book);
                flowPane.getChildren().add(bookPane);
            }
        }
    }

     */

    /*
    private Pane createBookPane(Book book) {
        // Create a pane to display the book with its image
        Pane pane = new Pane();
        pane.getStyleClass().add("book-pane");

        // Add book image
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(book.getImagePath()));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        // Add book title
        Label titleLabel = new Label(book.getTitle());
        titleLabel.getStyleClass().add("book-title");


        pane.getChildren().addAll(imageView, titleLabel);

        return pane;
    }

     */



    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }
}
