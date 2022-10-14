package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.games.JPanelContent;

public class JPanelMain extends JPanel {

	private ImageIcon background;
	private JPanelStart jPanelStart;
	private JPanelResult jPanelResult;
	private JPanelContent jPanelContent;

	public JPanelMain() {
		super(new GridLayout());
		this.background = new ImageIcon(getClass().getResource("/res/background3.jpg"));
		this.jPanelStart = new JPanelStart();
		this.jPanelResult = new JPanelResult();
		this.jPanelContent = new JPanelContent();
		init();
		animate();
	}

	private void animate() {
		new Thread(() -> {
			while (true) {
				try {
					this.background = new ImageIcon(getClass().getResource("/res/background2.jpg"));
					this.updateUI();
					Thread.sleep(7000);
					this.background = new ImageIcon(getClass().getResource("/res/background.jpg"));
					this.updateUI();
					Thread.sleep(7000);
					this.background = new ImageIcon(getClass().getResource("/res/background3.jpg"));
					this.updateUI();
					Thread.sleep(7000);
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	public void changeTo(int option) {
		removeComponents();
		switch (option) {
			case 0:
				this.jPanelStart.setVisible(true);
				this.add(jPanelStart);
				break;
			case 1:
				this.jPanelResult.setVisible(true);
				this.add(jPanelResult);
				break;
			case 2:
				this.jPanelContent.setVisible(true);
				this.add(jPanelContent);
				break;
		}
		this.updateUI();
	}

	private void removeComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	private void init() {
		this.setOpaque(false);
		changeTo(0);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.setColor(new Color(0,0,0,0.65f));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paint(g);
	}

}
