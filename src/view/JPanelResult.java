package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.utils.Constants;
import view.utils.JTableResult;

@SuppressWarnings("unchecked")
public class JPanelResult extends JPanel {

	private JLabel jLabelTitle;
	private JLabel jLabelTitle2;
	private GridBagConstraints gbc;

	private JPanel jPanelContentLeft;
	private JLabel jLabelWinner;
	private JLabel jLabelPoints;
	private JTableResult jTableResult;
	private JTableResult jTableInitial;

	private JPanel jPanelContentRight;
	private JLabel jLabelGraph;
	private JLabel jLabelDescGraph;
	private JButton jButtonBack;
	private JButton jButtonSeeGames;

	private double[] points;

	
	public JPanelResult(Object[] data) {
		super(new GridBagLayout());
		String winner = "Equipo "+data[0];
		String points = data[1]+" pts.";
		ArrayList<Object[]> results = (ArrayList<Object[]>) data[3];
		ArrayList<Object[]> initial = (ArrayList<Object[]>) data[4];
		String[] columnNames = new String[] { "Juego", "Jugador con más suerte", "Jugador con más experiencia",
				"Género con más victorias" };
		String[] columnNames2 = new String[] { "Jugador", "Resistencia", "Género" };

		this.points = (double[]) data[2]; 
		this.jLabelTitle2 = new JLabel("<html><b>" + Constants.TITLE_RESULT2 + "</b></html>", JLabel.CENTER);
		this.jLabelTitle = new JLabel("<html><b>" + Constants.TITLE_RESULT + "</b></html>", JLabel.CENTER);
		this.gbc = new GridBagConstraints();
		this.jPanelContentLeft = new JPanel(new GridBagLayout());
		this.jPanelContentRight = new JPanel(new GridBagLayout());
		this.jLabelWinner = new JLabel(
				"<html><p style='text-align:center;'><b>" + Constants.WINNER + "</b>" + winner + "</p></html>",
				JLabel.CENTER);
		this.jLabelPoints = new JLabel(
				"<html><p style='text-align:center;'><b>" + Constants.SCORE + "</b>" + points + "</p></html>",
				JLabel.CENTER);
		this.jTableResult = new JTableResult(results, columnNames, 0);
		this.jTableInitial = new JTableResult(initial, columnNames2, 1);
		this.jLabelGraph = new JLabel();
		this.jLabelDescGraph = new JLabel(Constants.DESC_GRAPH, JLabel.CENTER);
		this.jButtonBack = new JButton(Constants.BTNBACK);
		this.jButtonSeeGames = new JButton(Constants.BTNSEEGAMES);
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelContentLeft.setOpaque(false);
		this.jPanelContentRight.setOpaque(false);

		configureLabel(jLabelTitle, Constants.FONT_APP, 60 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, true);
		configureLabel(jLabelTitle2, Constants.FONT_APP, 60 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, true);
		configureLabel(jLabelWinner, Constants.FONT_APP, 40 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, false);
		configureLabel(jLabelPoints, Constants.FONT_APP, 40 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, false);
		configureLabel(jLabelDescGraph, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, Font.ITALIC, false);

		configureButton(jButtonBack, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, false);
		configureButton(jButtonSeeGames, Constants.FONT_APP, 30 * JFrameMain.WIDTH_SCREEN / 1920, true);

		addComponents();
	}

	private void configureButton(JButton jButton, String font, int size, boolean p) {
		jButton.setFont(new Font(font, Font.PLAIN, size));
		jButton.setForeground(Color.WHITE);
		jButton.setFocusPainted(false);
		jButton.setPreferredSize(new Dimension(0, 50 * JFrameMain.HEIGHT_SCREEN / 1080));
		jButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		if (p) {
			jButton.setBackground(new Color(91, 155, 213));
			jButton.setBorder(new LineBorder(new Color(65, 113, 156)));
			jButton.addActionListener(JFrameMain.getInstance());
			jButton.setActionCommand("juegos");
		} else {
			jButton.setBackground(new Color(255, 153, 153));
			jButton.setBorder(new LineBorder(new Color(255, 0, 102)));
			jButton.setActionCommand("atras");
			jButton.addActionListener(JFrameMain.getInstance());
		}
	}

	private void addComponents() {
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets.top = 55 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.insets.left = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		this.add(jLabelTitle, gbc);
		gbc.insets.left = 0;
		gbc.insets.right = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.gridx = 1;
		this.add(jLabelTitle2, gbc);
		gbc.insets.left = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.gridx = 0;
		gbc.insets.top = 20 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.insets.right = 10 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.bottom = 100 * JFrameMain.HEIGHT_SCREEN / 1080;
		this.add(jPanelContentLeft, gbc);
		gbc.insets.left = 10 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.right = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.gridx = 1;
		this.add(jPanelContentRight, gbc);
		addComponentsLeft();
		addComponentsRight();
	}

	private void addComponentsRight() {
		this.jPanelContentRight.setPreferredSize(new Dimension(500, 0));
		this.jTableInitial.setPreferredSize(new Dimension(500, 300));

		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.gridwidth = 2;
		this.jPanelContentRight.add(jTableInitial, gbc);
		gbc.insets.top = 30 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.gridy = 1;
		generateGraph(points);
		this.jPanelContentRight.add(jLabelGraph, gbc);
		gbc.insets.top = 0;
		gbc.gridy = 2;
		this.jPanelContentRight.add(jLabelDescGraph, gbc);
		gbc.insets.top = 40 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.insets.left = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.right = 50 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		this.jPanelContentRight.add(jButtonBack, gbc);
		gbc.insets.right = 100 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.insets.left = 50 * JFrameMain.WIDTH_SCREEN / 1920;
		gbc.gridx = 1;
		this.jPanelContentRight.add(jButtonSeeGames, gbc);
	}

	private void addComponentsLeft() {
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.fill = 1;
		this.jPanelContentLeft.add(jTableResult, gbc);
		gbc.insets.top = 5 * JFrameMain.HEIGHT_SCREEN / 1080;
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.jPanelContentLeft.add(jLabelWinner, gbc);
		gbc.gridx = 1;
		this.jPanelContentLeft.add(jLabelPoints, gbc);
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style, boolean b) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(Color.BLACK);
		if (b) {
			jLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		}
	}

	private void generateGraph(double[] points) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		fillDataSet(dataset, points);
		JFreeChart jFreeChartBar = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false,
				false, true);
		jFreeChartBar.removeLegend();
		CategoryPlot plot = jFreeChartBar.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setOutlineVisible(false);
		BufferedImage img = jFreeChartBar.createBufferedImage(800 * JFrameMain.WIDTH_SCREEN / 1920,
				370 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jLabelGraph = new JLabel(new ImageIcon(img));
	}

	private void fillDataSet(DefaultCategoryDataset dataset, double[] points) {
		for (int i = 0; i < points.length; i++) {
			dataset.setValue(points[i], "", Double.valueOf(i+1));
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 240));
		g2d.fillRoundRect(50 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (100 * JFrameMain.HEIGHT_SCREEN / 1080),
				getHeight() - (100 * JFrameMain.HEIGHT_SCREEN / 1080), 45 * JFrameMain.WIDTH_SCREEN / 1920,
				45 * JFrameMain.WIDTH_SCREEN / 1920);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(50 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (100 * JFrameMain.HEIGHT_SCREEN / 1080),
				getHeight() - (100 * JFrameMain.HEIGHT_SCREEN / 1080), 45 * JFrameMain.WIDTH_SCREEN / 1920,
				45 * JFrameMain.WIDTH_SCREEN / 1920);
		super.paint(g);
	}
}
