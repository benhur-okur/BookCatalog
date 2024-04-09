package com.example.bookcatalog;

import java.util.ArrayList;

public class BookProcess {

    private PopUpScreen popUpScreen;
    private Book book;
    private ArrayList<Book> books = new ArrayList<>();
    private String subtitle = "";
    private String translator = "";
    private void addBook(){

        String title = popUpScreen.t1.getText();
        String isbn = popUpScreen.t2.getText();
        String publisher = popUpScreen.t3.getText();
        int edition = Integer.parseInt(popUpScreen.t4.getText());
        int pageNumber = Integer.parseInt(popUpScreen.t5.getText());
        String coverType = popUpScreen.t6.getText();
        boolean isSubtitle = popUpScreen.checkSubtitle.isSelected();
        boolean isTranslation = popUpScreen.checkTranslation.isSelected();
        if(isSubtitle){
            subtitle = popUpScreen.t8.getText();
        }
        if(isTranslation){
            translator = popUpScreen.t10.getText();
        }
        book = new Book(title,isbn,publisher,edition,pageNumber,coverType,isSubtitle,subtitle,isTranslation,translator);
        books.add(book);
    }

    public PopUpScreen getPopUpScreen() {
        return popUpScreen;
    }

    public void setPopUpScreen(PopUpScreen popUpScreen) {
        this.popUpScreen = popUpScreen;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
