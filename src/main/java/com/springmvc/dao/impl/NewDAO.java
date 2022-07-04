package com.springmvc.dao.impl;

import java.util.List;

import com.springmvc.dao.INewDAO;
import com.springmvc.mapper.NewMapper;
import com.springmvc.model.NewModel;
import com.springmvc.paging.Pageble;
import org.springframework.stereotype.Repository;


//@Repository
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewMapper());
	}

}
