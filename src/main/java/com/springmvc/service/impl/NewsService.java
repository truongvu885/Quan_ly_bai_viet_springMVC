package com.springmvc.service.impl;

import com.springmvc.converter.NewsConverter;
import com.springmvc.dto.NewsDTO;
import com.springmvc.entity.CategoryEntity;
import com.springmvc.entity.NewsEntity;
import com.springmvc.repository.CategoryRepository;
import com.springmvc.repository.NewsRepository;
import com.springmvc.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService implements INewService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<NewsDTO> findAll(Pageable pageable) {
        List<NewsDTO> models = new ArrayList<>();
        List<NewsEntity> entities =newsRepository.findAll(pageable).getContent();
        for (NewsEntity entity : entities) {
            NewsDTO newsDTO = newsConverter.entityToDTO(entity);
            models.add(newsDTO);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) newsRepository.count();
    }

    @Override
    public NewsDTO findById(long id) {
        NewsEntity entity = newsRepository.findOne(id);
        return newsConverter.entityToDTO(entity);
    }

//    @Override
//    @Transactional
//    public NewsDTO insert(NewsDTO insertNews) {
//        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCode(insertNews.getCategoryCode());
//        NewsEntity entity = newsConverter.DTOToEntity(insertNews);
//        entity.setCategoryId(categoryEntity);
//        return newsConverter.entityToDTO(newsRepository.save(entity));
//    }
//
//    @Override
//    @Transactional
//    public NewsDTO update(NewsDTO updateNews) {
//        NewsEntity oldNews = newsRepository.findOne(updateNews.getId());
//        NewsEntity newNews = newsConverter.DTOToEntity(updateNews,oldNews);
//        newNews.setCategoryId(categoryRepository.findCategoryEntityByCode(updateNews.getCategoryCode()));
//        return newsConverter.entityToDTO(newsRepository.save(newNews));
//    }

    @Override
    @Transactional
    public NewsDTO save(NewsDTO newDTO) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCode(newDTO.getCategoryCode());
        NewsEntity newsEntity = new NewsEntity();
        if(newDTO.getId()!=null){
            NewsEntity oldNews = newsRepository.findOne(newDTO.getId());
            newsEntity = new NewsConverter().DTOToEntity(newDTO,oldNews);
            newsEntity.setCategoryId(categoryEntity);
        }
        else {
            newsEntity = newsConverter.DTOToEntity(newDTO);
            newsEntity.setCategoryId(categoryEntity);
        }
        return newsConverter.entityToDTO(newsRepository.save(newsEntity));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long id : ids) {
            newsRepository.delete(id);
        }
    }

    @Override
    public void upLoadImg(MultipartFile img) throws IOException {
        String folder = "/photos/";
        byte[] bytes = img.getBytes();
        Path path = Paths.get(folder + img.getOriginalFilename());
        Files.write(path,bytes);

    }

}
