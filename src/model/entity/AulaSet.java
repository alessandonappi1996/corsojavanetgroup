package model.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class AulaSet {

	// sezione attributi immagine del db
	public int IDAula;
	public String Descrizione;
	public String numeroPosti;

	// sezione costruttore
	public AulaSet() {

	}

	public AulaSet(int chiave) {
		this.estrai(chiave);
	}

	public void estrai(int chiave) {
		String sql = "SELECT * FROM Aula WHERE IDAula = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAula = oConnessione.connetti.prepareStatement(sql);
			pstAula.setInt(1, chiave);
			ResultSet rsAula = pstAula.executeQuery();
			rsAula.next();
			this.IDAula = chiave;
			this.Descrizione = rsAula.getString("Descrizione");
			this.numeroPosti = rsAula.getString("numeroPosti");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione != null)
				oConnessione.chiudi();
		}
	}

	public void aggiorna() {
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstAggiorna = oConnessione.connetti.prepareCall("{call AGGIORNA_AULA (?, ?, ?, ?) }");
			cstAggiorna.setInt(1, this.IDAula);
			cstAggiorna.registerOutParameter(2, Types.VARCHAR);
			cstAggiorna.setString(3, this.Descrizione);
			cstAggiorna.setString(4, this.numeroPosti);
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

	public void inserisci() {
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstInserisci = oConnessione.connetti.prepareCall("{call INSERISCI_AULA (?, ?, ?, ?)}");
			cstInserisci.setString(1, this.Descrizione);
			cstInserisci.setString(2, this.numeroPosti);
			cstInserisci.registerOutParameter(3, Types.VARCHAR);
			cstInserisci.registerOutParameter(4, Types.NUMERIC);
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

	public void elimina() {
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstElimina = oConnessione.connetti.prepareCall("{call RIMUOVI_AULA (?, ?)}");
			cstElimina.registerOutParameter(1, Types.VARCHAR);
			cstElimina.setInt(2, this.IDAula);
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

	public boolean checkCorsoAula() {
		String sql = "SELECT COUNT(*) AS Record FROM CORSO WHERE IDAula = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDAula);
			ResultSet rsCheck = pstCheck.executeQuery();
			rsCheck.next();
			int record = rsCheck.getInt("Record");
			if (record == 0)
				chk = false;
			else
				chk = true;
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chk;

	}



	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "SELECT IDAula FROM aula";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDAula"));
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
		return lista;
	}

}
