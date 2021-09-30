package com.mha.harrypotter.model.dto;

/**
 * data transfer to house object
 * 
 * @author michel
 * @version 0.0.1
 * 
 */
import java.util.List;

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
public class HouseDTO {
	
	@ApiModelProperty(notes = "house id")
	@Getter
	@Setter
	private String id;
	@ApiModelProperty(notes = "house name")
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String headOfHouse;
	@Getter
	@Setter
	private List<String> values;
	@Getter
	@Setter
	private List<String> colors;
	@Getter
	@Setter
	private String school;
	@Getter
	@Setter
	private String mascot;
	@Getter
	@Setter
	private String houseGhost;
	@Getter
	@Setter
	private String founder;
	

}
