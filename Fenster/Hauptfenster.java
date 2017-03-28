package Fenster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import Query.QueryKnotenLöschen;
import Query.QueryPfadeFinden;
import Query.test;

public class Hauptfenster extends JFrame{

	private static final long serialVersionUID = 12345678876543212L;
	private JLayeredPane contentPane;
	private final static String NEO4J_DB_PATH = "E:/Neo4j/Graph Directory/";	
	private final JFileChooser fileChooser = new JFileChooser(NEO4J_DB_PATH);
	final static String LOOKANDFEEL = "System";
	final static String THEME = "System";
//	private String versionsnummer= "0.6.4";
	static JTextField TxtConsole;
	
	public Hauptfenster() {
		initLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 720);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Programm");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		mnNewMenu.addSeparator();
		JMenu submenu = new JMenu("Hilfe");
		mnNewMenu.add(submenu);
		JMenuItem smntmNewMenuItem_1 = new JMenuItem("Über");
//		smntmNewMenuItem_1.setAction(infoFenster);
		submenu.add(smntmNewMenuItem_1);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTxtConsole(new JTextField("CYPHER"));
		getTxtConsole().setBounds(10, 150, 385, 25);
		contentPane.add(getTxtConsole(), JLayeredPane.DEFAULT_LAYER);
		
		JButton btnNewButton_2 = new JButton("Knoten erstellen");
		btnNewButton_2.setBounds(10, 36, 152, 23);
		contentPane.add(btnNewButton_2, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKnotenErstellen _internesFensterKnotenErstellen = new InternesFensterKnotenErstellen();
					_internesFensterKnotenErstellen.erstelleFensterKnotenErstellen(_internesFensterKnotenErstellen);
					contentPane.add(_internesFensterKnotenErstellen.getInternesFensterKnotenErstellen());
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_3 = new JButton("Knoten \u00E4ndern");
		btnNewButton_3.setBounds(10, 59, 152, 23);
		contentPane.add(btnNewButton_3, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKnotenÄndern _internesFensterKnotenÄndern = new InternesFensterKnotenÄndern();
					_internesFensterKnotenÄndern.erstelleFensterKnotenÄndern(_internesFensterKnotenÄndern);
					_internesFensterKnotenÄndern.getInternesFensterKnotenÄndern();
					contentPane.add(_internesFensterKnotenÄndern.getInternesFensterKnotenÄndern(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_4 = new JButton("Knoten l\u00F6schen");
		btnNewButton_4.setBounds(10, 82, 152, 23);
		contentPane.add(btnNewButton_4, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_4.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKnotenLöschen _internesFensterKnotenLöschen = new InternesFensterKnotenLöschen();
					_internesFensterKnotenLöschen.erstelleFensterKnotenLöschen(_internesFensterKnotenLöschen);
					contentPane.add(_internesFensterKnotenLöschen.getInternesFensterKnotenLöschen(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_5 = new JButton("Knoten lesen");
		btnNewButton_5.setBounds(10, 105, 152, 23);
		contentPane.add(btnNewButton_5, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKnotenLesen _internesFensterKnotenLesen = new InternesFensterKnotenLesen();
					_internesFensterKnotenLesen.erstelleFensterKnotenLesen(_internesFensterKnotenLesen);
					contentPane.add(_internesFensterKnotenLesen.getInternesFensterKnotenLesen(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_6 = new JButton("Kante erstellen");
		btnNewButton_6.setBounds(243, 36, 152, 23);
		contentPane.add(btnNewButton_6, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKanteErstellen _internesFensterKanteErstellen = new InternesFensterKanteErstellen();
					_internesFensterKanteErstellen.erstelleFensterKanteErstellen(_internesFensterKanteErstellen);
					contentPane.add(_internesFensterKanteErstellen.getInternesFensterKanteErstellen(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_7 = new JButton("Kante \u00E4ndern");
		btnNewButton_7.setBounds(243, 59, 152, 23);
		contentPane.add(btnNewButton_7, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKanteÄndern _internesFensterKanteÄndern = new InternesFensterKanteÄndern();
					_internesFensterKanteÄndern.erstelleFensterKanteÄndern(_internesFensterKanteÄndern);
					contentPane.add(_internesFensterKanteÄndern.getInternesFensterKanteÄndern(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_8 = new JButton("Kante l\u00F6schen");
		btnNewButton_8.setBounds(243, 82, 152, 23);
		contentPane.add(btnNewButton_8, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKanteLöschen _internesFensterKanteLöschen = new InternesFensterKanteLöschen();
					_internesFensterKanteLöschen.erstelleFensterKanteLöschen(_internesFensterKanteLöschen);
					contentPane.add(_internesFensterKanteLöschen.getInternesFensterKanteLöschen(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_9 = new JButton("Kante lesen");
		btnNewButton_9.setBounds(243, 105, 152, 23);
		contentPane.add(btnNewButton_9, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					InternesFensterKanteLesen _internesFensterKanteLesen = new InternesFensterKanteLesen();
					_internesFensterKanteLesen.erstelleFensterKanteLesen(_internesFensterKanteLesen);
					contentPane.add(_internesFensterKanteLesen.getInternesFensterKanteLesen(), JLayeredPane.PALETTE_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_10 = new JButton("Brückenproblem");
		btnNewButton_10.setBounds(243, 225, 152, 23);
		contentPane.add(btnNewButton_10, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
				test ts= new test();
				ts.verbinden();
				try {
					ts.brueckenmodellErstellen();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
//				ts.verbindungAnalysieren(); 
				ts.verbindungTrennen();
				contentPane.repaint();
				contentPane.revalidate();
				}
			}
		});
		JButton btnNewButton_11 = new JButton("TestButton(empty)");
		btnNewButton_11.setBounds(10, 225, 152, 23);
		contentPane.add(btnNewButton_11, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() != null){
					QueryPfadeFinden _qpffi = new QueryPfadeFinden(); 
					_qpffi.verbinden();
					try{
						_qpffi.pfadFinden();
											
					} catch (SQLException e1) {
						e1.printStackTrace();
						_qpffi.verbindungTrennen();
					}
				}
			}
		});
		JButton btnNewButton_12 = new JButton("Breitensuche(empty)");
		btnNewButton_12.setBounds(10, 345, 152, 23);
		contentPane.add(btnNewButton_12, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		JButton btnNewButton_13 = new JButton("Tiefensuche(empty)");
		btnNewButton_13.setBounds(10, 368, 152, 23);
		contentPane.add(btnNewButton_13, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_13.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton btnNewButton_14 = new JButton("Dijkstra(empty)");
		btnNewButton_14.setBounds(10, 391, 152, 23);
		contentPane.add(btnNewButton_14, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton btnNewButton_15 = new JButton("Shortest Path(empty)");
		btnNewButton_15.setBounds(10, 414, 152, 23);
		contentPane.add(btnNewButton_15, JLayeredPane.DEFAULT_LAYER);
		btnNewButton_15.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JLabel lblKnoten = new JLabel("Knoten:");
		lblKnoten.setBounds(10, 11, 46, 14);
		contentPane.add(lblKnoten, JLayeredPane.DEFAULT_LAYER);
		JLabel lblKante = new JLabel("Kante:");
		lblKante.setBounds(243, 11, 46, 14);
		contentPane.add(lblKante, JLayeredPane.DEFAULT_LAYER);
		
		JLabel lblTest = new JLabel("Test:");
		lblTest.setBounds(10, 200, 46, 14);
		contentPane.add(lblTest, JLayeredPane.DEFAULT_LAYER);
		JLabel lblKBPGraph = new JLabel("Datenmodell laden:");
		lblKBPGraph.setBounds(243, 200, 200, 14);
		contentPane.add(lblKBPGraph, JLayeredPane.DEFAULT_LAYER);
		JLabel lblTrav = new JLabel("Traversierung:");
		lblTrav.setBounds(10, 320, 200, 14);
		contentPane.add(lblTrav, JLayeredPane.DEFAULT_LAYER);
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("NEO4J");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnNewRadioButton.isSelected()==true){
					fileChooser.setBounds(754, 86, 594, 760);
					contentPane.add(fileChooser, JLayeredPane.DEFAULT_LAYER);
					contentPane.repaint();
					contentPane.revalidate();
				}
				if(rdbtnNewRadioButton.isSelected()==false){
					contentPane.remove(fileChooser);
					contentPane.repaint();
					contentPane.revalidate();
				}
			}
		});
//		rdbtnNewRadioButton.setAction(datenbankAufDateiSystemAusgabe);
		rdbtnNewRadioButton.setBounds(754, 7, 109, 23);
//		contentPane.add(rdbtnNewRadioButton, JLayeredPane.DEFAULT_LAYER);
	}

	private static void initLookAndFeel(){
    	String lookAndFeel = null;
	    if (LOOKANDFEEL.equals("System")){
	            lookAndFeel = UIManager.getSystemLookAndFeelClassName();
	    } 
	    else {
	            System.err.println("Unexpected value of LOOKANDFEEL specified: "
	                               + LOOKANDFEEL);
	            lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
	    }
		try {
				UIManager.setLookAndFeel(lookAndFeel);
	            
	            // If L&F = "Metal", set the theme
	            
	            if (LOOKANDFEEL.equals("Metal")){
	              if (THEME.equals("DefaultMetal"))
	                 MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
	              else if (THEME.equals("Ocean"))
	                 MetalLookAndFeel.setCurrentTheme(new OceanTheme());
	              UIManager.setLookAndFeel(new MetalLookAndFeel()); 
	   			 }	
		} 
        catch (ClassNotFoundException e){
	            System.err.println("Couldn't find class for specified look and feel:"
	                               + lookAndFeel);
	            System.err.println("Did you include the L&F library in the class path?");
	            System.err.println("Using the default look and feel.");
        } 
        catch (UnsupportedLookAndFeelException e){
	            System.err.println("Can't use the specified look and feel ("
	                               + lookAndFeel
	                               + ") on this platform.");
	            System.err.println("Using the default look and feel.");
        } 
        catch (Exception e){
	            System.err.println("Couldn't get specified look and feel ("
	                               + lookAndFeel
	                               + "), for some reason.");
	            System.err.println("Using the default look and feel.");
	            e.printStackTrace();
        }
    }
	public static JTextField getTxtConsole() {
		return TxtConsole;
	}
	public void setTxtConsole(JTextField jTextField) {
		Hauptfenster.TxtConsole = jTextField;
	}

}
