package com.javaweb.mywebsite.Controllers;

import com.javaweb.mywebsite.Model.Book;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors/{author_id}/books")
public class BookController {
    private final List<Book> books;

    public BookController() {
        try {
            String strDate1 = "11.06.1842";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "19.10.1836";
            Date date2 = formatter.parse(strDate2);

            Book b1 = new Book(1L, 1L, "Мертвые души", date1, Book.Genre.Поэма);
            Book b2 = new Book(2L, 2L, "Капитанская дочка", date2, Book.Genre.Проза);

           books = new ArrayList<>();
           books.add(b1);
           books.add(b2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Book> getBooks(@PathVariable("author_id") Long authorId) {
        return books.stream()
                .filter(book -> book.authorId().equals(authorId))
                .toList();
    }

    @PostMapping
    public Book createBook(@PathVariable("author_id") Long authorId, @RequestBody Book book) {

        UUID uuid = UUID.randomUUID();


        long uuidBook = uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits();


        Book newBook = new Book(uuidBook, authorId, book.name(), book.publicationDate(), book.genre());


        books.add(newBook);

        return newBook;
    }

@PutMapping("/{book_id}")
    public Book updateBook(@PathVariable("author_id") Long authorId, @PathVariable("book_id") Long bookId, @RequestBody Book updatedBook) {

        Book existingBook = books.stream()
                .filter(book -> book.authorId().equals(authorId) && book.id().equals(bookId))
                .findFirst()
                .orElse(null);

        if (existingBook != null) {

            existingBook = new Book(bookId, authorId, updatedBook.name(), updatedBook.publicationDate(), updatedBook.genre());
        }

        return existingBook;
    }

@DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable("author_id") Long authorId,@PathVariable("book_id") Long bookId) {
        books.removeIf(book -> book.authorId().equals(authorId) && book.id().equals(bookId));
    }
}
