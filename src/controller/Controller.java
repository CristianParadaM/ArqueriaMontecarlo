package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import view.JFrameMain;

public class Controller extends ThreadInfo implements MouseListener {

	private Controller(int sleepTime) {
		super(sleepTime);
	}

	private static Controller controller = null;
	private JFrameMain view;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller(100);
		}
		return controller;
	}
	
	@Override
	protected void executeTask() {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		aux.add(new Object[] { 1, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 2, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 3, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 4, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 5, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 6, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 7, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 8, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 9, 10, 9, 8, 9, 10, 9 });
		aux.add(new Object[] { 10, "-", 9, "-", 9, 10, 9 });
		aux.add(new Object[] { 11, "-", "-", "-", "-", "-", "-" });
		ArrayList<Object[]> aux2 = new ArrayList<Object[]>();
		aux2.add(new Object[] { 0, 0, 0, 0, 0, 0, 0 });
		aux2.add(new Object[] { 2, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 3, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 4, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 5, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 6, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 7, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 8, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 9, 10, 9, 8, 9, 10, 9 });
		aux2.add(new Object[] { 10, "-", 9, "-", 9, 10, 9 });
		aux2.add(new Object[] { 11, "-", "-", "-", "-", "-", "-" });
		Object[] data = new Object[20001];
		Random random = new Random();
		double[] suerte = new double[10];
		int globalB = 0;
		int globalR = 0;
		DecimalFormat format = new DecimalFormat("#.0");
		int pointsRoundB = 0;
		int pointsRoundR = 0;
		int playerbest = 0;
		ArrayList<Object[]> rounds = new ArrayList<Object[]>();
		ArrayList<Object[]> results = new ArrayList<Object[]>();
		ArrayList<Object[]> initial = new ArrayList<Object[]>();
		test(results, initial);
		JFrameMain.createProgress(0, data.length, "Generando Juegos", null);
		for (int k = 0; k < data.length - 1; k++) { // cantidad de juegos
			JFrameMain.setProgressBar(k);
			rounds = new ArrayList<>();
			globalB = 0;
			globalR = 0;

			for (int i = 0; i < 10; i++) { // rondas

				suerte = new double[10];

				for (int j = 0; j < suerte.length; j++) {// suerte de los jugadores
					suerte[j] = Double
							.parseDouble(format.format(Math.random() + random.nextInt(3)).replace(",", "."));
				}

				pointsRoundB = random.nextInt(40);
				pointsRoundR = random.nextInt(40);
				playerbest = random.nextInt(10);

				rounds.add(new Object[] { new Object[] { i % 2 == 0 ? aux : aux2, // tabla
						pointsRoundB, // puntos ronda
						suerte // suerte
						}, new Object[] { aux, // tabla
								pointsRoundR, // puntos ronda
								suerte // suerte
						}, new Object[] { globalB += pointsRoundB, // puntos globales 1
								globalR += pointsRoundR, // puntos globales 2
								pointsRoundB > pointsRoundR ? 1 : 2, // equipo ganador ronda 1
								playerbest // jugador ganador individual
						} });
			}
			data[k] = new Object[] { rounds, globalB > globalR ? 1 : 2,
					globalB > globalR ? globalB : globalR };
		}
		data[data.length - 1] = new Object[] { 1, // equipo ganador de las 20000 rondas
				1203, // puntos del equipo ganador despues de las 20000 rondas
				new double[] { 13, 21, 31, 43, 54, 65, 71, 83, 79, 100 }, // puntos de los jugadores en las 20000
																			// rondas
				results, // tabla de resultados de las 20000 rondas
				initial // propiedades iniciales de los jugadores.. genero, resistencia inicial etc
		};
		JFrameMain.disposeDialog();
		view.changeTo(1, data);
		this.pause();
	}

	public void startApp() {
		this.view = JFrameMain.getInstance();
		this.view.init();
		this.pause();
		this.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.isPaused()) {
			this.resume();
		}
	}

	private void test(ArrayList<Object[]> results, ArrayList<Object[]> initial) {
		Random r = new Random();
		for (int i = 0; i < 20000; i++) {
			results.add(new Object[] { i + 1, "P" + (Math.random() > 0.5 ? "1" : "2"), "P1",
					Math.random() > 0.5 ? "Femenino" : "Masculino" });
		}
		for (int i = 0; i < 10; i++) {
			initial.add(new Object[] { "P" + (i + 1), 1 + Math.random() + r.nextInt(2),
					Math.random() > 0.5 ? "Femenino" : "Masculino" });
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent().getName() == "start") {
			((JButton) e.getComponent()).setBorderPainted(true);
			((JButton) e.getComponent()).setBorder(BorderFactory.createDashedBorder(Color.WHITE, 5f, 10f, 5f, true));
			((JButton) e.getComponent()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent().getName() == "start") {
			((JButton) e.getComponent()).setBorderPainted(false);
		}
	}

	public static void main(String[] args) {
		Controller.getInstance().startApp();
	}
}
