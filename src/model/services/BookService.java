package model.services;

import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.impl.BookDao;
import model.database.exception.DbException;
import model.entities.Book;
import view.util.Alerts;

public class BookService {

	private BookDao dao;

	public BookService() {
		try {
			dao = (BookDao) DaoFactory.createBookDao();
		}catch(DbException error) {
			Alerts.showErrorAlert(error);
		}
	}

	public void saveBook(Book book) {
		dao.insert(book);
	}

	public List<Book> listAllBooks() throws SQLException {
		return dao.findAll();
	}

	public List<Book> findByTitle(String title) throws SQLException {
		return dao.findByTitle(title);
	}

	public void deleteBook(Integer id) throws SQLException {
		dao.deleteById(id);
	}
}
