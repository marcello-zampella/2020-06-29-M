package it.polito.tdp.imdb.model;

public class CollegamentoRegisti {
	
	private int id1;
	private int id2;
	private int peso;
	public CollegamentoRegisti(int id1, int id2, int peso) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso=peso;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	@Override
	public String toString() {
		return "CollegamentoRegisti [id1=" + id1 + ", id2=" + id2 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id1;
		result = prime * result + id2;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollegamentoRegisti other = (CollegamentoRegisti) obj;
		if (id1 != other.id1)
			return false;
		if (id2 != other.id2)
			return false;
		return true;
	}
	
	

}
