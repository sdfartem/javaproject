package com.javaweb.mywebsite.Model;
import java.util.Date;

public record Author(Long id, String name, String surname, Citizenship citizenship, Date birthDate) {


    public enum Citizenship{
        Русское
    }
}


