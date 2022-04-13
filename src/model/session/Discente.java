package model.session;


import java.util.ArrayList;

import model.entity.DiscenteSet;



public class Discente {
	
	//attributi classe Discente
	private int chiave;
	private String cognome;
	private String nome;
	private String matricola;
	private DiscenteSet oggettoSet;

	

	//sezione metodi di lettura e scrittura 
	public String getCognome () 
	{
		return cognome;
	}
	
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}
	
	public String getNome ()
	{
		return nome;
	}
	
	public void setNome (String nome)
	{
		this.nome = nome;
	}
	
	public String getMatricola ()
	{
		return matricola;
	}
	
	public void setMatricola (String matricola)
	{
		this.matricola = matricola;
	}
	
	public int getChiave()
	{
		return chiave;
	}
	
	
	//sezione costruttore
	
	public Discente()
	{
		this.inizializza();
	}
	
	private void inizializza()
	{
		this.chiave = 0;
		this.setCognome("");
		this.setNome("");
		this.setMatricola("");
		this.oggettoSet = new DiscenteSet ();
	}
	
	public Discente(int chiave)
	{
		this.inizializza(chiave);
	}
	
	private void inizializza(int chiave)
	{
		this.oggettoSet = new DiscenteSet (chiave);
		this.chiave = chiave;
		this.setCognome(this.oggettoSet.Cognome);
		this.setNome(this.oggettoSet.Nome);
		this.setMatricola(this.oggettoSet.Matricola);
	}
	
	//sezione metodi di istanza
	public void salva()
	{
		this.oggettoSet.Cognome = this.getCognome();
		this.oggettoSet.Nome = this.getNome();
		this.oggettoSet.Matricola = this.getMatricola();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}
	
	
	public void elimina() 
	{
		this.oggettoSet.elimina();
	}
	
	
	public boolean checkdiscente()
	{
		return this.oggettoSet.checkcorsodiscente();
	}
	
	
	/*sezione metodi di classe
	public static ArrayList<String[]> elenco()
	{
		return DiscenteSet.lista();
	}*/
	
	public static ArrayList<Discente> elenco ()
	{
		ArrayList<Discente> elenco = new ArrayList<Discente>();
		ArrayList<Integer> chiavi = DiscenteSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
		{
			elenco.add(new Discente(chiavi.get(i))); 
		}
		return elenco;
	}
	
	







}


