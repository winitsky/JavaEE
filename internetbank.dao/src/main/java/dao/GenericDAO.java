package dao;


import java.util.List;

public interface GenericDAO<T> {
	T get(T object);

	void create(T object);

	/*void update(T object);*/

	void delete(T object);
	
	List<T> readAll();

}
