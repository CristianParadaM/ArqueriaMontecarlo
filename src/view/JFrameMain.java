package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import view.utils.Constants;

public class JFrameMain extends JFrame implements ActionListener {

	public static int WIDTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int HEIGHT_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().height;
	private static JFrameMain frameMain = null;
	private JPanelMain jPanelMain;
	private JButton buttonExit;
	private JButton buttonBack;
	private static JProgressBar bar;
	private static JDialog dialog;
	private Object[] data;

	private JFrameMain() {
		super();
		this.buttonExit = new JButton("X");
		this.buttonBack = new JButton("<--");
	}

	public static JFrameMain getInstance() {
		if (frameMain == null) {
			frameMain = new JFrameMain();
		}
		return frameMain;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "atras" -> jPanelMain.changeTo(0, null);
		case "juegos" -> jPanelMain.changeTo(2, null);
		}
	}

	public void init() {
		configureBtnExitB();
		this.jPanelMain = new JPanelMain();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
		this.setUndecorated(true);
		this.setLayout(null);
		this.buttonBack.setVisible(false);
		this.add(buttonBack).setBounds(0, 0, 30, 30);
		this.add(buttonExit).setBounds(WIDTH_SCREEN - 30, 0, 30, 30);
		this.add(jPanelMain).setBounds(0, 0, WIDTH_SCREEN, HEIGHT_SCREEN);
		this.setVisible(true);
	}

	private void configureBtnExitB() {
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setFont(new Font(Constants.FONT_APP, Font.BOLD, 20));
		buttonExit.setContentAreaFilled(false);
		buttonExit.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.WHITE));
		buttonExit.setFocusPainted(false);
		buttonExit.addActionListener((x) -> {
			if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?") == 0) {
				System.exit(0);
			}
		});
		buttonExit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				buttonExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonExit.setForeground(Color.RED);
				buttonExit.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.RED));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				buttonExit.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.WHITE));
				buttonExit.setForeground(Color.WHITE);
			}

		});
		buttonBack.setForeground(Color.WHITE);
		buttonBack.setFont(new Font(Constants.FONT_APP, Font.BOLD, 20));
		buttonBack.setContentAreaFilled(false);
		buttonBack.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.WHITE));
		buttonBack.setFocusPainted(false);
		buttonBack.addActionListener((x) -> {
			jPanelMain.changeTo(1, data);
			showBackButton(false);
		});
		
		buttonBack.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonBack.setForeground(Color.ORANGE);
				buttonBack.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.ORANGE));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				buttonBack.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				buttonBack.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.WHITE));
				buttonBack.setForeground(Color.WHITE);
			}
			
		});
	}
	
	public void showBackButton(boolean b) {
		this.buttonBack.setVisible(b);
	}
	
	public void changeTo(int i, Object[] data) {
		this.data = data;
		jPanelMain.changeTo(i, data);
	}

	public static void createProgress(int min, int max, String title, Point location) {
		bar = new JProgressBar(min, max);
		dialog = new JDialog(JFrameMain.getInstance(), "Cargando...");
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setSize(new Dimension(300 * JFrameMain.WIDTH_SCREEN / 1920, 120 * JFrameMain.HEIGHT_SCREEN / 1080));
		if (location != null) {
			dialog.setLocation(location);
		} else {
			dialog.setLocationRelativeTo(null);
		}
		dialog.setLayout(null);
		dialog.add(new JLabel(title, JLabel.CENTER)).setBounds(5 * JFrameMain.WIDTH_SCREEN / 1920,
				10 * JFrameMain.HEIGHT_SCREEN / 1080, 275 * JFrameMain.WIDTH_SCREEN / 1920,
				30 * JFrameMain.HEIGHT_SCREEN / 1080);
		dialog.add(bar).setBounds(5 * JFrameMain.WIDTH_SCREEN / 1920, 40 * JFrameMain.HEIGHT_SCREEN / 1080,
				275 * JFrameMain.WIDTH_SCREEN / 1920, 20 * JFrameMain.HEIGHT_SCREEN / 1080);
		dialog.setVisible(true);
	}

	public static void setProgressBar(int value) {
		bar.setValue(value);
	}

	public static void disposeDialog() {
		dialog.setVisible(false);
	}

}
