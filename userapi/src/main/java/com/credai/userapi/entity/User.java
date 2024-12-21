package com.credai.userapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="CRED_USER")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

//	@Size(min=1, message="firstName min size should be 1")
	@Column(name="first_name",length = 255)
	private String firstName;
	
	@Size(min=3, message = "lastName must be atleast 1 charcter")
	@Column(name="last_name",length = 255)
	private String lastName;
//	
//	@NotNull(message="userName should not be null")
//	@NotEmpty(message="userName should not be empty")
//	@Size(min =6, message="minimum size should be 6")
	@Column(name="user_name",length = 255)
	private String userName;
	
//	@NotNull(message="password should not be null")
//	@Size(min=10, message="passwrod should be min 10 charcters")
	@Column(name = "password",length = 255)
	private String password;
	
//	@NotNull(message="email should not be null")
//	@Email(message="email should be valid")
	@Column(name="email",length = 255)
	private String email;
	
//	@AssertTrue
	@Column(name="enabled")
	private boolean enabled;
	
//	@NotNull(message="role should not be null")
	@Column(name="role")
	private String role;
	
//	@Size(min=1, message="address must be min 10 and max 100")
	@Column(name="address",length = 255)
	private String address;
	
	
//		@Pattern(regexp="^[a-zA-Z0-9]{6,255}$", message="userName should be 6 to 15 characters without any special character")

}
