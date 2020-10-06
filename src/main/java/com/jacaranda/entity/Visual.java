package com.jacaranda.entity;

import java.time.LocalDate;

public class Visual {

	
	private int idVisual;
	private static int idSiguiente = 0;
	private LocalDate inicio;
	private LocalDate fin;
	private int idProduct;

	
	
	public Visual() {
		super();
	}
	public Visual(LocalDate inicio, LocalDate fin, int idProduct) {
		super();
		this.idVisual = idSiguiente++;
		this.inicio = inicio;
		this.fin = fin;
		this.idProduct = idProduct;
	}
	
	
	

	
}
