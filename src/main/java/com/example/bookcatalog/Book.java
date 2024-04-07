package com.example.bookcatalog;

import java.util.ArrayList;

public class Book {

    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private int edition;
    private int pageNumber;
    private String coverType;
    private boolean hasSubtitle; //kullanıcı 'yes' butonuna bastığında tekrar bir textfield açılacak
    private String subtitle;
    private boolean isTranslation; //kullanıcı 'yes' butonuna bastığında tekrar bir textfield açılacak
    private String translator;

    public Book(String title, ArrayList<String> authors, String isbn,
                String publisher, int edition, int pageNumber, String coverType,
                boolean hasSubtitle, String subtitle, boolean isTranslation, String translator) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.edition = edition;
        this.pageNumber = pageNumber;
        this.coverType = coverType;
        this.hasSubtitle = hasSubtitle;
        this.subtitle = subtitle;
        this.isTranslation = isTranslation;
        this.translator = translator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public boolean isHasSubtitle() {
        return hasSubtitle;
    }

    public void setHasSubtitle(boolean hasSubtitle) {
        this.hasSubtitle = hasSubtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isTranslation() {
        return isTranslation;
    }

    public void setTranslation(boolean translation) {
        isTranslation = translation;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
