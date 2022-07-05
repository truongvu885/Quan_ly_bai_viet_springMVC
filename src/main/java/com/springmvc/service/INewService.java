package com.springmvc.service;

import com.springmvc.dto.NewDTO;

import java.util.List;

public interface INewService {
    List<NewDTO> findAll();
}
