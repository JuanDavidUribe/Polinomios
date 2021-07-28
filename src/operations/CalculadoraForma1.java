package operations;

import java.util.ArrayList;
import java.util.List;

import polynomials.PolinomioForma1;

public class CalculadoraForma1 {
	private PolinomioForma1 polinomio1;
	private PolinomioForma1 polinomio2;

	public CalculadoraForma1(char[] polinomioC1, char[] polinomioC2) {
		this.polinomio1 = new PolinomioForma1(polinomioC1);
		this.polinomio2 = new PolinomioForma1(polinomioC2);
	}

	public PolinomioForma1 getPolinomio1() {
		return polinomio1;
	}

	public void setPolinomio1(PolinomioForma1 polinomio1) {
		this.polinomio1 = polinomio1;
	}

	public PolinomioForma1 getPolinomio2() {
		return polinomio2;
	}

	public void setPolinomio2(PolinomioForma1 polinomio2) {
		this.polinomio2 = polinomio2;
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
		PolinomioForma1 poliAdd = new PolinomioForma1(poliSuma);
		if (poliAdd.validate()) {
			poliAdd.show();
		}
		poliSuma = null;
		poliAdd = null;
	}

	public void multiply() {
		int grade = (this.polinomio1.getGrado() > this.polinomio2.getGrado()) ? this.polinomio1.getGrado()
				: this.polinomio2.getGrado();
		int w = 0, vectorSize = ((this.polinomio1.getnData() * this.polinomio2.getnData()));
		String[] vec = new String[(vectorSize + 1)];
		for (short h = 0; h < vec.length; h++) {
			vec[h] = "";
		}
		for (int i = 1; i < this.polinomio1.getPolynomial().length; i++) {
			for (int j = 1; j < this.polinomio2.getPolynomial().length; j++) {
				if ((this.polinomio1.getPolynomial()[i] != 0) && (this.polinomio2.getPolynomial()[j] != 0)) {
					vec[w] = String.valueOf(this.polinomio1.getPolynomial()[i] * this.polinomio2.getPolynomial()[j]);
					w++;
					vec[w] = String.valueOf(this.polinomio1.getExponent(i) + this.polinomio2.getExponent(j));
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

	public void divide() {
		List<Integer> list = new ArrayList<Integer>();
		int[] poli1 = simplify(polinomio1).clone(), poli2 = simplify(polinomio2).clone();
		int[] dividendo, divisor, cociente, res;
		int op = 0, k = 0;
		boolean terminado = false, encontrado = false;

		if (poli1.length > poli2.length) {
			dividendo = poli1.clone();
			divisor = poli2.clone();
		} else {
			dividendo = poli2.clone();
			divisor = poli1.clone();
		}
		cociente = new int[dividendo.length];
		res = new int[dividendo.length];
		for(short i = 0; i < dividendo.length; i += 2) {
			for(short j = 0; j < divisor.length; j++) {
				if(dividendo[i] < divisor[j]) {
					cociente[k] = 1;
					k++;
					cociente[k] = getexpoente(dividendo[i + 1], divisor[j + 1]);
					res[op] = (dividendo[i]);
				}
			}
		}
	}

	/**
	 * @param polinomio Es el polinomio que simplificaremos
	 * @return Se devolverá el polinomio el mismo polinomio per sin datos basura
	 */
	private int[] simplify(PolinomioForma1 polinomio) {
		List<Integer> poli = new ArrayList<Integer>();
		int[] myPoli;
		for (short i = 1; i < polinomio.getPolynomial().length; i++) {
			if (polinomio.getPolynomial()[i] != 0) {
				poli.add(polinomio.getPolynomial()[i]);
				poli.add(polinomio.getExponent(i));
			}
		}
		myPoli = new int[poli.size()];
		myPoli = poli.stream().mapToInt(i -> i).toArray(); // Para poder devolver el dato primitivo int y no integer
		return myPoli;

	}

	private int getexpoente(int eDividendo, int eDivisor) {
		short n = 1;
		if(eDividendo == eDivisor) {
			return 0;
		}  else {
			for(n = 1; n < eDividendo; n++) {
				if(eDivisor + n == eDividendo) {
					break;
				}
			}
		}
		return n;
	}
	public void cleanPolynomial() {
		this.polinomio1 = null;
		this.polinomio2 = null;
	}
}