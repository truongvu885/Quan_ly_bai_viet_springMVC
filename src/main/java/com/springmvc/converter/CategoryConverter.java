package com.springmvc.converter;

import com.springmvc.dto.CategoryDTO;
import com.springmvc.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO entityToDTO(CategoryEntity entity){
        CategoryDTO result = new CategoryDTO();
        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setName(entity.getName());
        return result;
    }
    public CategoryEntity DTOToEntity(CategoryDTO dto){
        CategoryEntity result = new CategoryEntity();
        result.setCode(dto.getCode());
        result.setName(dto.getName());
        return result;
    }

}
