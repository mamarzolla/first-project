package it.corso.service;

import it.corso.helper.Response;

public interface ProfileService {
	
	Response login(String username, String password);

}
