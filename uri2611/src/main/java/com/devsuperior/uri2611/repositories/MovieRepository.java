package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

// Uma Vídeo locadora contratou seus serviços para catalogar os filmes dela. Eles precisam que você selecione o código e o nome dos filmes cuja descrição do gênero seja 'Action'.

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	// SQL
	@Query(nativeQuery = true, value = "SELECT movies.id, movies.name from movies INNER JOIN genres	ON movies.id_genres = genres.id	WHERE UPPER(genres.description) = UPPER(:genre)")
	List<MovieMinProjection> search1(String genre);
	
	// JPQL
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) "
			+ "FROM Movie obj "
			+ "WHERE obj.genre.description = :genre")
	List<MovieMinDTO> search2(String genre);
}
