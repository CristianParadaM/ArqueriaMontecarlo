package view.games;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.utils.Constants;

public class JPanelGames extends JPanel {

	private int indexPage;
	private JLabel jlabelScoreBlue;
	private JLabel jlabelScoreRed;
	private JLabel jlabelRound;
	private JLabel jlabelWinner;
	private JLabel jlabelPlayerWinner;
	private ArrayList<JPanelRound> listRoundsBlue;
	private ArrayList<JPanelRound> listRoundsRed;
	private JButton buttonBack;
	private JButton buttonNext;

	public JPanelGames() {
		super();
		int scoreB = 120;
		int scorer = 162;
		int round = 1;
		int winner = 2;
		int pwinner = 10;
		this.indexPage = 0;
		this.listRoundsBlue = new ArrayList<JPanelRound>();
		this.listRoundsRed = new ArrayList<JPanelRound>();
		this.jlabelScoreBlue = new JLabel("<html><b>" + scoreB + "</b></html>", JLabel.CENTER);
		this.jlabelScoreRed = new JLabel("<html><b>" + scorer + "</b></html>", JLabel.CENTER);
		this.jlabelRound = new JLabel("Ronda " + round, JLabel.CENTER);
		this.jlabelWinner = new JLabel("Gana Equipo " + winner, JLabel.CENTER);
		this.jlabelPlayerWinner = new JLabel("<html><center>Ganador Individual P" + pwinner + "</center></html>",
				JLabel.CENTER);
		this.buttonBack = new JButton(new ImageIcon(getClass().getResource("/res/arrowroundleft.png")));
		this.buttonNext = new JButton(new ImageIcon(getClass().getResource("/res/arrowroundright.png")));
		test();
		init();
	}

	private void test() {
		for (int i = 0; i < 10; i++) {
			this.listRoundsBlue.add(new JPanelRound(0));
			this.listRoundsRed.add(new JPanelRound(1));
		}
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(null);
		configureButton(buttonBack, false);
		configureButton(buttonNext, true);
		configureLabel(jlabelScoreBlue, Constants.FONT_APP, 30, Font.ITALIC, Color.WHITE);
		configureLabel(jlabelScoreRed, Constants.FONT_APP, 30, Font.ITALIC, Color.WHITE);
		configureLabel(jlabelRound, Constants.FONT_APP, 30, Font.ITALIC, Color.BLACK);
		configureLabel(jlabelWinner, Constants.FONT_APP, 30, Font.ITALIC, Color.BLACK);
		configureLabel(jlabelPlayerWinner, Constants.FONT_APP, 30, Font.ITALIC, Color.BLACK);
		changePage();
	}

	private void configureButton(JButton button, boolean b) {
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		if (b) {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (indexPage < listRoundsBlue.size()-1) {
						indexPage++;
						changePage();
					}
				}
			});
		} else {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (indexPage != 0) {
						indexPage--;
						changePage();
					}
				}
			});
		}
	}

	private void addComponents() {
		jlabelRound.setText("Ronda " + (indexPage+1));
		this.jlabelScoreBlue.setVisible(true);
		this.jlabelScoreRed.setVisible(true);
		this.jlabelRound.setVisible(true);
		this.jlabelWinner.setVisible(true);
		this.jlabelPlayerWinner.setVisible(true);
		this.buttonBack.setVisible(true);
		this.buttonNext.setVisible(true);
		
		this.add(jlabelScoreBlue).setBounds(820, 40, 70, 50);
		this.add(jlabelScoreRed).setBounds(920, 40, 70, 50);
		this.add(jlabelRound).setBounds(860, 230, 100, 85);
		this.add(jlabelWinner).setBounds(840, 430, 145, 85);
		this.add(jlabelPlayerWinner).setBounds(840, 630, 140, 85);

		this.add(buttonBack).setBounds(0, 820, 100, 100);
		this.add(buttonNext).setBounds(1700, 820, 100, 100);

	}

	private void changePage() {
		removeComponents();
		addComponents();
		listRoundsBlue.get(indexPage).setVisible(true);
		listRoundsRed.get(indexPage).setVisible(true);
		this.add(listRoundsBlue.get(indexPage)).setBounds(0, 0, 905, 930);
		this.add(listRoundsRed.get(indexPage)).setBounds(905, 0, 905, 930);
	}

	private void removeComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style, Color color) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(color);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int height = getHeight()/20;
		g2d.setColor(new Color(100, 100, 100, 20));
		for (int i = 0; i < 20; i++) {
			g2d.drawLine(5, height, getWidth()-5, height);
			height += getHeight()/20;
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawLine(getWidth() / 2 - 110, 0, getWidth() / 2 - 110, 8);
		g2d.drawLine(getWidth() / 2 + 110, 0, getWidth() / 2 + 110, 8);
		g2d.drawLine(getWidth() / 2 - 110, 8, getWidth() / 2 + 110, 8);

		g2d.drawImage(new ImageIcon(getClass().getResource("/res/flagblue.png")).getImage(), getWidth() / 2 - 100, 5,
				100, 120, this);
		g2d.drawImage(new ImageIcon(getClass().getResource("/res/flagred.png")).getImage(), getWidth() / 2, 5, 100, 120,
				this);

		g2d.drawImage(new ImageIcon(getClass().getResource("/res/paperlittle.png")).getImage(), getWidth() / 2 - 90,
				200, 180, 150, this);

		g2d.drawImage(new ImageIcon(getClass().getResource("/res/paperbig.png")).getImage(), getWidth() / 2 - 110, 400,
				220, 140, this);

		g2d.drawImage(new ImageIcon(getClass().getResource("/res/paperbig.png")).getImage(), getWidth() / 2 - 110, 600,
				220, 140, this);
		super.paint(g);
	}
}
