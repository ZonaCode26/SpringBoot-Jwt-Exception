package com.soaint.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soaint.entity.Usuario;


@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	private static Logger log =  LogManager.getLogger("logins-all");
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
			roles.add(new SimpleGrantedAuthority("CLIENT"));
	
			Usuario usuario = login(username);
			if (usuario==null) {
				
				throw new UsernameNotFoundException(String.format("Usuario no existe", username));
			}
			
			PasswordEncoder bcrypt = 	new BCryptPasswordEncoder();
			
			UserDetails ud = new User(usuario.getUser(), bcrypt.encode(usuario.getPassword()) , roles);
		
		return ud;
	}

	public Usuario login(String user) {

		String record = null;
		FileReader in = null;
		boolean existe = false;
		Usuario usuario = null;
		try {

			in = new FileReader("src/main/resources/usuarios.txt");

			BufferedReader br = new BufferedReader(in);

			while ((record = br.readLine()) != null) {

				String[] word = record.split(","); // dividir en palabras delimitadas por espacios
				if (word[0].equals(user)) {
					// && word[1].equals(password)
					usuario = new Usuario();
					usuario.setUser(word[0]);
					usuario.setPassword(word[1]);
					existe = true;
					break;
				}
			}
			if (existe) {
				return usuario;
			} else {
				log.info("datos incorrectos en el logueo");
			}
		} catch (IOException e) {
			e.getCause();
			log.error("error: " + e.getMessage());
		}

		return usuario;

	}
	
}

