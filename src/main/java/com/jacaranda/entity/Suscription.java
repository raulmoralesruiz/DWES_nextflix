package com.jacaranda.entity;

public class Suscription {

	private int idSuscription;
	private static int idSiguiente = 0;
	private Enum tipo;  

	
	
	public Suscription() {
		super();
	}
	public Suscription(Enum tipo) {
		super();
		this.idSuscription = idSiguiente++;
		this.tipo = tipo;
	}
	
	
	
	public int getIdSuscription() {
		return idSuscription;
	}
	public void setIdSuscription(int idSuscription) {
		this.idSuscription = idSuscription;
	}
	public static int getIdSiguiente() {
		return idSiguiente;
	}
	public static void setIdSiguiente(int idSiguiente) {
		Suscription.idSiguiente = idSiguiente;
	}
	public Enum getTipo() {
		return tipo;
	}
	public void setTipo(Enum tipo) {
		this.tipo = tipo;
	}
	

	
}
