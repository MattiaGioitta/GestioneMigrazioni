package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {
	//Modello --> stato del sistema ad ogni passo
	private Graph<Country, DefaultEdge> grafo;
	
	
	//Tipo di evento --> coda prioritaria
	private PriorityQueue<Event> queue;
	
	
	//Parametri della simulazione
	private int nMigranti = 1000;
	private Country partenza;
	
	
	//I valori di output
	private int T = -1;
	Map<Country, Integer> stanziali;
	
	public void init(Country partenza, Graph<Country,DefaultEdge> grafo) {
		this.partenza = partenza;
		this.grafo = grafo;
		
		//impostazione dello stato iniziale
		this.T = 1;
		this.stanziali = new HashMap<>();
		for(Country c : this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		this.queue = new PriorityQueue<>();
		//Inserisco il primo evento
		this.queue.add(new Event(this.T,partenza,this.nMigranti));
				
	}
	public void run() {
		//Finchè c'è un evento nella coda lo estraggo e lo eseguo
		Event e;
		while((e = this.queue.poll())!= null) {
			this.T = e.getT();
			//Eseguo l'evento e
			//di solito switch case soprattutto con i diversi tipi
			//non in questo caso
			int nPersone = e.getN();
			Country stato = e.getStato();
			//cerco i vicini di stato
			List<Country> vicini = Graphs.neighborListOf(this.grafo, stato);
			
			int migranti = (nPersone/2) / vicini.size();//arrotondamento per difetto in automatico
			
			if(migranti > 0) {
				//People can move
				for(Country confinante : vicini) {
					queue.add(new Event(e.getT()+1,confinante,migranti));
				}
			}
			
			int stanziali = nPersone - (migranti*vicini.size());
			this.stanziali.put(stato, this.stanziali.get(stato)+stanziali);
			
			
		}
	}
	
	public Map<Country,Integer> getStanziali(){
		return this.stanziali;
	}
	public int getT() {
		return this.T;
	}

}
