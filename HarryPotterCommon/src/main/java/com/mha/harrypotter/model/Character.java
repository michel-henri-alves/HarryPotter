package com.mha.harrypotter.model;

/**
 * Entity of characters
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CHARACTER")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Character implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SQ_CHARACTER")
	@SequenceGenerator(name = "SQ_CHARACTER", sequenceName = "SQ_CHARACTER",  allocationSize = 1)
	@ApiModelProperty(notes = "character id")
	@Getter
	@Setter
	private int id;
	@Column(name = "NAME", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	@ApiModelProperty(notes = "character name")
	private String name;
	@Column(name = "ROLE", nullable = false, length = 50)
	@Getter
	@Setter
	private String role;
	@Column(name = "SCHOOL", nullable = false, length = 50)
	@Getter
	@Setter
	private String school;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_HOUSE")
	@Getter
	@Setter
	@ApiModelProperty(notes = "character house")
	private House house;
	@Column(name = "PATRONUS", nullable = false, length = 30)
	@Getter
	@Setter
	private String patronus;
	
}
