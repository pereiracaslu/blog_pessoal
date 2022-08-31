package com.generention.blogpessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generention.blogpessoal.model.Postagem;
import com.generention.blogpessoal.repository.PostagemRepository;
import java.util.List;

//Ter acesso a get, putch, delete e post
@RestController
//Passar um edpoint, e o endpoint será o caminho que executará o método.
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
		//injeção de independecia, tranfere a resposabilidade
		@Autowired
		private PostagemRepository postagemRepository;
	
	//SELECT * FROM tb_postagem// 	indica o http, pode ter get, putch, delete e post
	@GetMapping("/{id}")
	       //resposta	objeto do tipo postagem		
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		//executa a resposta				//status ok = 200 
		return postagemRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	}

