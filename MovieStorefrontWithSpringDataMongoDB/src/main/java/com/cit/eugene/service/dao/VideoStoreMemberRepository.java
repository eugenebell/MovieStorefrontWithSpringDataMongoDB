package com.cit.eugene.service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cit.eugene.model.VideoStoreMember;

/**
 * Used to manage the VideoStoreMember DAO layer.
 * 
 * @author Eugene Bell
 */
public interface VideoStoreMemberRepository extends MongoRepository<VideoStoreMember, Long> {
	
	/**
	 * Get VideoStoreMember by name.
	 * 
	 * @param username
	 * @return VideoStoreMember
	 */
	VideoStoreMember getVideoStoreMemberByName(String username);

}
