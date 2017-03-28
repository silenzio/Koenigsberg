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

public class QueryKanteÄndern {
	static Connection con = null;
	private String dbAdresse = "localhost";
	static GraphDatabaseFactory graphDatabaseFactory;
	public QueryKanteÄndern(){
	}
	public List<String> eineKanteÄndern(
									String titleAnfangsKnoten,
									String kantentyp, 
									String titleEndKnoten,
									String EigenschaftenSchlüssel_1,
									String EigenschaftenWert_1,
									String EigenschaftenSchlüssel_2,
									String EigenschaftenWert_2) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
		List<String> dataLoad = new ArrayList<String>(); 
	
		dataLoad.add("MATCH (n {title:'"+titleAnfangsKnoten+"'})");
		dataLoad.add("-[r:"+kantentyp+"]-(m {title:'"+titleEndKnoten+"'})"); 
		dataLoad.add("SET r."+EigenschaftenSchlüssel_1+" ");
		dataLoad.add("='"+EigenschaftenWert_1+"'");
		dataLoad.add(", r."+EigenschaftenSchlüssel_2+" ");
		dataLoad.add("='"+EigenschaftenWert_2+"'");
		ResultSet x=	connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
		while(x.next() == true){
			System.out.println(x.getString(1));
		}
		return dataLoad;
	}

	public List<String> eineKanteÄndernVorbereiten(String titleAnfangsKnoten, String kantentyp, String titleEndKnoten) throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				List<String> dataLoad = new ArrayList<String>();
				dataLoad.add("MATCH (n {title:'"+titleAnfangsKnoten+"'})-[r:`"+kantentyp+"`]-(m {title:'"+titleEndKnoten+"'})");
				dataLoad.add("RETURN id(r),r");
				ResultSet x=	connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
				Fenster.InternesFensterKanteÄndern.getKanteÄndernKnopf().setVisible(true);
				
				verbindungTrennen();
				String  reSx="";
				while(x.next() == true){
					Fenster.InternesFensterKanteÄndern.getKanteIdentifikator().setText(x.getString(1));
					reSx=x.getString(2);
				}	
				StringTokenizer sTok = new StringTokenizer( reSx,"\"{}[]:,");	
				int sTokMenge = sTok.countTokens();
				if(sTokMenge==4){
					Fenster.InternesFensterKanteÄndern.getKanteEigenschaftenSchluessel().setText(sTok.nextToken());
					Fenster.InternesFensterKanteÄndern.getKanteEigenschaftenWert().setText(sTok.nextToken());
					Fenster.InternesFensterKanteÄndern.getKanteEigenschaftenSchluessel_2().setText(sTok.nextToken());
					Fenster.InternesFensterKanteÄndern.getKanteEigenschaftenWert_2().setText(sTok.nextToken());
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
