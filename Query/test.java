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

public class test {
static Connection con = null;
private String dbAdresse = "localhost";
static GraphDatabaseFactory graphDatabaseFactory;
	public test(){	
	}
	public void verbinden(){
		try{
			DriverManager.registerDriver(new org.neo4j.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:neo4j://"+dbAdresse+":7474");
		}catch (SQLException e){
			ausnahmeAusgeben(e);
		}
	}
	public void brueckenmodellErstellen() throws SQLException{
		Neo4jConnection connect = null;
		try {
			connect = new Driver().
					  connect("jdbc:neo4j://localhost:7474", new Properties());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			List<String> dataLoad = new ArrayList<String>();
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblem1:Stadt {title:'Königsberg', name:'Königsberg' })");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblem:Problem{title:'Problemstellung',abstract:'Sind alle Stadtteile erreichbar indem man jede Brücke genau einmal benutzt?'})");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblemA:Stadtteil{title:'Löbenicht', Identifiaktionsbuchstabe:'A'})");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblemB:Stadtteil{title:'Lomse', Identifiaktionsbuchstabe:'B'})");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblemC:Stadtteil{title:'Vorstadt', Identifiaktionsbuchstabe:'C'})");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblemD:Stadtteil{title:'Kneiphof', Identifiaktionsbuchstabe:'D'})");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblem1)-[:HAT_DAS_PROBLEM {Problemursprung: ['etwa 1736'] }]->( KönigsbergerBrückenProblem)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (KönigsbergerBrückenProblemA)");	
			dataLoad.add("CREATE	(KönigsbergerBrückenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (KönigsbergerBrückenProblemB)");
			dataLoad.add("CREATE 	(KönigsbergerBrückenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (KönigsbergerBrückenProblemC)");		
			dataLoad.add("CREATE	(KönigsbergerBrückenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (KönigsbergerBrückenProblemD)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemA)-[:BRÜCKE {Brückenname: 'Holzbrücke', Brückenlänge: '55 Meter'}]->(KönigsbergerBrückenProblemB)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemA)-[:BRÜCKE {Brückenname: 'Krämer Brücke', Brückenlänge: '15 Meter'}]->(KönigsbergerBrückenProblemD)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemA)-[:BRÜCKE {Brückenname: 'Schmiedebrücke', Brückenlänge: '20 Meter'}]->(KönigsbergerBrückenProblemD)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemC)-[:BRÜCKE {Brückenname: 'Kaiserbrücke', Brückenlänge: '75 Meter'}]->(KönigsbergerBrückenProblemB)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemC)-[:BRÜCKE {Brückenname: 'Köttelbrücke', Brückenlänge: '50 Meter'}]->(KönigsbergerBrückenProblemD)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemC)-[:BRÜCKE {Brückenname: 'Grüne Brücke', Brückenlänge: '45 Meter'}]->(KönigsbergerBrückenProblemD)");
			dataLoad.add("CREATE	(KönigsbergerBrückenProblemB)-[:BRÜCKE {Brückenname: 'Honigbrücke', Brückenlänge: '40 Meter'}]->(KönigsbergerBrückenProblemD)");
			connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
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
			return;
		}
	}
}
