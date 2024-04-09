package com.example.bookcatalog;

import java.util.ArrayList;

public class BookProcess {

    PopUpScreen popUpScreen;
    Book book;
    ArrayList<Book> books = new ArrayList<>();
    public void addBook(){

        String title = popUpScreen.t1.getText();
        String isbn = popUpScreen.t2.getText();
        String publisher = popUpScreen.t3.getText();
        int edition = Integer.parseInt(popUpScreen.t4.getText());
        int pageNumber = Integer.parseInt(popUpScreen.t5.getText());
        String coverType = popUpScreen.t6.getText();

    }
}
