package tn.esprit.interfaces;

import java.util.List;

public interface IBaseService<T> {
	public boolean add(T entity);
	public T update(T entity);
	public boolean remove(T entity);
	public T get(long id);
	public List<T> getAll();
	
}
