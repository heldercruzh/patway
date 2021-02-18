package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public br.com.petwayapi.models.Uf getUf() {
		return uf;
	}

	public void setUf(br.com.petwayapi.models.Uf uf) {
		this.uf = uf;
	}
}
