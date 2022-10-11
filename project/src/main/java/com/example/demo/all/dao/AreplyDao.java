package com.example.demo.all.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.all.entity.Areply;

@Mapper
public interface AreplyDao {

	public Integer save(Areply reply);
}
