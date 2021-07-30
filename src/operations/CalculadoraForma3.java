package operations;

import polynomials.Nodo;
import polynomials.PolinomioForma3;

public class CalculadoraForma3 {
	private PolinomioForma3 polinomio1, polinomio2;
	
	public CalculadoraForma3 () {
		this.polinomio1 = null;
		this.polinomio2 = null;
	}
	public void showPolynomials() {
		this.polinomio1.mostrar();
		this.polinomio2.mostrar();
	}
	
	public void rebuild() {
	}
	
	public PolinomioForma3 add (PolinomioForma3 pol1, PolinomioForma3 pol2) {
		polinomio1 = pol1;
		polinomio2 = pol2;
		PolinomioForma3 res = new PolinomioForma3 ();
		Nodo p = polinomio1.getCab(), q = polinomio2.getCab();
		while (p != null) {
			res.llenarPol(p.getCoef(), p.getExp());
			p = p.getLiga();
		}
		while (q != null) {
			res.llenarPol(q.getCoef(), q.getExp());
			q = q.getLiga();
		}
		return res;
	}
	
	public PolinomioForma3 multiply (PolinomioForma3 pol1, PolinomioForma3 pol2) {
		polinomio1 = pol1;
		polinomio2 = pol2;
		PolinomioForma3 res = new PolinomioForma3 ();
		Nodo p = polinomio1.getCab(), q = polinomio2.getCab();
		while (p != null) {
			while(q != null) {
				res.llenarPol(q.getCoef() * p.getCoef(), q.getExp() + p.getExp());
				q = q.getLiga();
			}
			p = p.getLiga();
			q = polinomio2.getCab();
		}
		return res;
	}
}