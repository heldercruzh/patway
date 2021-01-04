package br.com.petwayapi.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity
public class Pessoa {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(message="O nome deve ter até 100 caracteres", max=100)
	@Size(message="O nome deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O nome é obrigatório")
	@Column(nullable = false)
	private String nome;

	@Valid
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	//@Pattern(regexp = “[0-9]{5}-[0-9]{3}”)
	@NotBlank(message = "O CPF é obrigatório")
	@Column(nullable = false)
	private String cpf;

	@Size(message="O RG deve ter até 20 caracteres", max=20)
	@Size(message="O RG deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O RG é obrigatório")
	@Column(nullable = false)
	private String rg;

	@Valid
	@NotNull(message = "O SSP é obrigatório")
	@ManyToOne
	@JoinColumn(name = "ssp_id")
	private Uf ssp;

	//@Pattern(regexp = “[0-9]{5}-[0-9]{3}”)
	@NotBlank(message = "A data de nascimento é obrigatória")
	@Column(nullable = false)
	private String dataNascimento;

	@NotNull(message = "O gênero é obrigatório")
	@Column(nullable = false)
	private boolean genero;

	//@Pattern(regexp = “[0-9]{5}-[0-9]{3}”)
	@NotBlank(message = "O telefone é obrigatório")
	@Column(nullable = false)
	private String telefone;

	//@Pattern(regexp = “[0-9]{5}-[0-9]{3}”)
	@NotBlank(message = "O celular é obrigatório")
	@Column(nullable = false)
	private String celular;

	//@Pattern(regexp = “[0-9]{5}-[0-9]{3}”)
	@NotBlank(message = "O CEP é obrigatório")
	@Column(nullable = false)
	private String cep;

	@Valid
	@NotNull(message = "O município é obrigatório")
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;

	@Size(message="O bairro deve ter até 100 caracteres", max=100)
	@Size(message="O bairro deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O bairro é obrigatório")
	@Column(nullable = false)
	private String bairro;

	@Size(message="O endereço deve ter até 100 caracteres", max=100)
	@Size(message="O endereço deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O endereço é obrigatório")
	@Column(nullable = false)
	private String endereco;

	@Size(message="O número deve ter até 100 caracteres", max=100)
	@Size(message="O número deve ter mais que 1 caractere", min=1)
	@NotBlank(message = "O número é obrigatório")
	@Column(nullable = false)
	private String numero;

	@Column(nullable = true)
	private String complemento;

	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean ativo;



}
	