package com.microservice.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String remark;
}
