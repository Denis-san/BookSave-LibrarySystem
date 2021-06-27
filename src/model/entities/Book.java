package model.entities;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 1l;
	
	private Integer id;
	private String title;
	private String publishCompany;
	private Integer year;
	private String code;
	private String cloak; // caminho da imagem
	private Author author;
	
	public Book() {
		
	}
	
	public Book(Integer id, String title, String publishCompany, Integer year, String code, String cloak, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.publishCompany = publishCompany;
		this.year = year;
		this.code = code;
		this.cloak = cloak;
		this.author = author;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishCompany() {
		return publishCompany;
	}
	public void setPublishCompany(String publishCompany) {
		this.publishCompany = publishCompany;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
	@Override
	public String toString() {
		return "livro: " + id + ", " + title + ", " + publishCompany + ", " + year + ", "
				+ code + ", " + cloak + ", " + author.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publishCompany == null) ? 0 : publishCompany.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (publishCompany == null) {
			if (other.publishCompany != null)
				return false;
		} else if (!publishCompany.equals(other.publishCompany))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	
	
}