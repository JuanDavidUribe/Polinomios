package operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;

import polynomials.PolinomioForma1;

public class CalculadoraForma1 {
	private PolinomioForma1 polinomio1;
	private PolinomioForma1 polinomio2;
	private int [] vec;

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
		int w = 0, vectorSize = ((this.polinomio1.getnData() * this.polinomio2.getnData()));
		String[] vec = new String[(vectorSize + 1)];
		for (short h = 0; h < vec.length; h++) {
			vec[h] = "";
		}
		for (int i = 1; i < this.polinomio1.getPolynomial().length; i++) {
			for (int j = 1; j < this.polinomio2.getPolynomial().length; j++) {
				if ((this.polinomio1.getData(i) != 0) && (this.polinomio2.getData(j) != 0)) {
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
        int coet, coea;
        int expt, posa, post, expa;
        List<String> vec = new ArrayList<String>();
        if (this.polinomio1.getData(0) >= this.polinomio2.getData(0)) {
            int a[] = new int[this.polinomio1.getData(0) + 2];
            int g[] = new int[this.polinomio1.getData(0) - this.polinomio2.getData(0) + 2];
            g[0] = this.polinomio1.getData(0) - this.polinomio2.getData(0);
            a = this.polinomio1.getPolynomial().clone();
            while(a[0] >= this.polinomio2.getData(0)) {
                expt = (int) (a[0] - this.polinomio2.getData(0));
                coet = a[1] / this.polinomio2.getData(1);
                post = (int) (g[0] + 1 - expt);
                g[post] = coet;
                for (int i = 1; i < this.polinomio2.getData(0) + 2; i++) {
                    expa = this.polinomio2.getData(0) + 1 - i + expt;
                    coea = this.polinomio2.getData(i) * coet;
                    posa = (int) (a[0] + 1 - expa);
                    a[posa] = a[posa] - coea;
                }
                a = ajustar(a);
            }
            vec.add(String.valueOf(this.polinomio1.getData(0) - this.polinomio2.getData(0)));
            for (int i = 1; i < g.length; i++) {
                vec.add(String.valueOf(g[i]));
            }
            int [] myVec = new int[vec.size()]; 
            for(int j = 0; j < vec.size(); j++) {
            	myVec[j] = Integer.parseInt(vec.get(j));
            }
            PolinomioForma1 c = new PolinomioForma1(myVec);
            c.show();
            vec = null;
    		c = null;
        } else {
        	JOptionPane.showMessageDialog(null, "No se ah podido realizar la division. Intenta cambiar de lugar los valores");
        }
	}

	private int[] ajustar(int vec[]) {
        int cont = 0, i;
        if (vec[1] == 0) {
            i = 1;
            while (i < vec[0] + 2 && vec[i] == 0) {
                cont = cont + 1;
                i = i + 1;
            }
            for (int j = i; j < vec[0] + 2; j++) {
                vec[j - cont] = vec[j];
            }
            vec[0] = vec[0] - cont;
        }
        return vec;
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