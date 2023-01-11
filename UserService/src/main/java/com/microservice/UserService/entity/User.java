package com.microservice.UserService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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
