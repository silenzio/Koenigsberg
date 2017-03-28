package Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.neo4j.server.database.GraphDatabaseFactory;

public class QueryKanteErstellen {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	public QueryKanteErstellen(){
	}

	public List<String> eineKanteErstellen(String startKnoten, String endKnoten, String Kantentyp, 
			String eigenschaftenSchlüssel, String eigenschaftenWert, String eigenschaftenSchlüssel_2, String eigenschaftenWert_2) 
			throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
		List<String> dataLoad = new ArrayList<String>();
		dataLoad.add("MATCH (n),(m)");
		dataLoad.add(" WHERE n.title = '"+startKnoten+"' AND m.title ='"+endKnoten+"'");
		dataLoad.add(" CREATE UNIQUE(n)-[r:"+Kantentyp+"{"+eigenschaftenSchlüssel+":'"+eigenschaftenWert+"',"+eigenschaftenSchlüssel_2+":'"+eigenschaftenWert_2+"'}]->(m)");
		dataLoad.add(" RETURN n, r, m");
		connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
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
}