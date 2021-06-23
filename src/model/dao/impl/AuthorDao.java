package model.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.DaoAuthor;
import model.database.DbConnection;
import model.database.exception.DbException;
import model.entities.Author;
import model.enums.Nationality;

public class AuthorDao implements DaoAuthor {

	private Connection connection;

	public AuthorDao(Connection connection) {
		this.connection = connection;
	}

	public void insert(Author author) throws SQLException {
		String sql = "INSERT INTO Author (name, biography, nationality) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, author.getName());
		statement.setString(2, author.getBiography());
		statement.setString(3, String.valueOf(author.getNationality()));

		int rowsAffected = statement.executeUpdate();
		ResultSet resultSet;

		if (rowsAffected > 0) {
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				author.setId(id);
			}
		} else {
			throw new DbException("Unexpected error! No rows affected!");
		}
	
		DbConnection.closeResultSet(resultSet);
		DbConnection.closeStatment(statement);

	}

	public void update(Author author) throws SQLException{
		String sql = "UPDATE Author "
					+ "SET name = ?, biography = ?, nationality = ? "
					+ "WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, author.getName());
		statement.setString(2, author.getBiography());
		statement.setString(3, String.valueOf(author.getNationality()));
		statement.setInt(4, author.getId());

		statement.executeUpdate();
		
		DbConnection.closeStatment(statement);

	}
	
	public void deleteById(Integer id) throws SQLException{
		String sql = "DELETE FROM Author WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		statement.executeUpdate();
		
		DbConnection.closeStatment(statement);

	}

	public List<Author> findAll() throws SQLException{
		String sql = "SELECT * FROM Author ORDER BY name";
		PreparedStatement statement = connection.prepareStatement(sql);
		List<Author> list = new ArrayList<Author>();
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			list.add(instantiateAuthor(result));
		}
		
		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);
		return list;
	}

	@Override
	public Author findById(Integer id) throws SQLException {

		String sql = "SELECT * FROM Author WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		
		Author author = null;
		
		if(result.next()) {
			author = instantiateAuthor(result);
		}
		
		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);
		return author;
	}

	@Override
	public Author findByName(String name) throws SQLException {
		String sql = "SELECT * FROM Author WHERE name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();

		Author author = null;

		if (result.next()) {
			author = instantiateAuthor(result);
		}

		DbConnection.closeResultSet(result);
		DbConnection.closeStatment(statement);
		return author;
	}

	private Author instantiateAuthor(ResultSet result) {
		Author author = null;
		try {
			author = new Author();
			author.setId(result.getInt("id"));
			author.setName(result.getString("name"));
			author.setBiography(result.getString("biography"));
			author.setNationality(Nationality.valueOf(result.getString("nationality")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}
}