package com.mha.harrypotter.model;

/**
 * Entity of house
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mha.harrypotter.model.dto.HouseDTO;
import com.mha.harrypotter.util.StringToListConverter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HOUSE")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class House implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, length = 200, unique = true)
	@Getter
	@Setter
	private String id;
	@Column(name = "NAME", nullable = false, length = 200, unique = true)
	@Getter
	@Setter
	private String name;
	@Column(name = "HEAD_OF_HOUSE", nullable = false, length = 200, unique = true)
	@Getter
	@Setter
	private String headOfHouse;
	@Column(name = "PRINCIPLES", nullable = false, length = 200)
	@Convert(converter = StringToListConverter.class)
	@Getter
	@Setter
	private List<String> principles;
	@Column(name = "COLORS", nullable = false, length = 200)
	@Convert(converter = StringToListConverter.class)
	@Getter
	@Setter
	private List<String> colors;
	@Column(name = "SCHOOL", nullable = true, length = 200)
	@Getter
	@Setter
	private String school;
	@Column(name = "MASCOT", nullable = false, length = 200)
	@Getter
	@Setter
	private String mascot;
	@Column(name = "houseGhost", nullable = false, length = 200)
	@Getter
	@Setter
	private String houseGhost;
	@Column(name = "FOUNDER", nullable = false, length = 200)
	@Getter
	@Setter
	private String founder;

	/**
	 *
	 * build a new instance using a dto by parameter
	 * 
	 * @param HouseDTO
	 * 
	 */
	public House(HouseDTO dto) {
		
		this.id = dto.getId();
		this.headOfHouse = dto.getHeadOfHouse();
		this.principles = dto.getValues();//changed name because 'values' is a reserved word
		this.colors = dto.getColors();
		this.school = dto.getSchool();
		this.mascot = dto.getMascot();
		this.houseGhost = dto.getHouseGhost();
		this.founder = dto.getFounder();
		this.name = dto.getName();
	}

}
