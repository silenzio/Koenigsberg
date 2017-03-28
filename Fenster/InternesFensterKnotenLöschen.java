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

import Query.QueryKnotenLöschen;

public class InternesFensterKnotenLöschen extends InternesFenster{

	private static final long serialVersionUID = 1L;
	private JInternalFrame internesFensterKnotenLöschen;
	private JPanel knotenLöschenPanelOben;
	private JPanel kommandoKnoepfePanel;
	private static JLabel knotenIdBezeichner;
	private static JLabel knotenId;
	private static JLabel instanzInfo;
	private static JSplitPane splitteNorden;
	private JPanel knotenLöschenPanelErgebnisMenge;
	private JLabel knotenIdentifikation;
	private JTextField knotenIdentifikator;
	static JTextField knotenEigenschaftenSchluessel_1;
	private static JTextField knotenEigenschaftenWert_1;
	private static JTextField knotenEigenschaftenSchluessel_2;
	static JTextField knotenEigenschaftenWert_2;
	private static JLabel anliegendeKanten;
	private static JLabel anzAnliegenderKanten;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;
	private static JButton knotenLöschenKnopf;

	@SuppressWarnings("static-access")
	public InternesFensterKnotenLöschen(){
		internesFensterKnotenLöschen = new InternesFenster();
		kommandoKnoepfePanel  = new JPanel();
		
		this.setKnotenLöschenPanelOben(new JPanel());
		this.setKnotenIdBezeichner(new JLabel("Knoten Id:"));
		this.setKnotenId(new JLabel(""));
		this.setInstanzInfo(new JLabel("Object-Id"));
		this.setKnotenLöschenPanelOben(new JPanel());
		this.setKnotenLöschenPanelErgebnisMenge(new JPanel());
		this.setSplitteNorden(new JSplitPane());
		this.setKnotenIdentifikation(new JLabel("Knoten-Titel: "));
		this.setKnotenIdentifikator(new JTextField("(*)"));
		this.setKnotenEigenschaftenSchluessel_1(new JTextField("Schlüssel_1"));
		this.setKnotenEigenschaftenWert_1(new JTextField("Wert_1"));
		this.setKnotenEigenschaftenSchluessel_2(new JTextField("Schlüssel_2"));
		this.setKnotenEigenschaftenWert_2(new JTextField("Wert_2"));
		this.setAnliegendeKanten(new JLabel("Anliegende Kanten:"));
		this.setAnzAnliegenderKanten(new JLabel(""));
		this.dataLoad = new ArrayList<String>();
		this.setKnotenLöschenKnopf(new JButton("Knoten löschen"));
		this.getKnotenLöschenKnopf().setVisible(false);
	}

	
	public JInternalFrame erstelleFensterKnotenLöschen(final JInternalFrame internerFrame){
		((InternesFensterKnotenLöschen) internerFrame).erstelleDialogFenster(internerFrame);
				JButton ok = new JButton("Okay");
				JButton abbr = new JButton("Abbrechen");
				getKommandoKnoepfePanel().add(ok);
				getKommandoKnoepfePanel().add(abbr);
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenLöschen _qknlöv = new QueryKnotenLöschen(); 
							_qknlöv.verbinden();
							try{
								setDataLoad(_qknlöv.einenKnotenLöschenVorbereiten(
														knotenIdentifikator.getText() 
														));
								for(int i=0; i< getDataLoad().size(); i++){
									String befehl_teil = getDataLoad().get(i);
									letzterBefehl=""+letzterBefehl+befehl_teil;
									setLetzterBefehl(letzterBefehl);
								}
								setEnd_befehl(letzterBefehl);
								Fenster.Hauptfenster.getTxtConsole().setText(getEnd_befehl());
								} catch (SQLException e1) {
								e1.printStackTrace();
								_qknlöv.verbindungTrennen();
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
				getKnotenLöschenPanelOben().setLayout(raster);
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
				getKnotenLöschenPanelOben().add(getKnotenIdBezeichner());
					grenzen.gridx = 2; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5; 
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
				raster.setConstraints(getKnotenId(), grenzen);
				getKnotenLöschenPanelOben().add(getKnotenId());
				getSplitteNorden().setLeftComponent(getKnotenLöschenPanelOben());
				getSplitteNorden().setDividerLocation(200);
		    	getSplitteNorden().setDividerSize(5);
				getSplitteNorden().setRightComponent(getInstanzInfo());
				
				
				GridBagLayout rasterCenter = new GridBagLayout();
				getKnotenLöschenPanelErgebnisMenge().setLayout(rasterCenter);
				GridBagConstraints grenzenCenter = new GridBagConstraints();
				rasterCenter.setConstraints(getKnotenLöschenPanelErgebnisMenge(), grenzenCenter);
					grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
					grenzenCenter.weightx=1;
					grenzenCenter.weighty=0;
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenIdentifikation, grenzenCenter);
				getKnotenLöschenPanelErgebnisMenge().add(knotenIdentifikation);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(getKnotenIdentifikator(), grenzenCenter);
				getKnotenLöschenPanelErgebnisMenge().add(knotenIdentifikator);
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenEigenschaftenSchluessel_1, grenzenCenter);
				getKnotenLöschenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_1);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert_1, grenzenCenter);
		   		getKnotenLöschenPanelErgebnisMenge().add(knotenEigenschaftenWert_1);
		   			grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenEigenschaftenSchluessel_2, grenzenCenter);
				getKnotenLöschenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_2);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert_2, grenzenCenter);
		   		getKnotenLöschenPanelErgebnisMenge().add(knotenEigenschaftenWert_2);
			   		grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnliegendeKanten(), grenzenCenter);
			    getKnotenLöschenPanelErgebnisMenge().add(getAnliegendeKanten());
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnzAnliegenderKanten(), grenzenCenter);
			    getKnotenLöschenPanelErgebnisMenge().add(getAnzAnliegenderKanten());
			   		grenzenCenter.gridheight=1;	
			   		grenzenCenter.gridwidth=2;
		   			grenzenCenter.gridx = 1; 
					grenzenCenter.gridy = 7; 
					grenzenCenter.ipadx = 5; 
					grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(getKnotenLöschenKnopf(), grenzenCenter);
				getKnotenLöschenPanelErgebnisMenge().add(getKnotenLöschenKnopf());
		   		getKnotenLöschenKnopf().addActionListener(new ActionListener() {
			   		@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenLöschen _qklö = new QueryKnotenLöschen(); 
							_qklö.verbinden();
							try{
								setDataLoad( _qklö.einenKnotenLöschen(
														knotenIdentifikator.getText(),
														getAnzAnliegenderKanten().getText()
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
								_qklö.verbindungTrennen();
							}
						}
					}	
				});
				internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
				internerFrame.add(getKnotenLöschenPanelErgebnisMenge(), BorderLayout.CENTER);
				setInternesFensterKnotenLöschen(internerFrame);
				return internerFrame;
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){
		internerFrame.setClosable(true);
		internerFrame.setTitle("Knoten löschen");
		internerFrame.setBounds(550, 50, 400, 300);
		internerFrame.getContentPane().setLayout(new BorderLayout());
		internerFrame.add(erstelleKommandoKnoepfe(internerFrame),BorderLayout.SOUTH);
		internerFrame.setVisible(true);
		return internerFrame;
	}
	public JPanel erstelleKommandoKnoepfe(final JInternalFrame internerFrame){
		kommandoKnoepfePanel.setLayout(new GridLayout());
		setKommandoKnoepfePanel(kommandoKnoepfePanel);
		return kommandoKnoepfePanel;
	}
	public JInternalFrame getInternesFensterKnotenLöschen(){
		return internesFensterKnotenLöschen;
	}
	public void setInternesFensterKnotenLöschen(JInternalFrame internesFensterKnotenLöschen) {
		this.internesFensterKnotenLöschen = internesFensterKnotenLöschen;
	}
	public JPanel getKnotenLöschenPanel() {
		return knotenLöschenPanelErgebnisMenge;
	}
	public void setKnotenLöschenPanel(JPanel knotenLöschenPanelErgebnisMenge) {
		this.knotenLöschenPanelErgebnisMenge = knotenLöschenPanelErgebnisMenge;
	}
	public JPanel getKommandoKnoepfePanel() {
		return kommandoKnoepfePanel;
	}
	public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
		this.kommandoKnoepfePanel = kommandoKnoepfePanel;
	}
	public JLabel getKnotenIdentifikation() {
		return knotenIdentifikation;
	}
	public void setKnotenIdentifikation(JLabel knotenIdentifikation) {
		this.knotenIdentifikation = knotenIdentifikation;
	}
	public JTextField getKnotenIdentifikator() {
		return knotenIdentifikator;
	}
	public void setKnotenIdentifikator(JTextField knotenIdentifikator) {
		this.knotenIdentifikator = knotenIdentifikator;
	}
	public JPanel getKnotenLöschenPanelOben() {
		return knotenLöschenPanelOben;
	}
	public void setKnotenLöschenPanelOben(JPanel knotenLöschenPanelOben) {
		this.knotenLöschenPanelOben = knotenLöschenPanelOben;
	}
	public static JLabel getKnotenIdBezeichner() {
		return knotenIdBezeichner;
	}
	public static void setKnotenIdBezeichner(JLabel knotenIdBezeichner) {
		InternesFensterKnotenLöschen.knotenIdBezeichner = knotenIdBezeichner;
	}
	public static JLabel getKnotenId() {
		return knotenId;
	}
	public static void setKnotenId(JLabel knotenId) {
		InternesFensterKnotenLöschen.knotenId = knotenId;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKnotenLöschen.instanzInfo = instanzInfo;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKnotenLöschen.splitteNorden = splitteNorden;
	}
	public JPanel getKnotenLöschenPanelErgebnisMenge() {
		return knotenLöschenPanelErgebnisMenge;
	}
	public void setKnotenLöschenPanelErgebnisMenge(
			JPanel knotenLöschenPanelErgebnisMenge) {
		this.knotenLöschenPanelErgebnisMenge = knotenLöschenPanelErgebnisMenge;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_1() {
		return knotenEigenschaftenSchluessel_1;
	}
	public static void setKnotenEigenschaftenSchluessel_1(
			JTextField knotenEigenschaftenSchluessel_1) {
		InternesFensterKnotenLöschen.knotenEigenschaftenSchluessel_1 = knotenEigenschaftenSchluessel_1;
	}
	public static JTextField getKnotenEigenschaftenWert_1() {
		return knotenEigenschaftenWert_1;
	}
	public static void setKnotenEigenschaftenWert_1(
			JTextField knotenEigenschaftenWert_1) {
		InternesFensterKnotenLöschen.knotenEigenschaftenWert_1 = knotenEigenschaftenWert_1;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_2() {
		return knotenEigenschaftenSchluessel_2;
	}
	public static void setKnotenEigenschaftenSchluessel_2(
			JTextField knotenEigenschaftenSchluessel_2) {
		InternesFensterKnotenLöschen.knotenEigenschaftenSchluessel_2 = knotenEigenschaftenSchluessel_2;
	}
	public static JTextField getKnotenEigenschaftenWert_2() {
		return knotenEigenschaftenWert_2;
	}
	public static void setKnotenEigenschaftenWert_2(
			JTextField knotenEigenschaftenWert_2) {
		InternesFensterKnotenLöschen.knotenEigenschaftenWert_2 = knotenEigenschaftenWert_2;
	}
	public static JLabel getAnliegendeKanten() {
		return anliegendeKanten;
	}
	public static void setAnliegendeKanten(JLabel anliegendeKanten) {
		InternesFensterKnotenLöschen.anliegendeKanten = anliegendeKanten;
	}
	public static JLabel getAnzAnliegenderKanten() {
		return anzAnliegenderKanten;
	}
	public static void setAnzAnliegenderKanten(JLabel anzAnliegenderKanten) {
		InternesFensterKnotenLöschen.anzAnliegenderKanten = anzAnliegenderKanten;
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
	public String getEnd_befehl() {
		return end_befehl;
	}
	public void setEnd_befehl(String end_befehl) {
		this.end_befehl = end_befehl;
	}
	public static JButton getKnotenLöschenKnopf() {
		return knotenLöschenKnopf;
	}
	public static void setKnotenLöschenKnopf(JButton knotenLöschenKnopf) {
		InternesFensterKnotenLöschen.knotenLöschenKnopf = knotenLöschenKnopf;
	}
}