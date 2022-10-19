package view.games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.JFrameMain;
import view.utils.Constants;
import view.utils.JTableResult;

@SuppressWarnings("unchecked")
public class JPanelRound extends JPanel {

	private GridBagConstraints gbc;
	private JLabel jLabelTeam;
	private JTableResult jTableResult;
	private JLabel jLabelPointsRound;
	private String[] columnNames;

	// data[0] = Arraylist<Object[]> infotable
	// data[1] = int points Round
	// data[6] = double[] suerte
	public JPanelRound(int i, Object[] data) {
		super(new GridBagLayout());
		columnNames = i == 0
				? new String[] { "Lanz.", "P1 - " + (((double[]) data[2])[0]), "P2 - " + (((double[]) data[2])[1]),
						"P3 - " + (((double[]) data[2])[2]), "P4 - " + (((double[]) data[2])[3]),
						"P5 - " + (((double[]) data[2])[4]) }
				: new String[] { "Lanz.", "P6 - " + (((double[]) data[2])[5]), "P7 - " + (((double[]) data[2])[6]),
						"P8 - " + (((double[]) data[2])[7]), "P9 - " + (((double[]) data[2])[8]),
						"P10 - " + (((double[]) data[2])[9]) };
		this.jTableResult = new JTableResult((ArrayList<Object[]>) data[0], columnNames, i);
		this.jLabelTeam = new JLabel("<html><b>Equipo " + (i + 1) + "</b><html>", JLabel.CENTER);
		this.jLabelPointsRound = new JLabel("<html><b>Puntos de la Ronda:</b> " + ((int) data[1]) + "</html>",
				JLabel.CENTER);
		init();
	}

	public void setData(int i, Object[] data) {
		columnNames = i == 0
				? new String[] { "Lanz.", "P1 - " + (((double[]) data[2])[0]), "P2 - " + (((double[]) data[2])[1]),
						"P3 - " + (((double[]) data[2])[2]), "P4 - " + (((double[]) data[2])[3]),
						"P5 - " + (((double[]) data[2])[4]) }
				: new String[] { "Lanz.", "P6 - " + (((double[]) data[2])[5]), "P7 - " + (((double[]) data[2])[6]),
						"P8 - " + (((double[]) data[2])[7]), "P9 - " + (((double[]) data[2])[8]),
						"P10 - " + (((double[]) data[2])[9]) };
		this.jTableResult.setInfoTable((ArrayList<Object[]>) data[0], columnNames);
		this.jLabelTeam.setText("<html><b>Equipo " + (i + 1) + "</b><html>");
		this.jLabelPointsRound.setText("<html><b>Puntos de la Ronda:</b> " + ((int) data[1]) + "</html>");
		removeComponents();
		addComponents();
	}

	private void removeComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	private void init() {
		this.setOpaque(false);
		this.jTableResult.setPreferredSize(new Dimension(0, 500 * JFrameMain.HEIGHT_SCREEN / 1080));
		configureLabel(jLabelTeam, Constants.FONT_APP, 60 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, Color.BLACK);
		configureLabel(jLabelPointsRound, Constants.FONT_APP, 50 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, Color.BLACK);
		addComponents();
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style, Color color) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(color);
	}

	private void addComponents() {
		jLabelTeam.setVisible(true);
		jTableResult.setVisible(true);
		jLabelPointsRound.setVisible(true);

		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets.left = 120 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.right = 120 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.top = 10 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.gridy = 0;
		this.add(jLabelTeam, gbc);
		gbc.fill = 1;
		gbc.gridy = 1;
		gbc.insets.top = 50 * JFrameMain.HEIGHT_SCREEN / 1080;
		this.add(jTableResult, gbc);
		gbc.insets.top = 50 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.gridy = 2;
		this.add(jLabelPointsRound, gbc);
		gbc.gridy = 3;
		gbc.weighty = 1;
		this.add(Box.createRigidArea(new Dimension(0, 0)), gbc);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(100 * JFrameMain.WIDTH_SCREEN / 1920, 92 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (100 * JFrameMain.WIDTH_SCREEN / 1920), 92 * JFrameMain.HEIGHT_SCREEN / 1080);
		super.paint(g);
	}

}
