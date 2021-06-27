package model.services;

import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.impl.AuthorDao;
import model.entities.Author;

public class AuthorService {
	private AuthorDao dao = (AuthorDao) DaoFactory.createAuthorDao();
	
	public void saveAuthor(Author author) {
		dao.insert(author);
	}
	
	public Author findAuhtor(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	public List<Author> listAll() throws SQLException{
		return dao.findAll();
	}
}
