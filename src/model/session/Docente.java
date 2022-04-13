package model.session;


import java.util.ArrayList;

import model.entity.DocenteSet;

public class Docente {
	
	//sezione attributi
	private int chiave;
	private String cognome;
	private String nome;
	private DocenteSet oggettoSet;
	
	//sezione metodi di lettura e scrittura
	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getChiave() {
		return chiave;
	}
	
	
	//sezione costruttore
	
	public Docente()
	{
		this.inizializza();
	}
	
	private void inizializza()
	{
		this.chiave = 0;
		this.setCognome("");
		this.setNome("");
		this.oggettoSet = new DocenteSet ();
	}
	
	public Docente(int chiave)
	{
		this.inizializza(chiave);
	}
	
	private void inizializza(int chiave)
	{
		this.oggettoSet = new DocenteSet (chiave);
		this.chiave = chiave;
		this.setCognome(this.oggettoSet.Cognome);
		this.setNome(this.oggettoSet.Nome);
	}
	

	//sezione metodi di istanza
	public void salva()
	{
		//this.oggettoSet = new DocenteSet ();
		this.oggettoSet.Cognome = this.getCognome();
		this.oggettoSet.Nome = this.getNome();
		if (this.chiave == 0) {
			this.oggettoSet.inserisci();
			this.chiave = this.oggettoSet.IDDocente;
		}
		else
			this.oggettoSet.aggiorna();
	}
	
	public void elimina()
	{
		this.oggettoSet.elimina();
	}
	
	public boolean checkDocente()
	{
		return this.oggettoSet.checkCorsoDocente();
	}
	
	/*sezione metodi di classe  	CI PUò ESSERE UN ELENCO DI STRINGHE COME QUESTO O UN ELENCO DI OGGETTI, ORA VEDREMO COME SI FA
	public static ArrayList <String[]> elenco () 
	{
	//	ArrayList<String[]> elenco = new ArrayList<String[]>(); si possono evitare
		//elenco = DocenteSet.lista(); si possono evitare
		return DocenteSet.lista();
	}*/
	
	//sezione metodi di classe
	public static ArrayList <Docente> elenco () 
	{
		ArrayList<Docente> elenco = new ArrayList<Docente>();
		ArrayList<Integer> chiavi = DocenteSet.listachiavi();
		int chiave; //1
		for (int i = 0; i < chiavi.size(); i++)
		{
			chiave = chiavi.get(i); //2
			Docente oDocente = new Docente (chiave); //3
			elenco.add(oDocente); //4
			//elenco.add(new Docente(chiavi.get(i))); si potrebbe usare solo questa riga al posto delle numerate
		}
			return elenco;
	}




}



