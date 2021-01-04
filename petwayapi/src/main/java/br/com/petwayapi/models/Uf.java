package br.com.petwayapi.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Uf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "A Uf é obrigatória")
	@Column(nullable = false)
	private String uf;

	@NotBlank(message = "O nome da UF é obrigatório")
	@Column(nullable = false)
	private String nome;

}
