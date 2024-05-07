package com.example.bookcatalog;

import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.sampled.Line;
import java.awt.*;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.List;

public class MainScreenController {
    static File selectedFile;
    boolean hasSelectedJSONFile;

    private ArrayList<Book> bookArrayList = new ArrayList<>();

    private JSON json;

    public GridPane getGridPane() {
        return gridPane;
    }

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
    private TextField searchField;
    @FXML
    private Button searchButton;

    @FXML
    private VBox tagVbox;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem importItem;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    //private Map<String, List<Book>> booksByTag = new HashMap<>();

    @FXML
    private ListView<String> listForTags = new ListView<>();

    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private Button exitBT;
    @FXML
    private void exit () {
        Platform.exit();
    }
    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CAUTION !!");
        alert.setHeaderText(null);
        alert.setContentText("If you are troubling after editing and adding \nPlease make sure you pressed save button on the file menu");
        alert.showAndWait();
    }
    @FXML
    public void setSave () {

        gridPane.getChildren().clear();
        json.saveFile();
        showBooks();
        Set<String> uniqueTags = new HashSet<>();
        for (Book book : bookArrayList) {
            uniqueTags.addAll(book.getTags());
        }
        // Önce etiketleri temizleyin
        tagVbox.getChildren().clear();
        // Sonra yenilerini ekleyin
        for (String tag : uniqueTags) {
            HBox hbox = new HBox();

                Label tagLabel = new Label(tag);
                CheckBox checkBox = new CheckBox();
                hbox.getChildren().addAll(checkBox, tagLabel);
                HBox.setMargin(tagLabel, new Insets(7));
                HBox.setMargin(checkBox, new Insets(7));
                tagVbox.getChildren().add(hbox);
            }
    }

    @FXML
    public void setRead() {
        json.readFile(bookArrayList);

    }

    @FXML
    private TextField filterText;

    @FXML
    public void openAddBookScreen() throws IOException {
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



    public void initialize () throws IOException {
        openSelectingFileScreen();

        if (hasSelectedJSONFile) {
            setRead();
            showBooks();
            Set<String> uniqueTags = new HashSet<>();
            for (Book book : bookArrayList) {
                uniqueTags.addAll(book.getTags());
            }

            for (String tag : uniqueTags) {
                HBox hbox = new HBox();

                Label tagLabel = new Label(tag);
                CheckBox checkBox = new CheckBox();
                hbox.getChildren().addAll(checkBox, tagLabel);
                HBox.setMargin(tagLabel, new Insets(7));
                HBox.setMargin(checkBox, new Insets(7));
                tagVbox.getChildren().add(hbox);
            }
        } else {
            Platform.exit();
        }

    }
    private void openSelectingFileScreen () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SelectingFile.fxml"));
        Parent root = fxmlLoader.load();



        //yeni stage oluştur ve .fxml'i göster
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Select FILE Screen");
        stage.setScene(new Scene(root));

        SelectingFileController selectingFileController = fxmlLoader.getController();
        selectingFileController.setMainScreenControllerAndStage(this, stage);

        stage.showAndWait();


    }

    @FXML
    public void backButton(){
        gridPane.getChildren().clear();
        showBooks();

    }

    @FXML
    private void searchBooks() {
        String searchText = searchField.getText().trim().toLowerCase();

        // Clear the grid pane
        gridPane.getChildren().clear();

        // Show books matching the search text
        int row = 0;
        int col = 0;
        for (Book book : bookArrayList) {
            String title = book.getTitle().toLowerCase();
            String isbn = String.valueOf(book.getIsbn()).toLowerCase();
            String publisher = book.getPublisher().toLowerCase();
            String edition = String.valueOf(book.getEdition()).toLowerCase();
            String coverType = book.getCoverType().toLowerCase();
            String subtitle = book.getSubtitle() != null ? book.getSubtitle().toLowerCase() : "";
            String language = book.getLanguage().toLowerCase();
            String date = book.getDate().toLowerCase();

            // Search in authors
            boolean foundInAuthors = false;
            for (String author : book.getAuthors()) {
                if (author.toLowerCase().contains(searchText)) {
                    foundInAuthors = true;
                    break;
                }
            }

            // Search in translators
            boolean foundInTranslators = false;
            if (book.getTranslators() != null) {
                for (String translator : book.getTranslators()) {
                    if (translator.toLowerCase().contains(searchText)) {
                        foundInTranslators = true;
                        break;
                    }
                }
            }

            // Search in tags
            boolean foundInTags = false;
            for (String tag : book.getTags()) {
                if (tag.toLowerCase().contains(searchText)) {
                    foundInTags = true;
                    break;
                }
            }

            // Check if any field matches the search text
            if (title.contains(searchText) || isbn.contains(searchText) || publisher.contains(searchText) ||
                    edition.contains(searchText) || coverType.contains(searchText) || subtitle.contains(searchText) ||
                    language.contains(searchText) || date.contains(searchText) || foundInAuthors || foundInTranslators ||
                    foundInTags) {
                File file = new File(book.getImagePath());
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(190);
                imageView.setFitHeight(130);

                gridPane.add(imageView, col, row);

                Label titleLabel = new Label();
                titleLabel.setText(book.getTitle());
                titleLabel.setFont(Font.font(24));
                GridPane.setMargin(titleLabel, new Insets(160, 0, 0, 50));
                gridPane.add(titleLabel, col, row);

                imageView.setOnMouseClicked(e -> {
                    try {
                        showViewBookScreen(book);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    public void showBooks () {
        int row = 0;
        int col = 0;

        for (Book book : bookArrayList) {
            File file = new File(book.getImagePath());
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(190); // Resmi genişlik değerine sığacak şekilde ayarlayın
            imageView.setFitHeight(130); // Resmi yükseklik değerine sığacak şekilde ayarlayın


            // ImageView'i GridPane'e ekleyin
            gridPane.add(imageView, col, row);

            Label titleLabel = new Label();
            titleLabel.setText(book.getTitle());
            titleLabel.setFont(Font.font(24));
            GridPane.setMargin(titleLabel, new Insets(160, 0, 0, 50));
            gridPane.add(titleLabel, col, row);


            imageView.setOnMouseClicked(e -> {
                try {
                    showViewBookScreen(book);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            // Sütun numarasını bir artırın
            col++;

            // Eğer sütun numarası 4'e ulaşırsa, bir sonraki satıra geçin ve sütun numarasını sıfırlayın
            if (col == 4) {
                col = 0;
                row++;
            }
        }

    }

    @FXML
    private void showBooksByTag() {
        int row = 0;
        int col = 0;
        gridPane.getChildren().clear();

        Set<String> selectedTags = new HashSet<>();
        for (Node node : tagVbox.getChildren()) {
            HBox hbox = (HBox) node;
            CheckBox checkBox = (CheckBox) hbox.getChildren().get(0);
            if (checkBox.isSelected()) {
                Label tagLabel = (Label) hbox.getChildren().get(1);
                selectedTags.add(tagLabel.getText());
            }
        }

        if (selectedTags.isEmpty()){
            gridPane.getChildren().clear();
            showBooks();
        }else {
            for (Book book : bookArrayList) {
                List<String> tags = book.getTags();

                if (!selectedTags.isEmpty() && !Collections.disjoint(tags, selectedTags)) {
                    File file = new File(book.getImagePath());
                    Image image = new Image(file.toURI().toString());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(190);
                    imageView.setFitHeight(130);

                    gridPane.add(imageView, col, row);

                    Label titleLabel = new Label();
                    titleLabel.setText(book.getTitle());
                    titleLabel.setFont(Font.font(24));
                    GridPane.setMargin(titleLabel, new Insets(160, 0, 0, 50));
                    gridPane.add(titleLabel, col, row);

                    imageView.setOnMouseClicked(e -> {
                        try {
                            showViewBookScreen(book);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                }
            }
        }

    }
    @FXML
    private void showViewBookScreen(Book currentBook) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewBook.fxml"));
        Parent root = fxmlLoader.load();

        ViewBookController viewBookController = fxmlLoader.getController();
        viewBookController.setMainScreenController(this, currentBook);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Book Information");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void OpenAndLoadJSONFile () {
        boolean hasSelectedJSONFile = false;

        // Dosya seçiciyi oluştur
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JSON File");

        // JSON dosyası filtresi ekle (opsiyonel)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Kullanıcıdan dosyayı seçmesini iste
        selectedFile = fileChooser.showOpenDialog(null);

        // Kullanıcı dosyayı seçti mi kontrol et
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            // Burada seçilen JSON dosyasını işlemek için gerekli adımları gerçekleştirebilirsiniz
            // Örneğin, dosyayı okuyup içeriğini işleyebilirsiniz
            hasSelectedJSONFile = true;
        } else {
            System.out.println("No file selected.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You didn't choose a JSON File \n " +
                    "you can manually try select a FILE From : \n" +
                    "File -> Open (TOP LEFT CORNER)");
            alert.showAndWait();
        }

        if (hasSelectedJSONFile) {
            listForTags.getItems().clear();
            gridPane.getChildren().clear();
            setRead();
            showBooks();
            Set<String> uniqueTags = new HashSet<>();
            for (Book book : bookArrayList) {
                uniqueTags.addAll(book.getTags());
            }

            for (String tag : uniqueTags) {
                HBox hbox = new HBox();

                Label tagLabel = new Label(tag);
                CheckBox checkBox = new CheckBox();
                hbox.getChildren().addAll(checkBox, tagLabel);
                HBox.setMargin(tagLabel, new Insets(7));
                HBox.setMargin(checkBox, new Insets(7));
                tagVbox.getChildren().add(hbox);
            }
        }
    }
    @FXML
    private void ExportAllBooksAsJSONFile() {
        // Hedef klasörü seçmek için DirectoryChooser oluştur
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Destination Folder");

        // Kullanıcıdan hedef klasörü seçmesini iste
        File destinationFolder = directoryChooser.showDialog(null);

        if (destinationFolder != null) {
            try {
                // Kaynak dosyayı ve hedef klasörü kontrol et
                if (selectedFile.isFile() && destinationFolder.isDirectory()) {
                    // Dosyayı hedef klasöre kopyala
                    File copiedFile = new File(destinationFolder.getPath() + File.separator + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), copiedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File exported successfully to: " + copiedFile.getPath());
                } else {
                    System.out.println("Invalid file or destination folder.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Hata durumunda kullanıcıya bilgi ver
                // ...
            }
        } else {
            System.out.println("No destination folder selected.");
        }
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {

        this.bookArrayList = bookArrayList;
    }
}