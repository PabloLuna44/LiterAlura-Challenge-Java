package com.booknexus.booknexus.main;

import com.booknexus.booknexus.model.*;
import com.booknexus.booknexus.repository.BookRepository;
import com.booknexus.booknexus.service.API;
import com.booknexus.booknexus.service.DataConvert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    private final String URL="https://gutendex.com/books/";
    private Scanner keyboard = new Scanner(System.in);
    private DataConvert dataConvert =new DataConvert();
    private API api = new API();
    private BookRepository repository;

    public Main(BookRepository bookRepository){
        this.repository=bookRepository;
    }

    public void menu() throws UnsupportedEncodingException {

        String menu= """
                
                - [1]  Find Book By Title
                - [2]  Find All Books
                - [3]  FInd All Authors
                - [4]  Find Living Authors In A Given Year
                - [5]  Find Books By Language
                
                - [0] Exit
                
                """;

        int opc=-1;
        while(opc!=0){

            System.out.println(menu);
            opc=keyboard.nextInt();

            switch(opc){

                case 1:{
                    findBookByTitle();
                    break;
                }

                case 2:{

                    findAllBooks();
                    break;
                }

                case 3:{
                    findAllAuthors();
                    break;
                }

                case 4:{
                    findLivingAuthorsInAGivenYear();
                    break;
                }

                case 5:{
                    findByLanguage();
                    break;
                }

                case 0:{
                    System.out.println("see you later");
                    break;
                }

                default:{
                    System.out.println("Not valid option");
                    break;
                }




            }



        }


    }



    public void findBookByTitle () throws UnsupportedEncodingException {
        keyboard.nextLine();
        System.out.println("Enter book name");
        String name=keyboard.nextLine();

        Optional<Book> book=repository.findByTitleContainingIgnoreCase(name);

        if (book.isPresent()){
            System.out.println(book);
        }else {
            String nameEncode = URLEncoder.encode(name, "UTF-8");
            String json = api.getData(URL + "?search=" + nameEncode);
            DataAPI book1 = dataConvert.getData(json, DataAPI.class);
            Optional<DataBook> findBook = book1.books().stream()
                    .findFirst();

            if (findBook.isPresent()) {
                Book findBook2 = new Book(findBook.get());
                repository.save(findBook2);

                System.out.println(findBook2);

            } else {
                System.out.println("Not found");
            }
        }
    }

    public void findAllBooks(){

        List<Book> books =repository.findAll();

        if(!books.isEmpty()){
            System.out.println(books);
        }

    }

    public void findAllAuthors(){
        List<Author> authors=repository.findAllAuthor();

        if(!authors.isEmpty()){
            System.out.println(authors);
        }

    }

    public void findByLanguage(){

        List<String> languages=repository.findDistinctLanguageNames();
        int opc2=-1;
        System.out.println("Select language");


        AtomicInteger index = new AtomicInteger(0);

        languages.stream().forEach(l -> {
            System.out.println("[" + index.getAndIncrement() + "]" + l.toString());
        });
        opc2=keyboard.nextInt();

        String language=languages.get(opc2);

        List<Book> books=repository.findBookByLanguages(language.toUpperCase());

        books.forEach(System.out::println);

    }

    public void findLivingAuthorsInAGivenYear() {

         System.out.println("Enter year");
         Integer year = keyboard.nextInt();

         List<Author> authors = repository.findLivingAuthorsInAGivenYear(year);

         if (!authors.isEmpty()) {
            authors.forEach(System.out::println);

         }
     }

    public void saveData() throws  UnsupportedEncodingException {

        String json=api.getData(URL);
        DataAPI book=dataConvert.getData(json, DataAPI.class);

        book.books().stream()
                .limit(5)
                .forEach(System.out::println);
        System.out.println(book);

        System.out.println("TOP TEN");
        book.books().stream()
                .sorted(Comparator.comparing(DataBook::downloadAccount).reversed())
                .limit(10)
                .forEach(e-> System.out.println(e.title().toUpperCase()));

        List<Book> books=book.books().stream()
                .map(b->new Book(b))
                .collect(Collectors.toList());

        repository.saveAll(books);

        System.out.println("Enter book name");
        String name=keyboard.nextLine();

        String nameEncode= URLEncoder.encode(name,"UTF-8");
        json=api.getData(URL+"?search="+nameEncode);
        DataAPI book1=dataConvert.getData(json,DataAPI.class);
         Optional<DataBook> findBook=book1.books().stream()
                 .findFirst();

         if(findBook.isPresent()){
             System.out.println(findBook);
         }else{
             System.out.println("Not found");
         }

         DoubleSummaryStatistics estatistics= book.books().stream()
                 .filter(b -> b.downloadAccount()>0)
                 .mapToDouble(DataBook::downloadAccount)
                 .summaryStatistics();

        System.out.println(estatistics);


//        DataBook book =dataConvert.getData(json,DataBook.class);



    }
}
