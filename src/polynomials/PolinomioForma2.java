package polynomials;

import javax.swing.JOptionPane;

import superPoli.PolinomioPadre;

public class PolinomioForma2 extends PolinomioPadre {

	private int nTerminos;

	public PolinomioForma2(char[] polinomio) {
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
		this.nTerminos = 0;
		this.build();
	}

	public PolinomioForma2(String[] vec) {
		this.polinomioFinal = vec.clone();
		this.polinomioDefinitivo = new int[this.polinomioFinal.length];
	}
	
	
	public int getnTerminos() {
		return nTerminos;
	}

	public void setnTerminos(int nTerminos) {
		this.nTerminos = nTerminos;
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

	public void polynomialForm2() {
		short j = 0;
		this.polynomial[j] = (this.nTerminos == 1) ? (1) : (this.nTerminos / 2);
		j++;
		for (int i = 1; i < this.nTerminos; i += 2) {
			this.polynomial[j] = this.polinomioDefinitivo[i];
			j++;
			this.polynomial[j] = this.polinomioDefinitivo[(i - 1)];
			j++;
		}
	}

	@Override
	public void order() {
		super.order();
		if (this.polinomioDefinitivo[1] == 0) {
			this.nTerminos = 2;
			this.polynomial = new int[3];
		} else {
			for (int i = 1; i < this.polinomioDefinitivo.length; i += 2) {
				if (this.polinomioDefinitivo[i] != 0) {
					this.nTerminos += 2;
					continue;
				} else if (this.polinomioDefinitivo[i] == 0 && this.polinomioDefinitivo[i - 1] != 0) {
					this.nTerminos += 2;
					break;
				} else {
					break;
				}
			}
			this.polynomial = new int[(this.nTerminos + 1)];
		}
		this.polynomialForm2();
	}

	@Override
	public void show() {
		int expo = 0;
		String message = "";
		for (int i = 2; i <= this.polynomial.length; i += 2) {
			if (this.polynomial[i] != 0) {
				expo = getExponent(i);
				if (i != 2) {
					if (this.polynomial[i] > 0) {
						message += "+";
					}
				}
				if (this.polynomial[i] != 0) {
					message += String.valueOf(this.polynomial[i]);
				}
				if (expo == 1) {
					message += "X";
				}
				if (expo > 1) {
					message += "X^" + String.valueOf(expo);
				}
			}
		}
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public int getExponent(int n) {
		return this.polynomial[n - 1];
	}
}