package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}
	
	@Autowired
	private MovieRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<MovieMinProjection> list = repository.search1("Action");
		List<MovieMinDTO> result1 = list.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\nResultado SQL RAÍZ");
		for(MovieMinDTO movie : result1) {
			System.out.println(movie); // ASSIM ELE CHAMA PELO TOSTRING
		}
		
		
		System.out.println("\n\nResultado JPQL\n\n");
		
		List<MovieMinDTO> result2 = repository.search2("Action");
		
		for(MovieMinDTO obj : result2) {
			System.out.println(obj); // ASSIM ELE CHAMA PELO TOSTRING
		}
		
	}
}