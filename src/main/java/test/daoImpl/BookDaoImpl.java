package test.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import test.dao.BookDao;

import test.entity.Book;

@Repository
public class BookDaoImpl implements BookDao{

	@PersistenceContext(unitName="primary")
	private EntityManager entityManager;
	@Transactional
	public void save(Book book) {
		entityManager.persist(book);
	}
	@Transactional
	public List<Book> findAll() {
		return entityManager.createQuery("from Book").getResultList();
	}
	@Transactional
	public Book findOne(String title) {
		return (Book) entityManager.createQuery("select b from Book b where b.title like :title")
				.setParameter("title", title).getSingleResult();
	}
	
	@Transactional
	public void delete(Book book) {
		entityManager.remove(entityManager.merge(book));
	}
	@Transactional
	public void update(Book book) {
		entityManager.merge(book);
	}
	@Override
	public List<Book> allBooks() {
		return entityManager.createQuery("from Book").getResultList();
	}




	
	
}
