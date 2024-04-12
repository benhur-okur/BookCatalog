package com.example.bookcatalog;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BookTypeAdaptor extends TypeAdapter<Book> {
    @Override
    public void write(JsonWriter out, Book book) throws IOException {
        out.beginObject();
        out.name("title").value(book.getTitle());
        out.name("isbn").value(book.getIsbn());
        out.name("publisher").value(book.getPublisher());
        out.name("edition").value(book.getEdition());
        out.name("rate").value(book.getRate());
        /*out.name("authors").beginArray();
        for (String author : book.getAuthors()) {
            out.value(author);
        }
        out.endArray();

         */
        out.name("cover type").value(book.getCoverType());
        out.name("has subtitle").value(book.isHasSubtitle());
        out.name("subtitle").value(book.getSubtitle());
        out.name("is translation").value(book.isTranslation());
        out.name("translator").value(book.getTranslator());
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
                    book.setIsbn(in.nextString());
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
                case "translator":
                    book.setTranslator(in.nextString());
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
