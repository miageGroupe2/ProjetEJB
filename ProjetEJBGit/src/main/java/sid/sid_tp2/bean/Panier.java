package sid.sid_tp2.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.glassfish.api.admin.CommandException;

import sid.sid_tp2.bean.gestionClientP.GestionClientLocal;
import sid.sid_tp2.bean.gestionCommandeP.GestionCommandeLocal;
import sid.sid_tp2.model.Commande;

@Stateful
public class Panier implements PanierRemote {

	
	@EJB
	private GestionClientLocal gestionClientLocal ;
	
	@EJB
	private GestionCommandeLocal gestionCommandeLocal;
	
	
	private List<LigneCommande> panier;
	

	public Panier() {

		this.panier = new ArrayList<LigneCommande>();
	}

	public List<LigneCommande> getPanier() {

		return panier;
	}

	public void modifierQuantite(int idProduit, int quantite) {

		for (LigneCommande ligne : panier){
			
			if(ligne.getProduit().getId() == idProduit){
				
				ligne.setQuantite(quantite);
				return ;
			}
			
		}
	}

	public void viderPanier() {

		this.panier.clear();
	}

	public void ajouterProduit(int idProduit){

		for (LigneCommande ligne : panier){
			
			if(ligne.getProduit().getId() == idProduit){
				
				return;
			}
		}
		Produit produit = Magasin.getProduitById(idProduit);
		this.panier.add(new LigneCommande(produit, 1));
		
	}
	
	public void supprimerProduit(int idProduit){
		
		for (int i = 0 ; i < panier.size() ; i++){
			
			LigneCommande ligne = this.panier.get(i);
			if(ligne.getProduit().getId() == idProduit){
				
				this.panier.remove(ligne);
				
				return ;
			}
		}
		
	}
	public double calculerTotal(){
		
		
		double total = 0 ;
		
		for (LigneCommande ligne : panier){
			
			total += ligne.getProduit().getPrix() * ligne.getQuantite() ;
		}
		
		return total ;
	}
	
	public void validerPanier(){
		
		/*
		boolean commandeValidee = Banque.verifierCompte(gestionClientLocal.getClient().getNumCompte(), calculerTotal());
		
		if(commandeValidee){
			
			gestionCommandeLocal.ajouterCommande(panier, gestionClientLocal.getClient());
			
		}
		*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        
        Commande commande = new Commande();
        
        commande.setMontant(200);
        
 
        em.persist(commande);
       
       	commande.setMontant(800);      
       	em.merge(commande);
       
       	transac.commit();
	
		
	}
}
