package com.example.spring_demo.services.impl;

import com.example.spring_demo.constants.GlobalConstants;
import com.example.spring_demo.entities.Category;
import com.example.spring_demo.repository.CategoryRepository;
import com.example.spring_demo.services.CategoryService;
import com.example.spring_demo.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0){
            return;
        }

        String[] fileContent=fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(c -> {
                    Category category = new Category(c);
                    this.categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getOne(id);
    }
}
