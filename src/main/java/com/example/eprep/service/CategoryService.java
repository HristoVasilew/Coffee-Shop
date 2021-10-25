package com.example.eprep.service;

import com.example.eprep.model.entity.Category;
import com.example.eprep.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initializeCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
