package polynomials;

public class Nodo {
	private int coef;
	private int exp;
	private Nodo liga;
	
	public Nodo () {
		coef = 0;      
		exp =0;
		liga = null;
	}
	
	public Nodo (int coef1, int exp1) {
		coef = coef1;
		exp = exp1;
		liga = null;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setLiga(Nodo liga) {
		this.liga = liga;
	}

	public Nodo getLiga() {
		return liga;
	}

}
