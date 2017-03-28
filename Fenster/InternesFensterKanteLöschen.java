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

import Query.QueryKanteL�schen;

public class InternesFensterKanteL�schen extends InternesFenster{

	private static final long serialVersionUID = 5L;
	private JInternalFrame internesFensterKanteL�schen;
	private JPanel kanteL�schenPanelOben;
	private JPanel kanteL�schenPanel;
	private static JLabel kanteIdentifikation;
	private static JLabel kanteIdentifikator;
	private static JSplitPane splitteNorden;
	private static JLabel instanzInfo;
	private JLabel kanteStartKnoten;
	private JLabel kanteEndKnoten;
	private JLabel kanteTypBeschreibung;
	private JTextField startKnoten;
	private JTextField endKnoten;
	private JTextField kanteTyp;
	private JTextField kanteEigenschaftenSchluessel;
	private JTextField kanteEigenschaftenWert;
	private static JTextField kanteEigenschaftenSchluessel_2;
	private static JTextField kanteEigenschaftenWert_2;
	private static JButton kanteL�schenKnopf;
	private JPanel kommandoKnoepfePanel;
	private List<String> dataLoad;
	private String letzterBefehl="",end_befehl;

	public InternesFensterKanteL�schen(){
		internesFensterKanteL�schen = new InternesFenster();
		setKanteL�schenPanelOben(new JPanel());
		setKanteL�schenPanel(new JPanel());
		setKanteIdentifikation(new JLabel("Kante Id: "));
		setKanteIdentifikator(new JLabel(""));
		setSplitteNorden(new JSplitPane());
		setInstanzInfo(new JLabel("Object-Id"));
		setDataLoad(new ArrayList<String>());
		setKommandoKnoepfePanel(new JPanel());
		setKanteStartKnoten(new JLabel("Start Knoten:"));
		setKanteEndKnoten(new JLabel("Ende Knoten:"));
		setKanteTypBeschreibung(new JLabel("Kantentyp"));
		setStartKnoten(new JTextField("(*)"));
		setEndKnoten(new JTextField("(*)"));
		setKanteTyp(new JTextField("(*)"));
		setKanteEigenschaftenSchluessel(new JTextField("(*)"));
		setKanteEigenschaftenWert(new JTextField("(*)"));
		setKanteEigenschaftenSchluessel_2(new JTextField("(*)"));
		setKanteEigenschaftenWert_2(new JTextField("(*)                   "));
		setKanteL�schenKnopf(new JButton("Kn�ten l�schen"));
		getKanteL�schenKnopf().setVisible(false);
	}
	public JInternalFrame erstelleFensterKanteL�schen(final InternesFensterKanteL�schen internerFrame){
		((InternesFensterKanteL�schen) internerFrame).erstelleDialogFenster(internerFrame);
		
		JButton ok = new JButton("Okay");
		JButton abbr = new JButton("Abbrechen");
		getKommandoKnoepfePanel().add(ok);
		getKommandoKnoepfePanel().add(abbr);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() != null){
					//Query Kante L�schen Vorbereiten
					QueryKanteL�schen _qkal�v = new QueryKanteL�schen(); 
					_qkal�v.verbinden();
					try{
						setDataLoad( _qkal�v.eineKanteL�schenVorbereiten(
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
//								internerFrame.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
						_qkal�v.verbindungTrennen();
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
			getKanteL�schenPanel().setLayout(raster);
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
			getKanteL�schenPanelOben().add(getKanteIdentifikation());
				grenzen.gridx = 2; 
		    	grenzen.gridy = 1; 
		    	grenzen.ipadx = 5; 
		    	grenzen.ipady = 5; 
		    	grenzen.insets.left = 5 ;
				grenzen.insets.top = 5 ;
				grenzen.insets.bottom = 5 ;
			raster.setConstraints(getKanteIdentifikator(), grenzen);
			getKanteL�schenPanelOben().add(getKanteIdentifikator());
			getSplitteNorden().setLeftComponent(getKanteL�schenPanelOben());
			getSplitteNorden().setDividerLocation(200);
			getSplitteNorden().setDividerSize(5);
			getSplitteNorden().setRightComponent(getInstanzInfo());
			
			GridBagLayout rasterCenter = new GridBagLayout();
			getKanteL�schenPanel().setLayout(rasterCenter);
			GridBagConstraints grenzenCenter = new GridBagConstraints();
			rasterCenter.setConstraints(getKanteL�schenPanel(), grenzenCenter);
			grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteStartKnoten(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteStartKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 1; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getStartKnoten(), grenzenCenter);
			getKanteL�schenPanel().add(getStartKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEndKnoten(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteEndKnoten());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 2; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getEndKnoten(), grenzenCenter);
			getKanteL�schenPanel().add(getEndKnoten());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTypBeschreibung(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteTypBeschreibung());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 3; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteTyp(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteTyp());
				grenzenCenter.gridx = 1; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenSchluessel(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteEigenschaftenSchluessel());
				grenzenCenter.gridx = 2; 
		    	grenzenCenter.gridy = 4; 
		    	grenzenCenter.ipadx = 5; 
		    	grenzenCenter.ipady = 5; 
		    rasterCenter.setConstraints(getKanteEigenschaftenWert(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteEigenschaftenWert());
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenSchluessel_2(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteEigenschaftenSchluessel_2());
				grenzenCenter.gridx = 2; 
				grenzenCenter.gridy = 5; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteEigenschaftenWert_2(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteEigenschaftenWert_2());
				grenzenCenter.gridheight=1;	
		   		grenzenCenter.gridwidth=2;
				grenzenCenter.gridx = 1; 
				grenzenCenter.gridy = 7; 
				grenzenCenter.ipadx = 5; 
				grenzenCenter.ipady = 5; 
			rasterCenter.setConstraints(getKanteL�schenKnopf(), grenzenCenter);
			getKanteL�schenPanel().add(getKanteL�schenKnopf());
				
			getKanteL�schenKnopf().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
				//Query Kante L�schen durchf�hren	
				if(arg0.getSource() != null){
						QueryKanteL�schen _qkal� = new QueryKanteL�schen(); 
						_qkal�.verbinden();
						try{
							setDataLoad( _qkal�.eineKanteL�schen(
													
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
							_qkal�.verbindungTrennen();
						}
					}
				}
			});
		internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
			internerFrame.add(getKanteL�schenPanel(), BorderLayout.CENTER);
		setInternesFensterKanteL�schen(internerFrame);
		return getInternesFensterKanteL�schen();
	}
	public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){	  
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Kante l�schen");
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
	
	public JPanel getKanteL�schenPanel() {
		return kanteL�schenPanel;
	}
	public void setKanteL�schenPanel(JPanel kanteL�schenPanel) {
		this.kanteL�schenPanel = kanteL�schenPanel;
	}
	public static JLabel getKanteIdentifikation() {
		return kanteIdentifikation;
	}
	public void setKanteIdentifikation(JLabel kanteIdentifikation) {
		InternesFensterKanteL�schen.kanteIdentifikation = kanteIdentifikation;
	}
	public static JLabel getKanteIdentifikator() {
		return kanteIdentifikator;
	}
	public void setKanteIdentifikator(JLabel kanteIdentifikator) {
		InternesFensterKanteL�schen.kanteIdentifikator = kanteIdentifikator;
	}
	public static JSplitPane getSplitteNorden() {
		return splitteNorden;
	}
	public static void setSplitteNorden(JSplitPane splitteNorden) {
		InternesFensterKanteL�schen.splitteNorden = splitteNorden;
	}
	public static JLabel getInstanzInfo() {
		return instanzInfo;
	}
	public static void setInstanzInfo(JLabel instanzInfo) {
		InternesFensterKanteL�schen.instanzInfo = instanzInfo;
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
	public JInternalFrame getInternesFensterKanteL�schen() {
		return internesFensterKanteL�schen;
	}
	public void setInternesFensterKanteL�schen(
			JInternalFrame internesFensterKanteL�schen) {
		this.internesFensterKanteL�schen = internesFensterKanteL�schen;
	}
	public JPanel getKommandoKnoepfePanel() {
		return kommandoKnoepfePanel;
	}
	public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
		this.kommandoKnoepfePanel = kommandoKnoepfePanel;
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
	public JLabel getKanteTypBeschreibung() {
		return kanteTypBeschreibung;
	}
	public void setKanteTypBeschreibung(JLabel kanteTypBeschreibung) {
		this.kanteTypBeschreibung = kanteTypBeschreibung;
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
	public JPanel getKanteL�schenPanelOben() {
		return kanteL�schenPanelOben;
	}
	public void setKanteL�schenPanelOben(JPanel kanteL�schenPanelOben) {
		this.kanteL�schenPanelOben = kanteL�schenPanelOben;
	}
	public static JButton getKanteL�schenKnopf() {
		return kanteL�schenKnopf;
	}
	public static void setKanteL�schenKnopf(JButton kanteL�schenKnopf) {
		InternesFensterKanteL�schen.kanteL�schenKnopf = kanteL�schenKnopf;
	}
	public static JTextField getKanteEigenschaftenWert_2() {
		return kanteEigenschaftenWert_2;
	}
	public static void setKanteEigenschaftenWert_2(
			JTextField kanteEigenschaftenWert_2) {
		InternesFensterKanteL�schen.kanteEigenschaftenWert_2 = kanteEigenschaftenWert_2;
	}
	public static JTextField getKanteEigenschaftenSchluessel_2() {
		return kanteEigenschaftenSchluessel_2;
	}
	public static void setKanteEigenschaftenSchluessel_2(
			JTextField kanteEigenschaftenSchluessel_2) {
		InternesFensterKanteL�schen.kanteEigenschaftenSchluessel_2 = kanteEigenschaftenSchluessel_2;
	}
}