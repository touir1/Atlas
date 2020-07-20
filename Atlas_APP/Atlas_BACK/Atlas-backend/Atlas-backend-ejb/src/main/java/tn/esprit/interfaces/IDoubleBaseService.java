package tn.esprit.interfaces;

import java.util.List;

public interface IDoubleBaseService<T> {
	public T add(T entity);
	public T update(T entity);
	public boolean remove(T entity);
	public T get(long idLeft, long idRight);
	public List<T> getAll();
}
