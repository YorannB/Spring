package poe.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poe.spring.domain.Trajet;
import poe.spring.domain.User;
import poe.spring.repository.TrajetRepository;
import poe.spring.repository.UserRepository;

@Service
public class TrajetManagerService {

	@Autowired
	private UserManagerService userManagerService;
	
	@Autowired
	private TrajetRepository trajetRepository;

	@Autowired
	private UserRepository userRepository;

	public Trajet creation(Long userId, String villeDepart, 
			String villeArrivee, Date dateDepart, int prixTrajet,
			int nbPlaces) throws Exception {

		Trajet trajet = new Trajet();

		User conducteur = userRepository.findOne(userId);
		//User conducteur = userManagerService.visualiserUser(userId);

		if (conducteur != null) {
			trajet.setVilleDepart(villeDepart);
			trajet.setVilleArrivee(villeArrivee);
			trajet.setDateDepart(dateDepart);
			trajet.setPrixTrajet(prixTrajet);
			trajet.setNbPlaces(nbPlaces);

			trajet.setConducteur(conducteur);
			conducteur.getTrajets().add(trajet);

			trajet = trajetRepository.save(trajet);
		} else {
			throw new Exception("conducteur introuvable");
		}
		return trajet;
	}

	public List<Trajet> lister() {
		List<Trajet> trajets = (List<Trajet>) trajetRepository.findAll();
		return trajets;
	}

	public Trajet visualiserTrajet(Long trajetId) {
		Trajet trajet = trajetRepository.findOne(trajetId);
		return trajet;
	}

}
