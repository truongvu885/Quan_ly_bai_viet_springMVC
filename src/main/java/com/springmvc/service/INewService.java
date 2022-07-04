package com.springmvc.service;

import com.springmvc.model.NewModel;
import com.springmvc.paging.Pageble;

import java.util.List;

public interface INewService {
    List<NewModel> findAll();
}
