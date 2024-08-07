package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.form.CategoryEditForm;
import com.example.nagoyameshi.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;    
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;        
    }    
    
    @Transactional
    public void create(CategoryEditForm categoryEditForm) {
    	Category category = new Category();        
        
    	category.setCategoryName(categoryEditForm.getCategoryName());   

                    
    	categoryRepository.save(category);
    }  
    
    @Transactional
    public void update(CategoryEditForm categoryEditForm) {
    	Category category = categoryRepository.getReferenceById(categoryEditForm.getCategoryId());
        
    	category.setCategoryName(categoryEditForm.getCategoryName());  
                    
    	categoryRepository.save(category);
    }  
}
