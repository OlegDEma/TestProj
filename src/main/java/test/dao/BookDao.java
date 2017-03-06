package test.dao;

import java.util.List;

import test.entity.Book;

public interface BookDao {

	void save(Book book);
	List<Book> findAll();
	Book findOne(String title);
	void delete(Book book);
	void update(Book book);
	List<Book> allBooks();
}
