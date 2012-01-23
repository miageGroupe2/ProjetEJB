package sid.sid_tp2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="commande", schema="projetejb")
public class Commande implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	
	private double montant;


	public Commande() {
		
	}


	public int getId() {
		return id;
	}


	public void setIde(int id) {
		this.id = id;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
	
}
