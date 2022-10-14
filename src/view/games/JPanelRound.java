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

import view.utils.Constants;
import view.utils.JTableResult;

public class JPanelRound extends JPanel {
	
	private GridBagConstraints gbc;
	private JLabel jLabelTeam;
	private JTableResult jTableResult;
	private JLabel jLabelPlayerXP;
	private JLabel jLabelPointsRound;
	
	public JPanelRound(int i) {
		super(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		aux.add(new Object[] {1,10,9,8,9,10,9});
		aux.add(new Object[] {2,10,9,8,9,10,9});
		aux.add(new Object[] {3,10,9,8,9,10,9});
		aux.add(new Object[] {4,10,9,8,9,10,9});
		aux.add(new Object[] {5,10,9,8,9,10,9});
		aux.add(new Object[] {6,10,9,8,9,10,9});
		aux.add(new Object[] {7,10,9,8,9,10,9});
		aux.add(new Object[] {8,10,9,8,9,10,9});
		aux.add(new Object[] {9,10,9,8,9,10,9});
		aux.add(new Object[] {10,"-",9,"-",9,10,9});
		aux.add(new Object[] {11,"-","-","-","-","-","-"});
		String[] columnNames = new String[] {"Lanz.", "P1","P2","P3","P4","P5"};
		String[] columnNames2 = new String[] {"Lanz.", "P6","P7","P8","P9","P10"};
		this.jTableResult = new JTableResult(aux, i == 0? columnNames:columnNames2, i);
		this.jLabelTeam = new JLabel("<html><b>Equipo "+(i+1)+"</b><html>", JLabel.CENTER);
		this.jLabelPlayerXP = new JLabel("<html><b>Jugador con más Exp</b>: P"+(i+1)+"</html>", JLabel.CENTER);
		this.jLabelPointsRound = new JLabel("<html><b>Puntos de la Ronda:</b> "+65+"</html>", JLabel.CENTER);
		init();
	}
	
	private void init() {
		this.setOpaque(false);
		this.jTableResult.setPreferredSize(new Dimension(0,500));
		configureLabel(jLabelTeam, Constants.FONT_APP, 60, Font.ITALIC, Color.BLACK);
		configureLabel(jLabelPlayerXP, Constants.FONT_APP, 50, Font.ITALIC, Color.BLACK);
		configureLabel(jLabelPointsRound, Constants.FONT_APP, 50, Font.ITALIC, Color.BLACK);
		addComponents();
	}
	
	private void configureLabel(JLabel jLabel, String font, int size, int style, Color color) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(color);
	}

	private void addComponents() {
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets.left = 120;
		gbc.insets.right = 120;
		gbc.insets.top = 10;
		this.add(jLabelTeam, gbc);
		gbc.fill = 1;
		gbc.gridy =1;
		gbc.insets.top = 50;
		this.add(jTableResult, gbc);
		gbc.insets.top = 10;
		gbc.gridy =2;
		this.add(jLabelPlayerXP, gbc);
		gbc.gridy =3;
		this.add(jLabelPointsRound, gbc);
		gbc.weighty =1;
		gbc.gridy =4;
		this.add(Box.createRigidArea(new Dimension(0,0)), gbc);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(100, 92, getWidth()-100, 92);
		super.paint(g);
	}
	
}
