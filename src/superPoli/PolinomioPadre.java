package superPoli;

public abstract class PolinomioPadre {
	protected int[] polinomioDefinitivo;
	protected int[] polynomial;
	protected String[] polinomioFinal;
	protected char[] poli;
	protected int nData = 0;
	
	public char[] getPoli() {
		return poli;
	}

	public void setPoli(char[] poli) {
		this.poli = poli;
	}

	public int[] getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(int[] polynomial) {
		this.polynomial = polynomial;
	}

	public int getnData() {
		return nData;
	}

	public void setnData(int nData) {
		this.nData = nData;
	}

	public String[] getPolinomioFinal() {
		return polinomioFinal;
	}

	public void setPolinomioFinal(String[] vec) {
		this.polinomioFinal = vec;
	}

	public int[] getPolinomioDefinitivo() {
		return polinomioDefinitivo;
	}

	public void setPolinomioDefinitivo(int[] polinomioDefinitivo) {
		this.polinomioDefinitivo = polinomioDefinitivo;
	}
	
	public void build() {
		String s = "";
		int j = 0;
		for (int i = 0; i < this.poli.length; i++) {
			if ((this.poli.length == 1) && Character.isDigit(this.poli[i])) {
				this.polinomioFinal[j] = Character.toString(this.poli[i]);
				j++;
				this.polinomioFinal[j] = "0";
				break;
			}
			if (this.poli[i] == '-' || this.poli[i] == '+') {
				if (!s.equals("")) {
					this.polinomioFinal[j] = s;
					j++;
					s = "";
					this.polinomioFinal[j] = "0";
					j++;
				}
			}
			if (this.poli[i] == '-' || Character.isDigit(this.poli[i])) {
				s += poli[i];
			}
			if (this.poli[i] == 'X' && s.equals("")) {
				this.polinomioFinal[j] = "1";
				j++;
			} else {
				if (this.poli[i] == 'X' && s.equals("-")) {
					this.polinomioFinal[j] = "-1";
					s = "";
					j++;
				} else {
					if (this.poli[i] == 'X') {
						if (s.equals("-0")) {
							s = "0";
						}
						this.polinomioFinal[j] = s;
						s = "";
						j++;
					}
				}
			}
			try {
				if (this.poli[i] == 'X' && this.poli[i + 1] != '^') {
					this.polinomioFinal[j] = "1";
					j++;
					i++;
				}
			} catch (Exception e) {
				this.polinomioFinal[j] = "1";
				j++;
				i++;
				break;
			}
			if (this.poli[i] == '-' && s.equals("")) {
				s += "-";
			}
			if (this.poli[i] == '^') {
				this.polinomioFinal[j] = Character.toString(this.poli[i + 1]);
				j++;
				i++;
			}
			if ((i == (this.poli.length - 1)) && (!s.equals(""))) {
				if (s.equals("-0")) {
					s = "0";
				}
				this.polinomioFinal[j] = s;
				j++;
				this.polinomioFinal[j] = "0";
			}
		}
	}
	
	public void order() {
		int maxExpo = 0, auxExpo = 0, maxCoe = 0, auxCoe = 0, coeNoZero = 0, zero = 0;
		boolean finish = true, firstZero = false; // Soluciona el error de cambio de posiciones

		for (int i = 1; i <= this.nData; i += 2) {
			maxExpo = i;
			for (int j = i + 2; j <= this.nData; j += 2) {
				if (this.polinomioDefinitivo[j] > this.polinomioDefinitivo[maxExpo]) {
					maxExpo = j;
					maxCoe = (j - 1);
					finish = false;
				}
			}
			if (!finish) {
				auxExpo = this.polinomioDefinitivo[i];
				auxCoe = this.polinomioDefinitivo[i - 1];
				this.polinomioDefinitivo[i] = this.polinomioDefinitivo[maxExpo];
				this.polinomioDefinitivo[i - 1] = this.polinomioDefinitivo[maxCoe];
				this.polinomioDefinitivo[maxExpo] = auxExpo;
				this.polinomioDefinitivo[maxCoe] = auxCoe;
				auxCoe = 0;
				auxExpo = 0;
				finish = true;
			}
		}
		for (int w = 1; w < this.polinomioDefinitivo.length; w += 2) {
			if (!firstZero) {
				if ((this.polinomioDefinitivo[w] == 0) && (this.polinomioDefinitivo[(w - 1)] == 0)) {
					zero = (w - 1);
					firstZero = true;
				}
			}
			try {
				if ((this.polinomioDefinitivo[w] == 0) && (this.polinomioDefinitivo[(w - 1)] != 0)) {
					coeNoZero = (w - 1);
					if (firstZero) {
						this.polinomioDefinitivo[zero] = this.polinomioDefinitivo[coeNoZero];
					}
					break;
				}
			} catch (Exception e) {
				break;
			}
		}
		for (int k = 0; k < this.nData; k++) { // Modificamos el vector de String para mostrar con mas facilidad
			this.polinomioFinal[k] = String.valueOf(this.polinomioDefinitivo[k]);
		}
	}

	public void simplify() {
		int s = 0;
		int[] posEliminar;
		// int[] vecIgual = this.polinomioDefinitivo.clone();
		int n = 0;
		boolean cambiar = false;

		for (int i = 1; i <= this.nData; i += 2) {
			posEliminar = new int[nData];
			for (int j = (i + 2); j <= this.nData; j += 2) {
				if (this.polinomioDefinitivo[j] == this.polinomioDefinitivo[i]) {
					if (n == 0) {
						s += this.polinomioDefinitivo[i - 1];
					}
					s += this.polinomioDefinitivo[j - 1];
					posEliminar[n] = j;
					n++;
					cambiar = true;
				}

			}
			if (cambiar) {
				this.polinomioDefinitivo[i - 1] = s;
				for (int k = 0; k < n; k++) {
					this.polinomioDefinitivo[posEliminar[k]] = 0;
					this.polinomioDefinitivo[(posEliminar[k]) - 1] = 0;
				}
				s = 0;
				n = 0;
				posEliminar = null;
				cambiar = false;
			}
		}
		order();
	}

	public boolean validate() {
		try {
			for (int i = 0; i < this.polinomioFinal.length; i++) {
				if (this.polinomioFinal[i].equals("")) {
					break;
				}
				this.polinomioDefinitivo[i] = Integer.parseInt(this.polinomioFinal[i]); // Este lo usaremos para hacer
																						// los procedimientos
				this.nData = (i + 1);
			}
			simplify();
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
	public abstract void show();
	
	/**
	 * @param n Es la posicion del coeficiente que le mandaremos
	 * @return El valor del exponente
	 */
	public abstract int exponent(int n);
}
