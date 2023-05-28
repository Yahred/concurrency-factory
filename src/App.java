import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class App extends JFrame {

	public static void main(String[] args) {
		new App();
	}

	public App() {
		super("Robotsines");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		Random r = new Random();

		int lineas = r.nextInt(15) + 8;
		int autos = r.nextInt(30) + 10;

		Fabrica fabric = new Fabrica(autos, 12);
		JScrollPane scroll = new JScrollPane(fabric.getPanel());
		add(scroll);
		setVisible(true);
		JButton btn = new JButton("Empezar");
		add(btn, BorderLayout.SOUTH);

		btn.addActionListener(e -> {
			fabric.empezar();
		});

	}

}
