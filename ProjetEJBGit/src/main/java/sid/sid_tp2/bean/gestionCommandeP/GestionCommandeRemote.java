package sid.sid_tp2.bean.gestionCommandeP;

import java.util.List;

import javax.ejb.Remote;

import sid.sid_tp2.bean.Client;
import sid.sid_tp2.bean.LigneCommande;

@Remote
public interface GestionCommandeRemote {
	
	
	public void ajouterCommande(List<LigneCommande> listeLigneCommande, Client client);

}
