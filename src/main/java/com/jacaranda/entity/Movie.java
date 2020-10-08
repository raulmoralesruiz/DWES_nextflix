package com.jacaranda.entity;

import java.io.Serializable;

public class Movie extends Product implements Serializable {

	private int idMovie;
	private static int idSiguiente = 0;
	private String title;
	private Enum tipoSuscripcion;
	//idProducto heredado de Producto
	
	
	
	public Movie() {
		super();
	}
	public Movie(String title) {
		super();
		this.idMovie = idSiguiente++;
		this.title = title;
	}
	public Movie(String title, Enum tipoSuscripcion) {
		super();
		this.idMovie = idSiguiente++;
		this.title = title;
		this.tipoSuscripcion = tipoSuscripcion;
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
	public Enum getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(Enum tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}

	
	
}
