package model.entity;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteSet {

	//sezione attributi immagine del DB
	public int IDDocente;
	public String Cognome;
	public String Nome;
	public String Errore;
	
	//sezione costruttori
	public DocenteSet(int chiave)
	{
		this.estrai(chiave);
	}

	public DocenteSet()
	{

	}

	private void estrai(int chiave) 
	{
		String sql = "Select * From Docente WHERE IDDocente = ?";
		//Connection oCon = null;
		Connessione oConnessione = new Connessione();

		try { 
			oConnessione.apri();
			//PASSO3, definire uno statement per eseguire le operazioni
			PreparedStatement pstDocente = oConnessione.connetti.prepareStatement(sql);
			//PASSO4, esequi l'operazione (query)
			pstDocente.setInt(1, chiave);
			ResultSet rsDocente = pstDocente.executeQuery();
			rsDocente.next();
			this.IDDocente = chiave;
			this.Cognome = rsDocente.getString("Cognome");
			this.Nome= rsDocente.getString("Nome");
			//PASSO5, chiusura connessione DB
			//oCon.close();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void aggiorna ()
	{
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstAggiorna = oConnessione.connetti.prepareCall("{call AGGIORNA_DOCENTE(?, ?, ?, ?)}");
			cstAggiorna.setInt(1, this.IDDocente);
			cstAggiorna.setString(2, this.Cognome);
			cstAggiorna.setString(3, this.Nome);
			cstAggiorna.registerOutParameter(4, java.sql.Types.VARCHAR);
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
	//	String sql = "Insert into Docente (IDDocente, Cognome, Nome) values (SeqChiaveDocente.nextval, INITCAP(?), INITCAP(?))";
		Connessione oConnessione =  new Connessione ();
		try {
			oConnessione.apri();
			CallableStatement cstInserisci = oConnessione.connetti.prepareCall("{call INSERISCI_DOCENTE (?, ?, ?, ?)}");
			cstInserisci.setString(1, this.Nome);
			cstInserisci.setString(2, this.Cognome);
			//this.IDDocente = cstInserisci.getInt(3);
			cstInserisci.registerOutParameter(3, java.sql.Types.NUMERIC);
			cstInserisci.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstInserisci.execute();
			oConnessione.chiudi ();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void elimina() 
	{
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstElimina = oConnessione.connetti.prepareCall("{call RIMUOVI_DOCENTE (?, ?)}");
			cstElimina.setInt(1, this.IDDocente);
			cstElimina.registerOutParameter(2, java.sql.Types.VARCHAR);
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
	
	public boolean checkCorsoDocente()
	{
		String sql = "SELECT COUNT(*) AS Record FROM CORSO WHERE IDDOCENTE = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDDocente);
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
	
	
	//sezione metodi di classe
	public static ArrayList<Integer> listachiavi ()
	{
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDDocente From Docente";
		Connessione oConnessione = new Connessione();
		try { 
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while(rsElenco.next()) 
				lista.add(rsElenco.getInt("IDDocente"));
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