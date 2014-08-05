package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import state.Screen;

public class DrawPanel extends JPanel {

	private Screen screen;

	/**
	 * Create the panel.
	 */
	public DrawPanel(Screen screen) {
		this.screen = screen;
	}


	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (this.screen != null){
			screen.paintScreen(g);
		}

	}

}
