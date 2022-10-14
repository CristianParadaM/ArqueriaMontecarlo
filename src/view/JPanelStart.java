package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import view.utils.Constants;

public class JPanelStart extends JPanel {

	private JLabel jLabelTitle;
	private JLabel jLabelAutors;
	private JButton jButtonStart;
	private JPanel jPanelContainerButton;

	public JPanelStart() {
		super(new BorderLayout());

		this.jLabelTitle = new JLabel("Simulación - Juego de Arquería", JLabel.CENTER);
		this.jLabelAutors = new JLabel("", JLabel.CENTER);
		this.jPanelContainerButton = new JPanel(new GridBagLayout());
		this.jButtonStart = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnstart.png"))
				.getImage().getScaledInstance(650 * JFrameMain.WIDTH_SCREEN / 1920,
						550 * JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH)));
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelContainerButton.setOpaque(false);
		configureLabel(jLabelTitle, Constants.FONT_APP, 100 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC);
		configureLabel(jLabelAutors, Constants.FONT_APP, 50 * JFrameMain.WIDTH_SCREEN / 1920, Font.PLAIN);
		configureButton(jButtonStart, "start");

		this.add(jLabelTitle, BorderLayout.NORTH);
		this.add(jLabelAutors, BorderLayout.SOUTH);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		jPanelContainerButton.add(jButtonStart, gbc);

		this.add(jPanelContainerButton, BorderLayout.CENTER);
		animate();
	}

	private void configureButton(JButton jButton, String command) {
		jButton.setContentAreaFilled(false);
		jButton.setBorderPainted(false);
		jButton.setFocusPainted(false);
		jButton.setName(command);
		jButton.addMouseListener(Controller.getInstance());
	}

	private void animate() {
		new Thread(() -> {
			int close = 0;
			while (close == 0) {
				try {
					String text = Constants.AUTORS;
					for (int i = 0; i < text.length(); i++) {
						jLabelAutors.setText(jLabelAutors.getText() + text.charAt(i));
						Thread.sleep(100);
					}
					Thread.sleep(5000);
					jLabelAutors.setText("");
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(Color.WHITE);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(90 * JFrameMain.WIDTH_SCREEN / 1920, 130 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (90 * JFrameMain.WIDTH_SCREEN / 1920), 130 * JFrameMain.HEIGHT_SCREEN / 1080);
		super.paint(g);
	}
}
