package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.OrderDetails;

public interface OrderDetailsDao extends CrudRepository<OrderDetails, Integer> {

}
