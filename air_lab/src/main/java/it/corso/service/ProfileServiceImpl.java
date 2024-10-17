package it.corso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.corso.dao.ProfileDao;
import it.corso.helper.Response;
import it.corso.helper.TokenGenerator;
import it.corso.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private TokenGenerator tokenGenerator;

	@Override
	
	public Response login(String username, String password) {
		Profile profile = profileDao.findByUsername(username);
		if (profile != null && encoder.matches(password, profile.getPassword())) {
			profile.getUser().setToken(tokenGenerator.generateToken(profile.getUsername()));
			profileDao.save(profile);
			return new Response(200, profile.getUser().getToken());
			}
		return new Response(400, "login failed");
		}
	}


