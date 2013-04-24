package com.example.wheretoeat;

public class MenuClass {

	int id;
	String imagen;
	String precio;
	String nombre;
	int restaurante;
	
	
	/**
	 * Constructor vacio de la clase MenuClass
	 */
	public MenuClass (){
		
	}
	
	/**
	 * Constructor de la clase MenuClass para la vista principal
	 * @param id
	 * @param imagen
	 * @param precio
	 * @param nombre
	 * @param restaurante
	 * 
	 */
	public MenuClass(int id, String imagen, String precio, String nombre, int restaurante){
		this.id = id;
		this.imagen = imagen;
		this.precio = precio;
		this.nombre = nombre;
		this.restaurante = restaurante;
	}

	public void setId(int id){ this.id = id; }
	public void setImagen(String imagen){ this.imagen = imagen; }
	public void setPrecio(String precio){ this.precio = precio; }
	public void setNombre(String nombre){ this.nombre = nombre; }
	public void setRestaurante(int restaurante){ this.restaurante = restaurante; }

	
	public int getId(){ return this.id;}
	public String getImagen(){ return this.imagen; }
	public String getPrecio(){ return this.precio; }
	public String getNombre(){ return this.nombre; }	
	public int getRestaurante(){ return this.restaurante; }
	
	
}
