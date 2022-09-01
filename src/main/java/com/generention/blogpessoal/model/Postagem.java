

package com.generention.blogpessoal.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// indica ao spring que o objeto abaixo será uma tabela no banco de dados
@Entity

//Dar um nome a tabela a ser ciada, sem ela a tabela fica com o nome do objeto
@Table (name = "tb_postagens")

public class Postagem {
	
	@Id//indica que o id da tabela será uma chave primaria
	//Definição de como será o auto incremento
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//define o campo de texto que não comece com espaço
	@NotBlank(message = "O atributo título é ogrigatório!")
	//Tamanho max e min de caracteres que podem ser inseridas
	@Size(min=5, max=100, message = "O atributo título deve conter no mínimo 05 caracteres e no máximo 100 caracteres")
	private String titulo;
	
	//@NotNull define o campo de texto como obrigatório
	@NotBlank(message = "O atributo título é ogrigatório!")
	@Size(min=10, max=1000, message = "O atributo texto deve conter no mínimo 10 caracteres e no máximo 1000 caracteres")
	private String texto;
	
	//Para mostrar a atualização da data.
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return this.data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}