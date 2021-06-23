package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entities.Author;

public interface DaoAuthor {
	public void insert(Author author) throws SQLException;
	public void update(Author author) throws SQLException;
	public void deleteById(Integer id) throws SQLException;
	public List<Author> findAll() throws SQLException;
	public Author findById(Integer id) throws SQLException;
	public Author findByName(String name) throws SQLException;
}
