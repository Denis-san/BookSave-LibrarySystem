package model.services;

import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.impl.BookDao;
import model.entities.Book;

public class BookService {

	private BookDao dao = (BookDao) DaoFactory.createBookDao();
	
	public void saveBook(Book book) {
		dao.insert(book);
	}
	
	public List<Book> listAllBooks() throws SQLException{
		return dao.findAll();
	}
	
	public List<Book> findByTitle(String title) throws SQLException {
		return dao.findByTitle(title);
	}
}
