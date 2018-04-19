package com.twilight.orm.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import com.twilight.orm.base.page.PageInfo;
import com.twilight.orm.base.page.PageResultSet;

public class BaseRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T,ID>
    implements BaseRepository<T,ID>{

	private final EntityManager entityManager;
	private Class<T> persistentClass;
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.entityManager = em;
		this.persistentClass = domainClass;
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 获得总数
	 * @param hql hql语句，例如"from Student where name = ? and age = ?"
	 * @param values Object[]{"aaa", 2}
	 * @return
	 */
	private int countAll(final String hql, final Object... values) {
		Long count = (long) 0;
		try {
			Query query = (Query) getEntityManager().createQuery(hql);
			if (null != values) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			count = (Long) query.getSingleResult();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		return count.intValue();
	}

	/**
	 * 分页查找
	 * @param hql hql语句，例如"from Student"
	 * @param pageSize 页数
	 * @param page 页码，默认从1开始
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageResultSet<T> find(String hql, int pageSize, int page) {
		// TODO Auto-generated method stub
		final PageResultSet<T> result = new PageResultSet<T>();
		try {
		    Query query = (Query) getEntityManager().createQuery(hql);
		    int totalRow = this.countAll("select count(*) " + hql);
		    PageInfo pageInfo = new PageInfo(totalRow, pageSize, page);
		    result.setList(query.setFirstResult(pageInfo.getBeginIndex())
		    		.setMaxResults(pageSize).getResultList());
		    result.setPageInfo(pageInfo);
		    return result;
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 带参数分页查找
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageResultSet<T> find(String hql, Object[] values, int pageSize, int page) {
		// TODO Auto-generated method stub
		final PageResultSet<T> result = new PageResultSet<T>();
		try {
		    Query query = (Query) getEntityManager().createQuery(hql);
		    if (null != values) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
		    int totalRow = this.countAll("select count(*) " + hql, values);
		    PageInfo pageInfo = new PageInfo(totalRow, pageSize, page);
		    result.setList(query.setFirstResult(pageInfo.getBeginIndex())
		    		.setMaxResults(pageSize).getResultList());
		    result.setPageInfo(pageInfo);
		    return result;
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return getEntityManager().find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		try {
		    return getEntityManager().createQuery(hql).getResultList();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] values) {
		// TODO Auto-generated method stub
		try {
		    Query query = (Query) getEntityManager().createQuery(hql);
		    if (null != values) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
		    return query.getResultList();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
}
