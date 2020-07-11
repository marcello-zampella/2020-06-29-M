package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	ImdbDAO dao;
	SimpleWeightedGraph<Director, DefaultWeightedEdge> grafo;
	ArrayList<CollegamentoRegisti> collegamenti;
	ArrayList<Director> registi;
	
	public Model() {
		dao= new ImdbDAO();
	}

	public ArrayList<Director> creaGrafo(int anno) {
		registi=dao.getAllRegistiByAnno(anno);
		grafo= new SimpleWeightedGraph<Director, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, registi);
		collegamenti=dao.getAllCollegamenti(anno);
		for(CollegamentoRegisti coll:collegamenti) {
			Graphs.addEdge(grafo, new Director(coll.getId1(),null,null),new Director(coll.getId2(),null,null), coll.getPeso());
		}
		return registi;
	}

	public LinkedList<Director> cercaRegistiAdiacenti(Director s) {
		LinkedList<Director> scelti=new LinkedList<Director>();
		Collections.sort(collegamenti, new ComparatorePeso());
		for(CollegamentoRegisti cr: collegamenti) {
			if(s.getId()==cr.getId1() || s.getId()==cr.getId2()) {
				if(s.getId()!=cr.getId1()) {
					scelti.add(registi.get(registi.indexOf(new Director(cr.getId1(),null,null))));
				} else {
					scelti.add(registi.get(registi.indexOf(new Director(cr.getId2(),null,null))));

				}
			}
		}
		return scelti;
	}
	
	ArrayList<Director> migliore;
	int max;
	int limite;

	public void cercaAttoriCondivisi(int numero, Director director) {
		ArrayList<Director> parziale=new ArrayList<Director>();
		migliore=null;
		max=0;
		int livello=0; //numero attori condivisi inserito
		limite=numero;
		parziale.add(director);
		espandi(livello,parziale,director);
		
		
	}

	private void espandi(int livello, ArrayList<Director> parziale, Director director) {
		if(livello>limite) {
			//condizione di terminazione
			return;
		}
		
		if(livello>max) {
			max=livello;
			migliore=new ArrayList<Director>(parziale);
		}
		
		
		ArrayList<Director> possibili=new ArrayList<Director>(this.cercaRegistiAdiacenti(director));
		for(Director r: possibili) {
			if(!parziale.contains(r)) {
			DefaultWeightedEdge e= grafo.getEdge(director, r);
			int peso=(int) grafo.getEdgeWeight(e);
			parziale.add(r);
			espandi(livello+peso,parziale,r);
			parziale.remove(r);
			}
		}
		
		
		
	}

	public ArrayList<Director> getMigliore() {
		return migliore;
	}

	public SimpleWeightedGraph<Director, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	
	
	

}
