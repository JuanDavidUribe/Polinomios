package operations;

import javax.swing.JOptionPane;

import polynomials.Nodo;
import polynomials.PolinomioForma3;

public class CalculadoraForma3 {
	private PolinomioForma3 polinomio1, polinomio2;
	
	public CalculadoraForma3 (PolinomioForma3 pol1, PolinomioForma3 pol2) {
		this.polinomio1 = pol1;
		this.polinomio2 = pol2;
	}
	public void showPolynomials() {
		this.polinomio1.mostrar();
		this.polinomio2.mostrar();
	}
	
	public void rebuild() {
		String s = "", s2 = "";
		Nodo p = polinomio1.getCab(), q = polinomio2.getCab();
		while (p != null) {
			s += "|" + p.getCoef() + "|" + p.getExp();
		}
		JOptionPane.showMessageDialog(null, s);
		
		while (q != null) {
			s += "|" + q.getCoef() + "|" + q.getExp();
		}
		JOptionPane.showMessageDialog(null, s2);

	}
	
	public PolinomioForma3 add () {
		PolinomioForma3 res = new PolinomioForma3 ();
		Nodo p = this.polinomio1.getCab(), q = this.polinomio2.getCab();
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
	
	public PolinomioForma3 multiply () {
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