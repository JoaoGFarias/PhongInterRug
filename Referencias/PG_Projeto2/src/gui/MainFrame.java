package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import state.Screen;

import fachada.Fachada;
import gui.keybidings.CameraMoveCloserAction;
import gui.keybidings.CameraMoveDownAction;
import gui.keybidings.CameraMoveFurtherAction;
import gui.keybidings.CameraMoveLeftAction;
import gui.keybidings.CameraMoveRightAction;
import gui.keybidings.CameraMoveUpAction;
import gui.keybidings.CameraTurnDownAction;
import gui.keybidings.CameraTurnLeftAction;
import gui.keybidings.CameraTurnRightAction;
import gui.keybidings.CameraTurnUpAction;
import gui.keybidings.RefreshAction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JSeparator;

import data.Triangulo;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DrawPanel drawPanel;

	private Fachada fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Screen screen = new Screen(1300, 800);
		final Fachada fachada = new Fachada(screen);

		class Teste implements Runnable {
			private MainFrame frame;
			private Screen screen;

			public Teste(Screen screen) {
				this.screen = screen;
			}

			public void run() {
				try {
					frame = new MainFrame(fachada);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public MainFrame getFrame() {
				return frame;
			}

			public void setFrame(MainFrame frame) {
				this.frame = frame;
			}

		}

		Teste t = new Teste(screen);

		EventQueue.invokeLater(t);

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			t.getFrame().getFachada().loadFiles("iluminacao.txt", "Objetos/vaso.byu", "Cameras/animais.cfg");
//			t.getFrame().redrawEverything();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


	}

	/**
	 * Create the frame.
	 */
	public MainFrame(Fachada fachada) {
	
		this.fachada = fachada;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 554);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "refresh");
		this.contentPane.getActionMap().put("refresh", new RefreshAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "move camera down");
		this.contentPane.getActionMap().put("move camera down", new CameraMoveDownAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "move camera up");
		this.contentPane.getActionMap().put("move camera up", new CameraMoveUpAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "move camera left");
		this.contentPane.getActionMap().put("move camera left", new CameraMoveLeftAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "move camera right");
		this.contentPane.getActionMap().put("move camera right", new CameraMoveRightAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "move camera closer");
		this.contentPane.getActionMap().put("move camera closer", new CameraMoveCloserAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F"), "move camera further");
		this.contentPane.getActionMap().put("move camera further", new CameraMoveFurtherAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "turn camera right");
		this.contentPane.getActionMap().put("turn camera right", new CameraTurnRightAction(this));

		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "turn camera left");
		this.contentPane.getActionMap().put("turn camera left", new CameraTurnLeftAction(this));
		
		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "turn camera up");
		this.contentPane.getActionMap().put("turn camera up", new CameraTurnUpAction(this));

		this.contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "turn camera down");
		this.contentPane.getActionMap().put("turn camera down", new CameraTurnDownAction(this));
				
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewScenario = new JMenuItem("New Scenario");
		mntmNewScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewScenarioDialog dialog = new NewScenarioDialog(MainFrame.this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		});
		mnFile.add(mntmNewScenario);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmRefreshF = new JMenuItem("Refresh              F5");
		mntmRefreshF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fachada fachada = MainFrame.this.getFachada();
				try {
					//TODO verificar se os carinhas sao null, pq provavelemente gera nullPointerException
					fachada.loadFiles(fachada.getPathIluminacao(), fachada.getPathObjeto(), fachada.getPathCamera());
					MainFrame.this.redrawEverything();
				} catch (FileNotFoundException e) {
					// TODO JOptionPanel, file inexistente
					e.printStackTrace();
				}
				
			}
		});
		mnFile.add(mntmRefreshF);
		
		JMenuItem mntmFatorMultiplicativo = new JMenuItem("Rugosity Factor");
		mntmFatorMultiplicativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
				                    MainFrame.this,
				                    "Enter rugosity factor value:",
				                    "Factor dialog",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    null, Triangulo.getFATOR());
				
				if (s != null){
					double fator = Double.parseDouble(s);
					Triangulo.setFATOR(fator);
				}				
			}
		});
		mnFile.add(mntmFatorMultiplicativo);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);

		JMenu mnCamera = new JMenu("Camera");
		menuBar.add(mnCamera);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					MainFrame.openFile(MainFrame.this.getFachada()
							.getPathCamera());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCamera.add(mntmEdit);

		JMenu mnLightAndColor = new JMenu("Light and Color");
		menuBar.add(mnLightAndColor);

		JMenuItem mntmEdit_1 = new JMenuItem("Edit");
		mntmEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.openFile(MainFrame.this.getFachada()
							.getPathIluminacao());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnLightAndColor.add(mntmEdit_1);

		JMenu mnObject = new JMenu("Object");
		menuBar.add(mnObject);

		JMenuItem mntmEditFile = new JMenuItem("Edit File");
		mntmEditFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.openFile(MainFrame.this.getFachada()
							.getPathObjeto());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnObject.add(mntmEditFile);

		drawPanel = new DrawPanel(fachada.getScreen());
		drawPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(drawPanel, BorderLayout.CENTER);

	}

	public void redrawEverything() {
		this.fachada.getScreen().clear();
		this.fachada.fillAllTriangles();
		this.repaint();
		this.validate();
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public void setDrawPanel(DrawPanel drawPanel) {
		this.drawPanel = drawPanel;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public static void openFile(String path) throws IOException {
		File file = new File(path);
		if (file.exists()) {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				String cmd = "rundll32 url.dll,FileProtocolHandler "
						+ file.getCanonicalPath();
				Runtime.getRuntime().exec(cmd);
			} else {
				Desktop.getDesktop().edit(file);
			}
		} else {
			throw new FileNotFoundException();
		}
	}

}
