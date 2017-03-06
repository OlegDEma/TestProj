package test.dao;

import java.util.List;

import test.entity.Author;

public interface AuthorDao {

	void save(Author author);
	List<Author> findAll();
	Author findOne(String surname);
	void delete(String surname);
	
}
