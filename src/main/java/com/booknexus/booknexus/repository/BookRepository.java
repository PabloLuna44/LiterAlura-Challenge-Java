package com.booknexus.booknexus.repository;

import com.booknexus.booknexus.model.Author;
import com.booknexus.booknexus.model.Book;
import com.booknexus.booknexus.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {


    Optional<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT a FROM Author a ")
    List<Author> findAllAuthor();


    @Query("SELECT DISTINCT l.name FROM Language l")
    List<String> findDistinctLanguageNames();

    @Query("SELECT a FROM Author a WHERE a.birthYear<=:year AND a.deathYear>=:year")
    List<Author> findLivingAuthorsInAGivenYear(Integer year);

    @Query("SELECT b FROM Book b JOIN b.languages l WHERE UPPER(l.name)=:name")
    List<Book> findBookByLanguages(String name);


    }
