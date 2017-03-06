package test.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.entity.Author;
import test.entity.Book;
import test.service.AuthorService;
import test.service.BookService;

public class Menu {
	
	BookService bookService = getBookService();
	AuthorService authorService = getAuthorService();
	Scanner scanner = new Scanner(System.in);

	public void menu(){
		System.out.println("Select 1 point");
		System.out.println("1:saveBook");
		System.out.println("2:saveAuthor");
		System.out.println("3:updateBook");
		System.out.println("4:deleteBook");
		System.out.println("5:allBooks");
		System.out.println("6:deleteAuthor");
		System.out.println("0:exit");
		String menu = scanner.next();
		switch (menu) {
		case "1":			
				saveBook();
			break;
		case "2":
				saveAuthor();
			break;
		case "3":
			updateBook();
			break;
		case "4":
			deleteBook();
			break;
		case "5":
			List<Book> books = bookService.allBooks();
			for (Book book : books) {
				System.out.println(book);
			}
			menu();
			break;
	case "6":
		deleteAuthor();
		break;
		case "0":
			System.exit(0);	
			break;

		default:
			break;
		}
		
	}
	
	public BookService getBookService(){
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("appContext.xml");
		
		BookService bookService = (BookService) context.getBean("bookService");
		
		return bookService;
	}
	
	public AuthorService getAuthorService(){
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("appContext.xml");
		
		AuthorService authorService = (AuthorService) context.getBean("authorService");
		
		
		return authorService;
	}
	
	public void saveAuthor(){
		
		System.out.println("Enter Author name");
		String name = scanner.next();
		System.out.println("Enter Author surname");
		String surname = scanner.next();
		Author author = new Author(name, surname);
		authorService.save(author);
		System.out.println("saved  "+author.getName()+" "+author.getSurname());
		menu();
	}
	
	public void saveBook(){
		
		System.out.println("Enter Book title");
		String title = scanner.next();
		System.out.println("Enter pages");
		int pages = scanner.nextInt();
		Book book = new Book(title, pages);
		List<Author> authors = authorService.findAll();
		for (Author author : authors) {
			System.out.println(author);
		}
		System.out.println("¬каж≥ть автора");
		String name = scanner.next();
		Author authorOfBook = null;
		for (Author author : authors) {
			if(author.getName().equals(name)){
				authorOfBook = author;
			}
		}
		book.setAuthor(authorOfBook);
		bookService.save(book);	
		System.out.println("Saved "+title);
		menu();
	}
	
	public void deleteAuthor(){
		int exist = 0;
		List<Author> list = authorService.findAll();
		for (Author author : list) {
			System.out.println(author);
		}
		System.out.println("Enter Author surname");
		String surname = scanner.next();
		Author author = null;
		for (Author author2 : list) {
			if(author2.getSurname().equalsIgnoreCase(surname)){
				authorService.delete(surname);
				exist = 1;
			}
		}
		if(exist != 1){
			System.out.println("error surname");
			deleteAuthor();
		}
		menu();
		
	}
	
	
	
	public void updateBook(){
		System.out.println("Enter Book title");
		String title = scanner.next();
		Book book = null;
		
		try {
			book = bookService.findOne(title);
		} catch (NoResultException e) {
			System.out.println("Error title");
			updateBook();
		}
		System.out.println("What you want to edit");
		System.out.println("1:title");
		System.out.println("2:pages");
		System.out.println("3:author");
		String choice =  scanner.next();
		switch (choice) {
		case "1":
			System.out.println("Enter title");
			String newTitle = scanner.next();
			book.setTitle(newTitle);
			bookService.update(book);
			menu();
			
			break;
		case "2":
			System.out.println("Enter pages");
			int newPages = scanner.nextInt();
			book.setPages(newPages);
			bookService.update(book);
			menu();
			
			break;
		case "3":
			List<Author> list = authorService.findAll();
			for (Author author : list) {
				System.out.println(author);
			}
			System.out.println("Enter the name of a new author");
			String nameAuthor = scanner.next();
			Author author = null;
			for (Author author2 : list) {
				if(author2.getName().equalsIgnoreCase(nameAuthor)){
					author=author2;
				}
			}
			book.setAuthor(author);
			bookService.update(book);
			break;

		default:
			System.out.println("Error input. You redirected back");
			updateBook();
			
			break;
		}
		menu();
	}
	
	public void deleteBook(){
		int number = 0;
		int i = 0;
		List<Book> books = bookService.findAll();
		for (Book book : books) {
			System.out.println(book);
		}
		System.out.println("Enter the name of the book you will remove");
		String title = scanner.next();
		List<Book> list = new ArrayList<>();
		for (Book book : books) {
			if(book.getTitle().equalsIgnoreCase(title)){
				list.add(book);
			}
		}
		if(list.size() == 0){
			System.out.println("This book is missing");
			deleteBook();
		}
		if(list.size() ==  1){
			Book book = list.get(0);
			System.out.println(book);
			bookService.delete(book);
			System.out.println("The book was removed");
		}else{
			System.out.println("Select book");
			for (Book book : list) {
				System.out.println((++i)+":"+ book);
			}
			try {
				number = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Enter number!!");
			}
			if(number > list.size()){
				System.out.println("You entered not the number");
				deleteBook();
			}else{
				Book book = list.get(number-1);
				bookService.delete(book);
				System.out.println("The book was removed");
			}
			
			}
		menu();
		}
	
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

