package com.springmvc.service.impl;

import com.springmvc.dto.NewDTO;
import com.springmvc.service.INewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewService implements INewService {

    @Override
    public List<NewDTO> findAll() {
        return null;
    }

}
