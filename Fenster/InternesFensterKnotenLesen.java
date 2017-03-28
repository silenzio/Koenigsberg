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

import Query.QueryKnotenLesen;
	
	public class InternesFensterKnotenLesen extends InternesFenster{
	
	private static final long serialVersionUID = 1L;
		
		private JInternalFrame internesFensterKnotenLesen;
		private static JSplitPane splitteNorden;
		private JPanel knotenLesenPanel;
		private JPanel knotenLesenPanelErgebnisMenge;
		private JPanel kommandoKnoepfePanel;
		private JLabel knotenIdBezeichner;
		private static JLabel instanzInfo;
		private static JTextPane ergebnisMenge;
		private static JLabel knotenId;
		private JLabel knotenTitleLabel;
		private JTextField knotenTitle;
		private static JTextField knotenEigenschaftenSchluessel;
		private static JTextField knotenEigenschaftenWert;
		private static JTextField knotenEigenschaftenSchluessel_2;
		private static JTextField knotenEigenschaftenWert_2;
		private static JLabel anliegendeKanten;
		private static JLabel anzAnliegenderKanten;
		
		
		private List<String> dataLoad;
		
		private String letzterBefehl="",end_befehl;
		
		public InternesFensterKnotenLesen(){
			internesFensterKnotenLesen = new InternesFenster();
			dataLoad = new ArrayList<String>();
			setKommandoKnoepfePanel(new JPanel());
			setKnotenLesenPanel(new JPanel());
			setInstanzInfo(new JLabel("Object-Id"));
			setSplitteNorden(new JSplitPane());
			setKnotenIdentifikation(new JLabel("Knoten Id: "));
			setKnotenId(new JLabel(""));
			setKnotenLesenPanelErgebnisMenge(new JPanel());
			setKnotenTitleLabel(new JLabel("Knoten Titel:"));
			setKnotenTitle(new JTextField("(*)"));
			setErgebnisMenge(new JTextPane());
			setKnotenEigenschaftenSchluessel(new JTextField("Eigenschaften_Schluessel"));
			setKnotenEigenschaftenWert(new JTextField("Eigenschaften_Wert"));
			setKnotenEigenschaftenSchluessel_2(new JTextField("Eigenschaften_Schluessel"));
			setKnotenEigenschaftenWert_2(new JTextField("Eigenschaften_Wert"));
			setAnliegendeKanten(new JLabel("Anliegende Kanten:"));
			setAnzAnliegenderKanten(new JLabel(""));
			getErgebnisMenge().setSize(100, 100);
		}
		public JInternalFrame erstelleFensterKnotenLesen(final JInternalFrame internerFrame){
			((InternesFensterKnotenLesen) internerFrame).erstelleDialogFenster(internerFrame);
				JButton ok = new JButton("Okay");
				JButton abbr = new JButton("Abbrechen");
				getKommandoKnoepfePanel().add(ok);
				getKommandoKnoepfePanel().add(abbr);
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(arg0.getSource() != null){
							QueryKnotenLesen _qknle = new QueryKnotenLesen(); 
							_qknle.verbinden();
							try{
								setDataLoad( _qknle.einenKnotenLesen(
														knotenTitle.getText() 
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
								_qknle.verbindungTrennen();
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
				getKnotenLesenPanel().setLayout(raster);
				GridBagConstraints grenzen = new GridBagConstraints();
				raster.setConstraints(getKnotenLesenPanel(), grenzen);
					grenzen.gridx = 1; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5;
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
				raster.setConstraints(getKnotenIdentifikation(), grenzen);
				getKnotenLesenPanel().add(getKnotenIdentifikation());
					grenzen.gridx = 2; 
			    	grenzen.gridy = 1; 
			    	grenzen.ipadx = 5; 
			    	grenzen.ipady = 5;
			    	grenzen.insets.left = 5 ;
					grenzen.insets.top = 5 ;
					grenzen.insets.bottom = 5 ;
			    raster.setConstraints(getKnotenId(), grenzen);
				getKnotenLesenPanel().add(getKnotenId());
		    	getSplitteNorden().setLeftComponent(getKnotenLesenPanel());
		    	getSplitteNorden().setDividerLocation(200);
		    	getSplitteNorden().setDividerSize(5);
				getSplitteNorden().setRightComponent(getInstanzInfo());

				GridBagLayout rasterCenter = new GridBagLayout();
				getKnotenLesenPanelErgebnisMenge().setLayout(rasterCenter);
				GridBagConstraints grenzenCenter = new GridBagConstraints();
				rasterCenter.setConstraints(getKnotenLesenPanelErgebnisMenge(), grenzenCenter);
				grenzenCenter.fill = GridBagConstraints.HORIZONTAL;
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
				rasterCenter.setConstraints(getKnotenTitleLabel(), grenzenCenter);
				getKnotenLesenPanelErgebnisMenge().add(getKnotenTitleLabel());
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 1; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5;
		    	rasterCenter.setConstraints(getKnotenTitle(), grenzenCenter);
				getKnotenLesenPanelErgebnisMenge().add(getKnotenTitle());
					grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenSchluessel, grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 2; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert, grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(knotenEigenschaftenWert);
				    grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenSchluessel_2, grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(knotenEigenschaftenSchluessel_2);
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 3; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(knotenEigenschaftenWert_2, grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(knotenEigenschaftenWert_2);
				    grenzenCenter.gridx = 1; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnliegendeKanten(), grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(getAnliegendeKanten());
					grenzenCenter.gridx = 2; 
			    	grenzenCenter.gridy = 4; 
			    	grenzenCenter.ipadx = 5; 
			    	grenzenCenter.ipady = 5; 
			    rasterCenter.setConstraints(getAnzAnliegenderKanten(), grenzenCenter);
			    getKnotenLesenPanelErgebnisMenge().add(getAnzAnliegenderKanten());
				internerFrame.add(getSplitteNorden(), BorderLayout.NORTH);
				internerFrame.add(getKnotenLesenPanelErgebnisMenge(), BorderLayout.CENTER);
				setInternesFensterKnotenLesen(internerFrame);
			return internerFrame;
		}
		public JInternalFrame erstelleDialogFenster(JInternalFrame internerFrame){
			internerFrame.setClosable(true);
			internerFrame.setResizable(true);
			internerFrame.setMaximizable(true);
			internerFrame.setTitle("Knoten lesen");
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
		public JInternalFrame getInternesFensterKnotenLesen() {
			return internesFensterKnotenLesen;
		}
		public void setInternesFensterKnotenLesen(JInternalFrame internesFensterKnotenLesen) {
			this.internesFensterKnotenLesen = internesFensterKnotenLesen;
		}
		public JPanel getKnotenLesenPanel() {
			return knotenLesenPanel;
		}
		public void setKnotenLesenPanel(JPanel knotenLesenPanel) {
			this.knotenLesenPanel = knotenLesenPanel;
		}
		public JPanel getKommandoKnoepfePanel() {
			return kommandoKnoepfePanel;
		}
		public void setKommandoKnoepfePanel(JPanel kommandoKnoepfePanel) {
			this.kommandoKnoepfePanel = kommandoKnoepfePanel;
		}
		public JLabel getKnotenIdentifikation() {
			return knotenIdBezeichner;
		}
		public void setKnotenIdentifikation(JLabel knotenIdBezeichner) {
			this.knotenIdBezeichner = knotenIdBezeichner;
		}
		public static JLabel getKnotenId() {
			return knotenId;
		}
		public void setKnotenId(JLabel knotenId) {
			InternesFensterKnotenLesen.knotenId = knotenId;
		}
		public String getEnd_befehl() {
			return end_befehl;
		}
		public void setEnd_befehl(String end_befehl) {
			this.end_befehl = end_befehl;
		}
		public static JTextPane getErgebnisMenge() {
			return ergebnisMenge;
		}
		public void setErgebnisMenge(JTextPane jTextPane) {
			InternesFensterKnotenLesen.ergebnisMenge = jTextPane;
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
		public JPanel getKnotenLesenPanelErgebnisMenge() {
			return knotenLesenPanelErgebnisMenge;
		}
		public void setKnotenLesenPanelErgebnisMenge(
				JPanel knotenLesenPanelErgebnisMenge) {
			this.knotenLesenPanelErgebnisMenge = knotenLesenPanelErgebnisMenge;
		}
		public static JTextField getKnotenEigenschaftenSchluessel() {
			return knotenEigenschaftenSchluessel;
		}
		public void setKnotenEigenschaftenSchluessel(
				JTextField knotenEigenschaftenSchluessel) {
			InternesFensterKnotenLesen.knotenEigenschaftenSchluessel = knotenEigenschaftenSchluessel;
		}
		public static JTextField getKnotenEigenschaftenWert() {
			return knotenEigenschaftenWert;
		}
		public void setKnotenEigenschaftenWert(JTextField knotenEigenschaftenWert) {
			InternesFensterKnotenLesen.knotenEigenschaftenWert = knotenEigenschaftenWert;
		}
		public static JTextField getKnotenEigenschaftenSchluessel_2() {
			return knotenEigenschaftenSchluessel_2;
		}
		public void setKnotenEigenschaftenSchluessel_2(
				JTextField knotenEigenschaftenSchluessel_2) {
			InternesFensterKnotenLesen.knotenEigenschaftenSchluessel_2 = knotenEigenschaftenSchluessel_2;
		}
		public static JTextField getKnotenEigenschaftenWert_2() {
			return knotenEigenschaftenWert_2;
		}
		public void setKnotenEigenschaftenWert_2(JTextField knotenEigenschaftenWert_2) {
			InternesFensterKnotenLesen.knotenEigenschaftenWert_2 = knotenEigenschaftenWert_2;
		}
		public JLabel getKnotenIdBezeichner() {
			return knotenIdBezeichner;
		}
		public void setKnotenIdBezeichner(JLabel knotenIdBezeichner) {
			this.knotenIdBezeichner = knotenIdBezeichner;
		}
		public static JLabel getAnliegendeKanten() {
			return anliegendeKanten;
		}
		public static void setAnliegendeKanten(JLabel anliegendeKanten) {
			InternesFensterKnotenLesen.anliegendeKanten = anliegendeKanten;
		}
		public static JLabel getAnzAnliegenderKanten() {
			return anzAnliegenderKanten;
		}
		public static void setAnzAnliegenderKanten(JLabel anzAnliegenderKanten) {
			InternesFensterKnotenLesen.anzAnliegenderKanten = anzAnliegenderKanten;
		}
		public JTextField getKnotenTitle() {
			return knotenTitle;
		}
		public void setKnotenTitle(JTextField knotenTitle) {
			this.knotenTitle = knotenTitle;
		}
		public static JLabel getInstanzInfo() {
			return instanzInfo;
		}
		public void setInstanzInfo(JLabel instanzInfo) {
			InternesFensterKnotenLesen.instanzInfo = instanzInfo;
		}
		public static JSplitPane getSplitteNorden() {
			return splitteNorden;
		}
		public void setSplitteNorden(JSplitPane splitteNorden) {
			InternesFensterKnotenLesen.splitteNorden = splitteNorden;
		}
		public JLabel getKnotenTitleLabel() {
			return knotenTitleLabel;
		}
		public void setKnotenTitleLabel(JLabel knotenTitleLabel) {
			this.knotenTitleLabel = knotenTitleLabel;
		}
	}
