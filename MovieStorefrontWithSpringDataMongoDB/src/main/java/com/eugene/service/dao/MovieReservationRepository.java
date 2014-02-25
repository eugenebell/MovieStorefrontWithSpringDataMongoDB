package com.eugene.service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eugene.model.MovieReservation;

/**
 * Used to manage the MovieReservation DAO layer.
 * 
 * @author Eugene Bell
 */
public interface MovieReservationRepository extends MongoRepository<MovieReservation, String>  {
	
}
