package com.jacaranda.entity;

public class Product {
	
	private int idProduct;
	private static int idSiguiente = 0;
	private Enum tipoSuscripcion;  

	
	
//	public Product() {
//		super();
//	}
//	public Product(Enum tipoSuscripcion) {
//		super();
//		this.idProduct = idSiguiente++;
//		this.tipoSuscripcion = tipoSuscripcion;
//	}
	public Product() {
		super();
		this.idProduct = idSiguiente++;
	}
	public Product(Enum tipoSuscripcion) {
	this();
	this.tipoSuscripcion = tipoSuscripcion;
}

	
	
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public static int getIdSiguiente() {
		return idSiguiente;
	}
	public static void setIdSiguiente(int idSiguiente) {
		Product.idSiguiente = idSiguiente;
	}
	public Enum getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(Enum tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	

	
	
}
