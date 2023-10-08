package com.javaweb.mywebsite.Controllers;


import com.javaweb.mywebsite.Model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/authors/{author_id}/books")
public class BookController {

    private final List<Book> books;

    public BookController(){
        try{

        String strDate1 = "11.06.1842";
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = formatter.parse(strDate1);

        String strDate2 = "19.10.1836";
        Date date2 = formatter.parse(strDate2);

        Book b1 = new Book(1L,1L,"Мертвые души",date1);
        Book b2 = new Book(2L,2L,"Капитанская дочка",date2);

        books = List.of(b1,b2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<Book> getBooks(@PathVariable("author_id") Long authorId){
        return books.stream().filter(book -> book.authorId().equals(authorId)).toList();
    }


}
