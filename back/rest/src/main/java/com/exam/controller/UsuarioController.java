package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.repository.UsuarioRepository;
import com.exam.dto.Usuario;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUsers(@RequestParam(required = false) String id){
		try {
		    List<Usuario> users = new ArrayList<Usuario>();

		    if (id == null)
		    	usuarioRepository.findAll().forEach(users::add);
		    else
		    	usuarioRepository.findById(id);

		    if (users.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(users, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

}
