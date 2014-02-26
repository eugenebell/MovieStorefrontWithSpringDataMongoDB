package com.eugene.service.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugene.model.Genre;
import com.eugene.service.dao.GenreRepository;

@Service(value="genreService")
public class GenreServiceImpl implements GenreService {

	private static final Logger LOG = Logger.getLogger(GenreServiceImpl.class);
	
	@Autowired
	private GenreRepository genreRepository;

	public void setGenreRepository(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	public List<Genre> getGenreListing() {
		LOG.debug("Get all genres");
		return genreRepository.findAll();
	}
	
}
