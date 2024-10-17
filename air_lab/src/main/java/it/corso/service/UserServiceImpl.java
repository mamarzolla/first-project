package it.corso.service;

import java.time.LocalDateTime;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.corso.dao.ProfileDao;
import it.corso.dao.UserDao;
import it.corso.helper.Response;
import it.corso.model.Address;
import it.corso.model.Profile;
import it.corso.model.User;

@Service
@Caching
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
//	@Autowired
	//private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Response setNewUser(User newUser) {
		if(profileDao.findByUsername(newUser.getProfile().getUsername())!=null)
			return new Response(409, "profile already registered");
		try {
			String criptedPassword = encoder.encode(newUser.getProfile().getPassword());
			newUser.getProfile().setPassword(criptedPassword);
			newUser.getProfile().setRegisterDate(LocalDateTime.now());
			//adderess list
			if(newUser.getAddressList()!=null) {
				for(Address add : newUser.getAddressList()){
					add.setUser(newUser);
					//System.out.println(newUser.getAddressList());
				}
			}
			userDao.save(newUser);
			return new Response(200,"user saved");
			
		} catch (DataIntegrityViolationException e) {
			return new Response(400, e.getMessage());
		}
	}

	@Override
	public Response deleteUser(String token) {
		User user = userDao.findByToken(token);
		if(user==null)
			return new Response(404, "user not found");
		try {
			Profile profile= user.getProfile();
            
            user.setProfile(null);
            profileDao.delete(profile);        	     
	        user.setToken(null);
	        userDao.save(user);		
			return new Response(202,"user deleted");
			
		} catch (Exception e) {
			  return new Response(500, "Error occurred: " + e.getMessage());
		}	
	}

	
	@Override
	public Object FindUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response logout(String token) {
		User user = userDao.findByToken(token);
		if(user== null)
			return new Response(400, "action not allowed");
		user.setToken(null);
		userDao.save(user);
		return new Response(200, "logout succesful");
	}

	@Override
	public Object setGuestUser(User newUser) {
		
		try {
			if (newUser.getProfile()!=null)
				return new Response(400, "Please continue on user register form");
			if(newUser.getAddressList()==null) 
				return new Response (400, "One address is mandatory");
			for(Address add : newUser.getAddressList()){
				add.setUser(newUser);
				//System.out.println(newUser.getAddressList());
			}
			User user = userDao.save(newUser);
			return user;
		} catch (Exception e) {
			return new Response(400, e.getMessage());
		}
	}
	
	

}
