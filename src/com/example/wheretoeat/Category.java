package com.example.wheretoeat;

public class Category {
	int id;
	String category;
	String thumbnail;
	
	/**
	 * Constructor vacio de la clase Category
	 */
	public Category (){
		
	}
	
	/**
	 * Constructor de la clase Category para la vista principal
	 * @param id
	 * @param category
	 * @param thumbnail
	 */
	public Category(int id, String category, String thumbnail){
		this.id = id;
		this.category = category;
		this.thumbnail = thumbnail;
	}

	public void setId(int id){ this.id = id; }
	public void setCategory(String category){ this.category = category; }
	public void setThumbnail(String thumbnail){ this.thumbnail = thumbnail; }
	
	public int getId(){ return this.id;}
	public String getCategory(){ return this.category; }
	public String getThumbnail(){ return this.thumbnail; }

}
