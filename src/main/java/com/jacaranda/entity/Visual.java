package com.jacaranda.entity;

import java.time.LocalDateTime;

public class Visual {

	private int idVisual;
	private static int idSiguiente = 0;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private Product producto;
	
	
	public Visual() {
		super();
	}
	public Visual(LocalDateTime inicio, 
			LocalDateTime fin, Product producto) {
		super();
		this.idVisual = idSiguiente++;
		this.inicio = inicio;
		this.fin = fin;
		this.producto = producto;
	}
	
	
	
	public int getIdVisual() {
		return idVisual;
	}
	public void setIdVisual(int idVisual) {
		this.idVisual = idVisual;
	}
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public LocalDateTime getFin() {
		return fin;
	}
	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}
	public Product getProducto() {
		return producto;
	}
	public void setProducto(Product producto) {
		this.producto = producto;
	}
	
	
	
	

	
}
