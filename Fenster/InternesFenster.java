package Fenster;

import javax.swing.JInternalFrame;


public class InternesFenster extends JInternalFrame {

	private static final long serialVersionUID = 7032660585470899513L;
	private JInternalFrame internesFenster;
	public InternesFenster(){
		this.setInternesFenster(new JInternalFrame()); 
	}
	public JInternalFrame getInternesFenster() {
		return internesFenster;
	}
	public void setInternesFenster(JInternalFrame internesFenster) {
		this.internesFenster = internesFenster;
	}

}
