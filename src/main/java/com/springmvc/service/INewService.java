package com.springmvc.service;

import com.springmvc.dto.NewsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface INewService {
    List<NewsDTO> findAll(Pageable pageable);
    int getTotalItem();
    NewsDTO findById(long id);
//    NewsDTO insert(NewsDTO insertNews);
//    NewsDTO update(NewsDTO updateNews);
    NewsDTO save(NewsDTO newDTO);
    void delete(long id[]);
    void upLoadImg(MultipartFile img) throws IOException;
}
