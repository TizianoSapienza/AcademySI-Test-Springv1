package com.academysi.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academysi.dao.UtenteDao;
import com.academysi.dto.UtenteDto;
import com.academysi.dto.UtenteLoginRequestDto;
import com.academysi.dto.UtenteRegistrazioneDto;
import com.academysi.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteDao utenteDao;
	
	 @Override
	    public void registerUser(UtenteRegistrazioneDto utenteDto) {
	        // Crea un nuovo oggetto Utente
	        Utente utente = new Utente();
	        utente.setFirstname(utenteDto.getFirstname());
	        utente.setLastname(utenteDto.getLastname());
	        utente.setEmail(utenteDto.getEmail());

	        // Hash della password
	        String sha256hex = DigestUtils.sha256Hex(utenteDto.getPassword());
	        utente.setPassword(sha256hex);

	        // Salva l'Utente nel repository
	        utenteDao.save(utente);
	    }

	@Override
	public Utente getUserById(int id) {
		 Optional<Utente> userOptionalDb = utenteDao.findById(id);
		    
		    if(!userOptionalDb.isPresent()) {
		    	return new Utente();
		    }
		    
		    return userOptionalDb.get();
	}

	@Override
	public void updateUserData(Utente utente) {
		Optional<Utente> userOptionalDb = utenteDao.findById(utente.getId());
		
		if(userOptionalDb.isPresent()) {
			
			//Estraggo l'oggetto e lo inserisco in un oggetto di tipo user
			Utente userDb = userOptionalDb.get();
			
			//Aggiorno dati
			userDb.setFirstname(utente.getFirstname());
			userDb.setLastname(utente.getLastname());
			userDb.setEmail(utente.getEmail());
			if (utente.getPassword() != null && !utente.getPassword().isEmpty()) {
	            String sha256hex = DigestUtils.sha256Hex(utente.getPassword());
	            userDb.setPassword(sha256hex);
	        }
			
			//Salvo oggetto aggiornato
			utenteDao.save(userDb);
		}
		
	}

	@Override
	public List<Utente> getUsers() {
		return (List<Utente>) utenteDao.findAll();
	}

	@Override
	public void deleteUserById(int id) {
		Optional<Utente> userOptional = utenteDao.findById(id);

        if (userOptional.isPresent()) {
            utenteDao.deleteById(id);
        }
		
	}

	@Override
	public boolean existUserByEmail(String email) {
		return utenteDao.existsByEmail(email);
	}

	@Override
	public Utente getUserByEmail(String email) {
		Optional<Utente> userOptionalDb = utenteDao.findByEmail(email);

        if (!userOptionalDb.isPresent()) {
            return new Utente();
        }

        return userOptionalDb.get();
	}

	@Override
    public void updateUserByEmail(String email, UtenteDto utenteDto) {
        Optional<Utente> userOptionalDb = utenteDao.findByEmail(email);

        if (userOptionalDb.isPresent()) {
            // Estraggo l'oggetto e lo inserisco in un oggetto di tipo user
            Utente userDb = userOptionalDb.get();

            // Aggiorno dati
            userDb.setFirstname(utenteDto.getFirstname());
            userDb.setLastname(utenteDto.getLastname());
            if (utenteDto.getPassword() != null && !utenteDto.getPassword().isEmpty()) {
                String sha256hex = DigestUtils.sha256Hex(utenteDto.getPassword());
                userDb.setPassword(sha256hex);
            }

            // Salvo oggetto aggiornato
            utenteDao.save(userDb);
        }
    }
	
	@Override
    public boolean login(UtenteLoginRequestDto utenteLoginRequest) {
        String email = utenteLoginRequest.getEmail();
        String password = utenteLoginRequest.getPassword();

        Optional<Utente> utenteOptional = utenteDao.findByEmail(email);

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            // Esegui il controllo della password (ad esempio, usando SHA-256)
            String hashedPassword = DigestUtils.sha256Hex(password);
            return hashedPassword.equals(utente.getPassword());
        }

        return false;
    }
	
}