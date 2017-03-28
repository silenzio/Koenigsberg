package Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.neo4j.server.database.GraphDatabaseFactory;

public class QueryKnotenÄndern {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	public QueryKnotenÄndern(){
	}
	
	public List<String> einenKnotenÄndern(String knotenTitle) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
		List<String> dataLoad = new ArrayList<String>();
		dataLoad.add("MERGE (n {title:'"+knotenTitle+"'})");
		dataLoad.add("ON MATCH SET"); 
		dataLoad.add("n.title = '" +Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenWert_1().getText()+"'");
		dataLoad.add(",n.Identifiaktionsbuchstabe = '"+Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenWert_2().getText()+"'");
		connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
		
		return dataLoad;
	}

	public List<String> einenKnotenÄndernVorbereiten(String knotenTitle) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
		List<String> dataLoad = new ArrayList<String>();
		dataLoad.add("MATCH (n {title: '"+knotenTitle+"'})");
		dataLoad.add("RETURN id(n),n");
		ResultSet x=	connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
		Fenster.InternesFensterKnotenÄndern.getKnotenÄndernKnopf().setVisible(true);
		String  reSx="";
		while(x.next() == true){
			Fenster.InternesFensterKnotenÄndern.getKnotenId().setText(x.getString(1));
			reSx=x.getString(2);
		}
		StringTokenizer sTok = new StringTokenizer( reSx,"\"{}[]:,");
		while(sTok.hasMoreTokens()==true){
		Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenSchluessel_1().setText((sTok.nextToken()));
		Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenWert_1().setText((sTok.nextToken()));
		Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenSchluessel_2().setText((sTok.nextToken()));
		Fenster.InternesFensterKnotenÄndern.getKnotenEigenschaftenWert_2().setText((sTok.nextToken()));
		}
		return dataLoad;
	}
	
	public void verbinden(){
		try{
			DriverManager.registerDriver(new org.neo4j.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:neo4j://"+dbAdresse+":7474");
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}
	private void ausnahmeAusgeben(SQLException e) {
		while(e!=null){
			System.err.println("NEO4J Fehlercode: "+e.getErrorCode());
			System.err.println("NOSQL: "+e.getSQLState());
			System.err.println(e);
			e=e.getNextException();
		}
	}
	public void verbindungTrennen(){
		if(con==null){
			System.out.println("Verbindung trennen. Keine Verbindung vorhanden");
			return;}
		try{
			con.close();System.out.println("\n--------------------\nDaten \u00fcbertragen.\n--------------------");
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}
}
