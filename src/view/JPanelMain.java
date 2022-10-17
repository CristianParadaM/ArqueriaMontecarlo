package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.games.JPanelContent;

public class JPanelMain extends JPanel {

	private ImageIcon background;
	private JPanelStart jPanelStart;
	private JPanelResult jPanelResult;
	private JPanelContent jPanelContent;
	private ImageIcon imageFlagBlue;
	private ImageIcon imageFlagRed;
	private ImageIcon imageBackRound;
	private ImageIcon imageNextRound;
	private ImageIcon imagePaperBig;
	private ImageIcon imagePaperLittle;
	private ImageIcon[] images;

	public JPanelMain() {
		super(new GridLayout());
		this.background = new ImageIcon(getClass().getResource("/res/background3.jpg"));
		this.jPanelStart = new JPanelStart();
		this.imageFlagBlue = new ImageIcon(getClass().getResource("/res/flagblue.png"));
		this.imageFlagRed = new ImageIcon(getClass().getResource("/res/flagred.png"));
		this.imagePaperLittle = new ImageIcon(getClass().getResource("/res/paperlittle.png"));
		this.imagePaperBig = new ImageIcon(getClass().getResource("/res/paperbig.png"));
		this.imageBackRound = new ImageIcon(new ImageIcon(getClass().getResource("/res/arrowroundleft.png")).getImage()
				.getScaledInstance(50  * JFrameMain.WIDTH_SCREEN / 1920, 50* JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH));
		this.imageNextRound = new ImageIcon(new ImageIcon(getClass().getResource("/res/arrowroundright.png")).getImage()
				.getScaledInstance(50  * JFrameMain.WIDTH_SCREEN / 1920, 50 * JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH));
		this.images = new ImageIcon[] { imageBackRound, imageNextRound, imageFlagBlue, imageFlagRed, imagePaperLittle,
				imagePaperBig };
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

	public void changeTo(int option, Object[] data) {
		removeComponents();
		switch (option) {
		case 0:
			this.jPanelStart.setVisible(true);
			this.add(jPanelStart);
			break;
		case 1:
			this.jPanelResult = new JPanelResult((Object[]) data[data.length-1]);
			this.jPanelContent = new JPanelContent(data, images);
			this.add(jPanelResult);
			break;
		case 2:
			JFrameMain.getInstance().showBackButton(true);
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
		changeTo(0, null);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.setColor(new Color(0, 0, 0, 0.65f));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paint(g);
	}

}
