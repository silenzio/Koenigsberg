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

import Query.QueryKnoten�ndern;

public class InternesFensterKnoten�ndern extends InternesFenster{

	private static final long serialVersionUID = 1L;
	private JInternalFrame internesFensterKnoten�ndern;
	private JPanel knoten�ndernPanel;
	private JPanel kommandoKnoepfePanel;
	
	
	private static JLabel knotenIdBezeichner;
	private static JLabel knotenId;


	private JLabel knotenIdentifikation;
	private static JTextField knotenIdentifikator;

	private static JLabel instanzInfo;
	private static JSplitPane splitteNorden;
	private JPanel knoten�ndernPanelErgebnisMenge;
	private JLabel knotenEigenschaft;
	static JTextField knotenEigenschaftenSchluessel_1;
	private static JTextField knotenEigenschaftenWert_1;
	private static JTextField knotenEigenschaftenSchluessel_2;
	static JTextField knotenEigenschaftenWert_2;
	private static JLabel anliegendeKanten;
	private static JLabel anzAnliegenderKanten;
	private static JButton knoten�ndernKnopf;
	private String letzterBefehl="",end_befehl;
	private List<String> dataLoad;
	
	@SuppressWarnings("static-access")
	public InternesFensterKnoten�ndern(){
		this.internesFensterKnoten�ndern = new InternesFenster();
		this.dataLoad = new ArrayList<String>();
		this.setKnoten�ndernPanel(new JPanel());
		this.setKnotenIdBezeichner(new JLabel("Knoten Id:"));
		this.setKnotenId(new JLabel(""));
		this.setKnotenIdentifikation(new JLabel("Knoten Titel:"));
		this.setKnotenIdentifikator(new JTextField("*"));
		this.setInstanzInfo(new JLabel("Object-Id"));
		this.setSplitteNorden(new JSplitPane());
		this.setKommandoKnoepfePanel(new JPanel());
		this.setKnotenEigenschaft(new JLabel("Eigenschaft der Kante"));
		this.setKnoten�ndernPanelErgebnisMenge(new JPanel());
		this.setKnotenEigenschaftenSchluessel_1(new JTextField("Schl�ssel_1"));
		this.setKnotenEigenschaftenWert_1(new JTextField("Wert_1"));
		this.setKnotenEigenschaftenSchluessel_2(new JTextField("Schl�ssel_2"));
		this.setKnotenEigenschaftenWert_2(new JTextField("Wert_2"));
		this.setAnliegendeKanten(new JLabel("Anliegende Kanten:"));
		this.setAnzAnliegenderKanten(new JLabel(""));
		this.setKnoten�ndernKnopf(new JButton("Knoten �ndern"));
		this.getKnoten�ndernKnopf().setVisible(false);
	}
	public JInternalFrame erstelleFensterKnoten�ndern(final InternesFensterKnoten�ndern internerFrame) {
		((InternesFensterKnoten�ndern) internerFrame).erstelleDialogFenster(internerFrame);
			JButton ok = new JButton("Okay");
			JButton abbr = new JButton("Abbrechen");
			this.getKommandoKnoepfePanel().add(ok);
			this.getKommandoKnoepfePanel().add(abbr);
			ok.addActionListener(new ActionListener() {
			@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnoten�ndern _qkn� = new QueryKnoten�ndern(); 
							_qkn�.verbinden();
							try{
								setDataLoad( _qkn�.einenKnoten�ndernVorbereiten(
														knotenIdentifikator.getText() 
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
								_qkn�.verbindungTrennen();
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
				getKnoten�ndernPanel().setLayout(raster);
				GridBagConstraints grenzen = new GridBagConstraints();
					grenzen.fill = GridBagConstraints.HORIZONTAL;
					grenzen.gridx = 1;
					grenzen.gridy = 1;
					grenzen.ipadx = 5;
					grenzen.ipady = 5;
					grenzen.insets.left = 5 ;
					grenzen.insets.bottom = 5 ;
					grenzen.insets.top = 5 ;
				raster.setConstraints(getKnotenIdBezeichner(), grenzen);
				getKnoten�ndernPanel().add(getKnotenIdBezeichner());
					grenzen.gridx = 2; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5; 
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
				raster.setConstraints(getKnotenId(), grenzen);
				getKnoten�ndernPanel().add(getKnotenId());
				getSplitteNorden().setLeftComponent(getKnoten�ndernPanel());
				getSplitteNorden().setDividerLocation(200);
		    	getSplitteNorden().setDividerSize(5);
				getSplitteNorden().setRightComponent(getInstanzInfo());
		
		GridBagLayout rasterCenter = new GridBagLayout();
		getKnoten�ndernPanelErgebnisMenge().setLayout(rasterCenter);
		GridBagConstraints grenzenCenter = new GridBagConstraints();
		grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
//			grenzenCenter.weightx=1;
//			grenzenCenter.weighty=0;
			grenzenCenter.gridx = 1;
			grenzenCenter.gridy = 1;
			grenzenCenter.ipadx = 5;
			grenzenCenter.ipady = 5;
		rasterCenter.setConstraints(getKnotenIdentifikation(), grenzenCenter);
		getKnoten�ndernPanelErgebnisMenge().add(getKnotenIdentifikation());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 1; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKnotenIdentifikator(), grenzenCenter);
		getKnoten�ndernPanelErgebnisMenge().add(getKnotenIdentifikator());
//			grenzenCenter.gridx = 1;
//			grenzenCenter.gridy = 1;
//			grenzenCenter.ipadx = 5; 
//	    	grenzenCenter.ipady = 5; 
//		rasterCenter.setConstraints(knotenEigenschaft, grenzenCenter);
//		getKnoten�ndernPanelErgebnisMenge().add(knotenEigenschaft);
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(knotenEigenschaftenSchluessel_1, grenzenCenter);
		getKnoten�ndernPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_1);
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(knotenEigenschaftenWert_1, grenzenCenter);
   		getKnoten�ndernPanelErgebnisMenge().add(knotenEigenschaftenWert_1);
   			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(knotenEigenschaftenSchluessel_2, grenzenCenter);
		getKnoten�ndernPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_2);
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(knotenEigenschaftenWert_2, grenzenCenter);
   		getKnoten�ndernPanelErgebnisMenge().add(knotenEigenschaftenWert_2);	
	   		grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getAnliegendeKanten(), grenzenCenter);
	    getKnoten�ndernPanelErgebnisMenge().add(getAnliegendeKanten());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getAnzAnliegenderKanten(), grenzenCenter);
	    getKnoten�ndernPanelErgebnisMenge().add(getAnzAnliegenderKanten());
	   		grenzenCenter.gridheight=1;	
	   		grenzenCenter.gridwidth=2;
   			grenzenCenter.gridx = 1; 
			grenzenCenter.gridy = 7; 
			grenzenCenter.ipadx = 5; 
			grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(getKnoten�ndernKnopf(), grenzenCenter);
		getKnoten�ndernPanelErgebnisMenge().add(getKnoten�ndernKnopf());
				getKnoten�ndernKnopf().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnoten�ndern _qkn� = new QueryKnoten�ndern(); 
							_qkn�.verbinden();
							try{
								setDataLoad( _qkn�.einenKnoten�ndern(
														knotenIdentifikator.getText() 
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
								_qkn�.verbindungTrennen();
							}
						}
					}	
				});
			internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKnoten�ndernPanelErgebnisMenge(), BorderLayout.CENTER);
			setInternesFensterKnoten�ndern(internerFrame);
		return internerFrame;
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Knoten �ndern");
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
	public JInternalFrame getInternesFensterKnoten�ndern() {
		return internesFensterKnoten�ndern;
	}
	public void setInternesFensterKnoten�ndern(JInternalFrame internesFensterKnoten�ndern) {
		this.internesFensterKnoten�ndern = internesFensterKnoten�ndern;
	}
	public JPanel getKommandoKnoepfePanel() {
		return kommandoKnoepfePanel;
	}
	public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
		this.kommandoKnoepfePanel = kommandoKnoepfePanel;
	}

	public static JLabel getKnotenIdBezeichner() {
		return knotenIdBezeichner;
	}
	public static void setKnotenIdBezeichner(JLabel knotenIdBezeichner) {
		InternesFensterKnoten�ndern.knotenIdBezeichner = knotenIdBezeichner;
	}
	public static JLabel getKnotenId() {
		return knotenId;
	}
	public static void setKnotenId(JLabel knotenId) {
		InternesFensterKnoten�ndern.knotenId = knotenId;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKnoten�ndern.instanzInfo = instanzInfo;
	}
	public JPanel getKnoten�ndernPanel() {
		return knoten�ndernPanel;
	}
	public void setKnoten�ndernPanel(JPanel knoten�ndernPanel) {
		this.knoten�ndernPanel = knoten�ndernPanel;
	}
	public JLabel getKnotenIdentifikation() {
		return knotenIdentifikation;
	}
	public void setKnotenIdentifikation(JLabel knotenIdentifikation) {
		this.knotenIdentifikation = knotenIdentifikation;
	}
	public static JTextField getKnotenIdentifikator() {
		return knotenIdentifikator;
	}
	public static void setKnotenIdentifikator(JTextField knotenIdentifikator) {
		InternesFensterKnoten�ndern.knotenIdentifikator = knotenIdentifikator;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKnoten�ndern.splitteNorden = splitteNorden;
	}
	public JPanel getKnoten�ndernPanelErgebnisMenge(){
		return knoten�ndernPanelErgebnisMenge;
	}
	public void setKnoten�ndernPanelErgebnisMenge(JPanel knoten�ndernPanelErgebnisMenge){
		this.knoten�ndernPanelErgebnisMenge=knoten�ndernPanelErgebnisMenge;
	}
	public JLabel getKnotenEigenschaft(){
		return knotenEigenschaft;
	}
	public void setKnotenEigenschaft(JLabel knotenEigenschaft){
		this.knotenEigenschaft=knotenEigenschaft;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_1(){
		return knotenEigenschaftenSchluessel_1;
	}
	public void setKnotenEigenschaftenSchluessel_1(JTextField knotenEigenschaftenSchluessel_1){
		InternesFensterKnoten�ndern.knotenEigenschaftenSchluessel_1=knotenEigenschaftenSchluessel_1;
	}
	public static JTextField getKnotenEigenschaftenWert_1(){
		return knotenEigenschaftenWert_1;
	}
	public void setKnotenEigenschaftenWert_1(JTextField knotenEigenschaftenWert_1){
		InternesFensterKnoten�ndern.knotenEigenschaftenWert_1=knotenEigenschaftenWert_1;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_2(){
		return knotenEigenschaftenSchluessel_2;
	}
	public void setKnotenEigenschaftenSchluessel_2(JTextField knotenEigenschaftenSchluessel_2){
		InternesFensterKnoten�ndern.knotenEigenschaftenSchluessel_2=knotenEigenschaftenSchluessel_2;
	}
	public static JTextField getKnotenEigenschaftenWert_2(){
		return knotenEigenschaftenWert_2;
	}
	public void setKnotenEigenschaftenWert_2(JTextField knotenEigenschaftenWert_2){
		InternesFensterKnoten�ndern.knotenEigenschaftenWert_2=knotenEigenschaftenWert_2;
	}
	public static JLabel getAnliegendeKanten() {
		return anliegendeKanten;
	}
	public static void setAnliegendeKanten(JLabel anliegendeKanten) {
		InternesFensterKnoten�ndern.anliegendeKanten = anliegendeKanten;
	}
	public static JLabel getAnzAnliegenderKanten() {
		return anzAnliegenderKanten;
	}
	public static void setAnzAnliegenderKanten(JLabel anzAnliegenderKanten) {
		InternesFensterKnoten�ndern.anzAnliegenderKanten = anzAnliegenderKanten;
	}
	public List<String> getDataLoad() {
		return dataLoad;
	}
	public void setDataLoad(List<String> dataLoad) {
		this.dataLoad = dataLoad;
	}
	public String getEnd_befehl() {
		return end_befehl;
	}
	public void setEnd_befehl(String end_befehl) {
		this.end_befehl = end_befehl;
	}
	public String getLetzterBefehl() {
		return letzterBefehl;
	}
	public void setLetzterBefehl(String letzterBefehl) {
		this.letzterBefehl = letzterBefehl;
	}
	public static JButton getKnoten�ndernKnopf() {
		return knoten�ndernKnopf;
	}
	public void setKnoten�ndernKnopf(JButton knoten�ndernKnopf) {
		InternesFensterKnoten�ndern.knoten�ndernKnopf = knoten�ndernKnopf;
	}

}
