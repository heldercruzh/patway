package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Perfil {

	@ApiModelProperty(value = "Identificador do perfil")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Nome do município")
	@NotBlank(message = "O nome do perfil é obrigatório")
	@Column(nullable = false)
	private String name;

}
