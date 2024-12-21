package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "USERTABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NewUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="place")
	private String place;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
}
