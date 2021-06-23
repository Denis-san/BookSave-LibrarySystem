package model.services;

import java.sql.SQLException;

import model.dao.DaoFactory;
import model.dao.impl.AuthorDao;
import model.entities.Author;

public class AuthorService {
	private AuthorDao dao = (AuthorDao) DaoFactory.createAuthorDao();
	
	public void saveAuthor(Author author) throws SQLException {
		dao.insert(author);
	}
}
