package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.Manager;
import view.JFrameMain;

public class Controller extends ThreadInfo implements MouseListener {

	private static Controller controller = null;
	private JFrameMain view;
	private Manager model;
	private Point point;
	private static final int QUANTY_GAMES = 2000;
	
	private Controller(int sleepTime) {
		super(sleepTime);
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller(100);
		}
		return controller;
	}
	
	@Override
	protected void executeTask() {
		Object[] data = new Object[QUANTY_GAMES+1];
		double[] suerte = new double[10];
		int globalTeamOne = 0;
		int globalTeamTwo = 0;
		int pointsRoundOne = 0;
		int pointsRoundTwo = 0;
		int playerbest = 0;
		ArrayList<Object[]> rounds = new ArrayList<Object[]>();
		ArrayList<Object[]> results = new ArrayList<Object[]>();
		ArrayList<Object[]> initial = new ArrayList<Object[]>();
		JFrameMain.createProgress(0, data.length-2, "Generando Juegos", null);
		this.model = new Manager(point.x, point.y);
		JFrameMain.disposeDialog();
		JFrameMain.createProgress(0, data.length-2, "Cargando Información", null);
		int quantyWinTeamOne = 0;
		int quantyWinTeamTwo = 0;
		int pointsTotalTeamOne = 0;
		int pointsTotalTeamTwo = 0;
		test(results, initial);
		for (int k = 0; k < data.length - 1; k++) { // cantidad de juegos
			
			JFrameMain.setProgressBar(k);
			rounds = new ArrayList<>();
			globalTeamOne = 0;
			globalTeamTwo = 0;
			
			for (int i = 0; i < 10; i++) { // rondas
				
				suerte = model.getLuck(k, i);
				pointsRoundOne = model.getPointsRoundTeamFirst(k, i);
				pointsRoundTwo = model.getPointsRoundTeamSecond(k, i);
				playerbest = model.getBestPlayer(k, i);
				
				ArrayList<Object[]> tableTeamOne = cutTable(model.getTableRound(k, i), 0);
				ArrayList<Object[]> tableTeamTwo = cutTable(model.getTableRound(k, i), 1);
				
				rounds.add(new Object[] { new Object[] { tableTeamOne , // tabla
						pointsRoundOne, // puntos ronda
						suerte // suerte
				}, new Object[] { tableTeamTwo, // tabla
						pointsRoundTwo, // puntos ronda
						suerte // suerte
				}, new Object[] { globalTeamOne += pointsRoundOne, // puntos globales 1
				globalTeamTwo += pointsRoundTwo, // puntos globales 2
				pointsRoundOne == pointsRoundTwo ? 0 : pointsRoundOne > pointsRoundTwo ? 1 : 2, // equipo ganador ronda 1
						playerbest // jugador ganador individual
				} });
			}
			
			quantyWinTeamOne = globalTeamOne > globalTeamTwo ? quantyWinTeamOne+1:quantyWinTeamOne;
			quantyWinTeamTwo = globalTeamTwo > globalTeamOne ? quantyWinTeamTwo+1:quantyWinTeamTwo;
			pointsTotalTeamOne += globalTeamOne;
			pointsRoundTwo += globalTeamTwo;
			
			data[k] = new Object[] { rounds, globalTeamOne > globalTeamTwo ? 1 : 2,
					globalTeamOne > globalTeamTwo ? globalTeamOne : globalTeamTwo };
		}
		data[data.length - 1] = new Object[] { quantyWinTeamOne > quantyWinTeamTwo ? 1 : 2, // equipo ganador de las 20000 rondas
				pointsTotalTeamOne > pointsTotalTeamTwo ? pointsTotalTeamOne: pointsTotalTeamTwo, // puntos del equipo ganador despues de las 20000 rondas
				calculatePointsPLayers(), // puntos de los jugadores en las 20000 rondas
				model.getResults()	, // tabla de resultados de las 20000 rondas
				model.getInitialStatics() // propiedades iniciales de los jugadores.. genero, resistencia inicial etc
		};
		JFrameMain.disposeDialog();
		view.changeTo(1, data);
		this.pause();
	}

	private double[] calculatePointsPLayers() {
		double[] aux = new double[10];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = model.getTotalPointsOfPlayer(i);
		}
		return aux;
	}

	private ArrayList<Object[]> cutTable(ArrayList<Object[]> tableRound, int index) {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		Object[] aux2 = new Object[6];
		if (index == 0) {
			for (int i = 0; i < tableRound.size(); i++) {
				aux2 = new Object[6];
				aux2[0] = i+1;
				for (int j = 0; j < 5; j++) {
					aux2[j+1] = tableRound.get(i)[j];
				}
				aux.add(aux2);
			}
		}else {
			for (int i = 0; i < tableRound.size(); i++) {
				aux2 = new Object[6];
				aux2[0] = i+1;
				for (int k = 1, j = tableRound.get(i).length/2; j < tableRound.get(i).length; j++, k++) {
					aux2[k] = tableRound.get(i)[j];
				}
				aux.add(aux2);
			}
		}
		
		return aux;
	}

	public void setProgress(int k) {
		JFrameMain.setProgressBar(k);
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
			point = e.getPoint();
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
