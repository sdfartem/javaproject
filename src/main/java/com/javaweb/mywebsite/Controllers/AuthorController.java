package com.javaweb.mywebsite.Controllers;


import com.javaweb.mywebsite.Model.Author;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private final List<Author> authors;

    public AuthorController() {
        try {
            String strDate1 = "1.04.1809";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "6.06.1799";
            Date date2 = formatter.parse(strDate2);

            Author a1 = new Author(1L, "Николай", "Гоголь", Author.Citizenship.Русское, date1);
            Author a2 = new Author(2L, "Александр", "Пушкин", Author.Citizenship.Русское, date2);

            authors = new ArrayList<>();
            authors.add(a1);
            authors.add(a2);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authors;
    }

    @GetMapping("/{author_id}")
    public Author getAuthors(@PathVariable("author_id") Long authorId) {
        return authors.stream().filter(author -> author.id().equals(authorId)).findAny().orElse(null);
    }


    @PostMapping
    public Author createAuthor(@RequestBody Author author) {

        UUID uuid = UUID.randomUUID();


        long newAuthorId = uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits();

        Author newAuthor = new Author(newAuthorId, author.name(), author.surname(), author.citizenship(),author.birthDate());

        authors.add(newAuthor);

        return newAuthor;
    }

    @PutMapping("/{author_id}")
    public Author updateAuthor(@PathVariable("author_id") Long authorId, @RequestBody Author updatedAuthor) {

        Author existingAuthor = authors.stream()
                .filter(author -> author.id().equals(authorId))
                .findFirst()
                .orElse(null);

        if (existingAuthor != null) {

            existingAuthor = new Author(authorId, updatedAuthor.name(), updatedAuthor.surname(), updatedAuthor.citizenship(), updatedAuthor.birthDate());
        }
        return existingAuthor;
    }

    @DeleteMapping("/{author_id}")
    public void deleteAuthor(@PathVariable("author_id") Long authorId){
        authors.removeIf(author -> author.id().equals(authorId));
    }
}
