package com.example.coursework.facade;

import com.example.coursework.dto.CategoryDTO;
import com.example.coursework.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFacade {

    public CategoryDTO categoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }

}
