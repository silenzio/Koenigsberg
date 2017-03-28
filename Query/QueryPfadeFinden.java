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

public class QueryPfadeFinden {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	public QueryPfadeFinden(){
	}
	
	public List<String> pfadFinden() throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		List<String> dataLoad = new ArrayList<String>();
//		dataLoad.add("START from=node(*), to=node(*)");
//		dataLoad.add("MATCH p=from-[r:BRÜCKE*]-to");
//		dataLoad.add("WHERE from.title = to.title");
//		dataLoad.add("RETURN length(p) AS `enthaltene Brücken` ");
//		dataLoad.add(",from AS Anfang");
//		dataLoad.add(",relationships(p)AS Brücken");
//		dataLoad.add(",to AS Ende");
//		dataLoad.add(",nodes(p) AS `betretene Stadtteile`");
//		dataLoad.add("ORDER BY length(p)");
//		
//		ResultSet x=  connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
//		
//		while(x.next() == true){
//			System.out.println(x.getString(1));
//			System.out.println(x.getString(2));
//			System.out.println(x.getString(3));
//			System.out.println(x.getString(4));
//			System.out.println(x.getString(5));
//		}
//		return dataLoad;
//	}

			List<String> dataLoad2 = new ArrayList<String>();
			dataLoad2.add("START from=node(*), to=node(*)");
			dataLoad2.add("MATCH p=from-[r:BRÜCKE*]-to");
			dataLoad2.add("WHERE from.title = to.title");
			dataLoad2.add("RETURN extract(x IN nodes(p) | x.title ),length(p) AS `enthaltene Brücken` ");
			dataLoad2.add("ORDER BY length(p)");
			ResultSet x=  connect.createStatement().executeQuery(StringUtils.join(dataLoad2, "\n"));

			while(x.next() == true){
				System.out.println(x.getString(1));
				System.out.println(x.getString(2));
		}
		return dataLoad2;
	}
	
//			List<String> dataLoad2 = new ArrayList<String>();
//			dataLoad2.add("START from=node(*), to=node(*)");
//			dataLoad2.add("MATCH p=from-[r:BRÜCKE*]-to");
//			dataLoad2.add("WHERE from.title = to.title");
//			dataLoad2.add("RETURN extract(x IN nodes(p) | x.title ),length(p) AS `enthaltene Brücken` ");
//			dataLoad2.add("ORDER BY length(p)");
//			ResultSet x=  connect.createStatement().executeQuery(StringUtils.join(dataLoad2, "\n"));
//		
//			while(x.next() == true){
//				System.out.println(x.getString(1));
//				System.out.println(x.getString(2));
//		}
//		return dataLoad2;
//		}
	
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
			con.close();
			System.out.println("\nVerbindung getrennt.");
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
