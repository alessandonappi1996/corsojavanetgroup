package model.session;

import java.util.ArrayList;
import java.util.Date;

import model.entity.CorsoSet;

public class Corso {

	// Sezione attributi privati

	private int chiave;
	private Date datainizio;
	private String corso;
	private String durata;
	private CorsoSet oggettoSet;
	// Modalità disconnessa per la connessione di istanza
	/*
	 * private int chiaveDocente; private String docente; private int chiaveAula;
	 * private String aula; private ArrayList<String[]> partecipanti;
	 */

	// Modalità connessa per la connessione di istanza
	private Docente oDocente;
	private Aula oAula;
	private ArrayList<Discente> partecipanti;

	// Sezione metodi get e set

	public Date getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(Date datainizio) {
		this.datainizio = datainizio;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public int getChiave() {
		return chiave;
	}

	public ArrayList<Integer> getChiaviPartecipanti() {
		ArrayList<Integer> listachiavipartecipanti = new ArrayList<Integer>();
		for (int i = 0; i < this.partecipanti.size(); i++)
			listachiavipartecipanti.add(this.partecipanti.get(i).getChiave());
		return listachiavipartecipanti;
	}

	// Metodi get e set
	public void setDocente(int chiave) {
		this.oDocente = new Docente(chiave);
	}

	public int getChiaveDocente() {
		return this.oDocente.getChiave();
	}

	public String getDocente() {
		return this.oDocente.getCognome() + " " + this.oDocente.getNome();
	}

	public void setAula(int chiave) {
		this.oAula = new Aula(chiave);
	}

	public int getChiaveAula() {
		return this.oAula.getChiave();
	}

	public String getAula() {
		return this.oAula.getDescrizione() + " " + this.oAula.getnumeroPosti();
	}

	public ArrayList<Discente> getPartecipanti() {
		return this.partecipanti;
	}

	// Sezione costruttori

	public Corso() {
		this.inizializza();
	}

	private void inizializza() {
		this.chiave = 0;
		this.setDatainizio(null);
		this.setCorso("");
		this.setDurata("");
		this.oggettoSet = new CorsoSet();
		// inizializzazione attributi connessi
		this.partecipanti = new ArrayList<Discente>();
		this.oAula = null;
		this.oDocente = null;

	}

	public Corso(int chiave) {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) {// Injzializzare gli attributi
		this.oggettoSet = new CorsoSet(chiave);
		this.chiave = chiave;
		this.setDatainizio(this.oggettoSet.DataInizio);
		this.setCorso(this.oggettoSet.Corso);
		this.setDurata(this.oggettoSet.Durata);
		// inizializzazione di attributi disconnessi
		this.setDocente(this.oggettoSet.IDDocente);
		this.setAula(this.oggettoSet.IDAula);
		// inizializzazione dei partecipanti
		this.partecipanti = new ArrayList<Discente>();
		for (int i = 0; i < this.oggettoSet.ListaIDDiscente.size(); i++) {
			this.aggiungiPartecipante(this.oggettoSet.ListaIDDiscente.get(i));
		}
	}

	// Sezione metodi di istanza

	public void aggiungiPartecipante(int chiaveDiscente) {
		this.partecipanti.add(new Discente(chiaveDiscente));
	}

	public void rimuoviPartecipante(int chiavePartecipante) {
		for (int i = 0; this.partecipanti.get(i).getChiave() != chiavePartecipante; i++)
			this.partecipanti.remove(i);
	}

	public void salva() {
		this.oggettoSet.DataInizio = this.getDatainizio();// new Date(this.getDatainizio().getTime());
		this.oggettoSet.Corso = this.getCorso();
		this.oggettoSet.Durata = this.getDurata();
		this.oggettoSet.IDAula = this.getChiaveAula();
		this.oggettoSet.IDDocente = this.getChiaveDocente();
		this.oggettoSet.ListaIDDiscente = this.getChiaviPartecipanti();
	if(this.chiave == 0)
		this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	public ArrayList<Discente> discentiNonPartecipanti() {
		// questo metodo prende l'elenco dei discenti ed esclude i partecipanti
		ArrayList<Discente> nonPartecipanti = Discente.elenco();
		int i = 0;
		while (i < this.partecipanti.size()) {
			int j = 0;
			while (nonPartecipanti.get(j).getChiave() != this.partecipanti.get(i).getChiave()) {
				j++;
			}
			nonPartecipanti.remove(j);
			i++;
		}
		return nonPartecipanti;
	}
	
	
	
	

	// Sezioni metodi di classe
	public static ArrayList<Corso> elenco() {
		ArrayList<Corso> elenco = new ArrayList<Corso>();
		ArrayList<Integer> chiavi = CorsoSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++) {
			elenco.add(new Corso(chiavi.get(i)));
		}
		return elenco;
	}
}
