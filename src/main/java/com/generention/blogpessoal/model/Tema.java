package com.generention.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas")
public class Tema {
	
	@Id
	//id	Long	BIGINT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// descricao	String	VARCHAR(255)
	@NotNull(message = "O Atributo Descrição é obrigatório")
	private String descricao;
	
	
	//1 pra muitos; mB = passa quem vai ser o um; cascade cada vez atualizado,
	//todas as postagem são atualizadas, caso o tema seja excluido tudo é excluido
	//										Efeito cascata
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	//Evita a recursividade, para não entrar em um loop infinito
	/*e bom ter em mente que nas anotacoes tipo OneToMany, a primeira palavra One
	 * vai sempre se referir a classe que foi inserida. Ou seja One dessa classe 
	 * aqui to many da outra classe. E na postagem foi ManyToOne ou seja, many
	 * dessa classe postagem to One da outra classe
*/
	@JsonIgnoreProperties("tema")
	//Ao passar o many, o program já diz que vai passar, o List prepara para receber
	private List<Postagem> postagem;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Postagem> getPostagem() {
		return this.postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}