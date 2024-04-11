package com.example.bookcatalog;

import java.util.ArrayList;

public class BookProcess {

    private PopUpScreen popUpScreen;
    private Book book;
    private ArrayList<Book> books = new ArrayList<>();
    private String subtitle = "";
    private String translator = "";

    /*
    public void addBook(String title, String isbn, String publisher, int edition, int pageNumber,
                        String coverType, boolean hasSubtitle, String subtitle, boolean isTranslation,
                        String translator, ArrayList<String> tags) {
        book = new Book(title, isbn, publisher, edition, pageNumber, coverType,
                hasSubtitle, subtitle, isTranslation, translator);
        book.setTags(tags); // Set the tags for the book
        books.add(book);
    }

     */

    // Other methods...

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
