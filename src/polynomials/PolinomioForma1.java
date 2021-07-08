package polynomials;

import javax.swing.JOptionPane;

import superPoli.PolinomioPadre;

public class PolinomioForma1 extends PolinomioPadre {

	private int grado;
	
	public PolinomioForma1(char[] polinomio) {
		this.poli = polinomio.clone();
		if (this.poli.length == 1) {
			this.polinomioFinal = new String[2]; // Si se ingresa un 5, quedará [5][0]
		} else {
			this.polinomioFinal = new String[this.poli.length];
		}
		for (int i = 0; i < polinomioFinal.length; i++) {
			this.polinomioFinal[i] = "";
		}
		this.polinomioDefinitivo = new int[(this.polinomioFinal.length)];
		this.build();
	}

	public PolinomioForma1(String[] vec) {
		this.polinomioFinal = vec.clone();
		this.polinomioDefinitivo = new int[this.polinomioFinal.length];
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	@Override
	public void order() {
		super.order();
		if (this.polinomioDefinitivo[1] == 0) {
			this.grado = 0;
			this.polynomial = new int[2];
		} else {
			this.grado = this.polinomioDefinitivo[1];
			this.polynomial = new int[(this.grado + 2)];
		}
		this.polynomialForm1();
	}

	public void reconstruct() {
		String s = "";
		for (int i = 0; i < this.poli.length; i++) {
			if (Character.isDigit(this.poli[i])) {
				s += (" | " + String.valueOf(this.poli[i]));
			}
			if (this.poli[i] == 'X') {
				try {
					if (this.poli[(i - 1)] == '-') {
						s += " | -1";
					}
					if (this.poli[(i - 1)] == '+') {
						s += " | 1";
					}
				} catch (Exception e) {
					s += " | 1";
				}
			}
		}
		JOptionPane.showMessageDialog(null, s);
	}

	public void polynomialForm1() {
		int i = 1, j = 0, numSearched = this.grado;
		boolean found = false;
		this.polynomial[j] = this.grado;
		j++;
		try {
			while (i < polinomioDefinitivo.length) {
				if (this.polinomioDefinitivo[i] == numSearched) {
					this.polynomial[j] = this.polinomioDefinitivo[(i - 1)];
					found = true;
				} else {
					this.polynomial[j] = 0;
				}
				if (j == (this.grado + 1)) {
					break;
				}
				j++;
				numSearched--;
				if (found) {
					i += 2;
					found = false;
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void show() {
		int expo = 0;
		String SP = "";
		for (int k = 1; k < this.polynomial.length; k++) {
			if (this.polynomial[k] != 0) {
				if (k != 1) {
					if (this.polynomial[k] >= 0) {
						SP += "+";
					}
				}
				expo = exponent(k);
				if (this.polynomial[k] != 1) {
					if (this.polynomial[k] == -1) {
						SP += "-";
					} else {
						SP += String.valueOf(this.polynomial[k]);
					}
				}
				if (expo == 1) {
					SP += "X";
				}
				if (expo > 1) {
					SP += ("X^" + exponent(k));
				}
			}
		}
		JOptionPane.showMessageDialog(null,"\nPolinomio :      "+SP);
	}

	@Override
	public int exponent(int n) {
		int DU = (this.polynomial.length - 1);
		if (this.grado == 0) {
			return 0;
		} else {
			return (DU - n);
		}
	}
}