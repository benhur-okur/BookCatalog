package com.example.bookcatalog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;


public class JSON {
    private ArrayList<Book> bookArrayList;
    private Gson gson;
    private String myJson;
    private final FileWriter fileWriter = new FileWriter("books.json");
    private final FileReader fileReader = new FileReader("books.json");

    public JSON(ArrayList<Book> bookArrayList) throws IOException {
        //gson = new Gson();
        gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookTypeAdaptor()).create();
        this.bookArrayList = bookArrayList;
        myJson = gson.toJson(bookArrayList);
    }

    public void saveFile () {
        try {
            fileWriter.write(myJson);
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Kitap kataloga başarılı bi şekilde kaydedildi");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kitap kataloga kaydedilirken bi hata oluştu");
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Book> readFile () {
        ArrayList<Book> booksFromJson;
        Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
        try {
            booksFromJson = gson.fromJson(fileReader, bookListType);
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return booksFromJson;
    }
}
