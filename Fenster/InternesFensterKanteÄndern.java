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

import Query.QueryKante�ndern;

public class InternesFensterKante�ndern extends InternesFenster{
	private static final long serialVersionUID = 5L;
	private JInternalFrame internesFensterKante�ndern;
	private JPanel kante�ndernPanel;
	private JLabel kanteIdentifikation;
	private static JLabel kanteIdentifikator;
	private static JSplitPane splitteNorden;
	private JPanel kante�ndernPanelErgebnisMenge;
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
	private static JButton kante�ndernKnopf;
	private JPanel kommandoKnoepfePanel;
	private static JLabel instanzInfo;
	private static JTextPane ergebnisMenge;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;

	public InternesFensterKante�ndern(){
		internesFensterKante�ndern = new InternesFenster();
		setKante�ndernPanel(new JPanel());
		setKanteIdentifikation(new JLabel("Kante Id: "));
		setKanteIdentifikator(new JLabel(""));
		setSplitteNorden(new JSplitPane());
		setKante�ndernPanelErgebnisMenge(new JPanel());
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
		setKante�ndernKnopf(new JButton("Kante �ndern"));
		getKante�ndernKnopf().setVisible(false);
		setInstanzInfo(new JLabel("Object-Id"));
		setDataLoad(new ArrayList<String>());
		setKommandoKnoepfePanel(new JPanel());
	}
	public JInternalFrame erstelleFensterKante�ndern(final InternesFensterKante�ndern internerFrame){
			((InternesFensterKante�ndern) internerFrame).erstelleDialogFenster(internerFrame);
			JButton ok = new JButton("Okay");
			JButton abbr = new JButton("Abbrechen");
			getKommandoKnoepfePanel().add(ok);
			getKommandoKnoepfePanel().add(abbr);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() != null){
						QueryKante�ndern _qka�n = new QueryKante�ndern(); 
						_qka�n.verbinden();
						try{
							setDataLoad( _qka�n.eineKante�ndernVorbereiten(
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
							_qka�n.verbindungTrennen();
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
			getKante�ndernPanel().setLayout(raster);
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
			getKante�ndernPanel().add(getKanteIdentifikation());
				grenzen.gridx = 2; 
		    	grenzen.gridy = 1; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    	grenzen.insets.left = 5 ;
				grenzen.insets.top = 5 ;
				grenzen.insets.bottom = 5 ;
			raster.setConstraints(getKanteIdentifikator(), grenzen);
			getKante�ndernPanel().add(getKanteIdentifikator());
			getSplitteNorden().setLeftComponent(getKante�ndernPanel());
			getSplitteNorden().setDividerLocation(200);
			getSplitteNorden().setDividerSize(5);
			getSplitteNorden().setRightComponent(getInstanzInfo());
			GridBagLayout rasterCenter = new GridBagLayout();
			getKante�ndernPanelErgebnisMenge().setLayout(rasterCenter);
			GridBagConstraints grenzenCenter = new GridBagConstraints();
			rasterCenter.setConstraints(getKante�ndernPanelErgebnisMenge(), grenzenCenter);
			grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteStartKnoten(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteStartKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getStartKnoten(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getStartKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEndKnoten(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteEndKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getEndKnoten(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getEndKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTypBeschreibung(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteTypBeschreibung());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTyp(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteTyp());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenSchluessel(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenWert(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteEigenschaftenWert());
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenSchluessel_2(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel_2());
				grenzenCenter.gridx = 2; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenWert_2(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKanteEigenschaftenWert_2());
				grenzenCenter.gridheight=1;	
		   		grenzenCenter.gridwidth=2;
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 7; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKante�ndernKnopf(), grenzenCenter);
			getKante�ndernPanelErgebnisMenge().add(getKante�ndernKnopf());
			getKante�ndernKnopf().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() != null){
						QueryKante�ndern _qka�n = new QueryKante�ndern(); 
						_qka�n.verbinden();
						try{
							setDataLoad( _qka�n.eineKante�ndern(
													
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
							_qka�n.verbindungTrennen();
						}
					}
				}
			});
			internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKante�ndernPanelErgebnisMenge(), BorderLayout.CENTER);
			setInternesFensterKante�ndern(internerFrame);
		return getInternesFensterKante�ndern();
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Kante �ndern");
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
	public JInternalFrame getInternesFensterKante�ndern() {
		return internesFensterKante�ndern;
	}
	public void setInternesFensterKante�ndern(
			JInternalFrame internesFensterKante�ndern) {
		this.internesFensterKante�ndern = internesFensterKante�ndern;
	}
	public JPanel getKante�ndernPanel() {
		return kante�ndernPanel;
	}
	public void setKante�ndernPanel(JPanel kante�ndernPanel) {
		this.kante�ndernPanel = kante�ndernPanel;
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
		InternesFensterKante�ndern.kanteIdentifikator = kanteIdentifikator;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKante�ndern.splitteNorden = splitteNorden;
	}
	public JPanel getKante�ndernPanelErgebnisMenge() {
		return kante�ndernPanelErgebnisMenge;
	}
	public void setKante�ndernPanelErgebnisMenge(
			JPanel kante�ndernPanelErgebnisMenge) {
		this.kante�ndernPanelErgebnisMenge = kante�ndernPanelErgebnisMenge;
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
		InternesFensterKante�ndern.startKnoten = startKnoten;
	}
	public static JTextField getEndKnoten() {
		return endKnoten;
	}
	public void setEndKnoten(JTextField endKnoten) {
		InternesFensterKante�ndern.endKnoten = endKnoten;
	}
	public static JTextField getKanteTyp() {
		return kanteTyp;
	}
	public void setKanteTyp(JTextField kanteTyp) {
		InternesFensterKante�ndern.kanteTyp = kanteTyp;
	}
	public static JTextField getKanteEigenschaftenSchluessel() {
		return kanteEigenschaftenSchluessel;
	}
	public void setKanteEigenschaftenSchluessel(
			JTextField kanteEigenschaftenSchluessel) {
		InternesFensterKante�ndern.kanteEigenschaftenSchluessel = kanteEigenschaftenSchluessel;
	}
	public static JTextField getKanteEigenschaftenWert() {
		return kanteEigenschaftenWert;
	}
	public void setKanteEigenschaftenWert(JTextField kanteEigenschaftenWert) {
		InternesFensterKante�ndern.kanteEigenschaftenWert = kanteEigenschaftenWert;
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
		InternesFensterKante�ndern.instanzInfo = instanzInfo;
	}
	public static JTextPane getErgebnisMenge() {
		return ergebnisMenge;
	}
	public static void setErgebnisMenge(JTextPane ergebnisMenge) {
		InternesFensterKante�ndern.ergebnisMenge = ergebnisMenge;
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
		InternesFensterKante�ndern.kanteEigenschaftenSchluessel_2 = kanteEigenschaftenSchluessel_2;
	}
	public static JTextField getKanteEigenschaftenWert_2() {
		return kanteEigenschaftenWert_2;
	}
	public static void setKanteEigenschaftenWert_2(
			JTextField kanteEigenschaftenWert_2) {
		InternesFensterKante�ndern.kanteEigenschaftenWert_2 = kanteEigenschaftenWert_2;
	}
	public static JButton getKante�ndernKnopf() {
		return kante�ndernKnopf;
	}
	public void setKante�ndernKnopf(JButton kante�ndernKnopf) {
		InternesFensterKante�ndern.kante�ndernKnopf = kante�ndernKnopf;
	}
}
