package model.session;


import java.util.ArrayList;

import model.entity.AulaSet;

public class Aula {
	private int chiave;
	private String descrizione;
	private String numeroposti;
	private AulaSet oggettoSet;

	
	
	//sezione costruttore
	
	public Aula() 
	{
		this.inizializza();
	}
	
	private void inizializza ()
	{
		this.chiave = 0;
		this.setDescrizione("");
		this.setnumeroPosti("");
		this.oggettoSet = new AulaSet();
	}
	
	public Aula(int chiave) 
	{
		this.inizializza(chiave);
	}
	
	private void inizializza (int chiave)
	{
		this.oggettoSet = new AulaSet (chiave);
		this.chiave = chiave;
		this.setDescrizione(this.oggettoSet.Descrizione);
		this.setnumeroPosti(this.oggettoSet.numeroPosti);
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getnumeroPosti() {
		return numeroposti;
	}

	public void setnumeroPosti(String numeroposti) {
		this.numeroposti = numeroposti;
	}

	public int getChiave() {
		return chiave;
	}


	
	
	//metodi di istanza
	public void salva()
	{
		this.oggettoSet.Descrizione = this.getDescrizione();
		this.oggettoSet.numeroPosti = this.getnumeroPosti();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}
	
	public void elimina() 
	{
		this.oggettoSet.elimina();
	}
	
	public boolean checkAula()
	{
		return this.oggettoSet.checkCorsoAula();
	}
	
	/*public static ArrayList<String[]> elenco() 
	{
		return AulaSet.lista();
	}*/
	
	public static ArrayList<Aula> elenco ()
	{
		ArrayList<Aula> elenco = new ArrayList<Aula>();
		ArrayList<Integer> chiavi = AulaSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
		{
			elenco.add(new Aula(chiavi.get(i)));
		}
		return elenco;
	}



	
	
	




}
