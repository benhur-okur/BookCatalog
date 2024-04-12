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
            }
        }
        in.endObject();
        return book;
    }
}
