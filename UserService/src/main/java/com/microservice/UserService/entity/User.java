package com.microservice.UserService.entity;

import java.util.ArrayList;
import java.util.List;

 
 

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class User {
	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	@Transient
	private List<Ratings> ratings = new ArrayList<>();
}
