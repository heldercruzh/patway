package br.com.petwayapi.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Uf {

	@ApiModelProperty(value = "Identificador da UF")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Sigla da UF")
	@NotBlank(message = "A Uf é obrigatória")
	@Column(nullable = false)
	private String uf;

	@ApiModelProperty(value = "descrição da UF")
	@NotBlank(message = "O nome da UF é obrigatório")
	@Column(nullable = false)
	private String nome;

}
