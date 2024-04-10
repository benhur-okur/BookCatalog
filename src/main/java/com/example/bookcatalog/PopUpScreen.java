package com.example.bookcatalog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PopUpScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    VBox popupContent;
    Book book;
    ArrayList<Book> books = new ArrayList<>();
    Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, tagLabel;
    TextField t1, t2, t3, t4, t5, t6, t8, t10, tagField;
    HBox h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, tagBox;
    MenuItem add;
    Button saveBttn, cancelBttn;
    Stage popupStage;
    CheckBox checkSubtitle, checkTranslation;
    ListView<String> tagListView;

    @Override
    public void start(Stage stage) {

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        showPopup(stage);

        Scene scene = new Scene(popupContent, 600, 600);
        stage.setScene(scene);
        stage.setTitle("BOOK CATALOG");
        stage.show();
    }

    public void showPopup(Stage stage) {

        popupContent = new VBox(10);
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(stage);
        popupStage.setTitle("Add Book");
        popupContent.setPadding(new Insets(10));
        h1 = new HBox(8);
        l1 = new Label("Title");
        t1 = new TextField();
        h1.getChildren().addAll(l1, t1);
        h2 = new HBox(8);
        l2 = new Label("ISBN");
        t2 = new TextField();
        h2.getChildren().addAll(l2, t2);
        h3 = new HBox(8);
        l3 = new Label("Publisher");
        t3 = new TextField();
        h3.getChildren().addAll(l3, t3);
        h4 = new HBox(8);
        l4 = new Label("Edition");
        t4 = new TextField();
        h4.getChildren().addAll(l4, t4);
        h5 = new HBox(8);
        l5 = new Label("Page Number");
        t5 = new TextField();
        h5.getChildren().addAll(l5, t5);
        h6 = new HBox(8);
        l6 = new Label("Cover Type");
        t6 = new TextField();
        h6.getChildren().addAll(l6, t6);

        h7 = new HBox(8);
        checkSubtitle = new CheckBox("Subtitle");
        checkSubtitle.setOnAction(e -> checkSubtitle());
        h7.getChildren().addAll(checkSubtitle);

        h8 = new HBox(8);
        l8 = new Label("Subtitle");
        t8 = new TextField();
        h8.getChildren().addAll(l8, t8);
        h8.setVisible(false);

        h9 = new HBox(8);
        checkTranslation = new CheckBox("Translation");
        checkTranslation.setOnAction(e -> checkTranslation());
        h9.getChildren().addAll(checkTranslation);

        h10 = new HBox(8);
        l10 = new Label("Translator");
        t10 = new TextField();
        h10.getChildren().addAll(l10, t10);
        h10.setVisible(false);

        tagBox = new HBox(8);
        tagLabel = new Label("Tags");
        tagField = new TextField();
        Button addTagBtn = new Button("Add");
        addTagBtn.setOnAction(e -> addTag());
        tagListView = new ListView<>();
        tagBox.getChildren().addAll(tagLabel, tagField, addTagBtn, tagListView);

        h11 = new HBox(8);
        saveBttn = new Button("Save");
        cancelBttn = new Button("Cancel");
        cancelBttn.setOnAction(e -> cancelButton(stage));
        h11.getChildren().addAll(cancelBttn, saveBttn);
        h11.setAlignment(Pos.CENTER_RIGHT);
        popupContent.getChildren().addAll(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, tagBox, h11);

        Scene scene = new Scene(popupContent, 400, 400);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    public void checkSubtitle() {
        if (checkSubtitle.isSelected()) {
            h8.setVisible(true);
        } else {
            h8.setVisible(false);
        }
    }

    public void checkTranslation() {
        if (checkTranslation.isSelected()) {
            h10.setVisible(true);
        } else {
            h10.setVisible(false);
        }
    }

    public void addTag() {
        String tag = tagField.getText();
        if (!tag.isEmpty()) {
            tagListView.getItems().add(tag);
            tagField.clear();
        }
    }

    public void cancelButton(Stage stage) {
        popupStage.close();
    }
}
