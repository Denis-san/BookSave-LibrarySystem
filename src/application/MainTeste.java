package application;

import java.sql.SQLException;
import java.util.Date;

import model.dao.impl.AuthorDao;
import model.dao.impl.BookDao;
import model.database.DbConnection;
import model.entities.Author;
import model.entities.Book;
import model.enums.Nationality;

public class MainTeste {

	public static void main(String[] args) {		
		
		BookDao dao = new BookDao(DbConnection.getConnection());
		Author a1 = new Author(null, "Artur", "um texto", Nationality.British);
		
		AuthorDao ada = new AuthorDao(DbConnection.getConnection());
		try {
			ada.insert(a1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.insert(new Book(null, "Sherlock Holmes", "North American Inc.", new Date(), "ashidua21", "path//", a1));
		
	}
	
}
