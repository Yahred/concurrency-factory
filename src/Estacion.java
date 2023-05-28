import javax.swing.JLabel;
import javax.swing.JPanel;

public class Estacion extends Thread {
	private int linea, numero;
	private int auto;
	private static Semaforo[] robots;
	private static Semaforo robotsMotor, robotsTransmision;
	private static Semaforo producidos, nextNumeroSemaforo;
	private static int autosProducidos;
	private static int nextNumeroAuto;
	private Estacion next;
	private Semaforo ocupado;
	private String operacion;
	private int total;
	private int tiempo;
	private Graficos graficos;
	private JLabel lblAutos;
	private JPanel fabrica;

	public Estacion(int linea, int numero, int total, int tiempo, String operacion, Graficos graficos) {
		this.linea = linea;
		this.numero = numero;
		this.tiempo = tiempo * 1000;
		auto = -1;
		if (robots == null) {
			construirRobots();
		}
		this.total = total;
		autosProducidos = 0;
		producidos = new Semaforo(1);
		ocupado = new Semaforo(1);
		nextNumeroAuto = 0;
		nextNumeroSemaforo = new Semaforo(1);
		this.graficos = graficos;
		this.operacion = operacion;
		if (numero == 2) {
			robotsMotor = new Semaforo(4);
			robotsTransmision = new Semaforo(2);
		}
	}

	public void setLabel(JLabel autos) {
		this.lblAutos = autos;
	}

	public void setFabrica(JPanel fabrica) {
		this.fabrica = fabrica;
	}

	private void construirRobots() {
		robots = new Semaforo[6];
		robots[0] = new Semaforo(5);
		robots[1] = new Semaforo(2);
		robots[2] = new Semaforo(3);
		robots[3] = new Semaforo(3);
		robots[4] = new Semaforo(4);
		robots[5] = new Semaforo(5);
	}

	public void setNextEstacion(Estacion next) {
		this.next = next;
	}

	public void run() {
		while (true) {
			producidos.acquire();
			if (autosProducidos == total) {
				producidos.release();
				return;
			}
			producidos.release();

			if (auto == -1) {
				if (numero != 1)
					continue;
				if (!nuevoAuto()) {
					ocupado.release();
					continue;
				}
			}

			// empieza a operar

			graficos.mostrarEstacion(numero - 1, auto);
			fabrica.repaint();
			if (numero == 2) {
				robotsMotor.acquire();
				graficos.mostrarRobot(numero - 1);
				System.out.println("Linea " + linea + " Estacion " + numero + " Auto " + auto + " Motor");
				espera(6);

				robotsTransmision.acquire();
				graficos.ocultarRobot(numero - 1);
				graficos.mostrarRobot(numero - 1);
				graficos.mostrarTransimion();
				robotsMotor.release();

				System.out.println("Linea " + linea + " Estacion " + numero + " Auto " + auto + " Transimsion");
				espera(4);
				graficos.quitarTransmision();
				graficos.ocultarRobot(numero - 1);
				robotsTransmision.release();
			}

			else {
				robots[numero - 1].acquire();
				graficos.mostrarRobot(numero - 1);
				fabrica.repaint();
				System.out.println("Linea " + linea + " Estacion " + numero + " Auto " + auto + " " + operacion);
				espera();
				graficos.ocultarRobot(numero - 1);
				robots[numero - 1].release();
			}

			// pasar auto a la siguiente estacion si existe
			if (next != null) {
				System.out.println("Linea " + linea + " Estacion " + numero + " Pasando auto " + auto);
				graficos.ocultarEstacion(numero - 1);
				next.recibirAuto(auto);
			}

			if (numero == 6) {
				// terminar auto
				producidos.acquire();
				autosProducidos++;
				System.out.println("Linea " + linea + " Estacion " + numero + " TERMINADO auto " + auto);
				this.lblAutos.setText("Autos Producidos: " + autosProducidos);
				graficos.ocultarEstacion(numero - 1);
				producidos.release();
			}

			auto = -1;
			ocupado.release();
		}
	}

	public void recibirAuto(int auto) {
		ocupado.acquire();
		this.auto = auto;
	}

	private boolean nuevoAuto() {
		ocupado.acquire();
		nextNumeroSemaforo.acquire();

		if (nextNumeroAuto == total) {
			nextNumeroSemaforo.release();
			return false;
		}
		nextNumeroAuto++;
		this.auto = nextNumeroAuto;
		nextNumeroSemaforo.release();
		return true;
	}

	private void espera(int tiempo) {
		tiempo *= 1000;
		try {
			sleep(tiempo);
		} catch (Exception e) {

		}
	}

	private void espera() {
		try {
			sleep(tiempo);
		} catch (Exception e) {

		}
	}

}
