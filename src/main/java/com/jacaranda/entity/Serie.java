package com.jacaranda.entity;

import java.io.Serializable;

public class Serie extends Product implements Serializable {

	private int idSerie;
	private static int idSiguiente = 0;
	private String title;
	private Enum tipoSuscripcion;
	//idProducto heredado de Producto
	
	
	
	public Serie() {
		super();
	}
	public Serie(String title) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
	}
	public Serie(String title, Enum tipoSuscripcion) {
		super();
		this.idSerie = idSiguiente++;
		this.title = title;
		this.tipoSuscripcion = tipoSuscripcion;
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
	public Enum getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(Enum tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	
	
	

	
}
