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

import Query.QueryKanteErstellen;

public class InternesFensterKanteErstellen extends InternesFenster{

	private static final long serialVersionUID = 6L;
	private JInternalFrame internesFensterKanteErstellen;
	private JPanel kanteErstellenPanelOben;
	private JPanel kanteErstellenPanel;
	private JLabel kanteIdentifikation;
	private JTextField kanteIdentifikator;
	private static JSplitPane splitteNorden;
	private JLabel kanteStartKnoten;
	private JLabel kanteEndKnoten;
	private JLabel kanteTypBeschreibung;
	private JTextField startKnoten;
	private JTextField endKnoten;
	private JTextField kanteTyp;
	private JTextField kanteEigenschaftenSchluessel;
	private JTextField kanteEigenschaftenWert;
	private JTextField kanteEigenschaftenSchluessel_2;
	private JTextField kanteEigenschaftenWert_2;
	private JPanel kommandoKnoepfePanel;
	private static JLabel instanzInfo;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;

	public InternesFensterKanteErstellen(){
		internesFensterKanteErstellen = new InternesFenster();
		setKanteErstellenPanelOben(new JPanel());
		setKanteErstellenPanel(new JPanel());
		setKanteIdentifikation(new JLabel("Kante Abfrage: "));

//		setSplitteNorden(new JSplitPane());

		setKanteStartKnoten(new JLabel("Start Knoten: "));
		setKanteEndKnoten(new JLabel("Ende Knoten: "));
		setKanteTypBeschreibung(new JLabel("KANTENTYP"));
		setStartKnoten(new JTextField("(*)"));
		setEndKnoten(new JTextField("(*)"));
		setKanteTyp(new JTextField("(*)"));
		setKanteEigenschaftenSchluessel(new JTextField("(*)"));
		setKanteEigenschaftenWert(new JTextField("(*)"));
		setKanteEigenschaftenSchluessel_2(new JTextField("(*)"));
		setKanteEigenschaftenWert_2(new JTextField("(*)"));
		setInstanzInfo(new JLabel("Object-Id"));
		setDataLoad(new ArrayList<String>());
		setKommandoKnoepfePanel(new JPanel());
	}
	public JInternalFrame erstelleFensterKanteErstellen(final InternesFensterKanteErstellen internerFrame){
		((InternesFensterKanteErstellen) internerFrame).erstelleDialogFenster(internerFrame);
		JButton ok = new JButton("Okay");
		JButton abbr = new JButton("Abbrechen");
		getKommandoKnoepfePanel().add(ok);
		getKommandoKnoepfePanel().add(abbr);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() != null){
					QueryKanteErstellen _qkaer = new QueryKanteErstellen(); 
					_qkaer.verbinden();
					try{
						setDataLoad( _qkaer.eineKanteErstellen(
																getStartKnoten().getText(), 
																getEndKnoten().getText(), 
																getKanteTyp().getText(), 
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
						_qkaer.verbindungTrennen();
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
			getKanteErstellenPanel().setLayout(raster);
			GridBagConstraints grenzen = new GridBagConstraints();
				grenzen.fill = GridBagConstraints.HORIZONTAL;
				grenzen.gridx = 1;
				grenzen.gridy = 1;
				grenzen.ipadx = 5;
				grenzen.ipady = 5;
				grenzen.insets.left = 5 ;
				grenzen.insets.top = 5;
				grenzen.insets.bottom = 5;
			raster.setConstraints(getKanteIdentifikation(), grenzen);
			getKanteErstellenPanelOben().add(getKanteIdentifikation());
	//			grenzen.gridx = 2; 
	//	    	grenzen.gridy = 1; 
	//	    	grenzen.ipadx = 5; 
	//	    	grenzen.ipady = 5; 
	//	    	grenzen.insets.left = 2 ;
	//		raster.setConstraints(getKanteIdentifikator(), grenzen);
	//		getKanteErstellenPanel().add(getKanteIdentifikator());
//			getSplitteNorden().setLeftComponent(getKanteErstellenPanelOben());
//	    	getSplitteNorden().setDividerSize(5);
//			getSplitteNorden().setRightComponent(getInstanzInfo());
			
			GridBagLayout rasterCenter = new GridBagLayout();
			getKanteErstellenPanel().setLayout(rasterCenter);
			GridBagConstraints grenzenCenter = new GridBagConstraints();
			rasterCenter.setConstraints(getKanteErstellenPanel(), grenzenCenter);
			grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteStartKnoten(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteStartKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getStartKnoten(), grenzenCenter);
			getKanteErstellenPanel().add(getStartKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEndKnoten(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteEndKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getEndKnoten(), grenzenCenter);
			getKanteErstellenPanel().add(getEndKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTypBeschreibung(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteTypBeschreibung());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTyp(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteTyp());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenSchluessel(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteEigenschaftenSchluessel());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenWert(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteEigenschaftenWert());
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenSchluessel_2(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteEigenschaftenSchluessel_2());
				grenzenCenter.gridx = 2; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenWert_2(), grenzenCenter);
			getKanteErstellenPanel().add(getKanteEigenschaftenWert_2());
//		internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKanteErstellenPanel(), BorderLayout.CENTER);
		setInternesFensterKanteErstellen(internerFrame);
		return getInternesFensterKanteErstellen();
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Kante erstellen");
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
	public  JInternalFrame getInternesFensterKanteErstellen() {
		return internesFensterKanteErstellen;
	}
	public  void setInternesFensterKanteErstellen(
			JInternalFrame internesFensterKanteErstellen) {
		this.internesFensterKanteErstellen = internesFensterKanteErstellen;
	}
	public JPanel getKanteErstellenPanel() {
		return kanteErstellenPanel;
	}
	public void setKanteErstellenPanel(JPanel kanteErstellenPanel) {
		this.kanteErstellenPanel = kanteErstellenPanel;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKanteErstellen.splitteNorden = splitteNorden;
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
	public JTextField getStartKnoten() {
		return startKnoten;
	}
	public void setStartKnoten(JTextField startKnoten) {
		this.startKnoten = startKnoten;
	}
	public JTextField getEndKnoten() {
		return endKnoten;
	}
	public void setEndKnoten(JTextField endKnoten) {
		this.endKnoten = endKnoten;
	}
	public JTextField getKanteTyp() {
		return kanteTyp;
	}
	public void setKanteTyp(JTextField kanteTyp) {
		this.kanteTyp = kanteTyp;
	}
	public JTextField getKanteEigenschaftenSchluessel() {
		return kanteEigenschaftenSchluessel;
	}
	public void setKanteEigenschaftenSchluessel(
			JTextField kanteEigenschaftenSchluessel) {
		this.kanteEigenschaftenSchluessel = kanteEigenschaftenSchluessel;
	}
	public JTextField getKanteEigenschaftenWert() {
		return kanteEigenschaftenWert;
	}
	public void setKanteEigenschaftenWert(JTextField kanteEigenschaftenWert) {
		this.kanteEigenschaftenWert = kanteEigenschaftenWert;
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
		InternesFensterKanteErstellen.instanzInfo = instanzInfo;
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
	public JLabel getKanteIdentifikation() {
		return kanteIdentifikation;
	}
	public void setKanteIdentifikation(JLabel kanteIdentifikation) {
		this.kanteIdentifikation = kanteIdentifikation;
	}
	public JTextField getKanteIdentifikator() {
		return kanteIdentifikator;
	}
	public void setKanteIdentifikator(JTextField kanteIdentifikator) {
		this.kanteIdentifikator = kanteIdentifikator;
	}
	public JPanel getKanteErstellenPanelOben() {
		return kanteErstellenPanelOben;
	}
	public void setKanteErstellenPanelOben(JPanel kanteErstellenPanelOben) {
		this.kanteErstellenPanelOben = kanteErstellenPanelOben;
	}
	public JTextField getKanteEigenschaftenSchluessel_2() {
		return kanteEigenschaftenSchluessel_2;
	}
	public void setKanteEigenschaftenSchluessel_2(
			JTextField kanteEigenschaftenSchluessel_2) {
		this.kanteEigenschaftenSchluessel_2 = kanteEigenschaftenSchluessel_2;
	}
	public JTextField getKanteEigenschaftenWert_2() {
		return kanteEigenschaftenWert_2;
	}
	public void setKanteEigenschaftenWert_2(JTextField kanteEigenschaftenWert_2) {
		this.kanteEigenschaftenWert_2 = kanteEigenschaftenWert_2;
	}
}
