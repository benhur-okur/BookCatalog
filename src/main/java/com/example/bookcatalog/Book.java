package com.example.bookcatalog;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String title;
    private int isbn;
    private String publisher;
    private int edition;
    private int rate;
    private String coverType;
    private boolean hasSubtitle;
    private String subtitle;
    private boolean isTranslation;
    private ArrayList<String> translators;
    public List<String> tags; // New field for storing tags
    private String language;
    private ArrayList<String> authors; // yapılacak
    private String date;
    private String imagePath;


    public Book (String title, int isbn, String publisher, int edition, int rate,
                String coverType, String subtitle, ArrayList<String> translators, String language,
                 ArrayList<String> authors, String date, String imagePath) throws InvocationTargetException {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.edition = edition;
        this.rate = rate;
        this.coverType = coverType;
        this.subtitle = subtitle;
        this.translators = translators;
        this.tags = new ArrayList<>();
        this.language = language;
        this.authors = authors;
        this.date = date;
        this.imagePath = imagePath;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    public ArrayList<String> getTranslators() {
        return translators;
    }

    public void setTranslators(ArrayList<String> translators) {
        this.translators = translators;
    }

    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
