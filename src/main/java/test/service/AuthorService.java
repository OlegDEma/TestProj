package test.service;

import java.util.List;

import test.entity.Author;

public interface AuthorService {

	void save(Author author);
	List<Author> findAll();
	Author findOne(String surname);
	void delete(String surname);
	
}
