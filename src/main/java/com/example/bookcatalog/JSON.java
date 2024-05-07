package com.example.bookcatalog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;


public class JSON {
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    //private ArrayList<Book> existingBooks = new ArrayList<>();
    private Gson gson;
    private String myJson;


    public JSON(ArrayList<Book> bookArrayList) throws IOException {
        //gson = new Gson();
        this.bookArrayList = bookArrayList;
        gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookTypeAdaptor()).
                setPrettyPrinting().create();
        //myJson = gson.toJson(existingBookArraylist);
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(MainScreenController.selectedFile);
            clearFileContent();
            gson.toJson(bookArrayList, fileWriter);
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Book catalog has been successfully saved.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving the book catalog.");
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Book> readFile(ArrayList<Book> bookArrayList) {
        try (Reader reader = new FileReader(MainScreenController.selectedFile)) {
            Type bookListType = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> existingBooks = gson.fromJson(reader, bookListType);
            if (existingBooks != null) {
                bookArrayList.addAll(existingBooks);
            }
            JOptionPane.showMessageDialog(null, "Book catalog has been successfully loaded.");
            return existingBooks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void clearFileContent() throws IOException {
        // Dosyanın içeriğini temizle
        Files.write(Path.of("books.json"), Collections.emptyList());
    }
    /*
    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

     */



}
