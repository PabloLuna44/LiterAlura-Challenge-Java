package com.booknexus.booknexus.main;

import com.booknexus.booknexus.model.DataAPI;
import com.booknexus.booknexus.model.DataBook;
import com.booknexus.booknexus.service.API;
import com.booknexus.booknexus.service.DataConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Main {

    private final String URL="https://gutendex.com/books/";
    private Scanner keyboard = new Scanner(System.in);
    private DataConvert dataConvert =new DataConvert();
    private API api = new API();

    public void menu() throws JsonProcessingException, UnsupportedEncodingException {

        String json=api.getData(URL);
        DataAPI book=dataConvert.getData(json, DataAPI.class);

        book.books().stream()
                .limit(5)
                .forEach(System.out::println);


        System.out.println("TOP TEN");
        book.books().stream()
                .sorted(Comparator.comparing(DataBook::downloadAccount).reversed())
                .limit(10)
                .forEach(e-> System.out.println(e.title().toUpperCase()));

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
