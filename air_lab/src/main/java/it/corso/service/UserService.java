package it.corso.service;

import it.corso.helper.Response;
import it.corso.model.User;

public interface UserService {
	
	Response setNewUser(User newUser);
	Response deleteUser(String token);
	Object FindUserById(Integer id);
	Response logout (String token);
	Object setGuestUser(User user);
}
