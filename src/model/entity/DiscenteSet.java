package model.entity;




import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class DiscenteSet {

	//sezione attributi immagine del db
	public int IDDiscente;
	public String Cognome;
	public String Nome;
	public String Matricola;

	//sezione costruttori
	public DiscenteSet(int chiave)
	{
		this.estrai(chiave);
	}


	public DiscenteSet()
	{

	}


	//metodi di istanza
	public void estrai(int chiave) 
	{
		String sql = "SELECT * FROM Discente WHERE IDDiscente = ?";
		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstDiscente = oConnessione.connetti.prepareStatement(sql);
			pstDiscente.setInt(1, chiave);
			ResultSet rsDiscente = pstDiscente.executeQuery();
			rsDiscente.next();
			this.IDDiscente = chiave;
			this.Cognome = rsDiscente.getString("Cognome");
			this.Nome = rsDiscente.getString("Nome");
			this.Matricola = rsDiscente.getString("Matricola");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}



	public void aggiorna()
	{
		//String sql = "UPDATE Discente SET Cognome = INITCAP(?), Nome = INITCAP(?), Matricola = ? WHERE IDDiscente = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstAggiorna = oConnessione.connetti.prepareCall("{call AGGIORNA_DISCENTE(?, ?, ?, ?, ?)}");
			cstAggiorna.setInt(1, this.IDDiscente);
			cstAggiorna.setString(2, this.Nome);
			cstAggiorna.setString(3, this.Cognome);
			cstAggiorna.setString(4, this.Matricola);
			cstAggiorna.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstAggiorna.execute();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}



	public void inserisci ()
	{
		// TODO Auto-generated method stub
	//	String sql = ("INSERT INTO Discente VALUES (Discente_IDDiscente.nextval, INITCAP(?), INITCAP(?), ?)");
		Connessione oConnessione = new Connessione ();
		try {
			oConnessione.apri();
			CallableStatement cstInserisci = oConnessione.connetti.prepareCall("{call INSERISCI_DISCENTE (?, ?, ?, ?, ?)}");
			cstInserisci.setString(1, this.Nome);
			cstInserisci.setString(2, this.Cognome);
			cstInserisci.setString(3, this.Matricola);
			cstInserisci.registerOutParameter(4, java.sql.Types.NUMERIC);
			cstInserisci.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}



	public void elimina() 
	{
		//String sql = "delete from Discente WHERE IDDiscente = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstElimina = oConnessione.connetti.prepareCall("{call RIMUOVI_DISCENTE (?, ?)}");
			cstElimina.setInt(1, this.IDDiscente);
			cstElimina.registerOutParameter(2, Types.VARCHAR);
			cstElimina.execute();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	
	
	public boolean checkcorsodiscente()
	{
		
		String sql = "SELECT COUNT(*) AS Record FROM CORSODISCENTE WHERE IDDISCENTE = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDDiscente);
			ResultSet rsCheck = pstCheck.executeQuery();
			rsCheck.next();
			int record = rsCheck.getInt("Record");
			if (record == 0)
				chk = false;
			else
				chk = true ;
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chk;
				
	}



	/*public static ArrayList<String[]> lista ()
	{
		String sql = "SELECT * FROM Discente";
		ArrayList<String[]> elencoDiscenti = new ArrayList<String[]>();
		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
			{
				String[] discente = new String[4];
				discente[0] = rsElenco.getString("IDDiscente");
				discente[1] = rsElenco.getString("Cognome");
				discente[2] = rsElenco.getString("Nome");
				discente[3] = rsElenco.getString("Matricola");
				elencoDiscenti.add(discente);
			}
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
					oConnessione.chiudi();
				}

		return elencoDiscenti;

	}*/

	public static ArrayList<Integer> listachiavi ()
	{
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDDiscente From Discente";
		Connessione oConnessione = new Connessione();
		try { 
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while(rsElenco.next()) 
				lista.add(rsElenco.getInt("IDDiscente"));
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
		return lista;
	}


}

