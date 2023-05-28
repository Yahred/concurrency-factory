import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Fabrica implements ComponentListener {

	private Estacion[][] estaciones;
	private int numeroDeLineas;
	private int total;
	private JLabel autosProducidos, lblTotal, lblNumeroLineas;
	private Graficos[] graficos;
	private int h, w;
	private JPanel panel;
	private Font fuente;

	public Fabrica(int total, int numeroDeLineas) {
		this.total = total;
		this.numeroDeLineas = numeroDeLineas;
		fuente = new Font("Arial", 18, 20);
		estaciones = new Estacion[numeroDeLineas][6];
		graficos = new Graficos[numeroDeLineas];
		autosProducidos = new JLabel();
		lblTotal = new JLabel("Autos a producir: " + total);
		lblNumeroLineas = new JLabel("Numero de lineas: " + numeroDeLineas);
		for (int i = 0; i < numeroDeLineas; i++)
			graficos[i] = new Graficos(i + 1);
		panel = new JPanel();
		panel.addComponentListener(this);
		inicializarEstaciones();
		hazInterfaz();
	}

	public JPanel getPanel() {
		return panel;
	}

	private void hazInterfaz() {
		panel.setPreferredSize(new Dimension(1000, (40 + numeroDeLineas * 110)));
		panel.setLayout(null);
		lblTotal.setFont(fuente);
		autosProducidos.setFont(fuente);
		lblNumeroLineas.setFont(fuente);
		autosProducidos.setText("Autos Producidos: 0");
		panel.add(lblTotal);
		panel.add(autosProducidos);
		panel.add(lblNumeroLineas);
		for (int i = 0; i < numeroDeLineas; i++) {
			panel.add(graficos[i]);
		}
		panel.add(autosProducidos);
	}

	private void inicializarEstaciones() {
		for (int i = 0; i < numeroDeLineas; i++) {
			estaciones[i][0] = new Estacion((i + 1), 1, total, 10, "Chasis y cableado", graficos[i]);
			estaciones[i][1] = new Estacion((i + 1), 2, total, 4, "Motor y transimision", graficos[i]);
			estaciones[i][2] = new Estacion((i + 1), 3, total, 10, "Carrocer�a", graficos[i]);
			estaciones[i][3] = new Estacion((i + 1), 4, total, 5, "Interiores", graficos[i]);
			estaciones[i][4] = new Estacion((i + 1), 5, total, 5, "Llantas", graficos[i]);
			estaciones[i][5] = new Estacion((i + 1), 6, total, 10, "Pruebas", graficos[i]);
			estaciones[i][0].setNextEstacion(estaciones[i][1]);
			estaciones[i][1].setNextEstacion(estaciones[i][2]);
			estaciones[i][2].setNextEstacion(estaciones[i][3]);
			estaciones[i][3].setNextEstacion(estaciones[i][4]);
			estaciones[i][4].setNextEstacion(estaciones[i][5]);
		}
		estaciones[0][5].setLabel(autosProducidos);
		for (int i = 0; i < numeroDeLineas; i++)
			for (int j = 0; j < 6; j++)
				estaciones[i][j].setFabrica(panel);
	}

	public void empezar() {
		for (int i = 0; i < numeroDeLineas; i++) {
			for (int j = 0; j < 6; j++) {
				estaciones[i][j].start();
			}
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		h = panel.getHeight();
		w = panel.getWidth();
		int alto = 110;
		autosProducidos.setBounds(0, 0, 250, 20);
		lblTotal.setBounds(250, 0, 250, 20);
		lblNumeroLineas.setBounds(500, 0, 250, 20);
		for (int i = 0; i < numeroDeLineas; i++) {
			graficos[i].setSize(w, alto);
			graficos[i].setLocation(0, (i * alto) + 20);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}
}
