package br.com.petwayapi.models;


import javax.persistence.*;
import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Pessoa {

	@ApiModelProperty(value = "Identificador da pessoa")
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Nome da pessoa")
	@Size(message="O nome deve ter até 100 caracteres", max=100)
	@Size(message="O nome deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O nome é obrigatório")
	@Column(nullable = false)
	private String nome;

	@ApiModelProperty(value = "Usuário relacionado a pessoa")
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	@ApiModelProperty(value = "CPF da pessoa")
	//@Pattern(regexp = “[0-9]{11}”)
	@NotBlank(message = "O CPF é obrigatório")
	@Column(nullable = false)
	private String cpf;

	@ApiModelProperty(value = "RG da pessoa")
	@Size(message="O RG deve ter até 20 caracteres", max=20)
	@Size(message="O RG deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O RG é obrigatório")
	@Column(nullable = false)
	private String rg;

	@ApiModelProperty(value = "SSP da pessoa")
	@NotNull(message = "O SSP é obrigatório")
	@ManyToOne
	@JoinColumn(name = "ssp_id")
	private Uf ssp;

	@ApiModelProperty(value = "Data de Nascimento da pessoa")
	//@Pattern(regexp = "([0-9]{4})([0-9]{1})?-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])") //2000-12-01
	@NotBlank(message = "A data de nascimento é obrigatória")
	@Column(nullable = false)
	private String dataNascimento;

	@ApiModelProperty(value = "Genero da pessoa")
	@NotNull(message = "O gênero é obrigatório")
	@Column(nullable = false)
	private boolean genero;

	@ApiModelProperty(value = "Telefone da pessoa")
	//@Pattern(regexp = "([0-9]{11})") //73081645
	@NotBlank(message = "O telefone é obrigatório")
	@Column(nullable = false)
	private String telefone;

	@ApiModelProperty(value = "Celular da pessoa")
	//@Pattern(regexp = "([0-9]{11})") //61981008100
	@NotBlank(message = "O celular é obrigatório")
	@Column(nullable = false)
	private String celular;

	@ApiModelProperty(value = "CEP da pessoa")
	//@Pattern(regexp = "([0-9]{8})") //7300000
	@NotBlank(message = "O CEP é obrigatório")
	@Column(nullable = false)
	private String cep;

	@ApiModelProperty(value = "Município da pessoa")
	@NotNull(message = "O município é obrigatório")
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;

	@ApiModelProperty(value = "Bairro da pessoa")
	@Size(message="O bairro deve ter até 100 caracteres", max=100)
	@Size(message="O bairro deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O bairro é obrigatório")
	@Column(nullable = false)
	private String bairro;

	@ApiModelProperty(value = "Endereço da pessoa")
	@Size(message="O endereço deve ter até 100 caracteres", max=100)
	@Size(message="O endereço deve ter mais que 3 caracteres", min=3)
	@NotBlank(message = "O endereço é obrigatório")
	@Column(nullable = false)
	private String endereco;

	@ApiModelProperty(value = "Número da pessoa")
	@Size(message="O número deve ter até 100 caracteres", max=100)
	@Size(message="O número deve ter mais que 1 caractere", min=1)
	@NotBlank(message = "O número é obrigatório")
	@Column(nullable = false)
	private String numero;

	@ApiModelProperty(value = "Complemento do endereço da pessoa")
	@Column(nullable = true)
	private String complemento;

	@ApiModelProperty(value = "Status se a pessoa está ativa")
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public br.com.petwayapi.models.Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(br.com.petwayapi.models.Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public br.com.petwayapi.models.Uf getSsp() {
		return ssp;
	}

	public void setSsp(br.com.petwayapi.models.Uf ssp) {
		this.ssp = ssp;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
	