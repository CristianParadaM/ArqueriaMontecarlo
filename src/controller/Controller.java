package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import view.JFrameMain;

public class Controller implements MouseListener, ActionListener {

	private static Controller controller = null;
	private JFrameMain view;
	
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void startApp() {
		this.view = JFrameMain.getInstance();
		this.view.init();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		view.changeTo(1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "juegos"-> seeGames();
		}
	}

	private void seeGames() {
		view.seeGames();
	}
	
}
