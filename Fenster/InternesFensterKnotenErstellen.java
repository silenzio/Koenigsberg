package Fenster;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Query.QueryKnotenErstellen;;

public class InternesFensterKnotenErstellen extends InternesFenster{

	private static final long serialVersionUID = 1L;

	private JInternalFrame internesFensterKnotenErstellen;
	private JPanel kommandoKnoepfePanel;
	private JPanel knotenErstellenPanel;
	private JLabel knotenBezeichnerIntern;
	private JLabel knotenIdentifikator;
	private JTextField knotenEigenschaftenSchluessel;
	private JTextField knotenEigenschaftenWert;
	private JTextField knotenEigenschaftenSchluessel_2;
	private JTextField knotenEigenschaftenWert_2;
	private JTextField knotenBezeichner;
	private JTextField knotenTitle;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;
		
	public InternesFensterKnotenErstellen() {
		internesFensterKnotenErstellen = new InternesFenster();
		kommandoKnoepfePanel  = new JPanel();
		knotenErstellenPanel  = new JPanel();
//		knotenBezeichnerIntern  = new JLabel("Knoten-Bezeichner: ");
		knotenIdentifikator = new JLabel("Knoten-Titel: ");
		knotenEigenschaftenSchluessel = new JTextField("(*)");
		knotenEigenschaftenWert = new JTextField("(*)");	
		knotenEigenschaftenSchluessel_2 = new JTextField("(*)");
		knotenEigenschaftenWert_2 = new JTextField("(*)");	
//		knotenBezeichner = new JTextField("Knoten_Bezeichner");
		knotenTitle = new JTextField("(*)");
	}
	public JInternalFrame getInternesFensterKnotenErstellen() {
		return internesFensterKnotenErstellen;
	}
	public void setInternesFensterKnotenErstellen(JInternalFrame internesFensterKnotenErstellen) {
		this.internesFensterKnotenErstellen = internesFensterKnotenErstellen;
	}
	public JInternalFrame erstelleFensterKnotenErstellen(final JInternalFrame internerFrame){
		((InternesFensterKnotenErstellen) internerFrame).erstelleDialogFenster(internerFrame);
			JButton ok = new JButton("Okay");
			JButton abbr = new JButton("Abbrechen");
			kommandoKnoepfePanel.setLayout(new GridLayout());
			kommandoKnoepfePanel.add(ok);
			kommandoKnoepfePanel.add(abbr);
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() != null){
						QueryKnotenErstellen _qkne = new QueryKnotenErstellen(); 
						_qkne.verbinden();
						try{
							setDataLoad(_qkne.einenKnotenErstellen(
//														knotenBezeichner.getText(), 
													knotenTitle.getText(), 
													knotenEigenschaftenSchluessel.getText(),
													knotenEigenschaftenWert.getText(),
													knotenEigenschaftenSchluessel_2.getText(),
													knotenEigenschaftenWert_2.getText()
													));
							for(int i=0; i< getDataLoad().size(); i++){
								String befehl_teil = getDataLoad().get(i);
								letzterBefehl=""+letzterBefehl+befehl_teil;
								setLetzterBefehl(letzterBefehl);
							}
							setEnd_befehl(letzterBefehl);
							Fenster.Hauptfenster.getTxtConsole().setText(getEnd_befehl());
							internerFrame.dispose();
							} catch (SQLException e1) {
							e1.printStackTrace();
							_qkne.verbindungTrennen();
						}
					}
				}	
			});
			abbr.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() != null){
						internerFrame.dispose();
					}	
				}
			});
			GridBagLayout raster = new GridBagLayout();
			knotenErstellenPanel.setLayout(raster);
			GridBagConstraints grenzen = new GridBagConstraints();
			raster.setConstraints(knotenErstellenPanel, grenzen);
				grenzen.fill = GridBagConstraints.HORIZONTAL;
				grenzen.gridx = 1; 
		    	grenzen.gridy = 1; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    raster.setConstraints(knotenIdentifikator, grenzen);
			knotenErstellenPanel.add(knotenIdentifikator);
				grenzen.gridx = 2; 
		    	grenzen.gridy = 1; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5;
			raster.setConstraints(knotenTitle, grenzen);
			knotenErstellenPanel.add(knotenTitle);
//				grenzen.gridx = 1; 
//		    	grenzen.gridy = 2; 
//		    	grenzen.ipadx = 5; 
//		    	grenzen.ipady = 5; 
//		    raster.setConstraints(knotenEigenschaftenSchluessel, grenzen);
//			knotenErstellenPanel.add(knotenEigenschaftenSchluessel);
//				grenzen.gridx = 2; 
//		    	grenzen.gridy = 2; 
//		    	grenzen.ipadx = 5; 
//		    	grenzen.ipady = 5; 
//		    raster.setConstraints(knotenEigenschaftenWert, grenzen);
//			knotenErstellenPanel.add(knotenEigenschaftenWert);
				grenzen.gridx = 1; 
		    	grenzen.gridy = 3; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    raster.setConstraints(knotenEigenschaftenSchluessel, grenzen);
			knotenErstellenPanel.add(knotenEigenschaftenSchluessel);
				grenzen.gridx = 2; 
		    	grenzen.gridy = 3; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    raster.setConstraints(knotenEigenschaftenWert, grenzen);
			knotenErstellenPanel.add(knotenEigenschaftenWert);
				grenzen.gridx = 1; 
	    		grenzen.gridy = 4; 
	    		grenzen.ipadx = 5; 
	    		grenzen.ipady = 5; 
		   	raster.setConstraints(knotenEigenschaftenSchluessel_2, grenzen);
			knotenErstellenPanel.add(knotenEigenschaftenSchluessel_2);
				grenzen.gridx = 2; 
	    		grenzen.gridy = 4; 
	    		grenzen.ipadx = 5; 
	    		grenzen.ipady = 5; 
			raster.setConstraints(knotenEigenschaftenWert_2, grenzen);
			knotenErstellenPanel.add(knotenEigenschaftenWert_2);
			internerFrame.add(kommandoKnoepfePanel, BorderLayout.SOUTH);
			internerFrame.add(knotenErstellenPanel, BorderLayout.CENTER);
			internerFrame.setVisible(true);
			setInternesFensterKnotenErstellen(internerFrame);
		return internerFrame;
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){
		internerFrame.setClosable(true);
		internerFrame.setTitle("Knoten erstellen");
		internerFrame.setBounds(550, 50, 400, 300);
		internerFrame.getContentPane().setLayout(new BorderLayout());
		internerFrame.add(erstelleKommandoKnoepfe(internerFrame),BorderLayout.SOUTH);
		internerFrame.setVisible(true);
		return internerFrame;
	}
	public JPanel erstelleKommandoKnoepfe(final JInternalFrame internerFrame){
		JPanel kommandoKnoepfePanel=new JPanel();
		JButton ok = new JButton("Okay");
		JButton abbr = new JButton("Abbrechen");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {}});
		abbr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					internerFrame.dispose();}}});
		kommandoKnoepfePanel.setLayout(new GridLayout());
		kommandoKnoepfePanel.add(ok);
		kommandoKnoepfePanel.add(abbr);
		return kommandoKnoepfePanel;
	}
	public String getLetzterBefehl() {
		return letzterBefehl;
	}
	public void setLetzterBefehl(String letzterBefehl) {
		this.letzterBefehl = letzterBefehl;
	}
	public List<String> getDataLoad() {
		return dataLoad;
	}
	public void setDataLoad(List<String> dataLoad) {
	
		this.dataLoad = dataLoad;
	}
	public JPanel getKommandoKnoepfePanel() {
		return kommandoKnoepfePanel;
	}
	public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
		this.kommandoKnoepfePanel = kommandoKnoepfePanel;
	}
	public JPanel getKnotenErstellenPanel() {
		return knotenErstellenPanel;
	}
	public void setKnotenErstellenPanel(JPanel knotenErstellenPanel) {
		this.knotenErstellenPanel = knotenErstellenPanel;
	}
	public JLabel getKnotenBezeichnerIntern() {
		return knotenBezeichnerIntern;
	}
	public void setKnotenBezeichnerIntern(JLabel knotenBezeichnerIntern) {
		this.knotenBezeichnerIntern = knotenBezeichnerIntern;
	}
	public JLabel getKnotenIdentifikator() {
		return knotenIdentifikator;
	}
	public void setKnotenIdentifikator(JLabel knotenIdentifikator) {
		this.knotenIdentifikator = knotenIdentifikator;
	}
	public JTextField getKnotenEigenschaftenSchluessel() {
		return knotenEigenschaftenSchluessel;
	}
	public void setKnotenEigenschaftenSchluessel(
			JTextField knotenEigenschaftenSchluessel) {
		this.knotenEigenschaftenSchluessel = knotenEigenschaftenSchluessel;
	}
	public JTextField getKnotenEigenschaftenWert() {
		return knotenEigenschaftenWert;
	}
	public void setKnotenEigenschaftenWert(JTextField knotenEigenschaftenWert) {
		this.knotenEigenschaftenWert = knotenEigenschaftenWert;
	}
	public JTextField getKnotenEigenschaftenSchluessel_2() {
		return knotenEigenschaftenSchluessel_2;
	}
	public void setKnotenEigenschaftenSchluessel_2(
			JTextField knotenEigenschaftenSchluessel_2) {
		this.knotenEigenschaftenSchluessel_2 = knotenEigenschaftenSchluessel_2;
	}
	public JTextField getKnotenEigenschaftenWert_2() {
		return knotenEigenschaftenWert_2;
	}
	public void setKnotenEigenschaftenWert_2(JTextField knotenEigenschaftenWert_2) {
		this.knotenEigenschaftenWert_2 = knotenEigenschaftenWert_2;
	}
	public JTextField getknotenBezeichner() {
		return knotenBezeichner;
	}
	public void setknotenBezeichner(JTextField knotenBezeichner) {
		this.knotenBezeichner = knotenBezeichner;
	}
	public JTextField getKnotenTitle() {
		return knotenTitle;
	}
	public void setKnotenTitle(JTextField knotenTitle) {
		this.knotenTitle = knotenTitle;
	}
	public String getEnd_befehl() {
		return end_befehl;
	}
	public void setEnd_befehl(String end_befehl) {
		this.end_befehl = end_befehl;
	}
	
}
