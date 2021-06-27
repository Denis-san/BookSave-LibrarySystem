package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.DaoBook;
import model.database.DbConnection;
import model.database.exception.DbException;
import model.entities.Author;
import model.entities.Book;
import model.enums.Nationality;

public class BookDao implements DaoBook {
	private Connection connection;

	public BookDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Book book) {
		PreparedStatement stmt = null;

		try {
			stmt = connection.prepareStatement("INSERT INTO Book"
					+ "(title, publishCompany, year, code, cloak, authorId) " + "VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			Calendar cal = Calendar.getInstance();
			cal.setTime(book.getYear());

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getPublishCompany());
			stmt.setDate(3, new Date(cal.get(Calendar.YEAR)));
			stmt.setString(4, book.getCode());
			stmt.setString(5, book.getCloak());
			stmt.setInt(6, book.getAuthor().getId());

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet resultSet = stmt.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					book.setId(id);
				}
				DbConnection.closeResultSet(resultSet);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConnection.closeStatment(stmt);
		}

	}

	@Override
	public void update(Book book) throws SQLException {
		String sql = "UPDATE Book " + "SET title = ?, publishCompany = ?, year = ?, code = ?, cloak = ?, authorId = ? "
				+ "WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getPublishCompany());
		statement.setDate(3, new java.sql.Date(book.getYear().getDate()));
		statement.setString(4, book.getCode());
		statement.setString(5, book.getCloak());
		statement.setInt(6, book.getAuthor().getId());
		statement.setInt(7, book.getId());

		statement.executeUpdate();

		DbConnection.closeStatment(statement);
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		String sql = "DELETE FROM Book WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		statement.executeUpdate();

		DbConnection.closeStatment(statement);

	}

	@Override
	public List<Book> findAll() throws SQLException {
		String sql = "SELECT Book.*,Author.name as BookAuthor, Author.nationality as BookAuthorNation, Author.biography as BookAuthorBio "
				+ "FROM Book INNER JOIN Author "
				+ "ON Book.authorId = Author.id ORDER BY title";
		PreparedStatement statement = connection.prepareStatement(sql);
		List<Book> list = new ArrayList<Book>();
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			list.add(instantiateBook(result, instantiateAuthor(result)));
		}

		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);
		return list;
	}

	@Override
	public List<Book> findByAuthor(Author author) throws SQLException {
		String sql = "SELECT Book.*,Author.name as BookAuthor, Author.nationality as BookAuthorNation, Author.biography as BookAuthorBio "
				+ "FROM Book INNER JOIN Author "
				+ "ON Book.authorId = Author.id " + "WHERE authorId = ? " + "ORDER BY title;";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, author.getId());

		ResultSet result = statement.executeQuery();

		List<Book> list = new ArrayList<Book>();
		Map<Integer, Author> map = new HashMap<Integer, Author>();

		while (result.next()) {

			Author other = map.get(result.getInt("AuthorId"));

			if (other == null) {
				other = instantiateAuthor(result);
				map.put(result.getInt("AuthorId"), other);
			}

			Book book = instantiateBook(result, other);
			list.add(book);

		}

		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);

		return list;
	}

	@Override
	public List<Book> findByTitle(String title) throws SQLException {
		String sql = "SELECT Book.*,Author.name as BookAuthor, Author.nationality as BookAuthorNation, Author.biography as BookAuthorBio "
				+ "FROM Book "
				+ "INNER JOIN Author ON Book.authorId = Author.id "
				+ "WHERE title = ? ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, title);

		ResultSet result = statement.executeQuery();
		
		List<Book> list = new ArrayList<Book>();

		while (result.next()) {
			list.add(instantiateBook(result, instantiateAuthor(result)));
		}

		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);
		return list;
}

	private Author instantiateAuthor(ResultSet result) {
		Author author = null;
		try {
			author = new Author();
			author.setId(result.getInt("authorId"));
			author.setName(result.getString("BookAuthor"));
			author.setNationality(Nationality.valueOf(result.getString("BookAuthorNation")));
			author.setBiography(result.getString("BookAuthorBio"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}

	private Book instantiateBook(ResultSet result, Author author) {
		Book book = new Book();
		try {
			book.setId(result.getInt("id"));
			book.setTitle(result.getString("title"));
			book.setPublishCompany(result.getString("publishCompany"));
			book.setYear(result.getDate("year"));
			book.setCode(result.getString("code"));
			book.setCloak(result.getString("cloak"));
			book.setAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}