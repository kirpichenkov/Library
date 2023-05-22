package ru.kirpichenkov.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Название книги не может быт пустым")
    @Size(min=2, max=100, message = "Название книги не может быть меньше 2 символов и быть больше 100")
    private String title;

    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min=2, max=50, message = "Имя автора не может быть меньше 2 и превышать 50 символов")
    private String author;

    @Min(value = 1500, message = "год должен быть больше 1500")
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
