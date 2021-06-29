package model.services;

import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.impl.AuthorDao;
import model.database.exception.DbException;
import model.entities.Author;
import view.util.Alerts;

public class AuthorService {
	private AuthorDao dao;
	
	public AuthorService() {
		try {
			dao = (AuthorDao) DaoFactory.createAuthorDao();
		}catch(DbException error) {
			Alerts.showErrorAlert(error);
		}
	}
	
	public void saveAuthor(Author author) {
		dao.insert(author);
	}
	
	public Author findAuhtor(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	public List<Author> listAll() throws SQLException{
		return dao.findAll();
	}

	public void updateAuthor(Author author) throws SQLException{
		dao.update(author);
		
	}
	
	
}
