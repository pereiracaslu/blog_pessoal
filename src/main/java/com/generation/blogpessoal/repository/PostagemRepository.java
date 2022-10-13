//repository> Classe com os métodos pré-implementadados (get,post,put e delete).

package com.generation.blogpessoal.repository;

import java.util.List;

//JPA>Biblioteca de métodos e instruções que descreve como deve ser o comportamento dos frameworks.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

@Repository                                              //objeto   //id
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	//Mesma coisa que no MySQL SELECT * FROM tb_postagens WHER titulo LIKE "%%";
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	
	}
