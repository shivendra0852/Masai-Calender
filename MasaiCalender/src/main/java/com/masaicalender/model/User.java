package com.masaicalender.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	private String email;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must only contain alphabetic characters")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must only contain alphabetic characters")
	private String lastName;
	
	@Size(min=10, max=10, message="Mobile number must have exactly 10 digits")
	private String mobileNo;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=\\-\\]\\[]).{6,12}$", message = "Password should be alphanumeric, contain 6-12 characters, and have at least one special character, one upper case, one lowercase, and one digit")
	private String password;
	
	@Past(message = "Date of birth must be in the past")
	private LocalDateTime dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Event> events = new ArrayList<>();
}
