import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Graficos extends JPanel implements ComponentListener {

	private int linea, h, w;
	private int seccion, alto;
	private JLabel header;
	private JLabel[] estaciones, robots, leyendas, autos;
	private ImageIcon robot;
	private Font fuente;
	private int x, y;

	public Graficos(int linea) {
		this.linea = linea;
		robot = new ImageIcon("robot.jpg");
		estaciones = new JLabel[6];
		leyendas = new JLabel[6];
		robots = new JLabel[6];
		autos = new JLabel[6];
		fuente = new Font("Arial", 12, 12);

		for (int i = 0; i < 6; i++) {
			autos[i] = new JLabel();
			autos[i].setFont(fuente);
			robots[i] = new JLabel();
			estaciones[i] = new JLabel();
			estaciones[i].setFont(fuente);
			leyendas[i] = new JLabel();
			leyendas[i].setFont(fuente);
			autos[i].setVisible(false);
			robots[i].setVisible(false);
			estaciones[i].setVisible(false);
			autos[i].setVisible(false);
		}
		hazInterfaz();
		addComponentListener(this);
	}

	private void hazInterfaz() {
		setSize(700, 250);
		h = this.getHeight();
		w = this.getWidth();
		seccion = (int) w / 12;
		alto = (int) h / 6;
		setLayout(null);
		Border border = new TitledBorder(new EtchedBorder(), "");
		setBorder(border);
		header = new JLabel("Linea " + linea);
		header.setBounds((int) (w * .03), 0, (int) (w * .1), (int) (h * .1));

		add(header);
		leyendas();
		asignarImagenes();
		colocarEstaciones();
		colocarRobots();
		colocarAutos();
	}

	private void leyendas() {
		h = this.getHeight();
		w = this.getWidth();
		int ancho, alto, x, y;
		ancho = (int) w / 6;
		alto = (int) h / 7;
		y = (int) h / 10;

		for (int i = 0; i < 6; i++) {
			leyendas[i].setText("Estacion " + (i + 1));
			x = i * ancho;
			leyendas[i].setBounds(x, y, ancho, alto);
			add(leyendas[i]);
		}
	}

	private void colocarEstaciones() {
		int ancho, alto, x, y;
		y = (int) (h * .2);

		for (int i = 0; i < 6; i++) {
			x = i * (w / 6);
			estaciones[i].setBounds(x, y, 90, 90);
			add(estaciones[i]);
		}
	}

	private void asignarImagenes() {
		estaciones[0].setIcon(new ImageIcon("estacion1.jpg"));
		estaciones[1].setIcon(new ImageIcon("estacion2.jpg"));
		estaciones[2].setIcon(new ImageIcon("estacion3.jpg"));
		estaciones[3].setIcon(new ImageIcon("estacion4.jpg"));
		estaciones[4].setIcon(new ImageIcon("estacion5.jpg"));
		estaciones[5].setIcon(new ImageIcon("estacion6.jpg"));
		for (int i = 0; i < 6; i++)
			robots[i].setIcon(robot);
	}

	private void colocarRobots() {
		int ancho, alto, x, y;
		y = (int) (h * .2);

		for (int i = 0; i < 6; i++) {
			x = i * (w / 6) + 60;
			robots[i].setBounds(x, y, 90, 90);
			add(robots[i]);
		}
	}

	private void colocarAutos() {
		h = this.getHeight();
		w = this.getWidth();
		int ancho, alto, x, y;
		ancho = (int) w / 6;
		alto = (int) h / 10;
		y = (int) (h * 0.75);

		for (int i = 0; i < 6; i++) {
			x = i * ancho;
			autos[i].setBounds(x, y, ancho, 40);
			add(autos[i]);
		}
	}

	public void mostrarEstacion(int numero, int auto) {
		estaciones[numero].setVisible(true);
		autos[numero].setText("Auto# " + auto);
		autos[numero].setVisible(true);
	}

	public void ocultarEstacion(int numero) {
		estaciones[numero].setVisible(false);
		autos[numero].setVisible(false);
		repaint();
	}

	public void mostrarRobot(int numero) {
		robots[numero].setVisible(true);
		repaint();
	}

	public void ocultarRobot(int numero) {
		robots[numero].setVisible(false);
		repaint();
	}

	public void mostrarTransimion() {
		estaciones[1].setIcon(new ImageIcon("transmision.jpg"));
		estaciones[1].setVisible(true);
		repaint();
	}

	public void quitarTransmision() {
		estaciones[1].setIcon(new ImageIcon("estacion2.jpg"));
		estaciones[1].setVisible(false);
		repaint();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		h = this.getHeight();
		w = this.getWidth();
		leyendas();
		colocarAutos();
		colocarRobots();
		colocarEstaciones();
		header.setBounds(0, 0, (int) (w * .1), (int) (h * .1));

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
