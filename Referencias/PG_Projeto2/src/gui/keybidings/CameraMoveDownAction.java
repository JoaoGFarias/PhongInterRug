package gui.keybidings;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import fachada.Fachada;
import gui.MainFrame;

import javax.swing.AbstractAction;

import algoritmos.CameraMover;

import data.Camera;

public class CameraMoveDownAction extends AbstractAction {
	private MainFrame target;

	public CameraMoveDownAction(MainFrame target) {
		this.target = target;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Fachada fachada = target.getFachada();
		Camera cam = fachada.getWorld().getCamera();
		if (cam != null) {
			CameraMover.moveDown(cam);

			try {
				fachada.refreshFileIluminacao();
				fachada.refreshFileObjeto();
				target.redrawEverything();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
