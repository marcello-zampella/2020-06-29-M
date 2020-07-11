package it.polito.tdp.imdb.model;

import java.util.Comparator;

public class ComparatorePeso implements Comparator {
	public int compare (Object o1, Object o2) {
		CollegamentoRegisti a1=(CollegamentoRegisti) o1;
		CollegamentoRegisti a2 =(CollegamentoRegisti) o2;
		if(a1.getPeso()>a2.getPeso()) { //COSI' ORDINE DECRESCENTE
			return -1;
		}
		if(a1.getPeso()<a2.getPeso()) {
			return 1;
		}
		return 0;
	}

}
