package operations;

import polynomials.PolinomioForma1;

public class CalculadoraForma2 {
	private PolinomioForma1 polinomio1;
	private PolinomioForma1 polinomio2;

	public CalculadoraForma2(char[] polinomioC1, char[] polinomioC2) {
		this.polinomio1 = new PolinomioForma1(polinomioC1);
		this.polinomio2 = new PolinomioForma1(polinomioC2);
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
		char[] poliSuma;

		if (this.polinomio2.getPoli()[0] != '-') {
			poliSuma = new char[this.polinomio1.getPoli().length + this.polinomio2.getPoli().length + 1];
			System.arraycopy(this.polinomio1.getPoli(), 0, poliSuma, 0, this.polinomio1.getPoli().length);
			poliSuma[this.polinomio1.getPoli().length] = '+';
			System.arraycopy(this.polinomio2.getPoli(), 0, poliSuma, (this.polinomio1.getPoli().length + 1),
					this.polinomio2.getPoli().length);
		} else {
			poliSuma = new char[this.polinomio1.getPoli().length + this.polinomio2.getPoli().length];
			System.arraycopy(this.polinomio1.getPoli(), 0, poliSuma, 0, this.polinomio1.getPoli().length);
			System.arraycopy(this.polinomio2.getPoli(), 0, poliSuma, this.polinomio1.getPoli().length,
					this.polinomio2.getPoli().length);
		}
		PolinomioForma1 poliAdd = new PolinomioForma1(poliSuma);
		if (poliAdd.validate()) {
			poliAdd.show();
		}
		poliSuma = null;
		poliAdd = null;
	}

	public void multiply() {
		int vectorSize = 0, w = 0;
		int grade = (this.polinomio1.getGrado() > this.polinomio2.getGrado()) ? this.polinomio1.getGrado()
				: this.polinomio2.getGrado();
		String[] vec;
		vectorSize = ((this.polinomio1.getnData() * this.polinomio2.getnData()));
		vec = new String[(vectorSize + 1)];
		for(int h = 0; h < vec.length; h++) {
			vec[h] = "";
		}
		for (int i = 1; i < this.polinomio1.getPolynomial().length; i++) {
			for (int j = 1; j < this.polinomio2.getPolynomial().length; j++) {
				if((this.polinomio1.getPolynomial()[i] != 0) && (this.polinomio2.getPolynomial()[j] != 0)) {					
					vec[w] = String.valueOf(this.polinomio1.getPolynomial()[i] * this.polinomio2.getPolynomial()[j]);
					w++;
					vec[w] = String.valueOf(this.polinomio1.exponent(i) + this.polinomio2.exponent(j));
					w++;
				}
			}
		}
		PolinomioForma1 poliMult = new PolinomioForma1(vec);
		if (poliMult.validate()) {
			poliMult.show();
		}
		vec = null;
		poliMult = null;
	}

	public void cleanPolynomial() {
		this.polinomio1 = null;
		this.polinomio2 = null;
	}
}