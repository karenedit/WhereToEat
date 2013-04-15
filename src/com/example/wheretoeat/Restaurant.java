package com.example.wheretoeat;

public class Restaurant {

	int id;
	int category;
	int xCoordinate;
	int yCoordinate;
	int checkin;
	String restaurant;
	String sucursal;
	String thumbnail;
	String address;
	String phone;
	String hours;
	String days;
	String image;
	String webpage;
	String twitter;
	String facebook;
	
	/**
	 * Constructor vacio de la clase Restaurant
	 */
	public Restaurant (){
		
	}
	
	/**
	 * Constructor de la clase Restaurant para la vista principal
	 * @param id
	 * @param category
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param restaurant
	 * @param sucursal
	 * @param thumbnail
	 */
	public Restaurant(int id, int category, int xCoordinate, int yCoordinate, int checkin, String restaurant, 
			String sucursal, String thumbnail, String address, String phone, String hours, String days, String image, String webpage,
			String twitter, String facebook){
		this.id = id;
		this.category = category;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.checkin = checkin;
		this.restaurant = restaurant;
		this.sucursal = sucursal;
		this.thumbnail = thumbnail;
		this.address = address;
		this.phone = phone;
		this.hours = hours;
		this.days = days;
		this.image = image;
		this.webpage = webpage;
		this.twitter = twitter;
		this.facebook = facebook;
	}

	public void setId(int id){ this.id = id; }
	public void setCategory(int category){ this.category = category; }
	public void setXCoordinate(int xCoordinate){ this.xCoordinate = xCoordinate; }
	public void setYCoordinate(int yCoordinate){ this.yCoordinate = yCoordinate; }
	public void setCheckin(int checkin){ this.checkin = checkin; }
	public void setRestaurant(String restaurant){ this.restaurant = restaurant; }
	public void setSucursal(String sucursal){ this.sucursal = sucursal; }
	public void setThumbnail(String thumbnail){ this.thumbnail = thumbnail; }
	public void setAddress(String address){ this.address = address; }
	public void setPhone(String phone){ this.phone = phone; }
	public void setHours(String hours){ this.hours = hours; }
	public void setDays(String days){ this.days = days; }
	public void setImage(String image){ this.image = image; }
	public void setWebpage(String webpage){ this.webpage = webpage; }
	public void setTwitter(String twitter){ this.twitter = twitter; }
	public void setFacebook(String facebook){ this.facebook = facebook; }
	
	public int getId(){ return this.id;}
	public int getCategory(){ return this.category; }
	public int getXCoordinate(){ return this.xCoordinate; }
	public int getYCoordinate(){ return this.yCoordinate; }
	public int getCheckin(){ return this.checkin; }
	public String getRestaurant(){ return this.restaurant; }
	public String getSucursal(){ return this.sucursal; }
	public String getThumbnail(){ return this.thumbnail; }
	public String getAddress(){ return this.address; }
	public String getPhone(){ return this.phone; }
	public String getHours(){ return this.hours; }
	public String getDays(){ return this.days; }
	public String getImage(){ return this.image; }
	public String getWebpage(){ return this.webpage; }
	public String getTwitter(){ return this.twitter; }
	public String getFacebook(){ return this.facebook; }

	
	
	
}
