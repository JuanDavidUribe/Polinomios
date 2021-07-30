package run;

import javax.swing.JOptionPane;

import operations.*;
import polynomials.PolinomioForma1;
import polynomials.PolinomioForma3;

public class Menu {

	private static boolean ingresado;
	private static boolean modificar;
	private CalculadoraForma1 calcularF1;
	private CalculadoraForma2 calcularF2;
	private CalculadoraForma3 calcularF3;
	private PolinomioForma3 poli1;
	private PolinomioForma3 poli2;
	private PolinomioForma3 resF3;

	public Menu() {
		Menu.ingresado = false;
		Menu.modificar = false;
		poli1 = new PolinomioForma3();
		poli2 = new PolinomioForma3();
	}

	public static boolean isIngresado() {
		return ingresado;
	}

	public static void setIngresado(boolean ingresado) {
		Menu.ingresado = ingresado;
	}

	public static boolean isModificar() {
		return modificar;
	}

	public static void setModificar(boolean modificar) {
		Menu.modificar = modificar;
	}

	public CalculadoraForma1 getCalcularF1() {
		return calcularF1;
	}

	public void setCalcularF1(CalculadoraForma1 calcularF1) {
		this.calcularF1 = calcularF1;
	}

	public CalculadoraForma2 getCalcularF2() {
		return calcularF2;
	}

	public void setCalcularF2(CalculadoraForma2 calcularF2) {
		this.calcularF2 = calcularF2;
	}

	public void init() {
		short des = 0, desForm = 0;
		char desCambiar = 'N';
		boolean salir = false, exit = false;

		do {
			try {
				Menu.ingresado = false;
				this.calcularF1 = null;
				this.calcularF2 = null;
				this.calcularF3 = null;
				this.poli1 = null; 
				this.poli2 = null;
				desForm = Short.parseShort(JOptionPane.showInputDialog("\tMenu\n\n" + "[1] FORMA 1.\n"
						+ "[2] FORMA 2.\n" + "[3] FORMA 3.\n" + "[4] Salir.\n\n" + "Ingrese una opcion: "));
			} catch (Exception e) {
				desForm = 0;
				JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
			}
			switch (desForm) {
			case 1:
			case 2:
			case 3:
				do {
					try {
						des = Short.parseShort(JOptionPane.showInputDialog("\tMENU\n\n" + "[1] Ingresar polinomios.\n"
								+ "[2] Mostrar.\n" + "[3] Reconstruir.\n" + "[4] Sumar.\n" + "[5] Multiplicar.\n"
								+ "[6] Dividir.\n" + "[7] Salir.\n\n" + "Ingrese una opcion: "));
					} catch (Exception e) {
						des = 0;
						JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
					}

					switch (des) {
					case 1:
						if (!Menu.isIngresado()) {
							this.enterPolynomials(desForm);
							Menu.setIngresado(true);
						} else {
							desCambiar = JOptionPane
									.showInputDialog("Usted ya ah ingresado los polinomios, ¿Desea cambiarlos? [S/N]")
									.toUpperCase().trim().charAt(0);
							if (desCambiar == 'S') {
								Menu.modificar = true;
								this.enterPolynomials(desForm);
							}
						}
						break;
					case 2:
						if (Menu.isIngresado()) {
							switch (desForm) {
							case 1:
								calcularF1.showPolynomials();
								break;
							case 2:
								calcularF2.showPolynomials();
								break;
							case 3:
								calcularF3.showPolynomials();
								break;
							default:
								System.out.println("Error");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
						}
						break;
					case 3:
						if (Menu.isIngresado()) {
							switch (desForm) {
							case 1:
								calcularF1.rebuild();
								break;
							case 2:
								calcularF2.rebuild();
								break;
							case 3:
								calcularF3.rebuild();
								break;
							default:
								System.out.println("Error");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
						}
						break;

					case 4:
						if (Menu.isIngresado()) {
							switch (desForm) {
							case 1:
								calcularF1.add();
								break;
							case 2:
								calcularF2.add();
								break;
							case 3:
								resF3 = this.calcularF3.add(this.poli1, this.poli2);
								resF3.mostrar();
								break;
							default:
								System.out.println("Error");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
						}
						break;
					case 5:
						if (Menu.isIngresado()) {
							switch (desForm) {
							case 1:
								calcularF1.multiply();
								break;
							case 2:
								calcularF2.multiply();
								break;
							case 3:
								resF3 = this.calcularF3.multiply(this.poli1, this.poli2);
								resF3.mostrar();
								break;
							default:
								System.out.println("Error");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
						}
						break;
					case 6:
						if (Menu.isIngresado()) {
							switch (desForm) {
							case 1:
								calcularF1.divide();
								break;
							case 2:
								calcularF2.multiply();
								break;
							default:
								System.out.println("Error");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
						}
						break;
					case 7:
						salir = true;
						break;
					default:
						JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
						break;
					}
				} while (!salir);
				desForm = 0;
				salir = false;
				Menu.ingresado = false;
				Menu.modificar = false;
				break;
			case 4:
				exit = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Ingrese una opcion valida: ");
				break;
			}
		} while (!exit);

	}

	/**
	 * @param desForm : Es la forma de manipular el polinomio que eligio el usuario
	 */
	public void enterPolynomials(short desForm) {
		String polinomio1;
		String polinomio2;
		boolean salir = false;
		if (Menu.modificar == true) {
			try {
				switch (desForm) {
				case 1:
					this.calcularF1.cleanPolynomial();
					break;
				case 2:
					this.calcularF2.cleanPolynomial();
					break;
				default:
					JOptionPane.showMessageDialog(null, "No ah ingresado una forma valida");
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"No se han podido hacer las modificaciones, concerva los mismos polinomios");
				Menu.modificar = false;
				return;
			}
		}
		do {
			polinomio1 = JOptionPane.showInputDialog("Ingrese el polinomio 1").toUpperCase().trim();
			polinomio2 = JOptionPane.showInputDialog("Ingrese el polinomio 2").toUpperCase().trim();
			if (polinomio1.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor Ingrese un polinomio en el campo 1");
			} else if ((!Character.isDigit(polinomio1.charAt(0))) && (polinomio1.charAt(0) != 'X')
					&& (polinomio1.charAt(0) != '-')) { // Si no es digito, no empieza con letra diferente a x ni -
				JOptionPane.showMessageDialog(null, "Por favor Ingrese un polinomio valido en el campo 1");
			} else if (polinomio2.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor Ingrese un polinomio en el campo 2");
			} else if ((!Character.isDigit(polinomio2.charAt(0))) && (polinomio2.charAt(0) != 'X')
					&& (polinomio2.charAt(0) != '-')) { // Si no es digito, no empieza con letra diferente a x ni -
				JOptionPane.showMessageDialog(null, "Por favor Ingrese un polinomio valido en el campo 2");
			} else {
				char[] polinomioC1 = polinomio1.toCharArray();
				char[] polinomioC2 = polinomio2.toCharArray();

				switch (desForm) {
				case 1:
					this.calcularF1 = new CalculadoraForma1(polinomioC1, polinomioC2);
					if (calcularF1.validatePolynomials()) {
						salir = true;
						Menu.setIngresado(true);
					} else {
						JOptionPane.showMessageDialog(null, "Uno de los polinomios no es valido, ingreselos de nuevo");
					}
					break;
				case 2:
					this.calcularF2 = new CalculadoraForma2(polinomioC1, polinomioC2);
					if (calcularF2.validatePolynomials()) {
						salir = true;
						Menu.setIngresado(true);
					} else {
						JOptionPane.showMessageDialog(null, "Uno de los polinomios no es valido, ingreselos de nuevo");
					}
					break;
				case 3:
					this.calcularF3 = new CalculadoraForma3();
					poli1.ingresarPolinomio(polinomio1);
					poli2.ingresarPolinomio(polinomio2);
					salir = true;
					break;
				default:
					System.out.println("Error");
					break;
				}

			}
		} while (!salir);
		JOptionPane.showMessageDialog(null,
				"POLINOMIOS INICIALES \n" + "Polinomio 1:\n" + polinomio1 + "\n\nPolinomio 2:\n" + polinomio2);
	}

}
