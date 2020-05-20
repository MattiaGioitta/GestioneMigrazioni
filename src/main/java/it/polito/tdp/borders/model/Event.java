package it.polito.tdp.borders.model;

public class Event implements Comparable<Event> {
	//Abbiamo un solo evento e quindi niente enum
	
	private int t;
	private Country stato; //stato in cui arrivano i migranti al tempo t
	private int n; //Il numero dei migranti che arrivano nello stato al tempo t
	// e a loro volta si sposterà la metà di essi
	/**
	 * @param t
	 * @param stato
	 * @param n
	 */
	public Event(int t, Country stato, int n) {
		super();
		this.t = t;
		this.stato = stato;
		this.n = n;
	}
	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}
	/**
	 * @param t the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}
	/**
	 * @return the stato
	 */
	public Country getStato() {
		return stato;
	}
	/**
	 * @param stato the stato to set
	 */
	public void setStato(Country stato) {
		this.stato = stato;
	}
	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}
	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	@Override
	public int compareTo(Event o) {
		return this.t-o.getT();
	}
	
	

}
