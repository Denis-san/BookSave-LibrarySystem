package model.entities;

import model.enums.Nationality;

public class Author {
	
	private Integer id;
	private String name;
	private String biography;
	private Nationality nationality;
	
	
	public Author() {
		
	}

	public Author(Integer id, String name, String biography, Nationality nacionality
			) {
		super();
		this.id = id;
		this.name = name;
		this.biography = biography;
		this.nationality = nacionality;
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


	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nacionality) {
		this.nationality = nacionality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
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
		Author other = (Author) obj;
		if (nationality != other.nationality)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author: " + id + ", name:" + name + ", biography:" + biography + ", nationality:" + nationality;
	}
	
	
	
}