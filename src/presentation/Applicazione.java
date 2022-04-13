package presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import model.session.Aula;
import model.session.Corso;
import model.session.Discente;
import model.session.Docente;
import util.UDate;

public class Applicazione {

	private static BufferedReader oLeggi = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		int valore;
		do {
			System.out.println("***************Menù***************************");
			System.out.println("* 0: Esci                                    *");
			System.out.println("* 1: Esercizio 1 Elenco Docenti              *");
			System.out.println("* 2: Esercizio 2 Nuovo Docente               *");
			System.out.println("* 3: Esercizio 3 Modifica Docente            *");
			System.out.println("* 4: Esercizio 4 Elimina Docente             *");
			System.out.println("**********************************************");
			System.out.println("* 5: Esercizio 5 Elenco Discenti             *");
			System.out.println("* 6: Esercizio 6 Nuovo Discente              *");
			System.out.println("* 7: Esercizio 7 Modifica Discente           *");
			System.out.println("* 8: Esercizio 8 Elimina Discente            *");
			System.out.println("**********************************************");
			System.out.println("* 9: Esercizio 9 Elenco Aule                 *");
			System.out.println("* 10: Esercizio 10 Nuova Aula                *");
			System.out.println("* 11: Esercizio 11 Modifica Aula             *");
			System.out.println("* 12: Esercizio 12 Elimina Aula              *");
			System.out.println("**********************************************");
			System.out.println("* 13 Esercizio 13 Elenco Corsi               *");
			System.out.println("* 14 Esercizio 14 Nuovo Corso                *");
			System.out.println("* 15 Esercizio 15 Modifica Corso             *");
			System.out.println("* 16 Esercizio 16 Elimina Corso              *");
			System.out.println("**********************************************");
			System.out.println("Effettua la scelta: ");
			valore = Integer.parseInt(oLeggi.readLine());
			switch (valore) {
			case 0:
				break;
			case 1:
				elencoDocenti();
				break;
			case 2:
				nuovoDocente();
				break;
			case 3:
				modificaDocente();
				break;
			case 4:
				eliminaDocente();
				break;
			case 5:
				elencoDiscenti();
				break;
			case 6:
				nuovoDiscente();
				break;
			case 7:
				modificaDiscente();
				break;
			case 8:
				eliminaDiscente();
				break;
			case 9:
				elencoAula();
				break;
			case 10:
				nuovoAula();
				break;
			case 11:
				modificaAula();
				break;
			case 12:
				eliminaAula();
				break;
			case 13:
				elencoCorsi();
				break;
			case 14:
				nuovoCorso();
				break;
			case 15:
				modificaCorso();
				break;
			case 16:
				eliminaCorso();
				break;
			default:
				System.out.println("Valore inserito errato! Riprovare: ");
				break;

			}

		} while (valore != 0);

	}

	private static void eliminaCorso() throws IOException, ParseException {
		ArrayList<Corso> elenco = Corso.elenco();
		System.out.println("Elenco dei Corsi");
		for (int i = 0; i < elenco.size(); i++)
			System.out.println(elenco.get(i).getChiave() + " " + UDate.ctrlStringa(elenco.get(i).getDatainizio()) + " "
					+ elenco.get(i).getCorso() + " " + elenco.get(i).getDurata() + " "
					+ elenco.get(i).getChiaveDocente() + " " + elenco.get(i).getChiaveAula());
		System.out.println("Inserisci la chiave del corso da eliminare");
		Corso oCorso = new Corso(Integer.parseInt(oLeggi.readLine()));
		oCorso.elimina();
	}

	private static void modificaCorso() throws NumberFormatException, IOException, ParseException {
		System.out.println("Inserici la chiave del corso da modificare: ");
		Corso oCorso = new Corso(Integer.parseInt(oLeggi.readLine()));
		elencoDocenti();
		System.out.println("Inserisci la chiave del nuovo Docente che gestirà il corso: ");
		oCorso.setDocente(Integer.parseInt(oLeggi.readLine()));
		elencoAula();
		System.out.println("Inserisci la nuova chiave della nuova Aula dove si sosterrà il corso: ");
		oCorso.setAula(Integer.parseInt(oLeggi.readLine()));
		System.out.println("Inserisci il nuovo nome del corso: ");
		oCorso.setCorso(oLeggi.readLine());
		System.out.println("Inserisci la nuova data di inizio: ");
		oCorso.setDatainizio(UDate.ctrlData(oLeggi.readLine()));
		System.out.println("Inserisci la nuova durata del corso: ");
		oCorso.setDurata(oLeggi.readLine());
		for (int i = 0; i < oCorso.getPartecipanti().size(); i++)
			System.out.println(
					oCorso.getPartecipanti().get(i).getChiave() + " " + oCorso.getPartecipanti().get(i).getCognome()
							+ " " + oCorso.getPartecipanti().get(i).getNome());
		System.out.println("Questo è l'elenco dei partecipanti, vuoi aggiungerne o rimuoverne?.");
		System.out.println("Premi 1 per aggiungere.");
		System.out.println("Premi 2 per rimuovere. ");
		System.out.println("Premi qualsiasi altro numero per non modificare l'elenco dei partecipanti.");
		int val = Integer.parseInt(oLeggi.readLine());
		while (val == 1 || val == 2)
			if (val == 1) {
				ArrayList <Discente> nonPartecipanti = oCorso.discentiNonPartecipanti();
				for (int i = 0; i < nonPartecipanti.size(); i++)
					System.out.println(nonPartecipanti.get(i).getChiave() + " " + nonPartecipanti.get(i).getCognome() + " " + nonPartecipanti.get(i).getNome());
				System.out.println("Inserisci la chiave del Discente da aggiungere: ");
				oCorso.aggiungiPartecipante(Integer.parseInt(oLeggi.readLine()));
				System.out.println("Discente aggiunto, vuoi continuare a modificare l'elenco dei partecipanti?");
				System.out.println("Premi 1 per aggiungere.");
				System.out.println("Premi 2 per rimuovere.");
				System.out.println("Premi un qualsiasi altro numero per non modificare l'elenco dei partecipanti.");
				val = Integer.parseInt(oLeggi.readLine());
			} else {
				
				for (int i = 0; i < oCorso.getPartecipanti().size(); i++)
					System.out.println(oCorso.getPartecipanti().get(i).getChiave() + " "
							+ oCorso.getPartecipanti().get(i).getCognome() + " "
							+ oCorso.getPartecipanti().get(i).getNome());
				System.out.println("Inserisci la chiave del Partecipante da rimuovere: ");
				oCorso.rimuoviPartecipante(Integer.parseInt(oLeggi.readLine()));
				System.out.println("Discente rimosso, vuoi continuare a modificare l'elenco dei partecipanti?");
				System.out.println("Premi 1 per aggiungere.");
				System.out.println("Premi 2 per rimuovere partecipanti.");
				System.out.println("Premi 0 per uscire");
				val = Integer.parseInt(oLeggi.readLine());
			}
		
		
		oCorso.salva();
	}

	private static void nuovoCorso() throws IOException, ParseException {
		Corso oCorso = new Corso();
		System.out.println("Inserire il nome del Corso: ");
		oCorso.setCorso(oLeggi.readLine());
		System.out.println("Inserire la durata del corso: ");
		oCorso.setDurata(oLeggi.readLine());
		System.out.println("Inserire la data di inizio del corso: ");
		oCorso.setDatainizio(UDate.ctrlData(oLeggi.readLine()));
		elencoDocenti();
		System.out.println("Seleziona il docente che farà il corso: ");
		oCorso.setDocente(Integer.parseInt(oLeggi.readLine()));
		elencoAula();
		System.out.println("Seleziona l'aula in cui verrà fatto il discorso: ");
		oCorso.setAula(Integer.parseInt(oLeggi.readLine()));
		// Inserire i partecipanti
		ArrayList <Discente> nonPartecipanti = oCorso.discentiNonPartecipanti();
		System.out.println("L'elenco degli studenti da poter aggiungere al corso: ");
		for (int i = 0; i < nonPartecipanti.size(); i++)
			System.out.println(nonPartecipanti.get(i).getChiave() + " " + nonPartecipanti.get(i).getCognome() + " " + nonPartecipanti.get(i).getNome());
		System.out.println("Inserire la chiave del Discente da aggiungere al corso: ");
		int valpart = Integer.parseInt(oLeggi.readLine());
		while (valpart != 0) {
			oCorso.aggiungiPartecipante(valpart);
			nonPartecipanti = oCorso.discentiNonPartecipanti();
			for (int i = 0; i < nonPartecipanti.size(); i++)
				System.out.println(nonPartecipanti.get(i).getChiave() + " " + nonPartecipanti.get(i).getCognome() + " " + nonPartecipanti.get(i).getNome());
			System.out.println(
					"Inserire la chiave del successivo Discente da aggiungere al corso (Premi 0 per uscire): ");
			valpart = Integer.parseInt(oLeggi.readLine());
		      
		}

		oCorso.salva();
		// oCorso.salvapartecipanti();

	}

	private static void elencoCorsi() throws IOException, ParseException {
		ArrayList<Corso> elenco = Corso.elenco();
		System.out.println("Elenco dei Corsi");
		for (int i = 0; i < elenco.size(); i++)
			System.out.println(elenco.get(i).getChiave() + " " + UDate.ctrlStringa(elenco.get(i).getDatainizio()) + " "
					+ elenco.get(i).getCorso() + " " + elenco.get(i).getDurata() + " "
					+ elenco.get(i).getChiaveDocente() + " " + elenco.get(i).getDocente() + " "
					+ elenco.get(i).getChiaveAula() + " " + elenco.get(i).getAula() + " " + elenco.get(i).getDocente());
		System.out.println("Inserisci la chiave del corso di cui vuoi visualizzare i partecipanti(premi 0 per uscire)");
		int val = Integer.parseInt(oLeggi.readLine());
		while (val != 0) {
			Corso oCorso = new Corso(val);
			for (int i = 0; i < oCorso.getPartecipanti().size(); i++)
				System.out.println(
						oCorso.getPartecipanti().get(i).getChiave() + " " + oCorso.getPartecipanti().get(i).getCognome()
								+ " " + oCorso.getPartecipanti().get(i).getNome());
			System.out.println(
					"Inserisci la Chiave del corso di cui vuoi visualizzare i partecipanti(premi 0 per uscire)");
			val = Integer.parseInt(oLeggi.readLine());
		}
	}

	private static void eliminaAula() throws IOException {
		System.out.println("Inserire l'ID dell'aula da eliminare: ");
		int IDAula = Integer.parseInt(oLeggi.readLine());
		Aula oAula = new Aula(IDAula);
		boolean check = oAula.checkAula();
		if (check)
			System.out.println("Impossibile eliminare l'aula poichè si svolge un corso al suo interno.");
		else {
			oAula.elimina();
			System.out.println("Aula eliminato correttaemente");
		}
	}

	private static void eliminaDiscente() throws IOException {
		System.out.println("Inserire l'ID del Discente da eliminare: ");
		Discente oDiscente = new Discente(Integer.parseInt(oLeggi.readLine()));
		boolean check = oDiscente.checkdiscente();
		if (check) {
			System.out.println(
					"Impossibile eliminare il discente, eliminarlo prima dai partecipanti del corso di cui fa parte");
		} else {
			oDiscente.elimina();
			System.out.println("Il Discente è stato eliminato correttamente");
		}
	}

	private static void modificaAula() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Inserire l'ID dell'aula da modificare: ");
		int IDAula = Integer.parseInt(oLeggi.readLine());
		Aula oAula = new Aula(IDAula);
		System.out.println("L'aula da modificare: " + oAula.getDescrizione() + " " + oAula.getnumeroPosti());
		System.out.println("Inserire la nuova descrizione dell'aula: ");
		oAula.setDescrizione(oLeggi.readLine());
		System.out.println("Inserire il nuovo numero dei posti dell'aula: ");
		oAula.setnumeroPosti(oLeggi.readLine());
		oAula.salva();

	}

	private static void nuovoAula() throws IOException {
		// TODO Auto-generated method stub
		Aula oAula = new Aula();
		System.out.println("Inserisci la descirizione della nuova aula: ");
		oAula.setDescrizione(oLeggi.readLine());
		System.out.println("Inserisci il numero dei posti della nuova aula: ");
		oAula.setnumeroPosti(oLeggi.readLine());
		oAula.salva();
	}

	private static void elencoAula() throws IOException {
		ArrayList<Aula> elenco = Aula.elenco();
		System.out.println("Elenco delle Aule");
		for (int j = 0; j < elenco.size(); j++) {
			System.out.println(elenco.get(j).getChiave() + " " + elenco.get(j).getDescrizione() + " "
					+ elenco.get(j).getnumeroPosti());
		}
	}

	private static void modificaDiscente() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Inserire l'ID del Discente da modificare: ");
		int IDDiscente = Integer.parseInt(oLeggi.readLine());
		Discente oDiscente = new Discente(IDDiscente);
		System.out.println("Il discente da modificare: " + oDiscente.getCognome() + " " + oDiscente.getNome());
		System.out.println("Inserire il nuovo Cognome del Discente: ");
		oDiscente.setCognome(oLeggi.readLine());
		System.out.println("Inserire il nuovo Nome del Discente: ");
		oDiscente.setNome(oLeggi.readLine());
		System.out.println("Inserire la nuova matricola del Discente");
		oDiscente.setMatricola(oLeggi.readLine());
		oDiscente.salva();
	}

	private static void nuovoDiscente() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Inserisci il Cognome del nuovo Discente: ");
		String Cognome = oLeggi.readLine();
		System.out.println("Inserisci il Nome del nuovo Discente: ");
		String Nome = oLeggi.readLine();
		System.out.println("Inserisci la Matricola del nuovo Discente: ");
		String Matricola = oLeggi.readLine();
		Discente oDiscente = new Discente();
		oDiscente.setCognome(Cognome);
		oDiscente.setNome(Nome);
		oDiscente.setMatricola(Matricola);
		oDiscente.salva();
	}

	private static void elencoDiscenti() throws IOException {
		ArrayList<Discente> elencoDiscenti = Discente.elenco();
		System.out.println("Elenco dei Discenti");
		for (int j = 0; j < elencoDiscenti.size(); j++) {
			System.out.println(elencoDiscenti.get(j).getChiave() + " " + elencoDiscenti.get(j).getCognome() + " "
					+ elencoDiscenti.get(j).getNome());
		}
	}

	private static void eliminaDocente() throws IOException {
		System.out.println("Inserire l'ID del Docente da eliminare: ");
		int IDDocente = Integer.parseInt(oLeggi.readLine());
		Docente oDocente = new Docente(IDDocente);
		boolean check = oDocente.checkDocente();
		if (check)
			System.out.println(
					"Impossibile eliminare il Docente poichè conduce un corso. Eliminare prima il docente dal corso");
		else {
			oDocente.elimina();
			System.out.println("Il docente è stato eliminato correttamente");
		}

	}

	private static void modificaDocente() throws IOException {
		System.out.println("Inserire l'ID del Docente da modificare: ");
		int IDDocente = Integer.parseInt(oLeggi.readLine());
		Docente oDocente = new Docente(IDDocente);
		System.out.println("Il docente da modificare: " + oDocente.getCognome() + " " + oDocente.getNome());
		System.out.println("Inserire il nuovo Cognome del Docente: ");
		oDocente.setCognome(oLeggi.readLine());
		System.out.println("Inserire il nuovo Nome del Docente: ");
		oDocente.setNome(oLeggi.readLine());
		oDocente.salva();

	}

	private static void nuovoDocente() throws IOException {
		System.out.println("Inserire il Cognome del Docente: ");
		String Cognome = oLeggi.readLine();
		System.out.println("Inserire il Nome del Docente: ");
		String pNome = oLeggi.readLine();
		// Docente.aggiungi (Cognome, pNome);
		Docente oDocente = new Docente();
		oDocente.setCognome(Cognome);
		oDocente.setNome(pNome);
		oDocente.salva();
	}

	private static void elencoDocenti() throws IOException {
		ArrayList<Docente> elencoDocenti = Docente.elenco();
		System.out.println("Elenco dei Docenti");
		for (int j = 0; j < elencoDocenti.size(); j++) {
			System.out.println(elencoDocenti.get(j).getChiave() + " " + elencoDocenti.get(j).getCognome() + " "
					+ elencoDocenti.get(j).getNome());
		}
	}

}
