package view.games;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.JFrameMain;
import view.utils.Constants;

@SuppressWarnings("unchecked")
public class JPanelGame extends JPanel {

	private int indexPage;
	private JLabel jlabelScoreBlue;
	private JLabel jlabelScoreRed;
	private JLabel jlabelRound;
	private JLabel jlabelWinner;
	private JLabel jlabelPlayerWinner;
	private JLabel jLabelIndexPages;
	private JPanelRound jPanelRoundBlue;
	private JPanelRound jPanelRoundRed;
	private JButton buttonBack;
	private JButton buttonNext;
	private int scoreGlobalBlue;
	private int scoreGlobalRed;
	private int pwinner;
	private int winner;
	private ImageIcon[] images;
	private ArrayList<Object[]> gameData;
	private JLabel jLabelWinnerTeam;
	private JLabel jLabelWinnerTeamPoints;

	public JPanelGame(Object[] data, ImageIcon[] images) {
		super();
		this.gameData = ((ArrayList<Object[]>) data[0]);
		this.indexPage = 0;
		this.images = images;
		this.scoreGlobalBlue = ((int) ((Object[]) gameData.get(0)[0])[1]);
		this.scoreGlobalRed = ((int) ((Object[]) gameData.get(0)[1])[1]);
		this.winner = ((int) ((Object[]) gameData.get(0)[2])[2]);
		this.pwinner = ((int) ((Object[]) gameData.get(0)[2])[3]);
		this.jLabelIndexPages = new JLabel((indexPage + 1) + " / " + 11);
		this.jlabelRound = new JLabel("<html><b>Ronda " + 1 + "</b></html>", JLabel.CENTER);
		this.jlabelScoreBlue = new JLabel("<html><b>" + scoreGlobalBlue + "</b></html>", JLabel.CENTER);
		this.jlabelScoreRed = new JLabel("<html><b>" + scoreGlobalRed + "</b></html>", JLabel.CENTER);
		this.jlabelWinner = new JLabel(winner == 0 ? "Empate" : "Gana Equipo " + winner, JLabel.CENTER);
		this.jlabelPlayerWinner = new JLabel("<html><center>Ganador Individual P" + pwinner + "</center></html>",
				JLabel.CENTER);
		this.jPanelRoundBlue = new JPanelRound(0, (Object[]) gameData.get(0)[0]);
		this.jPanelRoundRed = new JPanelRound(1, (Object[]) gameData.get(0)[1]);
		this.buttonBack = new JButton(images[0]);
		this.buttonNext = new JButton(images[1]);
		if ((int) data[1] == 1) {
			this.jLabelWinnerTeam = new JLabel("<html>Equipo <b style='color:blue;'>" + data[1] + "</b> ganador</html>",
					JLabel.CENTER);
			this.setBackground(new Color(217,222,255));
		} else {
			this.jLabelWinnerTeam = new JLabel("<html>Equipo <b style='color:red;'>" + data[1] + "</b> ganador</html>",
					JLabel.CENTER);
			this.setBackground(new Color(255,217,217));
		}
		this.jLabelWinnerTeamPoints = new JLabel("<html>Puntos Totales Obtenidos: <b>" + data[2] + "pts.</b></html>",
				JLabel.CENTER);
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(null);
		configureButton(buttonBack, false);
		configureButton(buttonNext, true);
		configureLabel(jLabelIndexPages, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.WHITE);
		configureLabel(jlabelScoreBlue, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.WHITE);
		configureLabel(jlabelScoreRed, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.WHITE);
		configureLabel(jlabelRound, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, Color.BLACK);
		configureLabel(jlabelWinner, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, Color.BLACK);
		configureLabel(jlabelPlayerWinner, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.BLACK);
		configureLabel(jLabelWinnerTeam, Constants.FONT_APP, 100 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.BLACK);
		configureLabel(jLabelWinnerTeamPoints, Constants.FONT_APP, 90 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC,
				Color.BLACK);
		changePage();
	}

	public void setGameData(Object[] data) {
		this.gameData = ((ArrayList<Object[]>) data[0]);
		this.indexPage = 0;
		if ((int) data[1] == 1) {
			this.jLabelWinnerTeam.setText("<html>Equipo <b style='color:blue;'>" + data[1] + "</b> ganador</html>");
			this.setBackground(new Color(217,222,255));
		} else {
			this.jLabelWinnerTeam.setText("<html>Equipo <b style='color:red;'>" + data[1] + "</b> ganador</html>");
			this.setBackground(new Color(255,217,217));
		}
		this.jLabelWinnerTeamPoints.setText("<html>Puntos Totales Obtenidos: <b>" + data[2] + "pts.</b></html>");
		changePage();
	}

	private void configureButton(JButton button, boolean b) {
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		if (b) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
					button.setIcon(new ImageIcon(getClass().getResource("/res/arrowroundrighthover.png")));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					button.setIcon(new ImageIcon(getClass().getResource("/res/arrowroundright.png")));
				}
			});
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if ((indexPage+1) < gameData.size()) {
						indexPage++;
						changePage();
					}else {
						indexPage = 10;
						changePage(1);
					}
				}
			});
		} else {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
					button.setIcon(new ImageIcon(getClass().getResource("/res/arrowroundlefthover.png")));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					button.setIcon(new ImageIcon(getClass().getResource("/res/arrowroundleft.png")));
				}
			});
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
		this.setOpaque(false);
		this.updateUI();
		this.jLabelIndexPages.setForeground(Color.WHITE);
		this.jlabelRound.setText("<html><b>Ronda " + (indexPage + 1) + "</b></html>");
		this.jLabelIndexPages.setVisible(true);
		this.jlabelScoreBlue.setVisible(true);
		this.jlabelScoreRed.setVisible(true);
		this.jlabelRound.setVisible(true);
		this.jlabelWinner.setVisible(true);
		this.jlabelPlayerWinner.setVisible(true);
		this.buttonBack.setVisible(true);
		this.buttonNext.setVisible(true);

		this.add(jlabelScoreBlue).setBounds(820 * JFrameMain.WIDTH_SCREEN / 1920, 40 * JFrameMain.HEIGHT_SCREEN / 1080,
				70 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jlabelScoreRed).setBounds(920 * JFrameMain.WIDTH_SCREEN / 1920, 40 * JFrameMain.HEIGHT_SCREEN / 1080,
				70 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jlabelRound).setBounds(860 * JFrameMain.WIDTH_SCREEN / 1920, 230 * JFrameMain.HEIGHT_SCREEN / 1080,
				100 * JFrameMain.WIDTH_SCREEN / 1920, 85 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jlabelWinner).setBounds(840 * JFrameMain.WIDTH_SCREEN / 1920, 430 * JFrameMain.HEIGHT_SCREEN / 1080,
				145 * JFrameMain.WIDTH_SCREEN / 1920, 85 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jlabelPlayerWinner).setBounds(840 * JFrameMain.WIDTH_SCREEN / 1920,
				630 * JFrameMain.HEIGHT_SCREEN / 1080, 140 * JFrameMain.WIDTH_SCREEN / 1920,
				85 * JFrameMain.HEIGHT_SCREEN / 1080);

		this.add(buttonBack).setBounds(0, 820 * JFrameMain.HEIGHT_SCREEN / 1080, 100 * JFrameMain.WIDTH_SCREEN / 1920,
				100 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(buttonNext).setBounds(1700 * JFrameMain.WIDTH_SCREEN / 1920, 820 * JFrameMain.HEIGHT_SCREEN / 1080,
				100 * JFrameMain.WIDTH_SCREEN / 1920, 100 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jLabelIndexPages).setBounds(880 * JFrameMain.WIDTH_SCREEN / 1920,
				865 * JFrameMain.HEIGHT_SCREEN / 1080, 100 * JFrameMain.WIDTH_SCREEN / 1920,
				50 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

	private void changePage(int... i) {
		removeComponents();
		if (i.length == 0) {
			addComponents();
			reConfigure();
		} else {
			addComponentsWinner();
		}
	}

	private void addComponentsWinner() {
		this.jLabelWinnerTeam.setVisible(true);
		this.jLabelWinnerTeamPoints.setVisible(true);
		this.jLabelIndexPages.setVisible(true);
		this.buttonBack.setVisible(true);
		this.buttonNext.setVisible(true);
		this.jLabelIndexPages.setForeground(Color.BLACK);
		this.setOpaque(true);
		this.jLabelIndexPages.setText(11 + " / " + 11);
		this.add(jLabelWinnerTeam).setBounds(0, 300 * JFrameMain.HEIGHT_SCREEN / 1080,
				1850 * JFrameMain.WIDTH_SCREEN / 1920, 150 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jLabelWinnerTeamPoints).setBounds(0, 450 * JFrameMain.HEIGHT_SCREEN / 1080,
				1850 * JFrameMain.WIDTH_SCREEN / 1920, 150 * JFrameMain.HEIGHT_SCREEN / 1080);
		
		this.add(buttonBack).setBounds(0, 820 * JFrameMain.HEIGHT_SCREEN / 1080, 100 * JFrameMain.WIDTH_SCREEN / 1920,
				100 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(buttonNext).setBounds(1700 * JFrameMain.WIDTH_SCREEN / 1920, 820 * JFrameMain.HEIGHT_SCREEN / 1080,
				100 * JFrameMain.WIDTH_SCREEN / 1920, 100 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jLabelIndexPages).setBounds(880 * JFrameMain.WIDTH_SCREEN / 1920,
				865 * JFrameMain.HEIGHT_SCREEN / 1080, 100 * JFrameMain.WIDTH_SCREEN / 1920,
				50 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

	private void reConfigure() {
		this.scoreGlobalBlue = ((int) ((Object[]) gameData.get(indexPage)[2])[0]);
		this.scoreGlobalRed = ((int) ((Object[]) gameData.get(indexPage)[2])[1]);
		this.winner = ((int) ((Object[]) gameData.get(indexPage)[2])[2]);
		this.pwinner = ((int) ((Object[]) gameData.get(indexPage)[2])[3]);
		this.jLabelIndexPages.setText((indexPage + 1) + " / " + 11);
		this.jlabelScoreBlue.setText("<html><b>" + scoreGlobalBlue + "</b></html>");
		this.jlabelScoreRed.setText("<html><b>" + scoreGlobalRed + "</b></html>");
		this.jlabelWinner
				.setText(winner == 0 ? "Empate" : "<html><center>Gana Ronda Equipo " + winner + "</center></html>");
		this.jlabelPlayerWinner.setText("<html><center>Ganador Individual P" + pwinner + "</center></html>");
		jPanelRoundBlue.setData(0, ((Object[]) gameData.get(indexPage)[0]));
		jPanelRoundRed.setData(1, ((Object[]) gameData.get(indexPage)[1]));
		jPanelRoundBlue.setVisible(true);
		jPanelRoundRed.setVisible(true);
		this.add(jPanelRoundBlue).setBounds(0, 0, 905 * JFrameMain.WIDTH_SCREEN / 1920,
				930 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.add(jPanelRoundRed).setBounds(905 * JFrameMain.WIDTH_SCREEN / 1920, 0,
				905 * JFrameMain.WIDTH_SCREEN / 1920, 930 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

	private void removeComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	public void setGameIndex(int indexPage) {
		this.indexPage = indexPage;
		changePage();
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style, Color color) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(color);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int height = getHeight() / 20;
		g2d.setColor(new Color(100, 100, 100, 20));

		for (int i = 0; i < 20; i++) {
			g2d.drawLine(5 * JFrameMain.WIDTH_SCREEN / 1920, height, getWidth() - (5 * JFrameMain.WIDTH_SCREEN / 1920),
					height);
			height += getHeight() / 20;
		}

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5 * JFrameMain.WIDTH_SCREEN / 1920));
		g2d.fillPolygon(
				new int[] { getWidth() / 2 - (70 * JFrameMain.WIDTH_SCREEN / 1920),
						getWidth() / 2 - (70 * JFrameMain.WIDTH_SCREEN / 1920),
						getWidth() / 2 - (50 * JFrameMain.WIDTH_SCREEN / 1920),
						getWidth() / 2 + (50 * JFrameMain.WIDTH_SCREEN / 1920),
						getWidth() / 2 + (70 * JFrameMain.WIDTH_SCREEN / 1920),
						getWidth() / 2 + (70 * JFrameMain.WIDTH_SCREEN / 1920) },
				new int[] { getHeight(), getHeight() - (50 * JFrameMain.HEIGHT_SCREEN / 1080),
						getHeight() - (70 * JFrameMain.HEIGHT_SCREEN / 1080),
						getHeight() - (70 * JFrameMain.HEIGHT_SCREEN / 1080),
						getHeight() - (50 * JFrameMain.HEIGHT_SCREEN / 1080), getHeight() },
				6);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawLine(getWidth() / 2 - (110 * JFrameMain.WIDTH_SCREEN / 1920), 0,
				getWidth() / 2 - (110 * JFrameMain.WIDTH_SCREEN / 1920), 8 * JFrameMain.HEIGHT_SCREEN / 1080);
		g2d.drawLine(getWidth() / 2 + (110 * JFrameMain.WIDTH_SCREEN / 1920), 0,
				getWidth() / 2 + (110 * JFrameMain.WIDTH_SCREEN / 1920), 8 * JFrameMain.HEIGHT_SCREEN / 1080);
		g2d.drawLine(getWidth() / 2 - (110 * JFrameMain.WIDTH_SCREEN / 1920), 8 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() / 2 + (110 * JFrameMain.WIDTH_SCREEN / 1920), 8 * JFrameMain.HEIGHT_SCREEN / 1080);

		g2d.drawImage(images[2].getImage(), getWidth() / 2 - (100 * JFrameMain.WIDTH_SCREEN / 1920),
				5 * JFrameMain.HEIGHT_SCREEN / 1080, 100 * JFrameMain.WIDTH_SCREEN / 1920,
				120 * JFrameMain.HEIGHT_SCREEN / 1080, this);
		g2d.drawImage(images[3].getImage(), getWidth() / 2, 5 * JFrameMain.HEIGHT_SCREEN / 1080,
				(100 * JFrameMain.WIDTH_SCREEN / 1920), 120 * JFrameMain.HEIGHT_SCREEN / 1080, this);

		g2d.drawImage(images[4].getImage(), getWidth() / 2 - (90 * JFrameMain.WIDTH_SCREEN / 1920),
				200 * JFrameMain.HEIGHT_SCREEN / 1080, 180 * JFrameMain.WIDTH_SCREEN / 1920,
				150 * JFrameMain.HEIGHT_SCREEN / 1080, this);

		g2d.drawImage(images[5].getImage(), getWidth() / 2 - (110 * JFrameMain.WIDTH_SCREEN / 1920),
				400 * JFrameMain.HEIGHT_SCREEN / 1080, 220 * JFrameMain.WIDTH_SCREEN / 1920,
				140 * JFrameMain.HEIGHT_SCREEN / 1080, this);

		g2d.drawImage(images[5].getImage(), getWidth() / 2 - (110 * JFrameMain.WIDTH_SCREEN / 1920),
				600 * JFrameMain.HEIGHT_SCREEN / 1080, 220 * JFrameMain.WIDTH_SCREEN / 1920,
				140 * JFrameMain.HEIGHT_SCREEN / 1080, this);
		super.paint(g);
	}

}
