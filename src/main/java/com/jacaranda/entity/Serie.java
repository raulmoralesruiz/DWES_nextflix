package com.jacaranda.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Serie extends Product implements Serializable {

	private int idSerie;
	private static int idSiguiente = 0;
	private String title;
	private SuscriptionEnum tipoSuscripcion;
	private Category categoria;
	//idProducto heredado de Producto
	
	
	
	public Serie() {
		super();
	}
	public Serie(String title) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
	}
	public Serie(String title, SuscriptionEnum tipoSuscripcion) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
		this.tipoSuscripcion = tipoSuscripcion;
	}
	public Serie(String title, SuscriptionEnum tipoSuscripcion, Category categoria) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
		this.tipoSuscripcion = tipoSuscripcion;
		this.categoria = categoria;
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
	public SuscriptionEnum getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(SuscriptionEnum tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	public Category getCategoria() {
		return categoria;
	}
	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
	
	
	

	
}
