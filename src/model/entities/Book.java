package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private static final long serialVersionUID = 1l;
	
	private Integer id;
	private String name;
	private String publishCompany;
	private Date year;
	private String code;
	private String cloak; // caminho da imagem
	private Author autor;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	
	public Book() {
		
	}
	
	public Book(Integer id, String name, String publishCompany, Date year, String code, String cloak, Author autor) {
		super();
		this.id = id;
		this.name = name;
		this.publishCompany = publishCompany;
		this.year = year;
		this.code = code;
		this.cloak = cloak;
		this.autor = autor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublishCompany() {
		return publishCompany;
	}
	public void setPublishCompany(String publishCompany) {
		this.publishCompany = publishCompany;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCloak() {
		return cloak;
	}
	public void setCloak(String cloak) {
		this.cloak = cloak;
	}
	public Author getAutor() {
		return autor;
	}
	public void setAutor(Author autor) {
		this.autor = autor;
	}
	
	
	@Override
	public String toString() {
		return "livro: " + id + ", " + name + ", " + publishCompany + ", " + year + ", "
				+ code + ", " + cloak + ", " + autor.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}