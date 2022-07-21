package com.springmvc.converter;

import com.springmvc.dto.NewsDTO;
import com.springmvc.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {

    public NewsDTO entityToDTO(NewsEntity entity){
        NewsDTO result = new NewsDTO();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setShortDescription(entity.getShortDescription());
        result.setContent(entity.getContent());
        result.setThumbnail(entity.getThumbnail());
        result.setCategoryCode(entity.getCategoryId().getCode());
        return result;
    }

    public NewsEntity DTOToEntity(NewsDTO dto){
        NewsEntity result = new NewsEntity();
        result.setTitle(dto.getTitle());
        result.setThumbnail(dto.getThumbnail());
        result.setContent(dto.getContent());
        result.setShortDescription(dto.getShortDescription());
        return result;
    }

    public NewsEntity DTOToEntity(NewsDTO dto,NewsEntity result){
        result.setTitle(dto.getTitle());
        result.setThumbnail(dto.getThumbnail());
        result.setContent(dto.getContent());
        result.setShortDescription(dto.getShortDescription());
        return result;
    }

}
