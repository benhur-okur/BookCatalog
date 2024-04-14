package com.example.bookcatalog;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeAdaptor extends TypeAdapter<Book> {
    @Override
    public void write(JsonWriter out, Book book) throws IOException {
        out.beginObject();
        out.name("title").value(book.getTitle());
        out.name("isbn").value(book.getIsbn());
        out.name("publisher").value(book.getPublisher());
        out.name("edition").value(book.getEdition());
        out.name("rate").value(book.getRate());
        if (book.getAuthors() != null) {
            out.name("authors").beginArray();
            for (String author : book.getAuthors()) {
                out.value(author);
            }
            out.endArray();
        }
        out.name("cover type").value(book.getCoverType());
        out.name("has subtitle").value(book.isHasSubtitle());
        out.name("subtitle").value(book.getSubtitle());
        out.name("is translation").value(book.isTranslation());
        if (book.getTranslators() != null) {
            out.name("translators").beginArray();
            for (String translator : book.getTranslators()) {
                out.value(translator);
            }
            out.endArray();
        }
        if (book.getTags() != null) {
            out.name("tags").beginArray();
            for (String tag : book.getTags()) {
                out.value(tag);
            }
            out.endArray();
        }
        out.name("language").value(book.getLanguage());
        out.name("date").value(book.getDate());
        out.name("image path").value(book.getImagePath());
        out.endObject();
    }

    @Override
    public Book read(JsonReader in) throws IOException {
        Book book = new Book();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "isbn":
                    book.setIsbn(in.nextInt());
                    break;
                case "publisher":
                    book.setPublisher(in.nextString());
                    break;
                case "edition":
                    book.setEdition(in.nextInt());
                    break;
                case "rate":
                    book.setRate(in.nextInt());
                    break;
                case "authors":
                    in.beginArray();
                    ArrayList<String> authors = new ArrayList<>();
                    while (in.hasNext()) {
                        authors.add(in.nextString());
                    }
                    in.endArray();
                    book.setAuthors(authors);
                    break;
                case "cover type":
                    book.setCoverType(in.nextString());
                    break;
                case "has subtitle":
                    book.setHasSubtitle(in.nextBoolean());
                    break;
                case "subtitle":
                    book.setSubtitle(in.nextString());
                    break;
                case "is translation":
                    book.setTranslation(in.nextBoolean());
                    break;
                case "translators":
                    in.beginArray();
                    ArrayList<String> translators = new ArrayList<>();
                    while (in.hasNext()) {
                        translators.add(in.nextString());
                    }
                    in.endArray();
                    book.setTranslators(translators);
                    break;
                case "tags":
                    in.beginArray();
                    List<String> tags = new ArrayList<>();
                    while (in.hasNext()) {
                        tags.add(in.nextString());
                    }
                    in.endArray();
                    book.setTags((ArrayList<String>) tags);
                    break;
                case "date":
                    book.setDate(in.nextString());
                    break;
                case "language":
                    book.setLanguage(in.nextString());
                    break;
                case "image path":
                    book.setImagePath(in.nextString());
                    break;
                default:
                    in.skipValue(); // Geçersiz bir alanı atla
                    break;
            }
        }
        in.endObject();
        return book;
    }
}
