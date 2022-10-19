package view.games;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import view.JFrameMain;
import view.utils.Constants;

public class JPanelContent extends JPanel {

	private JPanelGame jPanelGame;
	private JPanel jPanelContainerGame;
	private int indexPage;
	private JButton buttonBack;
	private JButton buttonNext;
	private JTextField jTextFieldIndex;
	private JLabel jLabelIndexes;
	private Object[] gamesData;

	private GridBagConstraints gbc;

	public JPanelContent(Object[] gamesData, ImageIcon[] images) {
		super(new GridBagLayout());
		this.gamesData = gamesData;
		this.indexPage = 0;
		this.jPanelGame = new JPanelGame((Object[])gamesData[0], images);
		this.buttonBack = new JButton(new ImageIcon(getClass().getResource("/res/arrowleft.png")));
		this.buttonNext = new JButton(new ImageIcon(getClass().getResource("/res/arrowright.png")));
		this.jTextFieldIndex = new JTextField((indexPage + 1) + " ");
		this.jLabelIndexes = new JLabel("/ " + (gamesData.length - 1));
		this.jPanelContainerGame = new JPanel(new GridLayout());
		this.gbc = new GridBagConstraints();
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelContainerGame.setOpaque(false);
		configureButtons(buttonBack, false);
		configureButtons(buttonNext, true);
		configureLabel(jLabelIndexes, Constants.FONT_APP, 30, Font.ITALIC);
		configureField(jTextFieldIndex, Constants.FONT_APP, 30, Font.ITALIC);
		addComponents();
		changePanel();
	}

	private void changePanel() {
		removeComponents(jPanelContainerGame);
		jPanelGame.setVisible(true);
		jPanelGame.setGameData((Object[]) gamesData[indexPage]);
		this.jPanelContainerGame.add(jPanelGame);
	}

	private void removeComponents(Container container) {
		for (int i = 0; i < container.getComponentCount(); i++) {
			container.getComponent(i).setVisible(false);
		}
		container.removeAll();
	}

	private void configureField(JTextField jTextField, String font, int size, int style) {
		jTextField.setFont(new Font(font, style, size));
		jTextField.setForeground(Color.WHITE);
		jTextField.setBorder(BorderFactory.createEmptyBorder());
		jTextField.setOpaque(false);
		jTextField.setPreferredSize(new Dimension(80, 30));
		jTextField.setHorizontalAlignment(JTextField.RIGHT);
		jTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = jTextField.getText();
				if ((int) e.getKeyChar() == 8 && text.length() >= 1) {
					if (text.length() != 1) {
						jTextField.setText(jTextField.getText().substring(0, jTextField.getText().length() - 1) + " ");
						indexPage = Integer.parseInt(jTextField.getText().replace(" ", ""));
						changePanel();
					} else {
						jTextField.setText("1 ");
						indexPage = 0;
						changePanel();
					}
				}
				if (Character.isDigit((int) e.getKeyChar())) {
					if (Integer.parseInt(jTextField.getText().replace(" ", "") + e.getKeyChar()) <= gamesData.length
							- 1) {
						jTextField.setText(jTextField.getText().replace(" ", "") + e.getKeyChar() + " ");
						indexPage = Integer.parseInt(jTextField.getText().replace(" ", ""));
						changePanel();
					}
					e.consume();
				} else {
					e.consume();
				}
			}
		});
	}

	private void configureLabel(JLabel jLabel, String font, int size, int style) {
		jLabel.setFont(new Font(font, style, size));
		jLabel.setForeground(Color.WHITE);
		jLabel.setPreferredSize(new Dimension(90, 30));
	}

	private void configureButtons(JButton button, boolean b) {
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		if (b) {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (indexPage < gamesData.length-2) {
						jTextFieldIndex.setText(((++indexPage) + 1) + " ");
						changePanel();
					}
				}
			});
		} else {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (indexPage != 0) {
						jTextFieldIndex.setText(((--indexPage) + 1) + " ");
						changePanel();
					}
				}
			});
		}
	}

	private void addComponents() {
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = 1;
		gbc.gridwidth = 4;
		gbc.insets = new Insets(50, 55, 0, 55);
		this.add(jPanelContainerGame, gbc);
		gbc.gridwidth = 1;
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbc.insets.right = 0;
		this.add(buttonBack, gbc);
		gbc.insets.left = 0;
		gbc.weightx = 0;
		gbc.gridx = 1;
		this.add(jTextFieldIndex, gbc);
		gbc.gridx = 2;
		this.add(jLabelIndexes, gbc);
		gbc.weightx = 1;
		gbc.gridx = 3;
		gbc.insets.right = 55;
		this.add(buttonNext, gbc);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 200));
		g2d.fillRoundRect(55 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (110 * JFrameMain.HEIGHT_SCREEN / 1080),
				getHeight() - (155 * JFrameMain.HEIGHT_SCREEN / 1080), 45 * JFrameMain.WIDTH_SCREEN / 1920,
				45 * JFrameMain.WIDTH_SCREEN / 1920);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(55 * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080,
				getWidth() - (110 * JFrameMain.HEIGHT_SCREEN / 1080),
				getHeight() - (155 * JFrameMain.HEIGHT_SCREEN / 1080), 45 * JFrameMain.WIDTH_SCREEN / 1920,
				45 * JFrameMain.WIDTH_SCREEN / 1920);
		g2d.drawLine(getWidth() / 2, 50 * JFrameMain.HEIGHT_SCREEN / 1080, getWidth() / 2,
				getHeight() - (110 * JFrameMain.HEIGHT_SCREEN / 1080));
		super.paint(g);
	}
}
