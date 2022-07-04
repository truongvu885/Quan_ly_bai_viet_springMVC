package com.springmvc.service.impl;

import com.springmvc.dao.INewDAO;
import com.springmvc.model.NewModel;
import com.springmvc.paging.Pageble;
import com.springmvc.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewService implements INewService {
    @Override
    public List<NewModel> findAll() {
        return null;
    }

//    @Autowired
//    private INewDAO newDao;

//    @Override
//    public List<NewModel> findAll() {
//        return newDao.findAll();
//    }
}
