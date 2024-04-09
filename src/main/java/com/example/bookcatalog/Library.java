package com.example.bookcatalog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Library extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Book book;
    ArrayList<Book> books = new ArrayList<>();
    Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    TextField t1, t2, t3, t4, t5, t6, t8, t10;
    HBox h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11;
    MenuItem add;
    Button saveBttn, cancelBttn;
    Stage popupStage;
    CheckBox checkSubYes, checkSubNo, checkTranslationYes, checkTranslationNo;
    @Override
    public void start(Stage stage) {

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        add = new MenuItem("Add");
        add.setOnAction(event -> showPopup(stage));

        file.getItems().add(add);
        menu.getMenus().add(file);
        mainLayout.getChildren().add(menu);

        Scene scene = new Scene(mainLayout, 600, 600);
        stage.setScene(scene);
        stage.setTitle("BOOK CATALOG");
        stage.show();
    }
    public void showPopup(Stage stage){

        VBox popupContent = new VBox(10);
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
        h4.getChildren().addAll(l1, t1);
        h5 = new HBox(8);
        l5 = new Label("Page Number");
        t5 = new TextField();
        h5.getChildren().addAll(l5, t5);
        h6 = new HBox(8);
        l6 = new Label("Cover Type");
        t6 = new TextField();
        h6.getChildren().addAll(l6, t6);

        h7 = new HBox(8);
        checkSubYes = new CheckBox("Yes");
        checkSubYes.setOnAction(e -> checkSubtitle());
        checkSubNo = new CheckBox("No");
        checkSubNo.setOnAction(e -> checkSubtitle());
        l7 = new Label("Subtitle?");
        h7.getChildren().addAll(l7, checkSubYes, checkSubNo);

        h8 = new HBox(8);
        l8 = new Label("Subtitle");
        t8 = new TextField();
        h8.getChildren().addAll(l8, t8);
        h8.setVisible(false);

        h9 = new HBox(8);
        checkTranslationYes = new CheckBox("Yes");
        checkTranslationYes.setOnAction(e -> checkTranslation());
        checkTranslationNo = new CheckBox("No");
        checkTranslationNo.setOnAction(e -> checkTranslation());
        l9 = new Label("Translation?");
        h9.getChildren().addAll(l9, checkTranslationYes, checkTranslationNo);

        h10 = new HBox(8);
        l10 = new Label("Translator");
        t10 = new TextField();
        h10.getChildren().addAll(l10, t10);
        h10.setVisible(false);

        h11 = new HBox(8);
        saveBttn = new Button("Save");
        cancelBttn = new Button("Cancel");
        cancelBttn.setOnAction(e -> cancelButton(stage));
        h11.getChildren().addAll(saveBttn, cancelBttn);
        popupContent.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11);

        Scene scene = new Scene(popupContent, 400, 400);
        popupStage.setScene(scene);
        popupStage.showAndWait();

        /*
        popup.getContent().add(popupContent);
        popup.setAutoHide(true);
        popup.show(stage);*/
    }

    public void checkSubtitle(){

        if(checkSubYes.isSelected()){
            h8.setVisible(true);
            checkSubNo.setSelected(false);
        }else {
            h8.setVisible(false);
        }

    }

    public void checkTranslation(){

        if(checkTranslationYes.isSelected()){
            h10.setVisible(true);
            checkTranslationNo.setSelected(false);
        }else {
            h10.setVisible(false);
        }

    }

    public void cancelButton(Stage stage){
        popupStage.close();
    }
}
