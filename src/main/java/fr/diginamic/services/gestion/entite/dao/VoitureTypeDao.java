package fr.diginamic.services.gestion.entite.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.services.gestion.entite.VoitureType;


public class VoitureTypeDao extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public VoitureType insertVoitureType(String type, Double tarif) throws IOException {
		VoitureType voitureTypeInsert = null;
		
		TypedQuery<VoitureType> query = em.createQuery("SELECT v FROM VoitureType v WHERE v.type = ?1",
				VoitureType.class);
		query.setParameter(1, type);
		List<VoitureType> voitureTypeList = query.getResultList();
		
		if(voitureTypeList.isEmpty()) {
			transaction.begin();
			voitureTypeInsert = new VoitureType(type, tarif);
			em.persist(voitureTypeInsert);
			transaction.commit();
		}
		else {
			voitureTypeInsert = voitureTypeList.get(0);
			System.out.println("Ce type de voiture existe déja");
		}
		return voitureTypeInsert;
	}
}
