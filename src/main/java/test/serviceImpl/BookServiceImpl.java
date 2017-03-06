package test.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.BookDao;
import test.entity.Book;
import test.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;

	public void save(Book book) {
		bookDao.save(book);
	}

	public List<Book> findAll() {
		return bookDao.findAll();
	}

	public Book findOne(String title) {
		return bookDao.findOne(title);
	}

	public void delete(Book book) {
		bookDao.delete(book);
	}

	public void update(Book book) {
		bookDao.update(book);		
	}

	@Override
	public List<Book> allBooks() {
		return bookDao.allBooks();
	}



	
	
	
	
}
