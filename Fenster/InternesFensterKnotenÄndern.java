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

import Query.QueryKnotenÄndern;

public class InternesFensterKnotenÄndern extends InternesFenster{

	private static final long serialVersionUID = 1L;
	private JInternalFrame internesFensterKnotenÄndern;
	private JPanel knotenÄndernPanel;
	private JPanel kommandoKnoepfePanel;
	
	
	private static JLabel knotenIdBezeichner;
	private static JLabel knotenId;


	private JLabel knotenIdentifikation;
	private static JTextField knotenIdentifikator;

	private static JLabel instanzInfo;
	private static JSplitPane splitteNorden;
	private JPanel knotenÄndernPanelErgebnisMenge;
	private JLabel knotenEigenschaft;
	static JTextField knotenEigenschaftenSchluessel_1;
	private static JTextField knotenEigenschaftenWert_1;
	private static JTextField knotenEigenschaftenSchluessel_2;
	static JTextField knotenEigenschaftenWert_2;
	private static JLabel anliegendeKanten;
	private static JLabel anzAnliegenderKanten;
	private static JButton knotenÄndernKnopf;
	private String letzterBefehl="",end_befehl;
	private List<String> dataLoad;
	
	@SuppressWarnings("static-access")
	public InternesFensterKnotenÄndern(){
		this.internesFensterKnotenÄndern = new InternesFenster();
		this.dataLoad = new ArrayList<String>();
		this.setKnotenÄndernPanel(new JPanel());
		this.setKnotenIdBezeichner(new JLabel("Knoten Id:"));
		this.setKnotenId(new JLabel(""));
		this.setKnotenIdentifikation(new JLabel("Knoten Titel:"));
		this.setKnotenIdentifikator(new JTextField("*"));
		this.setInstanzInfo(new JLabel("Object-Id"));
		this.setSplitteNorden(new JSplitPane());
		this.setKommandoKnoepfePanel(new JPanel());
		this.setKnotenEigenschaft(new JLabel("Eigenschaft der Kante"));
		this.setKnotenÄndernPanelErgebnisMenge(new JPanel());
		this.setKnotenEigenschaftenSchluessel_1(new JTextField("Schlüssel_1"));
		this.setKnotenEigenschaftenWert_1(new JTextField("Wert_1"));
		this.setKnotenEigenschaftenSchluessel_2(new JTextField("Schlüssel_2"));
		this.setKnotenEigenschaftenWert_2(new JTextField("Wert_2"));
		this.setAnliegendeKanten(new JLabel("Anliegende Kanten:"));
		this.setAnzAnliegenderKanten(new JLabel(""));
		this.setKnotenÄndernKnopf(new JButton("Knoten ändern"));
		this.getKnotenÄndernKnopf().setVisible(false);
	}
	public JInternalFrame erstelleFensterKnotenÄndern(final InternesFensterKnotenÄndern internerFrame) {
		((InternesFensterKnotenÄndern) internerFrame).erstelleDialogFenster(internerFrame);
			JButton ok = new JButton("Okay");
			JButton abbr = new JButton("Abbrechen");
			this.getKommandoKnoepfePanel().add(ok);
			this.getKommandoKnoepfePanel().add(abbr);
			ok.addActionListener(new ActionListener() {
			@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenÄndern _qknä = new QueryKnotenÄndern(); 
							_qknä.verbinden();
							try{
								setDataLoad( _qknä.einenKnotenÄndernVorbereiten(
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
								_qknä.verbindungTrennen();
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
				getKnotenÄndernPanel().setLayout(raster);
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
				getKnotenÄndernPanel().add(getKnotenIdBezeichner());
					grenzen.gridx = 2; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5; 
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
				raster.setConstraints(getKnotenId(), grenzen);
				getKnotenÄndernPanel().add(getKnotenId());
				getSplitteNorden().setLeftComponent(getKnotenÄndernPanel());
				getSplitteNorden().setDividerLocation(200);
		    	getSplitteNorden().setDividerSize(5);
				getSplitteNorden().setRightComponent(getInstanzInfo());
		
		GridBagLayout rasterCenter = new GridBagLayout();
		getKnotenÄndernPanelErgebnisMenge().setLayout(rasterCenter);
		GridBagConstraints grenzenCenter = new GridBagConstraints();
		grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
//			grenzenCenter.weightx=1;
//			grenzenCenter.weighty=0;
			grenzenCenter.gridx = 1;
			grenzenCenter.gridy = 1;
			grenzenCenter.ipadx = 5;
			grenzenCenter.ipady = 5;
		rasterCenter.setConstraints(getKnotenIdentifikation(), grenzenCenter);
		getKnotenÄndernPanelErgebnisMenge().add(getKnotenIdentifikation());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 1; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getKnotenIdentifikator(), grenzenCenter);
		getKnotenÄndernPanelErgebnisMenge().add(getKnotenIdentifikator());
//			grenzenCenter.gridx = 1;
//			grenzenCenter.gridy = 1;
//			grenzenCenter.ipadx = 5; 
//	    	grenzenCenter.ipady = 5; 
//		rasterCenter.setConstraints(knotenEigenschaft, grenzenCenter);
//		getKnotenÄndernPanelErgebnisMenge().add(knotenEigenschaft);
			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(knotenEigenschaftenSchluessel_1, grenzenCenter);
		getKnotenÄndernPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_1);
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 2; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(knotenEigenschaftenWert_1, grenzenCenter);
   		getKnotenÄndernPanelErgebnisMenge().add(knotenEigenschaftenWert_1);
   			grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(knotenEigenschaftenSchluessel_2, grenzenCenter);
		getKnotenÄndernPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_2);
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 3; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(knotenEigenschaftenWert_2, grenzenCenter);
   		getKnotenÄndernPanelErgebnisMenge().add(knotenEigenschaftenWert_2);	
	   		grenzenCenter.gridx = 1; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getAnliegendeKanten(), grenzenCenter);
	    getKnotenÄndernPanelErgebnisMenge().add(getAnliegendeKanten());
			grenzenCenter.gridx = 2; 
	    	grenzenCenter.gridy = 4; 
	    	grenzenCenter.ipadx = 5; 
	    	grenzenCenter.ipady = 5; 
	    rasterCenter.setConstraints(getAnzAnliegenderKanten(), grenzenCenter);
	    getKnotenÄndernPanelErgebnisMenge().add(getAnzAnliegenderKanten());
	   		grenzenCenter.gridheight=1;	
	   		grenzenCenter.gridwidth=2;
   			grenzenCenter.gridx = 1; 
			grenzenCenter.gridy = 7; 
			grenzenCenter.ipadx = 5; 
			grenzenCenter.ipady = 5; 
		rasterCenter.setConstraints(getKnotenÄndernKnopf(), grenzenCenter);
		getKnotenÄndernPanelErgebnisMenge().add(getKnotenÄndernKnopf());
				getKnotenÄndernKnopf().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenÄndern _qknä = new QueryKnotenÄndern(); 
							_qknä.verbinden();
							try{
								setDataLoad( _qknä.einenKnotenÄndern(
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
								_qknä.verbindungTrennen();
							}
						}
					}	
				});
			internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKnotenÄndernPanelErgebnisMenge(), BorderLayout.CENTER);
			setInternesFensterKnotenÄndern(internerFrame);
		return internerFrame;
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Knoten ändern");
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
	public JInternalFrame getInternesFensterKnotenÄndern() {
		return internesFensterKnotenÄndern;
	}
	public void setInternesFensterKnotenÄndern(JInternalFrame internesFensterKnotenÄndern) {
		this.internesFensterKnotenÄndern = internesFensterKnotenÄndern;
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
		InternesFensterKnotenÄndern.knotenIdBezeichner = knotenIdBezeichner;
	}
	public static JLabel getKnotenId() {
		return knotenId;
	}
	public static void setKnotenId(JLabel knotenId) {
		InternesFensterKnotenÄndern.knotenId = knotenId;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKnotenÄndern.instanzInfo = instanzInfo;
	}
	public JPanel getKnotenÄndernPanel() {
		return knotenÄndernPanel;
	}
	public void setKnotenÄndernPanel(JPanel knotenÄndernPanel) {
		this.knotenÄndernPanel = knotenÄndernPanel;
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
		InternesFensterKnotenÄndern.knotenIdentifikator = knotenIdentifikator;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKnotenÄndern.splitteNorden = splitteNorden;
	}
	public JPanel getKnotenÄndernPanelErgebnisMenge(){
		return knotenÄndernPanelErgebnisMenge;
	}
	public void setKnotenÄndernPanelErgebnisMenge(JPanel knotenÄndernPanelErgebnisMenge){
		this.knotenÄndernPanelErgebnisMenge=knotenÄndernPanelErgebnisMenge;
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
		InternesFensterKnotenÄndern.knotenEigenschaftenSchluessel_1=knotenEigenschaftenSchluessel_1;
	}
	public static JTextField getKnotenEigenschaftenWert_1(){
		return knotenEigenschaftenWert_1;
	}
	public void setKnotenEigenschaftenWert_1(JTextField knotenEigenschaftenWert_1){
		InternesFensterKnotenÄndern.knotenEigenschaftenWert_1=knotenEigenschaftenWert_1;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_2(){
		return knotenEigenschaftenSchluessel_2;
	}
	public void setKnotenEigenschaftenSchluessel_2(JTextField knotenEigenschaftenSchluessel_2){
		InternesFensterKnotenÄndern.knotenEigenschaftenSchluessel_2=knotenEigenschaftenSchluessel_2;
	}
	public static JTextField getKnotenEigenschaftenWert_2(){
		return knotenEigenschaftenWert_2;
	}
	public void setKnotenEigenschaftenWert_2(JTextField knotenEigenschaftenWert_2){
		InternesFensterKnotenÄndern.knotenEigenschaftenWert_2=knotenEigenschaftenWert_2;
	}
	public static JLabel getAnliegendeKanten() {
		return anliegendeKanten;
	}
	public static void setAnliegendeKanten(JLabel anliegendeKanten) {
		InternesFensterKnotenÄndern.anliegendeKanten = anliegendeKanten;
	}
	public static JLabel getAnzAnliegenderKanten() {
		return anzAnliegenderKanten;
	}
	public static void setAnzAnliegenderKanten(JLabel anzAnliegenderKanten) {
		InternesFensterKnotenÄndern.anzAnliegenderKanten = anzAnliegenderKanten;
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
	public static JButton getKnotenÄndernKnopf() {
		return knotenÄndernKnopf;
	}
	public void setKnotenÄndernKnopf(JButton knotenÄndernKnopf) {
		InternesFensterKnotenÄndern.knotenÄndernKnopf = knotenÄndernKnopf;
	}

}
