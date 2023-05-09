package com.pjdepartement.service.implement;


import com.pjdepartement.entity.Enseignant;
import com.pjdepartement.entity.repository.EnseignantRepository;
import com.pjdepartement.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantServiceImpl implements EnseignantService {

    @Autowired
    EnseignantRepository enseignantRepository;

    @Override
    public ResponseEntity<String> create(Enseignant enseignant) {
        try {
            if (enseignant.getNom() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre nom",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getPrenom() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre prénom",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getMatricule() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre matricule",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getDomain() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre domain",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getTitre() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre titre",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getMail() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre mail",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

            if (enseignant.getPassword() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre mot de passe",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            
            if (enseignant.getPhoto() == null)
                return new ResponseEntity<>(
                        "Vous devez entrer votre photo",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            
            if (enseignant.getNumWhatsApp() == 0L)
                return new ResponseEntity<>(
                        "Vous devez entrer votre numero de telephone",
                        HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500
            
            Enseignant etmp = enseignantRepository.save(enseignant);
            return new ResponseEntity<>(
                    "Enregistrement reussi" + etmp,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "500 Error",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> update(Enseignant enseignant, Long idE) {
        Optional<Enseignant> uses = enseignantRepository.findById(idE);
        if (uses.isEmpty())
            return new ResponseEntity<>(
                    "Enterprise not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);//renvoie une erreur 500

        if (enseignant.getNom() != null || !uses.get().getNom().equals(enseignant.getNom()))
            uses.get().setNom(enseignant.getNom());
        
        if (enseignant.getPrenom() != null || !uses.get().getPrenom().equals(enseignant.getPrenom()))
            uses.get().setPrenom(enseignant.getPrenom());

        if (enseignant.getMatricule() != null || !uses.get().getMatricule().equals(enseignant.getMatricule()))
            uses.get().setMatricule(enseignant.getMatricule());

        if (enseignant.getDomain() != null || !uses.get().getDomain().equals(enseignant.getDomain()))
            uses.get().setDomain(enseignant.getDomain());

        if (enseignant.getTitre() != null || !uses.get().getTitre().equals(enseignant.getTitre()))
            uses.get().setTitre(enseignant.getTitre());

        if (enseignant.getMail() != null || !uses.get().getMail().equals(enseignant.getMail()))
            uses.get().setMail(enseignant.getMail());

        if (enseignant.getPassword() != null || !uses.get().getPassword().equals(enseignant.getPassword()))
            uses.get().setPassword(enseignant.getPassword());

        if (enseignant.getPhoto() != null || !uses.get().getPhoto().equals(enseignant.getPhoto()))
            uses.get().setPhoto(enseignant.getPhoto());

        if (enseignant.getNumWhatsApp() != 0L || !(uses.get().getNumWhatsApp()==enseignant.getNumWhatsApp()))
            uses.get().setNumWhatsApp(enseignant.getNumWhatsApp());

        enseignantRepository.save(enseignant);
        return new ResponseEntity<>(
                "Modification reussie" ,
                HttpStatus.OK);
    }

    @Override
    public Enseignant getEnseignant(Long id) {
        if (enseignantRepository.findById(id).isPresent())
            return enseignantRepository.findById(id).get();
        else return null;
    }

    @Override
    public List<Enseignant> getAll() {
        return enseignantRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        enseignantRepository.deleteById(id);
        return "l'enseignant a été supprimé";
    }
}