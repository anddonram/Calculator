package calculo.tipos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Ventana extends JFrame implements ActionListener {
	private static final Integer PUNT = 10;
	private Random generador;
	private Jugador j1, j2;
	private Integer numero;
	private Boolean turno1 = true;

	private static final long serialVersionUID = 1L;
	private JTextField jtf;
	private JLabel jl, jl2, jp1, jp2;
	private Timer temp;

	public Ventana() {
		super();
		generador = new Random();
		j1 = new JugadorImpl(PUNT);
		j2 = new JugadorImpl(PUNT);
		numero = generador.nextInt(5);

		jtf = new JTextField(4);
		jl = new JLabel(" Turno del jugador 1");
		jl2 = new JLabel("Comencemos por el " + numero);
		jp1 = new JLabel("Jugador 1: " + j1.getPuntuacion());
		jp2 = new JLabel("Jugador 2: " + j2.getPuntuacion());

		JPanel panel = new JPanel(new BorderLayout());
		this.add(panel);
		panel.add(jl, BorderLayout.NORTH);
		panel.add(jl2, BorderLayout.CENTER);
		panel.add(jtf, BorderLayout.SOUTH);
		panel.add(jp1, BorderLayout.WEST);
		panel.add(jp2, BorderLayout.EAST);

		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		temp = new Timer(3000, this);
		temp.setInitialDelay(4000);
		temp.start();
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Integer n = new Integer(jtf.getText());
			if (n.equals(numero) && turno1) {
				j1.acierto();
				Integer k = generador.nextInt(5);
				jl2.setText("Más " + k);
				numero += k;
			} else if (n.equals(numero) && !turno1) {
				j2.acierto();
				Integer k = generador.nextInt(5);
				jl2.setText("Más " + k);
				numero += k;
			} else if (!n.equals(numero) && turno1)
				j1.fallo();
			else if (!n.equals(numero) && !turno1)
				j2.fallo();
		} catch (NumberFormatException ex) {
			if (turno1) {
				j1.fallo();

			} else
				j2.fallo();
		}
		if (j1.getPuntuacion() == 0) {
			temp.stop();
			JOptionPane.showMessageDialog(this, "Gana el jugador 2");
			System.exit(0);
		} else if (j2.getPuntuacion() == 0) {
			temp.stop();
			JOptionPane.showMessageDialog(this, "Gana el jugador 1");
			System.exit(0);
		}
		jp1.setText("Jugador 1: " + j1.getPuntuacion());
		jp2.setText("Jugador 2: " + j2.getPuntuacion());
		turno1 = !turno1;
		if (turno1)
			jl.setText("Turno del jugador 1");
		else
			jl.setText("Turno del jugador 2");
		jtf.setText("");

	}

	public static void main(String[] args) {
		new Ventana();
	}
}
