package com.decagon.rewardyourteacherapi;


import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.repository.SchoolRepository;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class RewardYourTeacherApiApplication implements CommandLineRunner {
	@Autowired
	SchoolRepository schoolRepository;
	public static void main(String[] args) {
		SpringApplication.run(RewardYourTeacherApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<School>> typeReference = new TypeReference<>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/Schools.json");
		try {
			List<School> schools = mapper.readValue(inputStream, typeReference);
			List<School> dBSchools = schoolRepository.findAll();
			for(School sch: schools){
				if(!dBSchools.contains(sch)){
					dBSchools.add(sch);
				}
			}
			schoolRepository.saveAll(dBSchools);
		} catch (IOException e) {
			throw new RuntimeException("Cannot save school");
		}
	}
}
