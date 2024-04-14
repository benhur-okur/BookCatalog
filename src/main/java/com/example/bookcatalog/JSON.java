package com.example.bookcatalog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class JSON {
    private ArrayList<Book> bookArrayList;
    //private ArrayList<Book> existingBookArraylist = new ArrayList<>();
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
            FileWriter fileWriter = new FileWriter("books.json");
            gson.toJson(bookArrayList, fileWriter);
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Book catalog has been successfully saved.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving the book catalog.");
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Book> readFile(ArrayList<Book> bookArrayList) {
        try (Reader reader = new FileReader("books.json")) {
            Type bookListType = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> existingBooks = gson.fromJson(reader, bookListType);
            if (existingBooks == null) {
                existingBooks = new ArrayList<>();
            }
            JOptionPane.showMessageDialog(null, "Book catalog has been successfully loaded.");
            return existingBooks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
