package calculo.tipos;

public class JugadorImpl implements Jugador {
	private Integer puntuacion;

	public JugadorImpl(Integer p) {
		puntuacion = p;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void acierto() {
		puntuacion++;
	}

	public void fallo() {
		if (puntuacion != 0)
			puntuacion--;
	}


}
