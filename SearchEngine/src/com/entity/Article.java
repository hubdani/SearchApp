package com.entity;

import java.util.List;

public class Article {
	private long id;
	private String mdate;
	private String key; 
	private List<Person> authors;
	private String[] authorNamesArr;
	private String title;
	private String year;
	private String journal;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Person> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}
	
	public String[] getAuthorsNames() {
		   String[] authorNamesArr = new String[authors.size()];
		   for (int i = 0; i < authors.size(); i++) {
			   System.out.println("???"+authors.get(i));
			   authorNamesArr[i] = authors.get(i).getName();
			
		   }
		return authorNamesArr;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String[] getAuthorNamesArr() {
		return authorNamesArr;
	}
	public void setAuthorNamesArr(String[] authorNamesArr) {
		this.authorNamesArr = authorNamesArr;
	}
	
	

}
