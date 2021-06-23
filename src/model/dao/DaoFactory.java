package model.dao;


import model.dao.impl.AuthorDao;
import model.dao.impl.BookDao;
import model.database.DbConnection;

public class DaoFactory {
	//The DaoBook is a Interface
	public static DaoBook createBookDao() {
		return new BookDao(DbConnection.getConnection());
	}
	//The DaoAuthor is a Interface
	public static DaoAuthor createAuthorDao() {
		return new AuthorDao(DbConnection.getConnection());
	}
}