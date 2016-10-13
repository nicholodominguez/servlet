package com.ecc.ems;

import com.ecc.ems.Employee;
import java.io.Serializable;
import java.util.List;

public interface BaseDAOInterface<T, Id extends Serializable>{
    
    public void saveOrUpdate(T entity);
    
    public T findById(Id id);
    
    public List<T> findAll(String query);
    
    public void delete(T entity);

}
