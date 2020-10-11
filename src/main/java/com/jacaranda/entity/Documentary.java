package com.jacaranda.entity;

public class Documentary extends Product {

	private int idDocumentary;
	private static int idSiguiente = 0;
	private String title;
	//idProducto heredado de Producto
	
	
	
	public Documentary() {
		super();
	}
	public Documentary(String title, int idProducto) {
		super();
		this.idDocumentary = idSiguiente++;
		this.title = title;
	}
	
	
	
	public int getIdDocumentary() {
		return idDocumentary;
	}
	public void setIdDocumentary(int idDocumentary) {
		this.idDocumentary = idDocumentary;
	}
	public static int getIdSiguiente() {
		return idSiguiente;
	}
	public static void setIdSiguiente(int idSiguiente) {
		Documentary.idSiguiente = idSiguiente;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public int getIdMovie() {
		return -1;
	}
	@Override
	public int getIdSerie() {
		return -1;
	}
	
	

	
}
