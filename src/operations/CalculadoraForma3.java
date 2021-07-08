package operations;

import polynomials.PolinomioForma3;

public class CalculadoraForma3 {
	private PolinomioForma3 polinomio1;
	private PolinomioForma3 polinomio2;

	public CalculadoraForma3(char[] polinomioC1, char[] polinomioC2) {
		this.polinomio1 = new PolinomioForma3(polinomioC1);
		this.polinomio2 = new PolinomioForma3(polinomioC2);
	}

	public void showPolynomials() {
		this.polinomio1.show();
		System.out.println("\n");
		this.polinomio2.show();
	}

	public boolean validatePolynomials() {
		if (!this.polinomio1.validate() || !this.polinomio2.validate()) {
			return false;
		} else {
			return true;
		}
	}

	public void rebuild() {
		this.polinomio1.reconstruct();
		this.polinomio2.reconstruct();
	}

	public void add() {
		//Agregar
	}

	public void multiply() {
		//Agregar
	}

	public void cleanPolynomial() {
		this.polinomio1 = null;
		this.polinomio2 = null;
	}
}