package com.mono.pjdepartement.service.implement;

import java.util.List;
import java.util.Optional;

import com.mono.pjdepartement.entity.app.Emploi;
import com.mono.pjdepartement.entity.repository.EmploiRepository;
import com.mono.pjdepartement.service.EmploiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmploiServiceImpl implements EmploiService {
	
	@Autowired
    EmploiRepository emploiRepository;

	@Override
	public ResponseEntity<String> createOffreEmploi(Emploi emploie) {
		try {
            if (emploie.getPoste() == null) {
                return new ResponseEntity<>(
                        "Vous devez entrer un poste",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            }
            else if (emploie.getDescription() == null) {
                return new ResponseEntity<>(
                        "Vous devez entrer une Description",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            }
            else if (emploie.getDomaineRequis() == null) {
                return new ResponseEntity<>(
                        "Vous devez entrer un Domaine",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            }
            emploiRepository.save(emploie);
            return new ResponseEntity<>(
                    "L'offre d'emploie a été enregistré avec succès" + emploiRepository.save(emploie),
                    HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(
					"An exception has occured: "+e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@Override
	public ResponseEntity<String> updateEmploi(Emploi emploie, Long id) {
		Optional<Emploi> use = emploiRepository.findById(id);
        if(use.isEmpty()) {
            return new ResponseEntity<>(
                    "Student not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
        }
        if (emploie.getPoste() != null || !use.get().getPoste().equals(emploie.getPoste())) {
            use.get().setPoste(emploie.getPoste());
        }
        if (emploie.getDescription() != null || !use.get().getDescription().equals(emploie.getDescription())) {
            use.get().setDomaineRequis(emploie.getDescription());
        }
        
        emploiRepository.save(use.get());
        return new ResponseEntity<>(
                "Modification reussie" ,
                HttpStatus.OK);
	}

	@Override
	public List<Emploi> getAll() {
		return emploiRepository.findAll();
	}

	@Override
	public Emploi getEmploi(Long id) {
		if (emploiRepository.findById(id).isPresent())
            return emploiRepository.findById(id).get();
        else return null;
	}

	@Override
	public String deleteEmploi(Long id) {
		emploiRepository.deleteById(id);
        return "l'offre d'emploie a été supprimé";
	}

	@Override
	public List<Emploi> findByPoste(String poste) {
		return emploiRepository.findByPoste(poste);//recherche d'une offre d'emploie par le poste
	}
	@Override
	public Emploi findByDomainRequis(String domaineRequis) {
		return emploiRepository.findByDomaineRequis(domaineRequis);
	}
     
}
