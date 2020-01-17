package com.intiformation.tpcomptes.dao;
import java.util.List;

/**
 * interface de la couche DAO
 * @author IN-MP-014
 *
 */
public interface IDatabaseDAO<T> {
	
	public List<T> getAll();
	public T getById(int id);
	public boolean add(T t);
	public boolean update(T t);
	public boolean delete(int t);
}
