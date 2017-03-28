package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.neo4j.server.database.GraphDatabaseFactory;

public class QueryKanteL�schen {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	
	public QueryKanteL�schen(){
	}
	public void verbinden(){
			try{
				DriverManager.registerDriver(new org.neo4j.jdbc.Driver());
				con = DriverManager.getConnection("jdbc:neo4j://"+dbAdresse+":7474");
			}catch (SQLException e){
				ausnahmeAusgeben(e);
			}
	}
	public List<String> eineKanteL�schen(String startKnoten, String endKnoten, String Kantentyp
			, String eigenschaftenSchl�ssel, String eigenschaftenWert
			, String eigenschaftenSchl�ssel_2, String eigenschaftenWert_2) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
				List<String> dataLoad = new ArrayList<String>();
				dataLoad.add("START n = node(*)");
				dataLoad.add("MATCH (n{title:'"+startKnoten+"'})-");
				dataLoad.add("[r:`"+Kantentyp+"`{"+eigenschaftenSchl�ssel+":'"+eigenschaftenWert+"',");
				dataLoad.add(""+eigenschaftenSchl�ssel_2+":'"+eigenschaftenWert_2+"'}]");
				dataLoad.add("->(m{title:'"+endKnoten+"'})");
				dataLoad.add("DELETE r");
				connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
				Fenster.InternesFensterKanteL�schen.getKanteL�schenKnopf().setVisible(true);
		return dataLoad;
}	
	public List<String> eineKanteL�schenVorbereiten(String startKnoten, String endKnoten, String Kantentyp
				, String eigenschaftenSchl�ssel, String eigenschaftenWert
				, String eigenschaftenSchl�ssel_2, String eigenschaftenWert_2) throws SQLException{
			Neo4jConnection connect = null;
			try {
				connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			} catch (SQLException e1) {
				e1.printStackTrace();	
			}
					List<String> dataLoad = new ArrayList<String>();

					dataLoad.add("Start n = node(*)");
					dataLoad.add("MATCH (n)-[r:`"+Kantentyp+"`]-(m)");
					dataLoad.add("WHERE r.`"+eigenschaftenSchl�ssel+"`='"+eigenschaftenWert+"'");
					dataLoad.add("AND r.`"+eigenschaftenSchl�ssel_2+"`='"+eigenschaftenWert_2+"'");
					dataLoad.add("AND n.`title` = '"+startKnoten+"'");
					dataLoad.add("AND m.`title` = '"+endKnoten+"'");
					dataLoad.add("RETURN id(r)");
					ResultSet x=	connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
					Fenster.InternesFensterKanteL�schen.getKanteL�schenKnopf().setVisible(true);

					verbindungTrennen();
					while(x.next() == true){
						Fenster.InternesFensterKanteL�schen.getKanteIdentifikator().setText(x.getString(1));
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