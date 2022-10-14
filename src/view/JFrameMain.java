package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.utils.Constants;

public class JFrameMain extends JFrame implements ActionListener {

	public static int WIDTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int HEIGHT_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().height;
	private static JFrameMain frameMain = null;
	private JPanelMain jPanelMain;
	private JButton buttonExit;

	private JFrameMain() {
		super();
		this.buttonExit = new JButton("X");
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
		case "atras" -> changeTo(0);
		}
	}

	public void init() {
		configureBtnExit();
		this.jPanelMain = new JPanelMain();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
		this.setUndecorated(true);
		this.setLayout(null);
		this.add(buttonExit).setBounds(WIDTH_SCREEN - 30, 0, 30, 30);
		this.add(jPanelMain).setBounds(0, 0, WIDTH_SCREEN, HEIGHT_SCREEN);
		this.setVisible(true);
	}

	private void configureBtnExit() {
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
	}

	public void changeTo(int i) {
		jPanelMain.changeTo(i);
	}

	public void seeGames() {
		jPanelMain.changeTo(2);
	}
	

}
