package model.entity;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import util.UDate;

public class CorsoSet {

	// Sezione attributi ed immaggine del DB
	public int IDCorso;
	public int IDDocente;
	public int IDAula;
	public String Corso;
	public Date DataInizio;
	public String Durata;
	public ArrayList<Integer> ListaIDDiscente;
	public int[] result;

	// Sezione costruttori
	public CorsoSet() {
		this.inizializza();
	}

	private void inizializza() {
		this.ListaIDDiscente = new ArrayList<Integer>();
	}

	public CorsoSet(int chiave) {
		this.estrai(chiave);
	}

	// Sezione metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * FROM Corso WHERE IDCorso=?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstCorso = oConnessione.connetti.prepareStatement(sql);
			pstCorso.setInt(1, chiave);
			ResultSet rsCorso = pstCorso.executeQuery();
			rsCorso.next();
			this.IDCorso = chiave;
			this.IDDocente = rsCorso.getInt("IDDocente");
			this.IDAula = rsCorso.getInt("IDAula");
			this.Corso = rsCorso.getString("Corso");
			this.DataInizio = rsCorso.getDate("DataInizio");
			this.Durata = rsCorso.getString("Durata");
			oConnessione.chiudi();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
		this.estraiChiaviPartecipanti(chiave);
	}

	private void estraiChiaviPartecipanti(int chiave) {
		this.ListaIDDiscente = new ArrayList<Integer>();
		String sql = "SELECT IDDiscente FROM CorsoDiscente WHERE IDCorso = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstChiaviPartecipanti = oConnessione.connetti.prepareStatement(sql);
			pstChiaviPartecipanti.setInt(1, chiave);
			ResultSet rsElencoChiaviPartecipanti = pstChiaviPartecipanti.executeQuery();
			while (rsElencoChiaviPartecipanti.next()) {
				this.ListaIDDiscente.add(rsElencoChiaviPartecipanti.getInt("IDDiscente"));
			}
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
		// String sqlID = "SELECT CORSO_IDCORSO.nextval as CORSO_IDCORSO FROM dual";
		// String sql = "Insert into Corso (IDCorso, IDDocente, IDAula, Corso, Durata,
		// DataInizio) "
		// + " values (?, ?, ?, ?, ?, ? )";
		// int item = ListaIDDiscente.toArray(new int[ListaIDDiscente.size()]);
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstInserisci = oConnessione.connetti.prepareCall("{call INSERISCI_CORSO(?,?,?,?,?,?,?)}");
			cstInserisci.setInt(1, this.IDDocente);
			cstInserisci.setInt(2, this.IDAula);
			cstInserisci.setString(3, this.Corso);
			cstInserisci.setString(4, this.Durata);
			cstInserisci.setDate(5, UDate.newInstance(this.DataInizio).getDateAsDate());
			cstInserisci.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstInserisci.registerOutParameter(7, java.sql.Types.NUMERIC);
			cstInserisci.execute();
			this.IDCorso = cstInserisci.getInt(7);
			CallableStatement cstInserisciPartecipante = oConnessione.connetti
					.prepareCall("{call INSERISCI_PARTECIPANTE (?,?,?)}");
			for (int i = 0; i < this.ListaIDDiscente.size(); i++) {
				cstInserisciPartecipante.setInt(1, this.IDCorso);
				cstInserisciPartecipante.setInt(2, this.ListaIDDiscente.get(i));
				cstInserisciPartecipante.registerOutParameter(3, java.sql.Types.VARCHAR);
				cstInserisciPartecipante.execute();
			}
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	/*public void convert(ArrayList<Integer> ListaIDDiscente) {

		int s = ListaIDDiscente.size();
		int[] result = new int[s];
		for (int i = 0; i < s; i++) {
			result[i] = ListaIDDiscente.get(i).intValue();
		}
		this.result = result;
	}*/

	private void inserisciPartecipanti(Connessione oConnessione) {
		try {
			CallableStatement cstInserisciPartecipante = oConnessione.connetti
					.prepareCall("{call INSERISCI_PARTECIPANTE (?,?,?)}");
			for (int i = 0; i < this.ListaIDDiscente.size(); i++) {
				cstInserisciPartecipante.setInt(1, this.IDCorso);
				cstInserisciPartecipante.setInt(2, this.ListaIDDiscente.get(i));
				cstInserisciPartecipante.registerOutParameter(3, java.sql.Types.VARCHAR);
				cstInserisciPartecipante.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void aggiorna() {

		// String sql = "UPDATE Corso SET IDDocente = ?, IDAula = ?, Corso = INITCAP(?),
		// Durata = INITCAP(?), DataInizio = ? WHERE IDCorso = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstAggiorna = oConnessione.connetti.prepareCall("{call AGGIORNA_CORSO (?,?,?,?,?,?,?)}");
			cstAggiorna.setInt(1, this.IDCorso);
			cstAggiorna.setInt(2, this.IDDocente);
			cstAggiorna.setInt(3, this.IDAula);
			cstAggiorna.setString(4, this.Corso);
			cstAggiorna.setString(5, this.Durata);
			cstAggiorna.setDate(6, UDate.newInstance(this.DataInizio).getDateAsDate());
			cstAggiorna.registerOutParameter(7, Types.VARCHAR);
			cstAggiorna.execute();
			eliminaTuttiPartecipanti();
			inserisciPartecipanti(oConnessione);
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void elimina() {
		this.eliminaTuttiPartecipanti();
		// String sql = "delete FROM Corso WHERE IDCorso = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			CallableStatement cstElimina = oConnessione.connetti.prepareCall("{ call RIMUOVI_CORSO (?, ?)}");
			cstElimina.setInt(1, this.IDCorso);
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

	private void eliminaTuttiPartecipanti() {
		Connessione oConnessione = new Connessione();
		// String sql = "delete from CorsoDiscente WHERE IDCorso = ?";
		try {
			oConnessione.apri();
			CallableStatement cstEliminaPartecipanti = oConnessione.connetti
					.prepareCall("{call RIMUOVI_PARTECIPANTI (?, ?)}");
			cstEliminaPartecipanti.setInt(1, this.IDCorso);
			cstEliminaPartecipanti.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstEliminaPartecipanti.execute();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione != null)
				oConnessione.chiudi();
		}
	}

	/*
	 * public void eliminaPartecipante(int chiavePartecipante) { String sql =
	 * "DELETE FROM CorsoDiscente WHERE IDCorso = ? AND IDDiscente = ?"; Connessione
	 * oConnessione = new Connessione(); try { oConnessione.apri();
	 * PreparedStatement pstEliminaPartecipante =
	 * oConnessione.connetti.prepareStatement(sql); pstEliminaPartecipante.setInt(1,
	 * this.IDCorso); pstEliminaPartecipante.setInt(2, chiavePartecipante);
	 * pstEliminaPartecipante.executeUpdate(); oConnessione.chiudi(); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * finally { if (oConnessione.connetti != null) oConnessione.chiudi(); }
	 * 
	 * }
	 */

	// Sezioni metodi di classe

	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDCorso From Corso";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDCorso"));
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
