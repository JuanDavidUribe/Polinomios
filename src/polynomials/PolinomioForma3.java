package polynomials;

import superPoli.PolinomioPadre;

public class PolinomioForma3 extends PolinomioPadre {

	private int nTerminos;

	public PolinomioForma3(char[] polinomio) {
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

	public PolinomioForma3(String[] vec) {
		this.polinomioFinal = vec.clone();
		this.polinomioDefinitivo = new int[this.polinomioFinal.length];
	}

	public void reconstruct() {
		//Agregar contenido
	}

	public void polynomialForm3() {
		//Agregar contenido
	}

	@Override
	public void order() {
		super.order();
		if (this.polinomioDefinitivo[1] == 0) {
			this.nTerminos = 1;
			this.polynomial = new int[2];
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
			this.polynomial = new int[(this.nTerminos)];
		}
		this.polynomialForm3();
	}

	@Override
	public void show() {
		// Agegar
	}

	@Override
	public int getExponent(int n) {
		// Agregar
		return (Integer) null;
	}
}