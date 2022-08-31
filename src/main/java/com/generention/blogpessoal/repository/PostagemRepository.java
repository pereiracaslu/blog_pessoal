//repository> Classe com os métodos pré-implementadados (get,post,put e delete).

package com.generention.blogpessoal.repository;

//JPA>Biblioteca de métodos e instruções que descreve como deve ser o comportamento dos frameworks.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generention.blogpessoal.model.Postagem;

@Repository                                              //objeto   //id
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
