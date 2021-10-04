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
public class CharacterDTO {
	
	@Getter
	@Setter
	private String id;
	@ApiModelProperty(notes = "character name")
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String role;
	@Getter
	@Setter
	private String school;
	@ApiModelProperty(notes = "character`s house")
	@Getter
	@Setter
	private String house;
	@Getter
	@Setter
	private String patronus;

}
