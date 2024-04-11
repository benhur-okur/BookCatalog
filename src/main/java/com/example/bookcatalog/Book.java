package com.example.bookcatalog;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String title;
    private String isbn;
    private String publisher;
    private int edition;
    private int pageNumber;
    private ArrayList<String> authors; // yapılacak
    private String coverType;
    private boolean hasSubtitle;
    private String subtitle;
    private boolean isTranslation;
    private String translator;


    private List<String> tags; // New field for storing tags

    public Book (String title, String isbn, String publisher, int edition, int pageNumber,
                String coverType, String subtitle, String translator) throws InvocationTargetException {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.edition = edition;
        this.pageNumber = pageNumber;
        this.coverType = coverType;
        this.hasSubtitle = hasSubtitle;
        this.subtitle = subtitle;
        this.isTranslation = isTranslation;
        this.translator = translator;
        this.tags = new ArrayList<>();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
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

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition=" + edition +
                ", pageNumber=" + pageNumber +
                ", coverType='" + coverType + '\'' +
                ", hasSubtitle=" + hasSubtitle +
                ", subtitle='" + subtitle + '\'' +
                ", isTranslation=" + isTranslation +
                ", translator='" + translator + '\'' +
                '}';
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
