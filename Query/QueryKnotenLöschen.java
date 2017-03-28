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

public class QueryKnotenLöschen {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	
	public QueryKnotenLöschen(){
	}
	public void verbinden(){
			try{
				DriverManager.registerDriver(new org.neo4j.jdbc.Driver());
				con = DriverManager.getConnection("jdbc:neo4j://"+dbAdresse+":7474");
			}catch (SQLException e){
				ausnahmeAusgeben(e);
			}
	}
	public List<String> einenKnotenLöschen(String knotenTitle, String anzVerbundeneKanten) throws SQLException{
		Neo4jConnection connect = null;
		StringBuffer strBuffer = new StringBuffer("0");
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				List<String> dataLoad = new ArrayList<String>();
				
				if(anzVerbundeneKanten.contentEquals(strBuffer)){
					dataLoad.add("MATCH (n {title: '"+knotenTitle+"' })");
					dataLoad.add("DELETE n;");
				}else{
					dataLoad.add("MATCH (n {title: '"+knotenTitle+"' })-[r]-(m)");
					dataLoad.add("DELETE n, r;");
				}
				connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
				verbindungTrennen();
		return dataLoad;
	}	
	public List<String> einenKnotenLöschenVorbereiten(String knotenTitle) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				List<String> dataLoad = new ArrayList<String>();
				dataLoad.add("MATCH (n {title:'"+knotenTitle+"'})");
				dataLoad.add("OPTIONAL MATCH (n {title: '"+knotenTitle+"' })-[r]-()");
				dataLoad.add("RETURN id(n),n,count(r)");
				ResultSet x=	connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));			
				Fenster.InternesFensterKnotenLöschen.getKnotenLöschenKnopf().setVisible(true);
				verbindungTrennen();
				String  reSx="";

				while(x.next() == true){
					Fenster.InternesFensterKnotenLöschen.getKnotenId().setText(x.getString(1));
					reSx=x.getString(2);
					Fenster.InternesFensterKnotenLöschen.getAnzAnliegenderKanten().setText(x.getString(3));
				}
				StringTokenizer sTok = new StringTokenizer( reSx,"\"{}[]:,");
				while(sTok.hasMoreTokens()==true){
					Fenster.InternesFensterKnotenLöschen.getKnotenEigenschaftenSchluessel_1().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLöschen.getKnotenEigenschaftenWert_1().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLöschen.getKnotenEigenschaftenSchluessel_2().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLöschen.getKnotenEigenschaftenWert_2().setText((sTok.nextToken()));
				}
		return dataLoad;
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
			System.out.println("Keine Verbindung vorhanden");
			return;}
		try{
			con.close();System.out.println("\nVerbindung getrennt.");
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}
}