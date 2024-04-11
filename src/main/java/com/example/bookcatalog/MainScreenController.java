package com.example.bookcatalog;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {
    private ArrayList<Book> bookArrayList = new ArrayList<>();


    private JSON json;

    {
        try {
            //bookArrayList.add(book);
            json = new JSON(bookArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private MenuItem save;

    @FXML
    public void setSave () {
        json.saveFile();
    }
}
