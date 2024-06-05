package com.booknexus.booknexus.model;

import jakarta.persistence.*;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToOne
    private Book book;

    public Author(){}
    public Author(DataAuthor dataAuthor){
        this.name=dataAuthor.name();
        this.birthYear=dataAuthor.birthYear();
        this.deathYear= dataAuthor.deathYear();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }


}
