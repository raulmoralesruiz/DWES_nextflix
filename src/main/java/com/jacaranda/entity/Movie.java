package com.jacaranda.entity;

public class Movie extends Product {

	private int idMovie;
	private static int idSiguiente = 0;
	private String title;
	//idProducto heredado de Producto
	
	
	
	public Movie() {
		super();
	}
	public Movie(String title, int idProducto) {
		super();
		this.idMovie = idSiguiente++;
		this.title = title;
	}
	
	
	
	
	public int getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	public static int getIdSiguiente() {
		return idSiguiente;
	}
	public static void setIdSiguiente(int idSiguiente) {
		Movie.idSiguiente = idSiguiente;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
