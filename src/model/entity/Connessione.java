package model.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
	
	public Connection connetti;
	
	public void apri()
	{
		//PASSO 1, Caricare i driver
		 //il blocco try non è un costrutto, quindi deve essere autoconsistente
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//PASSO2, stabilire la connessione con db
				this.connetti = DriverManager.getConnection("jdbc:oracle:thin:@TERAMO.DIGITALBUSINESSOLUTION.COM:1522:orcl", "Cors6", "Cors6");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public void chiudi()
	{
		try {
			this.connetti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
