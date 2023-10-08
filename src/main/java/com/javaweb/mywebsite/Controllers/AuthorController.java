package com.javaweb.mywebsite.Controllers;


import com.javaweb.mywebsite.Model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
   private final List<Author> authors;

   public AuthorController(){
       Author a1 = new Author(1L,"Николай","Гоголь");
       Author a2 = new Author(2L,"Александр","Пушкин");

       authors = List.of(a1,a2);
   }

   @GetMapping
   public List<Author> getAuthors(){
       return authors;
   }
   @GetMapping("/{author_id}")
   public Author getAuthors(@PathVariable("author_id") Long authorId){
       return authors.stream().filter(author -> author.id().equals(authorId)).findAny().orElse(null);
   }


}
