package com.academysi.service;

import java.util.List;

import com.academysi.dto.UtenteDto;
import com.academysi.dto.UtenteLoginRequestDto;
import com.academysi.dto.UtenteRegistrazioneDto;
import com.academysi.model.Utente;


public interface UtenteService {
	
	void registerUser(UtenteRegistrazioneDto utenteDto);
	
	Utente getUserById(int id);
	
	Utente getUserByEmail(String email);
	
	void updateUserData(Utente utente);
	
	List<Utente> getUsers();
	
	void deleteUserById(int id);
	
	boolean existUserByEmail(String email);
	
	void updateUserByEmail(String email, UtenteDto utenteDto);
	
	boolean login(UtenteLoginRequestDto utenteLoginRequest);
}
