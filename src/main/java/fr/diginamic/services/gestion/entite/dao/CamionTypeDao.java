package fr.diginamic.services.gestion.entite.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.services.gestion.entite.CamionType;
import fr.diginamic.services.gestion.entite.VoitureType;

public class CamionTypeDao extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public CamionType insertVoitureType(String type, Double tarif) throws IOException {
		CamionType camionTypeInsert = null;
		
		TypedQuery<CamionType> query = em.createQuery("SELECT v FROM VoitureType v WHERE v.type = ?1",
				CamionType.class);
		query.setParameter(1, type);
		List<CamionType> camionTypeList = query.getResultList();
		
		if(camionTypeList.isEmpty()) {
			transaction.begin();
			camionTypeInsert = new CamionType(type, tarif);
			em.persist(camionTypeInsert);
			transaction.commit();
		}
		else {
			camionTypeInsert = camionTypeList.get(0);
			System.out.println("Ce type de camion existe déja");
		}
		return camionTypeInsert;
	}

}
