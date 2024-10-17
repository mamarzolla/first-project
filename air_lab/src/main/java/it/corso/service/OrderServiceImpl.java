package it.corso.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import it.corso.dao.CartDao;
import it.corso.dao.OrderDao;
import it.corso.dao.OrderDetailsDao;
import it.corso.dao.UserDao;
import it.corso.helper.Response;
import it.corso.model.Cart;
import it.corso.model.Order;
import it.corso.model.OrderDetails;
import it.corso.model.User;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper mapper;
	@Caching(
			evict = {
					@CacheEvict(cacheNames = "CartList", allEntries = true )
			}
			)
	public Response createOrder(String token,User guestUser, List<Cart> cartItems) {
		try {
			
			User user;
			if(token!=null && !token.isEmpty()) {
				user= userDao.findByToken(token);
				if (user==null) 
					return new Response(400, "user not found");	
				List<Cart> existingCart = cartDao.findByUser(user);
				for (Cart cartOpt :existingCart) {
					for(Cart cartOrdered: cartItems) {
						if(cartOrdered.getId().equals(cartOpt.getId()) )
							cartDao.deleteById(cartOpt.getId());
					}
				}
			}
			else {
				Object newUser = userService.setGuestUser(guestUser);
				if (newUser instanceof Response)
					return (Response) newUser;
				user = (User) newUser;
				}
			Order order= new Order();
			order.setUser(user);
			order.setDate(LocalDateTime.now());
			order.setState("confirmed");
			order.setTotal(calculateTotal(cartItems));
			orderDao.save(order);
			
			 List<OrderDetails> orderDetailsList = cartItems.stream()
		                .map(cart -> {
		                    OrderDetails orderDetails = mapper.map(cart, OrderDetails.class);
		                    orderDetails.setOrder(order);
		                    orderDetails.setPartial(cart.getShoe().getPrice() * cart.getQty());
		                    return orderDetails;
		                })
		                .collect(Collectors.toList());
			 orderDetailsDao.saveAll(orderDetailsList);
			 return new Response(200, "Order successfully created");
		} catch (Exception e) {
			return new Response(400, e.getMessage());
		}
	}
	
	
	@Override
	public Response createLoggedOrder(List<Cart> cartItems,String token) {
		try {
			User user = null;	
			if(token!=null && !token.isEmpty()) {
				 user= userDao.findByToken(token);
				if (user==null) 
					return new Response(400, "user not found");
				List<Cart> existingCart = cartDao.findByUser(user);
					for (Cart cartOpt :existingCart) {
						for(Cart cartOrdered: cartItems) {
							if(cartOrdered.getId().equals(cartOpt.getId()) )
								cartDao.deleteById(cartOpt.getId());
						}
					}
			}
			Order order= new Order();
			order.setUser(user);
			order.setDate(LocalDateTime.now());
			order.setState("confirmed");
			order.setTotal(calculateTotal(cartItems));
			orderDao.save(order);
			 List<OrderDetails> orderDetailsList = cartItems.stream()
		                .map(cart -> {
		                    OrderDetails orderDetails = mapper.map(cart, OrderDetails.class);
		                    orderDetails.setOrder(order);
		                    orderDetails.setPartial(cart.getShoe().getPrice() * cart.getQty());
		                    return orderDetails;
		                })
		                .collect(Collectors.toList());
			 orderDetailsDao.saveAll(orderDetailsList);
			 return new Response(200, "Order successfully created");
		} catch (Exception e) {
			return new Response(400, e.getMessage());
		}
	}
	
   private double calculateTotal(List<Cart> cartItems) {
        return cartItems.stream().mapToDouble(cart -> cart.getShoe().getPrice() * cart.getQty()).sum();
    }
   
   @Override
   public Response createGuestOrder(List<Cart> cartItems, User newUser) {
	   try {
		   Object user = null;    
	        if (newUser != null) {
	            // Salva il nuovo utente nel database
	            user = userService.setGuestUser(newUser);
	           if (user instanceof Response) 
	                return (Response) user;	           
	        }
	        User createdUser = (User) user;
	        // Crea un nuovo ordine
	        Order order = new Order();
	        order.setUser(createdUser);
	        order.setDate(LocalDateTime.now());
	        order.setState("confirmed");
	        order.setTotal(calculateTotal(cartItems));
	        orderDao.save(order);

	 
	       List<OrderDetails> orderDetailsList = cartItems.stream()
	               .map(cart -> {
	                   OrderDetails orderDetails = mapper.map(cart, OrderDetails.class);
	                    orderDetails.setOrder(order);
	                    orderDetails.setPartial(cart.getShoe().getPrice() * cart.getQty());
	                    return orderDetails;
	                })
	                .collect(Collectors.toList());

	       orderDetailsDao.saveAll(orderDetailsList);
	        
	        return new Response(200, "Order successfully created");
		
	} catch (Exception e) {
		return new Response(400, e.getMessage());
	}
   }
}
