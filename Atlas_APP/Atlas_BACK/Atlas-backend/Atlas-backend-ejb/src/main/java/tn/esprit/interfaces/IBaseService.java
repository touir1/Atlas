package tn.esprit.interfaces;

import java.util.List;

import tn.esprit.entity.Choix;

public interface IBaseService<T> {
	public T add(T entity);
	public T update(T entity);
	public boolean remove(T entity);
	public T get(long id);
	public List<T> getAll();
	
	
}
