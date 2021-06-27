package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entities.Author;
import model.entities.Book;

public interface DaoBook {
	public void insert(Book book) throws SQLException;
	public void update(Book book) throws SQLException;
	public void deleteById(Integer id) throws SQLException;
	public List<Book> findAll() throws SQLException;
	public List<Book> findByAuthor(Author author) throws SQLException;
	public List<Book> findByTitle(String title) throws SQLException;
}