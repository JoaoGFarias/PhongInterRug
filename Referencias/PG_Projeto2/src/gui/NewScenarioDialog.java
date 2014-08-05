package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

public class NewScenarioDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldObject;
	private JTextField textFieldCamera;
	private JTextField textFieldIlumination;
	
	//Parent
	private MainFrame parentFrame;
	
	//File choosers;
	private JFileChooser objectChooser;
	private JFileChooser cameraChooser;
	private JFileChooser iluminationChooser;
	

	

	/**
	 * Create the dialog.
	 */
	public NewScenarioDialog(MainFrame parentFrame) {
		this.parentFrame = parentFrame;	
		setBounds(100, 100, 453, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel labelObject = new JLabel("Object File:");
			labelObject.setBounds(10, 26, 94, 14);
			contentPanel.add(labelObject);
		}
		{
			JLabel labelCamera = new JLabel("Camera File:");
			labelCamera.setBounds(10, 51, 94, 14);
			contentPanel.add(labelCamera);
		}
		{
			JLabel labelIlumination = new JLabel("Ilumination File:");
			labelIlumination.setBounds(10, 76, 94, 14);
			contentPanel.add(labelIlumination);
		}
		{
			textFieldObject = new JTextField();
			textFieldObject.setColumns(10);
			textFieldObject.setBounds(108, 23, 221, 20);
			contentPanel.add(textFieldObject);
		}
		{
			objectChooser = new JFileChooser();
			objectChooser.setCurrentDirectory(new File("."));
			JButton buttonObject = new JButton("Select");
			buttonObject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int returnVal = objectChooser.showOpenDialog(NewScenarioDialog.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            NewScenarioDialog.this.textFieldObject.setText(objectChooser.getSelectedFile().getAbsolutePath());
			            //This is where a real application would open the file.
			           // log.append("Opening: " + file.getName() + "." + newline);
			        } else {
			           // log.append("Open command cancelled by user." + newline);
			        }
				}
			});
			buttonObject.setBounds(338, 23, 89, 23);
			contentPanel.add(buttonObject);
		}
		{
			textFieldCamera = new JTextField();
			textFieldCamera.setColumns(10);
			textFieldCamera.setBounds(108, 48, 221, 20);
			contentPanel.add(textFieldCamera);
		}
		{
			cameraChooser = new JFileChooser();
			cameraChooser.setCurrentDirectory(new File("."));
			JButton buttonCamera = new JButton("Select");
			buttonCamera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int returnVal = cameraChooser.showOpenDialog(NewScenarioDialog.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            NewScenarioDialog.this.textFieldCamera.setText(cameraChooser.getSelectedFile().getAbsolutePath());
			            //This is where a real application would open the file.
			           // log.append("Opening: " + file.getName() + "." + newline);
			        } else {
			           // log.append("Open command cancelled by user." + newline);
			        }
				}
			});
			buttonCamera.setBounds(338, 48, 89, 23);
			contentPanel.add(buttonCamera);
		}
		{
			textFieldIlumination = new JTextField();
			textFieldIlumination.setColumns(10);
			textFieldIlumination.setBounds(108, 76, 221, 20);
			contentPanel.add(textFieldIlumination);
		}
		{
			iluminationChooser = new JFileChooser();
			iluminationChooser.setCurrentDirectory(new File("."));
			JButton buttonIlumination = new JButton("Select");
			buttonIlumination.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int returnVal = iluminationChooser.showOpenDialog(NewScenarioDialog.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            NewScenarioDialog.this.textFieldIlumination.setText(iluminationChooser.getSelectedFile().getAbsolutePath());
			            //This is where a real application would open the file.
			           // log.append("Opening: " + file.getName() + "." + newline);
			        } else {
			           // log.append("Open command cancelled by user." + newline);
			        }
				}
			});
			buttonIlumination.setBounds(338, 73, 89, 23);
			contentPanel.add(buttonIlumination);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String pathIluminacao;
							String pathCamera;
							String pathObjeto;
							
							if (NewScenarioDialog.this.textFieldIlumination.getText() == null || NewScenarioDialog.this.textFieldIlumination.getText().isEmpty()){
								pathIluminacao = NewScenarioDialog.this.parentFrame.getFachada().getPathIluminacao();
							} else {
								pathIluminacao = NewScenarioDialog.this.textFieldIlumination.getText();
							}
							
							if (NewScenarioDialog.this.textFieldCamera.getText() == null || NewScenarioDialog.this.textFieldCamera.getText().isEmpty()){
								pathCamera = NewScenarioDialog.this.parentFrame.getFachada().getPathCamera();
							} else {
								pathCamera = NewScenarioDialog.this.textFieldCamera.getText();
							}
							
							if (NewScenarioDialog.this.textFieldObject.getText() == null || NewScenarioDialog.this.textFieldObject.getText().isEmpty()){
								pathObjeto = NewScenarioDialog.this.parentFrame.getFachada().getPathObjeto();
							} else {
								pathObjeto = NewScenarioDialog.this.textFieldObject.getText();
							}
							
							
							NewScenarioDialog.this.parentFrame.getFachada().loadFiles(pathIluminacao, pathObjeto, pathCamera);
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						NewScenarioDialog.this.parentFrame.redrawEverything();
						NewScenarioDialog.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewScenarioDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
