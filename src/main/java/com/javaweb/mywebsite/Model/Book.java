package com.javaweb.mywebsite.Model;

import java.util.Date;

public record Book(Long id, Long authorId, String name, Date publicationDate, Genre genre) {

    public enum Genre {
        Проза,
        Поэма,
    }
}