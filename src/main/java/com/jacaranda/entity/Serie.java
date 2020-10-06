package com.jacaranda.entity;

public class Serie extends Product {

	private int idSerie;
	private static int idSiguiente = 0;
	private String title;	
	//idProducto heredado de Producto
	
	
	
	public Serie() {
		super();
	}
	public Serie(String title) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
	}

	
	
	
	public int getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}
	public static int getIdSiguiente() {
		return idSiguiente;
	}
	public static void setIdSiguiente(int idSiguiente) {
		Serie.idSiguiente = idSiguiente;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

	
}
