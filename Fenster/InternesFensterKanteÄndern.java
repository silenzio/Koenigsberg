package Fenster;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Query.QueryKanteÄndern;

public class InternesFensterKanteÄndern extends InternesFenster{
	private static final long serialVersionUID = 5L;
	private JInternalFrame internesFensterKanteÄndern;
	private JPanel kanteÄndernPanel;
	private JLabel kanteIdentifikation;
	private static JLabel kanteIdentifikator;
	private static JSplitPane splitteNorden;
	private JPanel kanteÄndernPanelErgebnisMenge;
	private JLabel kanteStartKnoten;
	private JLabel kanteEndKnoten;
	private JLabel kanteTypBeschreibung;
	private static JTextField startKnoten;
	private static JTextField endKnoten;
	private static JTextField kanteTyp;
	private static JTextField kanteEigenschaftenSchluessel;
	private static JTextField kanteEigenschaftenWert;
	private static JTextField kanteEigenschaftenSchluessel_2;
	private static JTextField kanteEigenschaftenWert_2;
	private static JButton kanteÄndernKnopf;
	private JPanel kommandoKnoepfePanel;
	private static JLabel instanzInfo;
	private static JTextPane ergebnisMenge;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;

	public InternesFensterKanteÄndern(){
		internesFensterKanteÄndern = new InternesFenster();
		setKanteÄndernPanel(new JPanel());
		setKanteIdentifikation(new JLabel("Kante Id: "));
		setKanteIdentifikator(new JLabel(""));
		setSplitteNorden(new JSplitPane());
		setKanteÄndernPanelErgebnisMenge(new JPanel());
		setKanteStartKnoten(new JLabel("Start Knoten:"));
		setKanteEndKnoten(new JLabel("Ende Knoten:"));
		setKanteTypBeschreibung(new JLabel("Kantentyp"));
		setStartKnoten(new JTextField("(*)"));
		setEndKnoten(new JTextField("(*)"));
		setKanteTyp(new JTextField("(*)"));
		setKanteEigenschaftenSchluessel(new JTextField("Eigenschaften_Schluessel"));
		setKanteEigenschaftenWert(new JTextField("Eigenschaften_Wert"));
		setKanteEigenschaftenSchluessel_2(new JTextField("Eigenschaften_Schluessel"));
		setKanteEigenschaftenWert_2(new JTextField("Eigenschaften_Wert"));
		setKanteÄndernKnopf(new JButton("Kante ändern"));
		getKanteÄndernKnopf().setVisible(false);
		setInstanzInfo(new JLabel("Object-Id"));
		setDataLoad(new ArrayList<String>());
		setKommandoKnoepfePanel(new JPanel());
	}
	public JInternalFrame erstelleFensterKanteÄndern(final InternesFensterKanteÄndern internerFrame){
			((InternesFensterKanteÄndern) internerFrame).erstelleDialogFenster(internerFrame);
			JButton ok = new JButton("Okay");
			JButton abbr = new JButton("Abbrechen");
			getKommandoKnoepfePanel().add(ok);
			getKommandoKnoepfePanel().add(abbr);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() != null){
						QueryKanteÄndern _qkaän = new QueryKanteÄndern(); 
						_qkaän.verbinden();
						try{
							setDataLoad( _qkaän.eineKanteÄndernVorbereiten(
										getStartKnoten().getText(),
										getKanteTyp().getText(),
										getEndKnoten().getText() 
													));
							for(int i=0; i< getDataLoad().size(); i++){
								String befehl_teil = getDataLoad().get(i);
								letzterBefehl=""+letzterBefehl+befehl_teil;
								setLetzterBefehl(letzterBefehl);
							}
							setEnd_befehl(letzterBefehl);
							Fenster.Hauptfenster.getTxtConsole().setText(getEnd_befehl());
//									internerFrame.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
							_qkaän.verbindungTrennen();
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
			getKanteÄndernPanel().setLayout(raster);
			GridBagConstraints grenzen = new GridBagConstraints();
//				grenzen.fill = GridBagConstraints.HORIZONTAL;
				grenzen.gridx = 1;
				grenzen.gridy = 1;
				grenzen.ipadx = 5;
				grenzen.ipady = 5;
				grenzen.insets.left = 5 ;
				grenzen.insets.top = 5 ;
				grenzen.insets.bottom = 5 ;
			raster.setConstraints(getKanteIdentifikation(), grenzen);
			getKanteÄndernPanel().add(getKanteIdentifikation());
				grenzen.gridx = 2; 
		    	grenzen.gridy = 1; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    	grenzen.insets.left = 5 ;
				grenzen.insets.top = 5 ;
				grenzen.insets.bottom = 5 ;
			raster.setConstraints(getKanteIdentifikator(), grenzen);
			getKanteÄndernPanel().add(getKanteIdentifikator());
			getSplitteNorden().setLeftComponent(getKanteÄndernPanel());
			getSplitteNorden().setDividerLocation(200);
			getSplitteNorden().setDividerSize(5);
			getSplitteNorden().setRightComponent(getInstanzInfo());
			GridBagLayout rasterCenter = new GridBagLayout();
			getKanteÄndernPanelErgebnisMenge().setLayout(rasterCenter);
			GridBagConstraints grenzenCenter = new GridBagConstraints();
			rasterCenter.setConstraints(getKanteÄndernPanelErgebnisMenge(), grenzenCenter);
			grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteStartKnoten(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteStartKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getStartKnoten(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getStartKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEndKnoten(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteEndKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getEndKnoten(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getEndKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTypBeschreibung(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteTypBeschreibung());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTyp(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteTyp());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenSchluessel(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenWert(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteEigenschaftenWert());
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenSchluessel_2(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel_2());
				grenzenCenter.gridx = 2; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenWert_2(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteEigenschaftenWert_2());
				grenzenCenter.gridheight=1;	
		   		grenzenCenter.gridwidth=2;
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 7; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteÄndernKnopf(), grenzenCenter);
			getKanteÄndernPanelErgebnisMenge().add(getKanteÄndernKnopf());
			getKanteÄndernKnopf().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() != null){
						QueryKanteÄndern _qkaän = new QueryKanteÄndern(); 
						_qkaän.verbinden();
						try{
							setDataLoad( _qkaän.eineKanteÄndern(
													
								getStartKnoten().getText(),
								getKanteTyp().getText(),
								getEndKnoten().getText(),
								getKanteEigenschaftenSchluessel().getText(),
								getKanteEigenschaftenWert().getText(),
								getKanteEigenschaftenSchluessel_2().getText(),
								getKanteEigenschaftenWert_2().getText()
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
							_qkaän.verbindungTrennen();
						}
					}
				}
			});
			internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKanteÄndernPanelErgebnisMenge(), BorderLayout.CENTER);
			setInternesFensterKanteÄndern(internerFrame);
		return getInternesFensterKanteÄndern();
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Kante ändern");
			internerFrame.setBounds(550, 50, 400, 300);
			internerFrame.getContentPane().setLayout(new BorderLayout());
			internerFrame.add(erstelleKommandoKnoepfe(internerFrame),BorderLayout.SOUTH);
			internerFrame.setVisible(true);
		return internerFrame;
	}
	public JPanel erstelleKommandoKnoepfe(final JInternalFrame internerFrame){
		getKommandoKnoepfePanel().setLayout(new GridLayout());
		setKommandoKnoepfePanel(kommandoKnoepfePanel);
		return kommandoKnoepfePanel;
	}
	public JInternalFrame getInternesFensterKanteÄndern() {
		return internesFensterKanteÄndern;
	}
	public void setInternesFensterKanteÄndern(
			JInternalFrame internesFensterKanteÄndern) {
		this.internesFensterKanteÄndern = internesFensterKanteÄndern;
	}
	public JPanel getKanteÄndernPanel() {
		return kanteÄndernPanel;
	}
	public void setKanteÄndernPanel(JPanel kanteÄndernPanel) {
		this.kanteÄndernPanel = kanteÄndernPanel;
	}
	public JLabel getKanteIdentifikation() {
		return kanteIdentifikation;
	}
	public void setKanteIdentifikation(JLabel kanteIdentifikation) {
		this.kanteIdentifikation = kanteIdentifikation;
	}
	public static JLabel getKanteIdentifikator() {
		return kanteIdentifikator;
	}
	public void setKanteIdentifikator(JLabel kanteIdentifikator) {
		InternesFensterKanteÄndern.kanteIdentifikator = kanteIdentifikator;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKanteÄndern.splitteNorden = splitteNorden;
	}
	public JPanel getKanteÄndernPanelErgebnisMenge() {
		return kanteÄndernPanelErgebnisMenge;
	}
	public void setKanteÄndernPanelErgebnisMenge(
			JPanel kanteÄndernPanelErgebnisMenge) {
		this.kanteÄndernPanelErgebnisMenge = kanteÄndernPanelErgebnisMenge;
	}
	public JLabel getKanteStartKnoten() {
		return kanteStartKnoten;
	}
	public void setKanteStartKnoten(JLabel kanteStartKnoten) {
		this.kanteStartKnoten = kanteStartKnoten;
	}
	public JLabel getKanteEndKnoten() {
		return kanteEndKnoten;
	}
	public void setKanteEndKnoten(JLabel kanteEndKnoten) {
		this.kanteEndKnoten = kanteEndKnoten;
	}
	public JLabel getKanteTypBeschreibung() {
		return kanteTypBeschreibung;
	}
	public void setKanteTypBeschreibung(JLabel kanteTypBeschreibung) {
		this.kanteTypBeschreibung = kanteTypBeschreibung;
	}
	public static JTextField getStartKnoten() {
		return startKnoten;
	}
	public void setStartKnoten(JTextField startKnoten) {
		InternesFensterKanteÄndern.startKnoten = startKnoten;
	}
	public static JTextField getEndKnoten() {
		return endKnoten;
	}
	public void setEndKnoten(JTextField endKnoten) {
		InternesFensterKanteÄndern.endKnoten = endKnoten;
	}
	public static JTextField getKanteTyp() {
		return kanteTyp;
	}
	public void setKanteTyp(JTextField kanteTyp) {
		InternesFensterKanteÄndern.kanteTyp = kanteTyp;
	}
	public static JTextField getKanteEigenschaftenSchluessel() {
		return kanteEigenschaftenSchluessel;
	}
	public void setKanteEigenschaftenSchluessel(
			JTextField kanteEigenschaftenSchluessel) {
		InternesFensterKanteÄndern.kanteEigenschaftenSchluessel = kanteEigenschaftenSchluessel;
	}
	public static JTextField getKanteEigenschaftenWert() {
		return kanteEigenschaftenWert;
	}
	public void setKanteEigenschaftenWert(JTextField kanteEigenschaftenWert) {
		InternesFensterKanteÄndern.kanteEigenschaftenWert = kanteEigenschaftenWert;
	}
	public JPanel getKommandoKnoepfePanel() {
		return kommandoKnoepfePanel;
	}
	public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
		this.kommandoKnoepfePanel = kommandoKnoepfePanel;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKanteÄndern.instanzInfo = instanzInfo;
	}
	public static JTextPane getErgebnisMenge() {
		return ergebnisMenge;
	}
	public static void setErgebnisMenge(JTextPane ergebnisMenge) {
		InternesFensterKanteÄndern.ergebnisMenge = ergebnisMenge;
	}
	public List<String> getDataLoad() {
		return dataLoad;
	}
	public void setDataLoad(List<String> dataLoad) {
		this.dataLoad = dataLoad;
	}
	public String getLetzterBefehl() {
		return letzterBefehl;
	}
	public void setLetzterBefehl(String letzterBefehl) {
		this.letzterBefehl = letzterBefehl;
	}
	public String getEnd_befehl() {
		return end_befehl;
	}
	public void setEnd_befehl(String end_befehl) {
		this.end_befehl = end_befehl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static JTextField getKanteEigenschaftenSchluessel_2() {
		return kanteEigenschaftenSchluessel_2;
	}
	public static void setKanteEigenschaftenSchluessel_2(
			JTextField kanteEigenschaftenSchluessel_2) {
		InternesFensterKanteÄndern.kanteEigenschaftenSchluessel_2 = kanteEigenschaftenSchluessel_2;
	}
	public static JTextField getKanteEigenschaftenWert_2() {
		return kanteEigenschaftenWert_2;
	}
	public static void setKanteEigenschaftenWert_2(
			JTextField kanteEigenschaftenWert_2) {
		InternesFensterKanteÄndern.kanteEigenschaftenWert_2 = kanteEigenschaftenWert_2;
	}
	public static JButton getKanteÄndernKnopf() {
		return kanteÄndernKnopf;
	}
	public void setKanteÄndernKnopf(JButton kanteÄndernKnopf) {
		InternesFensterKanteÄndern.kanteÄndernKnopf = kanteÄndernKnopf;
	}
}
