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
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblem1:Stadt {title:'K�nigsberg', name:'K�nigsberg' })");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblem:Problem{title:'Problemstellung',abstract:'Sind alle Stadtteile erreichbar indem man jede Br�cke genau einmal benutzt?'})");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblemA:Stadtteil{title:'L�benicht', Identifiaktionsbuchstabe:'A'})");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblemB:Stadtteil{title:'Lomse', Identifiaktionsbuchstabe:'B'})");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblemC:Stadtteil{title:'Vorstadt', Identifiaktionsbuchstabe:'C'})");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblemD:Stadtteil{title:'Kneiphof', Identifiaktionsbuchstabe:'D'})");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblem1)-[:HAT_DAS_PROBLEM {Problemursprung: ['etwa 1736'] }]->( K�nigsbergerBr�ckenProblem)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (K�nigsbergerBr�ckenProblemA)");	
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (K�nigsbergerBr�ckenProblemB)");
			dataLoad.add("CREATE 	(K�nigsbergerBr�ckenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (K�nigsbergerBr�ckenProblemC)");		
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblem)-[:IST_TEIL_DES_PROBLEMS]-> (K�nigsbergerBr�ckenProblemD)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemA)-[:BR�CKE {Br�ckenname: 'Holzbr�cke', Br�ckenl�nge: '55 Meter'}]->(K�nigsbergerBr�ckenProblemB)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemA)-[:BR�CKE {Br�ckenname: 'Kr�mer Br�cke', Br�ckenl�nge: '15 Meter'}]->(K�nigsbergerBr�ckenProblemD)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemA)-[:BR�CKE {Br�ckenname: 'Schmiedebr�cke', Br�ckenl�nge: '20 Meter'}]->(K�nigsbergerBr�ckenProblemD)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemC)-[:BR�CKE {Br�ckenname: 'Kaiserbr�cke', Br�ckenl�nge: '75 Meter'}]->(K�nigsbergerBr�ckenProblemB)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemC)-[:BR�CKE {Br�ckenname: 'K�ttelbr�cke', Br�ckenl�nge: '50 Meter'}]->(K�nigsbergerBr�ckenProblemD)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemC)-[:BR�CKE {Br�ckenname: 'Gr�ne Br�cke', Br�ckenl�nge: '45 Meter'}]->(K�nigsbergerBr�ckenProblemD)");
			dataLoad.add("CREATE	(K�nigsbergerBr�ckenProblemB)-[:BR�CKE {Br�ckenname: 'Honigbr�cke', Br�ckenl�nge: '40 Meter'}]->(K�nigsbergerBr�ckenProblemD)");
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
