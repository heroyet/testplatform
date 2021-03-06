package com.ymt.testplatform.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ymt.testplatform.dao.BaseDAO;

@Repository("baseDAO")
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}
	
	//add by sean 2016.12.13
	public List<T> findBySql(String sql, Object[] param,Class c) {
		Query q = createQueryBySql(sql,param,c);
		return q.list();
	}
	//add by sean 2016.12.13
	public List<T> findBySql(String sql, Object[] param,Class c, int pageSize,int pageNo) {  
		Query q = createQueryBySql(sql,param,c);		
        q.setFirstResult((pageNo - 1) * pageSize);  
        q.setMaxResults(pageSize);  
		return q.list(); 
	}  
	//add by sean 2016.12.13
	private Query createQueryBySql(String sql, Object[] param,Class c){		
		Query q = this.getCurrentSession().createSQLQuery(sql).addEntity(c);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q;
		
	}
	
	// add by  chenjiazhu 20170106
	public List<Map> findBySqlReturnMap(String sql, Object[] param, int pageSize,int pageNo) {

		//Query q = this.getCurrentSession().createSQLQuery(sql).addEntity(Object.class);
		Query q = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		 q.setFirstResult((pageNo - 1) * pageSize);  
	        q.setMaxResults(pageSize);  
			return q.list(); 
	}

	// add by  chenjiazhu 20170106
	public List<Map> findBySqlReturnMap(String sql, Object[] param) {

		//Query q = this.getCurrentSession().createSQLQuery(sql).addEntity(Object.class);
		Query q = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		//return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
		Query q = this.getCurrentSession().createQuery(hql);
		Long quantity =(Long) q.uniqueResult();
		return quantity;
	}

	public Long count(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	
	// 2015/3/29 by Eric
	public List<T> findByHql(String hql, Map<String, Object> map, int pageSize,int pageNo) {  
	    return this.getQuery(hql, map, pageSize, pageNo).list();  
	}  
	
	public Long count(String hql, Map<String, Object> map) {
		//return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
		Query q = this.getCurrentSession().createQuery(hql);
		q = this.setParameter(q, map);
		Long quantity =(Long) q.uniqueResult();
		return quantity;
	}
	  
	private Query getQuery(String hql, Map<String, Object> map, int pageSize,int pageNo) {  
	    Query query = this.createQuery(hql);  
	    query = this.setParameter(query, map);  
	    query = this.setPageProperty(query, pageSize, pageNo);  
	    return query;  
	}  
	  
	private Query createQuery(String hql) {  
	    return getCurrentSession().createQuery(hql);  
	}  
	  
	private Query setParameter(Query query, Map<String, Object> map) {  
        if (map != null) {  
            Set<String> keySet = map.keySet();  
            for (String string : keySet) {  
                Object obj = map.get(string);  
                //杩欓噷鑰冭檻浼犲叆鐨勫弬鏁版槸浠�涔堢被鍨嬶紝涓嶅悓绫诲瀷浣跨敤鐨勬柟娉曚笉鍚�  
                if(obj instanceof Collection<?>){  
                    query.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                    query.setParameterList(string, (Object[])obj);  
                }else if(obj instanceof Integer){  
                    query.setParameter(string, (Integer)obj);  
                }else{  
                    query.setParameter(string, "%"+obj+"%");  
                }  
            }  
        }  
        return query;  
    }   
	  
	private Query setPageProperty(Query query, int pageSize, int pageNo) {  
	    if (pageNo != 0 && pageSize != 0) {  
	        query.setFirstResult((pageNo - 1) * pageSize);  
	        query.setMaxResults(pageSize);  
	    }  
	    return query;  
	}  
	
	/**
	 * add by chenjiazhu
	 */
    public int excuteBySql(String sql)    
    {    
        int result ;    
        SQLQuery query = getCurrentSession().createSQLQuery(sql);    
        result = query.executeUpdate();    
        return result;    
    }  
}
