package com.mha.harrypotter.model.dto;

/**
 * Object will be passed by parameter to API for obtain token 
 * 
 * @author michel
 * @version 0.0.1
 * 
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
	
	@ApiModelProperty(notes = "User email")
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String password;
	@ApiModelProperty(notes = "User name")
	@Getter
	@Setter
	private String name;
	

}
