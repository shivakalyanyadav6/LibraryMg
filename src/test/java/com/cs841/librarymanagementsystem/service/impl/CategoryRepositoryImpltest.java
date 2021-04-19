package com.mehmetpekdemir.librarymanagementsystem.service.impl;
import com.mehmetpekdemir.librarymanagementsystem.entity.Category;
import com.mehmetpekdemir.librarymanagementsystem.repository.CategoryRepository;
import com.mehmetpekdemir.librarymanagementsystem.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository   CategoryRepository;

    @Test
    void findAllCategorys() {
        List<Category>  Category  = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
            Category.add( new Category ("user "+i ));
        }
        Mockito.when(CategoryRepository.findAll()).thenReturn(Category);
        CategoryServiceImpl   categoryService = new CategoryServiceImpl(CategoryRepository);
        List< Category> allCategory = categoryService.findAllCategories();
        assertIterableEquals(Category, allCategory);
    }

    @Test
    void findCategoryById(){
        Category category = new Category("Robert John");
        Long CategoryId = Integer.toUnsignedLong(24);
        Mockito.when(CategoryRepository.findById(CategoryId)).thenReturn(java.util.Optional.of(category));
        CategoryServiceImpl   categoryService = new CategoryServiceImpl(CategoryRepository);
        Category categoryById = categoryService.findCategoryById(CategoryId);
        assertEquals(category, categoryById);
    }

    @Test
    void createCategory() {
        Category category = new Category ("catogory");
        CategoryServiceImpl categoryService = new CategoryServiceImpl(CategoryRepository);
        categoryService.createCategory(category);
        Mockito.verify(CategoryRepository,Mockito.atLeastOnce()).save(category);
        Mockito.verifyNoMoreInteractions(CategoryRepository);

    }




    @Test

    void updateCategory() {

        Category category = new Category("Dave Greck");

        CategoryServiceImpl categoryService = new CategoryServiceImpl(CategoryRepository);

        categoryService.updateCategory(category);

        Mockito.verify(CategoryRepository,Mockito.atLeastOnce()).save(category);

        Mockito.verifyNoMoreInteractions(CategoryRepository);

    }



    @Test

    void deleteCategory() {

        Category category = new Category (" Almond milk ");
    }
