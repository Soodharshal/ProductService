package dev.harshal.productservice.models;

import jakarta.persistence.Entity;

@Entity(name ="categories")
public class Category extends BaseModel {
    private String name;
}
