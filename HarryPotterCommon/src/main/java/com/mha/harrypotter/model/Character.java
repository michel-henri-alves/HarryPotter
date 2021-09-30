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
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CHARACTER")
@ToString
@EqualsAndHashCode
public class Character implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SQ_CHARACTER")
	@SequenceGenerator(name = "SQ_CHARACTER", sequenceName = "SQ_CHARACTER",  allocationSize = 1)
	@Getter
	@Setter
	private int id;
	@Column(name = "NAME", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	private String name;
	@Column(name = "ROLE", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	private String role;
	@ElementCollection
	@Column(name = "COLORS", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	private List<String> colors;
	@Column(name = "SCHOOL", nullable = false, length = 30, unique = true)
	@Getter
	@Setter
	private String school;
	@Column(name = "HOUSE", nullable = false, length = 30, unique = false)
	@Getter
	@Setter
	private House house;
	@Column(name = "PATRONUS", nullable = false, length = 30, unique = false)
	@Getter
	@Setter
	private String patronus;
	
}
