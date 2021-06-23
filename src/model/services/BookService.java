package model.services;

import model.dao.DaoFactory;
import model.dao.impl.BookDao;
import model.entities.Book;

public class BookService {

	private BookDao dao = (BookDao) DaoFactory.createBookDao();
	
	public void saveBook(Book book) {
		dao.insert(book);
	}
	
}
