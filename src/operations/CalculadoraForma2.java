package operations;

import polynomials.PolinomioForma2;

public class CalculadoraForma2 {
	private PolinomioForma2 polinomio1;
	private PolinomioForma2 polinomio2;

	public CalculadoraForma2(char[] polinomioC1, char[] polinomioC2) {
		this.polinomio1 = new PolinomioForma2(polinomioC1);
		this.polinomio2 = new PolinomioForma2(polinomioC2);
	}

	public void showPolynomials() {
		this.polinomio1.show();
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
		PolinomioForma2 poliAdd = new PolinomioForma2(poliSuma);
		if (poliAdd.validate()) {
			poliAdd.show();
		}
		poliSuma = null;
		poliAdd = null;
	}

	public void multiply() {
		int  w = 0, vectorSize = ((this.polinomio1.getnTerminos() * this.polinomio2.getnTerminos()));
		String[] vec = new String[vectorSize];
		for(short h = 0; h < vec.length; h++) {
			vec[h] = "";
		}
		for (int i = 2; i < this.polinomio1.getPolynomial().length; i+=2) {
			for (int j = 2; j < this.polinomio2.getPolynomial().length; j+= 2) {
				try {
					if((this.polinomio1.getPolynomial()[i] != 0) && (this.polinomio2.getPolynomial()[j] != 0)) {					
						vec[w] = String.valueOf(this.polinomio1.getPolynomial()[i] * this.polinomio2.getPolynomial()[j]);
						w++;
						vec[w] = String.valueOf(this.polinomio1.getPolynomial()[i - 1] + this.polinomio2.getPolynomial()[j - 1]);
						w++;
					}					
				} catch (Exception e) {
					break;
				}
			}
		}
		PolinomioForma2 poliMult = new PolinomioForma2(vec);
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