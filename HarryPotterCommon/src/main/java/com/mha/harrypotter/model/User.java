package com.mha.harrypotter.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "USER")
@ToString
@EqualsAndHashCode
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SQ_USER")
	@SequenceGenerator(name = "SQ_USER", sequenceName = "SQ_USER",  allocationSize = 1)
	@Getter
	@Setter
	private int id;
	@Column(name = "EMAIL", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	private String email;
	@Column(name = "PASSWORD", nullable = false, length = 50, unique = true)
	@Getter
	@Setter
	private String password;
	@Column(name = "API_KEY", nullable = false, length = 12, unique = true)
	@Getter
	@Setter
	private String apiKey;
	@Column(name = "NAME", nullable = false, length = 12, unique = false)
	@Getter
	@Setter
	private String name;
	
}
