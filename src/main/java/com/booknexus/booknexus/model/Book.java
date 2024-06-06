package com.booknexus.booknexus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="books")
public class Book {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String title;
     private Integer downloadAccount;

     @OneToMany(mappedBy="book",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<Author> authors;

     @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<Language> languages;

     public Book(){}

     public Book(DataBook dataBook){
          this.title=dataBook.title();
          this.downloadAccount=dataBook.downloadAccount();
          this.authors = dataBook.authors().stream().map(a -> {
               Author author = new Author(a);
               author.setBook(this);
               return author;
          }).collect(Collectors.toList());
          this.languages = dataBook.languages().stream().map(l -> {
               Language language = new Language(l);
               language.setBook(this);
               return language;
          }).collect(Collectors.toList());

     }

     public Integer getDownloadAccount() {
          return downloadAccount;
     }


     public String toStringToAuthors() {
          return "Book" +
                  ", Title='" + title + '\'' +
                  ", Download Account=" + downloadAccount +
                  ", Languages=" + languages+'\n';
     }

     @Override
     public String toString() {
          return "        ------------Book-------------\n" +
                  "    Title='" + title + '\'' + ",\n" +
                  "    Download Account=" + downloadAccount + ",\n" +
                  "    Authors=" + authors + ",\n" +
                  "    Languages=" + languages + "\n";
     }
}
