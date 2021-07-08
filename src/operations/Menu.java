package operations;

import javax.swing.JOptionPane;

public class Menu {

	private static boolean ingresado;
	private Calculadora calcular;

	public Menu() {
		Menu.ingresado = false;
	}

	//
	public static boolean isIngresado() {
		return ingresado;
	}

	public static void setIngresado(boolean ingresado) {
		Menu.ingresado = ingresado;
	}

		
	public void inicio() {
		short des = 0;
		char desCambiar = 'N';
		boolean salir = false;

		do {
			try {
				des = Short.parseShort(JOptionPane.showInputDialog(
						"	MENU\n\n" + "[1] Ingresar polinomios. \n" + "[2] Mostrar. \n" + "[3] Reconstruir. \n"
								+ "[4] Sumar. \n"+ "[5] Multiplicar. \n" + "[6] Salir. \n\n" + "Ingrese una opcion: "));
			} catch (Exception e) {
				des = 0;
			}

			switch (des) {
			case 1:
				if (!Menu.isIngresado()) {
					this.ingresarPolinomios();
					Menu.setIngresado(true);
				} else {
					try {
						desCambiar = JOptionPane.showInputDialog("Seguro que quiere cambiar los polinomios [S/N] ")
								.toUpperCase().trim().charAt(0);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Respuesta invalida, se tomara como [NO]");
					}
					if (desCambiar == 'S') {
						this.ingresarPolinomios();
					}
				}
				break;
			case 2:
				if (Menu.isIngresado()) {
					calcular.showPolynomials();
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
				}
				break;
			case 3:
				if (Menu.isIngresado()) {
					calcular.rebuild();
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
				}
				break;
				
			case 4:
				if (Menu.isIngresado()) {
					calcular.add();
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
				}
				break;
			case 5:
				if (Menu.isIngresado()) {
					calcular.multiply();
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe ingresar los polinomios");
				}
				break;
			case 6:
				salir = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
				break;
			}
		} while (!salir);
	}

	public void ingresarPolinomios() {
		String polinomio1;
		String polinomio2;
		boolean salir = false;
		do {
			try {
				this.calcular.cleanPolynomial(); // Eliminamos el antiguo polinomio
			} catch (Exception e) { // por si aun no hay polinomio aún
			}
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

				calcular = new Calculadora(polinomioC1, polinomioC2);

				if (calcular.validatePolynomials()) {
					salir = true;
					Menu.setIngresado(true);
				} else {
					JOptionPane.showMessageDialog(null, "Uno de los polinomios no es valido, ingreselos de nuevo");
				}
			}
		} while (!salir);
		JOptionPane.showMessageDialog(null,
				"POLINOMIOS INICIALES \n" + "Polinomio 1:\n" + polinomio1 + "\n\nPolinomio 2:\n" + polinomio2);
	}

}
