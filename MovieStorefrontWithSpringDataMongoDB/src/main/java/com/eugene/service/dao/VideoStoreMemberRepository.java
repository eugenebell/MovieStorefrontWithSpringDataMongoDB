package com.eugene.service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.eugene.model.VideoStoreMember;

/**
 * Used to manage the VideoStoreMember DAO layer.
 * 
 * @author Eugene Bell
 */
public interface VideoStoreMemberRepository extends MongoRepository<VideoStoreMember, String> {
	
	/**
	 * Get VideoStoreMember by name.
	 * 
	 * @param username
	 * @return VideoStoreMember
	 */
	@Query("{'user' : { '$ref' : 'user', '$id' : { '$oid' : ?0}}}})")
	VideoStoreMember findByUserUserID(String userID);

}
