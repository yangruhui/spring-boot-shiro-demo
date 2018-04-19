package com.twilight.orm.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.twilight.orm.base.page.PageResultSet;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{

	public T findById(ID id);  //按主键id查找
	public List<T> findAll(); //查找所有数据
	public List<T> find(String hql); //根据查询语句查找
	public List<T> find(String hql,Object[] values); //根据带参数的hql语句查找
	public PageResultSet<T> find(String hql, int pageSize,int page); //分页查找
	public PageResultSet<T> find(String hql,Object[] values, int pageSize,int page); //带参数分页查找
}
