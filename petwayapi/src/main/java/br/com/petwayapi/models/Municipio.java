package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Municipio {

	@ApiModelProperty(value = "Identificador do município")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "código do município")
	@NotBlank(message = "O código do município é obrigatório")
	@Column(nullable = false)
	private String codigoIbge;

	@ApiModelProperty(value = "Nome do município")
	@NotBlank(message = "O nome do município é obrigatório")
	@Column(nullable = false)
	private String nomeMunicipio;

	@ApiModelProperty(value = "UF do município")
	@NotNull(message = "A Uf é obrigatória")
	@ManyToOne
	@JoinColumn(name = "uf_id")
	private Uf uf;

}
