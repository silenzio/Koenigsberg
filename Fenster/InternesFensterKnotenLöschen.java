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

import Query.QueryKnotenL�schen;

public class InternesFensterKnotenL�schen extends InternesFenster{

	private static final long serialVersionUID = 1L;
	private JInternalFrame internesFensterKnotenL�schen;
	private JPanel knotenL�schenPanelOben;
	private JPanel kommandoKnoepfePanel;
	private static JLabel knotenIdBezeichner;
	private static JLabel knotenId;
	private static JLabel instanzInfo;
	private static JSplitPane splitteNorden;
	private JPanel knotenL�schenPanelErgebnisMenge;
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
	private static JButton knotenL�schenKnopf;

	@SuppressWarnings("static-access")
	public InternesFensterKnotenL�schen(){
		internesFensterKnotenL�schen = new InternesFenster();
		kommandoKnoepfePanel  = new JPanel();
		
		this.setKnotenL�schenPanelOben(new JPanel());
		this.setKnotenIdBezeichner(new JLabel("Knoten Id:"));
		this.setKnotenId(new JLabel(""));
		this.setInstanzInfo(new JLabel("Object-Id"));
		this.setKnotenL�schenPanelOben(new JPanel());
		this.setKnotenL�schenPanelErgebnisMenge(new JPanel());
		this.setSplitteNorden(new JSplitPane());
		this.setKnotenIdentifikation(new JLabel("Knoten-Titel: "));
		this.setKnotenIdentifikator(new JTextField("(*)"));
		this.setKnotenEigenschaftenSchluessel_1(new JTextField("Schl�ssel_1"));
		this.setKnotenEigenschaftenWert_1(new JTextField("Wert_1"));
		this.setKnotenEigenschaftenSchluessel_2(new JTextField("Schl�ssel_2"));
		this.setKnotenEigenschaftenWert_2(new JTextField("Wert_2"));
		this.setAnliegendeKanten(new JLabel("Anliegende Kanten:"));
		this.setAnzAnliegenderKanten(new JLabel(""));
		this.dataLoad = new ArrayList<String>();
		this.setKnotenL�schenKnopf(new JButton("Knoten l�schen"));
		this.getKnotenL�schenKnopf().setVisible(false);
	}

	
	public JInternalFrame erstelleFensterKnotenL�schen(final JInternalFrame internerFrame){
		((InternesFensterKnotenL�schen) internerFrame).erstelleDialogFenster(internerFrame);
				JButton ok = new JButton("Okay");
				JButton abbr = new JButton("Abbrechen");
				getKommandoKnoepfePanel().add(ok);
				getKommandoKnoepfePanel().add(abbr);
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenL�schen _qknl�v = new QueryKnotenL�schen(); 
							_qknl�v.verbinden();
							try{
								setDataLoad(_qknl�v.einenKnotenL�schenVorbereiten(
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
								_qknl�v.verbindungTrennen();
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
				getKnotenL�schenPanelOben().setLayout(raster);
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
				getKnotenL�schenPanelOben().add(getKnotenIdBezeichner());
					grenzen.gridx = 2; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5; 
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
				raster.setConstraints(getKnotenId(), grenzen);
				getKnotenL�schenPanelOben().add(getKnotenId());
				getSplitteNorden().setLeftComponent(getKnotenL�schenPanelOben());
				getSplitteNorden().setDividerLocation(200);
		    	getSplitteNorden().setDividerSize(5);
				getSplitteNorden().setRightComponent(getInstanzInfo());
				
				
				GridBagLayout rasterCenter = new GridBagLayout();
				getKnotenL�schenPanelErgebnisMenge().setLayout(rasterCenter);
				GridBagConstraints grenzenCenter = new GridBagConstraints();
				rasterCenter.setConstraints(getKnotenL�schenPanelErgebnisMenge(), grenzenCenter);
					grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
					grenzenCenter.weightx=1;
					grenzenCenter.weighty=0;
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenIdentifikation, grenzenCenter);
				getKnotenL�schenPanelErgebnisMenge().add(knotenIdentifikation);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(getKnotenIdentifikator(), grenzenCenter);
				getKnotenL�schenPanelErgebnisMenge().add(knotenIdentifikator);
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenEigenschaftenSchluessel_1, grenzenCenter);
				getKnotenL�schenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_1);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert_1, grenzenCenter);
		   		getKnotenL�schenPanelErgebnisMenge().add(knotenEigenschaftenWert_1);
		   			grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(knotenEigenschaftenSchluessel_2, grenzenCenter);
				getKnotenL�schenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_2);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert_2, grenzenCenter);
		   		getKnotenL�schenPanelErgebnisMenge().add(knotenEigenschaftenWert_2);
			   		grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnliegendeKanten(), grenzenCenter);
			    getKnotenL�schenPanelErgebnisMenge().add(getAnliegendeKanten());
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnzAnliegenderKanten(), grenzenCenter);
			    getKnotenL�schenPanelErgebnisMenge().add(getAnzAnliegenderKanten());
			   		grenzenCenter.gridheight=1;	
			   		grenzenCenter.gridwidth=2;
		   			grenzenCenter.gridx = 1; 
					grenzenCenter.gridy = 7; 
					grenzenCenter.ipadx = 5; 
					grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(getKnotenL�schenKnopf(), grenzenCenter);
				getKnotenL�schenPanelErgebnisMenge().add(getKnotenL�schenKnopf());
		   		getKnotenL�schenKnopf().addActionListener(new ActionListener() {
			   		@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenL�schen _qkl� = new QueryKnotenL�schen(); 
							_qkl�.verbinden();
							try{
								setDataLoad( _qkl�.einenKnotenL�schen(
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
								_qkl�.verbindungTrennen();
							}
						}
					}	
				});
				internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
				internerFrame.add(getKnotenL�schenPanelErgebnisMenge(), BorderLayout.CENTER);
				setInternesFensterKnotenL�schen(internerFrame);
				return internerFrame;
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){
		internerFrame.setClosable(true);
		internerFrame.setTitle("Knoten l�schen");
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
	public JInternalFrame getInternesFensterKnotenL�schen(){
		return internesFensterKnotenL�schen;
	}
	public void setInternesFensterKnotenL�schen(JInternalFrame internesFensterKnotenL�schen) {
		this.internesFensterKnotenL�schen = internesFensterKnotenL�schen;
	}
	public JPanel getKnotenL�schenPanel() {
		return knotenL�schenPanelErgebnisMenge;
	}
	public void setKnotenL�schenPanel(JPanel knotenL�schenPanelErgebnisMenge) {
		this.knotenL�schenPanelErgebnisMenge = knotenL�schenPanelErgebnisMenge;
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
	public JPanel getKnotenL�schenPanelOben() {
		return knotenL�schenPanelOben;
	}
	public void setKnotenL�schenPanelOben(JPanel knotenL�schenPanelOben) {
		this.knotenL�schenPanelOben = knotenL�schenPanelOben;
	}
	public static JLabel getKnotenIdBezeichner() {
		return knotenIdBezeichner;
	}
	public static void setKnotenIdBezeichner(JLabel knotenIdBezeichner) {
		InternesFensterKnotenL�schen.knotenIdBezeichner = knotenIdBezeichner;
	}
	public static JLabel getKnotenId() {
		return knotenId;
	}
	public static void setKnotenId(JLabel knotenId) {
		InternesFensterKnotenL�schen.knotenId = knotenId;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKnotenL�schen.instanzInfo = instanzInfo;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKnotenL�schen.splitteNorden = splitteNorden;
	}
	public JPanel getKnotenL�schenPanelErgebnisMenge() {
		return knotenL�schenPanelErgebnisMenge;
	}
	public void setKnotenL�schenPanelErgebnisMenge(
			JPanel knotenL�schenPanelErgebnisMenge) {
		this.knotenL�schenPanelErgebnisMenge = knotenL�schenPanelErgebnisMenge;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_1() {
		return knotenEigenschaftenSchluessel_1;
	}
	public static void setKnotenEigenschaftenSchluessel_1(
			JTextField knotenEigenschaftenSchluessel_1) {
		InternesFensterKnotenL�schen.knotenEigenschaftenSchluessel_1 = knotenEigenschaftenSchluessel_1;
	}
	public static JTextField getKnotenEigenschaftenWert_1() {
		return knotenEigenschaftenWert_1;
	}
	public static void setKnotenEigenschaftenWert_1(
			JTextField knotenEigenschaftenWert_1) {
		InternesFensterKnotenL�schen.knotenEigenschaftenWert_1 = knotenEigenschaftenWert_1;
	}
	public static JTextField getKnotenEigenschaftenSchluessel_2() {
		return knotenEigenschaftenSchluessel_2;
	}
	public static void setKnotenEigenschaftenSchluessel_2(
			JTextField knotenEigenschaftenSchluessel_2) {
		InternesFensterKnotenL�schen.knotenEigenschaftenSchluessel_2 = knotenEigenschaftenSchluessel_2;
	}
	public static JTextField getKnotenEigenschaftenWert_2() {
		return knotenEigenschaftenWert_2;
	}
	public static void setKnotenEigenschaftenWert_2(
			JTextField knotenEigenschaftenWert_2) {
		InternesFensterKnotenL�schen.knotenEigenschaftenWert_2 = knotenEigenschaftenWert_2;
	}
	public static JLabel getAnliegendeKanten() {
		return anliegendeKanten;
	}
	public static void setAnliegendeKanten(JLabel anliegendeKanten) {
		InternesFensterKnotenL�schen.anliegendeKanten = anliegendeKanten;
	}
	public static JLabel getAnzAnliegenderKanten() {
		return anzAnliegenderKanten;
	}
	public static void setAnzAnliegenderKanten(JLabel anzAnliegenderKanten) {
		InternesFensterKnotenL�schen.anzAnliegenderKanten = anzAnliegenderKanten;
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
	public static JButton getKnotenL�schenKnopf() {
		return knotenL�schenKnopf;
	}
	public static void setKnotenL�schenKnopf(JButton knotenL�schenKnopf) {
		InternesFensterKnotenL�schen.knotenL�schenKnopf = knotenL�schenKnopf;
	}
}