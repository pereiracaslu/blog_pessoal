package com.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuarioModel){
		
		if(usuarioRepository.findByUsuario(usuarioModel.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		
		usuarioModel.setSenha(criptografarSenha(usuarioModel.getSenha()));
		return Optional.of(usuarioRepository.save(usuarioModel));
	}
	
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if(		(buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ja existe!", null);
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		
		return Optional.empty();
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
		Optional<Usuario> usuarioModel = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if(usuarioModel.isPresent()) {
			if(compararSenhas(usuarioLogin.get().getSenha(), usuarioModel.get().getSenha())) {
				usuarioLogin.get().setId(usuarioModel.get().getId());
				usuarioLogin.get().setNome(usuarioModel.get().getNome());
				usuarioLogin.get().setFoto(usuarioModel.get().getFoto());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuarioModel.get().getSenha());
				
				return usuarioLogin;
			}
			
		}
		return Optional.empty();
	}
	
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
		
	}
	
	private String gerarBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
	
}

