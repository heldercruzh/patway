package br.com.petwayapi.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"id", "email"}))
public class Usuario {

    @ApiModelProperty(value = "Identificador do usuário")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "Email do usuário")
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(nullable = false)
    private String email;

    @ApiModelProperty(value = "Senha do usuário")
    @NotBlank(message = "O senha é obrigatória")
    @Column(nullable = false)
    private String senha;

    @ApiModelProperty(value = "Perfil do usuário")
    @NotNull(message = "O perfil é obrigatório")
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;


    @ApiModelProperty(value = "Token do usuário")
    private String token;

}
