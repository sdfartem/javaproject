package com.javaweb.mywebsite.Model;

import java.util.Date;

public record Book(Long id, Long authorId, String Name, Date Year_of_publication) {
}
