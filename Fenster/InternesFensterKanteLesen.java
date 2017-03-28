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

import Query.QueryKanteLesen;

public class InternesFensterKanteLesen extends InternesFenster{

	private static final long serialVersionUID = 4L;
	private static JInternalFrame internesFensterKanteLesen;
	private JPanel kanteLesenPanel;
	private static JLabel kanteIdentifikation;
	private static JLabel kanteIdentifikator;
	private static JSplitPane splitteNorden;
	private JPanel kanteLesenPanelErgebnisMenge;
	private JLabel kanteStartKnoten;
	private JLabel kanteEndKnoten;
	private JLabel kanteTypBeschreibung;
	private static JTextField startKnoten;
	private static JTextField endKnoten;
	private JTextField kanteTyp;
	private static JTextField kanteEigenschaftenSchluessel;
	private static JTextField kanteEigenschaftenWert;
	private static JTextField kanteEigenschaftenSchluessel_2;
	private static JTextField kanteEigenschaftenWert_2;
	private JPanel kommandoKnoepfePanel;
	private static JLabel instanzInfo;
	private static JTextPane ergebnisMenge;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;

	public InternesFensterKanteLesen(){
		internesFensterKanteLesen = new InternesFenster();
		setKanteLesenPanel(new JPanel());
		setKanteIdentifikation(new JLabel("Kante Id: "));
		setKanteIdentifikator(new JLabel(""));
		setSplitteNorden(new JSplitPane());
		setKanteLesenPanelErgebnisMenge(new JPanel());
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
		setInstanzInfo(new JLabel("Object-Id"));
		setDataLoad(new ArrayList<String>());
		setKommandoKnoepfePanel(new JPanel());
	}
	public JInternalFrame erstelleFensterKanteLesen(final InternesFensterKanteLesen internerFrame){
		((InternesFensterKanteLesen) internerFrame).erstelleDialogFenster(internerFrame);
		JButton ok = new JButton("Okay");
		JButton abbr = new JButton("Abbrechen");
		getKommandoKnoepfePanel().add(ok);
		getKommandoKnoepfePanel().add(abbr);
		ok.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() != null){
				QueryKanteLesen _qkale = new QueryKanteLesen(); 
				_qkale.verbinden();
				try{
					setDataLoad( _qkale.eineKanteLesen(
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
//							internerFrame.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					_qkale.verbindungTrennen();
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
		getKanteLesenPanel().setLayout(raster);
		GridBagConstraints grenzen = new GridBagConstraints();
			grenzen.fill = GridBagConstraints.HORIZONTAL;
			grenzen.gridx = 1;
			grenzen.gridy = 1;
			grenzen.ipadx = 5;
			grenzen.ipady = 5;
			grenzen.insets.left = 5 ;
			grenzen.insets.top = 5 ;
			grenzen.insets.bottom = 5 ;
		raster.setConstraints(getKanteIdentifikation(), grenzen);
		getKanteLesenPanel().add(getKanteIdentifikation());
			grenzen.gridx = 2; 
	    	grenzen.gridy = 1; 
	    	grenzen.ipadx = 5; 
	    	grenzen.ipady = 5; 
	    	grenzen.insets.left = 5 ;
			grenzen.insets.top = 5 ;
			grenzen.insets.bottom = 5 ;
		raster.setConstraints(getKanteIdentifikator(), grenzen);
		getKanteLesenPanel().add(getKanteIdentifikator());
			getSplitteNorden().setLeftComponent(getKanteLesenPanel());
			getSplitteNorden().setDividerLocation(200);
	    	getSplitteNorden().setDividerSize(5);
			getSplitteNorden().setRightComponent(getInstanzInfo());
			GridBagLayout rasterCenter = new GridBagLayout();
		getKanteLesenPanelErgebnisMenge().setLayout(rasterCenter);
		GridBagConstraints grenzenCenter = new GridBagConstraints();
		rasterCenter.setConstraints(getKanteLesenPanelErgebnisMenge(), grenzenCenter);
		grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 1; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteStartKnoten(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteStartKnoten());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 1; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getStartKnoten(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getStartKnoten());
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteEndKnoten(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteEndKnoten());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getEndKnoten(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getEndKnoten());
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteTypBeschreibung(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteTypBeschreibung());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteTyp(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteTyp());
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteEigenschaftenSchluessel(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKanteEigenschaftenWert(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteEigenschaftenWert());
			grenzenCenter.gridx = 1; 
			grenzenCenter.gridy = 5; 
			grenzenCenter.ipadx = 5; 
			grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(getKanteEigenschaftenSchluessel_2(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteEigenschaftenSchluessel_2());
			grenzenCenter.gridx = 2; 
			grenzenCenter.gridy = 5; 
			grenzenCenter.ipadx = 5; 
			grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(getKanteEigenschaftenWert_2(), grenzenCenter);
		getKanteLesenPanelErgebnisMenge().add(getKanteEigenschaftenWert_2());
		internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
		internerFrame.add(getKanteLesenPanelErgebnisMenge(), BorderLayout.CENTER);
		setInternesFensterKanteLesen(internerFrame);
		return getInternesFensterKanteLesen();
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Kante lesen");
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
	
	public JInternalFrame getInternesFensterKanteLesen() {
		return internesFensterKanteLesen;
	}
	public void setInternesFensterKanteLesen(
			JInternalFrame internesFensterKanteLesen) {
		InternesFensterKanteLesen.internesFensterKanteLesen = internesFensterKanteLesen;
	}
	public JPanel getKanteLesenPanel() {
		return kanteLesenPanel;
	}
	public void setKanteLesenPanel(JPanel kanteLesenPanel) {
		this.kanteLesenPanel = kanteLesenPanel;
	}
	public static JLabel getKanteIdentifikation() {
		return kanteIdentifikation;
	}
	public void setKanteIdentifikation(JLabel kanteIdentifikation) {
		InternesFensterKanteLesen.kanteIdentifikation = kanteIdentifikation;
	}
	public static JLabel getKanteIdentifikator() {
		return kanteIdentifikator;
	}
	public void setKanteIdentifikator(JLabel jLabel) {
		InternesFensterKanteLesen.kanteIdentifikator = jLabel;
	}
	public JPanel getKanteLesenPanelErgebnisMenge() {
		return kanteLesenPanelErgebnisMenge;
	}
	public void setKanteLesenPanelErgebnisMenge(JPanel kanteLesenPanelErgebnisMenge) {
		this.kanteLesenPanelErgebnisMenge = kanteLesenPanelErgebnisMenge;
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
		InternesFensterKanteLesen.startKnoten = startKnoten;
	}
	public static JTextField getEndKnoten() {
		return endKnoten;
	}
	public void setEndKnoten(JTextField endKnoten) {
		InternesFensterKanteLesen.endKnoten = endKnoten;
	}
	public JTextField getKanteTyp() {
		return kanteTyp;
	}
	public void setKanteTyp(JTextField kanteTyp) {
		this.kanteTyp = kanteTyp;
	}
	public static JTextField getKanteEigenschaftenSchluessel() {
		return kanteEigenschaftenSchluessel;
	}
	public void setKanteEigenschaftenSchluessel(
			JTextField kanteEigenschaftenSchluessel) {
		InternesFensterKanteLesen.kanteEigenschaftenSchluessel = kanteEigenschaftenSchluessel;
	}
	public static JTextField getKanteEigenschaftenWert() {
		return kanteEigenschaftenWert;
	}
	public void setKanteEigenschaftenWert(JTextField kanteEigenschaftenWert) {
		InternesFensterKanteLesen.kanteEigenschaftenWert = kanteEigenschaftenWert;
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
		InternesFensterKanteLesen.instanzInfo = instanzInfo;
	}
	public static JTextPane getErgebnisMenge() {
		return ergebnisMenge;
	}
	public static void setErgebnisMenge(JTextPane ergebnisMenge) {
		InternesFensterKanteLesen.ergebnisMenge = ergebnisMenge;
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
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKanteLesen.splitteNorden = splitteNorden;
	}
	public static JTextField getKanteEigenschaftenSchluessel_2() {
		return kanteEigenschaftenSchluessel_2;
	}
	public static void setKanteEigenschaftenSchluessel_2(
			JTextField kanteEigenschaftenSchluessel_2) {
		InternesFensterKanteLesen.kanteEigenschaftenSchluessel_2 = kanteEigenschaftenSchluessel_2;
	}
	public static JTextField getKanteEigenschaftenWert_2() {
		return kanteEigenschaftenWert_2;
	}
	public static void setKanteEigenschaftenWert_2(
			JTextField kanteEigenschaftenWert_2) {
		InternesFensterKanteLesen.kanteEigenschaftenWert_2 = kanteEigenschaftenWert_2;
	}
}