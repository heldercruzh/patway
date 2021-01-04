package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Municipio {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O código do município é obrigatório")
	@Column(nullable = false)
	private String codigoIbge;

	@NotBlank(message = "O nome do município é obrigatório")
	@Column(nullable = false)
	private String nomeMunicipio;

	@Valid
	@NotNull(message = "A Uf é obrigatória")
	@ManyToOne
	@JoinColumn(name = "uf_id")
	private Uf uf;

}
