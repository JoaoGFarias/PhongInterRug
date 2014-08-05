package gui.keybidings;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import fachada.Fachada;
import gui.MainFrame;

import javax.swing.AbstractAction;

public class RefreshAction extends AbstractAction{

	private MainFrame target;
	
	public RefreshAction(MainFrame target) {
		this.target = target;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Fachada fachada = target.getFachada();
		try {
			fachada.loadFiles(fachada.getPathIluminacao(), fachada.getPathObjeto(), fachada.getPathCamera());
			target.redrawEverything();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
