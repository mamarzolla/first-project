package it.corso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import it.corso.dao.CartDao;
import it.corso.dao.ShoeDao;
import it.corso.dao.UserDao;
import it.corso.dto.CartDto;
import it.corso.helper.Response;
import it.corso.model.Cart;
import it.corso.model.Color;
import it.corso.model.Shoe;
import it.corso.model.Size;
import it.corso.model.User;

@Service
@CacheConfig(cacheNames = {"cart"})
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ShoeDao shoeDao;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public Response addItemsToCart(String tokeString, List<Cart> cartList) {

			int counter = 0;
			User user = userDao.findByToken(tokeString);
			if(user== null)
				return new Response(400, "user not logged");
			for(Cart cart: cartList) {
				
				Optional<Shoe> shoeOpt = shoeDao.findById(cart.getShoe().getId());
				if(shoeOpt.isEmpty())
					return new Response(401, "shoe not found");
				
				Shoe shoe =shoeOpt.get();
				
				List<Size> sizeList = shoe.getAvailableSizes();
				boolean checkSize = sizeList.stream().anyMatch( s -> s.getId().equals(cart.getSize().getId()));
			    if (!checkSize) 
		            return new Response(404, "Size not available for this shoe, array position: "+counter);
		        
				
				List<Color> colorList = shoe.getAvailableColors();
				boolean checkColor= colorList.stream().anyMatch(s -> s.getId().equals(cart.getColor().getId()));
			    if (!checkColor) 
			        return new Response(404, "Color not available for this shoe, array position: "+counter);
			        
			    Optional<Cart> existingCartOpt = cartDao.findByUserAndShoeAndColorAndSize(user, shoe, cart.getColor(), cart.getSize());
			    counter++;
			    if (existingCartOpt.isPresent()) { 	          
		            Cart existingCart = existingCartOpt.get();
		            existingCart.setQty(existingCart.getQty() + cart.getQty());
		            cartDao.save(existingCart);
		        } else {
		        	cart.setUser(user); 
		        	cart.setShoe(shoe);  
		        	cart.setPrice(shoe.getPrice());
		        	cartDao.save(cart); 
		        }
	 
			}
		return new Response(200, "item added to cart");
		}

	@Override
	@Cacheable(value = "CartList", key = "#token", sync = true)
	public Object findCartById(String token) {
		User user = userDao.findByToken(token);
		if(user== null)
			return new Response(400, "user not logged");	
		List<Cart> existingCart= cartDao.findByUser(user);	
		List<CartDto> cartDtoList = existingCart.stream()
		        .map(cart -> mapper.map(cart, CartDto.class))
		        .collect(Collectors.toList());
	
		return cartDtoList;
	}
	
	
	
	
	
	
	}
		
	


