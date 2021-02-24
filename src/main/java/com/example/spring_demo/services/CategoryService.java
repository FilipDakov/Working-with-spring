package com.example.spring_demo.services;

import com.example.spring_demo.entities.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface CategoryService {
    void seedCategories() throws IOException;

    Category getCategoryById(Long id);
}
