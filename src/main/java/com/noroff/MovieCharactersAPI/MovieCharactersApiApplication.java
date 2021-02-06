package com.noroff.MovieCharactersAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCharactersApiApplication {

	/**
	 * TODO: FIND ALTERNATIVE TO JSONIGNORE, CAUSES ISSUES WHEN UPDATING CHARACTER -> MOVIES vs MOVIES -> CHARACTERS
	 * TODO: MAYBE LOOK AT SERVICE INSTEAD OF REPOSITORY
	 * TODO: EXCEPTIONHANDLING FOR THE REST OF THE METHODS
	 * TODO: SWAGGER/OPENAI
	 * TODO: UPLOAD DB AND APP ON HEROKU
	 */


	public static void main(String[] args) {
		//Hello from Git!
		SpringApplication.run(MovieCharactersApiApplication.class, args);
		//Test, ser dere dette her?
		// Ja :)
		//hahaha
	}

}
