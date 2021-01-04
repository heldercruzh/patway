package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"id", "email"}))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email(message = "Email inválido")
	@NotBlank(message = "O email é obrigatório")
	@Column(nullable = false)
	private String email;

	@NotBlank(message = "O senha é obrigatória")
	@Column(nullable = false)
	private String senha;

	@Valid
	@NotNull(message = "O perfil é obrigatório")
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;

	private String token;



}
