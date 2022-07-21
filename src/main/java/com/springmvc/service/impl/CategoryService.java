package com.springmvc.service.impl;

import com.springmvc.entity.CategoryEntity;
import com.springmvc.repository.CategoryRepository;
import com.springmvc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Map<String,String> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        Map<String,String> result = new HashMap<>();
        for (CategoryEntity entity : entities) {
            result.put(entity.getCode(),entity.getName());
        }
        return result;
    }
}
