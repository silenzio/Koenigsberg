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

public class QueryKnotenLesen {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	public QueryKnotenLesen(){
	}
	public List<String> einenKnotenLesen(String knotenTitle) throws SQLException{
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
				ResultSet x = connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));

//				verbindungAnalysieren();
				verbindungTrennen();
				String  reSx="";
				while(x.next() == true){
					Fenster.InternesFensterKnotenLesen.getKnotenId().setText(x.getString(1));
					reSx=x.getString(2);
					Fenster.InternesFensterKnotenLesen.getAnzAnliegenderKanten().setText(x.getString(3));
				}
				StringTokenizer sTok = new StringTokenizer( reSx,"\"{}[]:,");
				while(sTok.hasMoreTokens()==true){
					Fenster.InternesFensterKnotenLesen.getKnotenEigenschaftenSchluessel().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLesen.getKnotenEigenschaftenWert().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLesen.getKnotenEigenschaftenSchluessel_2().setText((sTok.nextToken()));
					Fenster.InternesFensterKnotenLesen.getKnotenEigenschaftenWert_2().setText((sTok.nextToken()));
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
			System.out.println("Keine Verbindung vorhanden");
			return;}
		try{
			con.close();System.out.println("\nVerbindung getrennt.");
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}
	public void verbindungAnalysieren(){
		if(con==null){
			System.out.println("Keine Verbindung vorhanden");
			return;}
		try{
			Fenster.InternesFensterKnotenLesen.getInstanzInfo().setText(con.getMetaData().toString());
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}

}
