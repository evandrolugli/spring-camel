package com.brq.camel.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brq.camel.models.UsuarioModel;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {
	
	private ArrayList<UsuarioModel> usuarios = new ArrayList<>();
	
	public UsuarioController() {
		this.adicionarUsuario();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ArrayList<UsuarioModel> getAll(){
		return this.usuarios;
	}
	
	@GetMapping(value = "{id}")
	public UsuarioModel getOne (@PathVariable("id") int id){
		return this.usuarios.get(id);
	}
	
	@PostMapping(value = "")
	public void salvar(@RequestBody UsuarioModel usuario){
		System.out.println(usuario.getEmail());
		this.usuarios.add(usuario);
	}
	
	
	@PatchMapping(value = "{id}")
	public UsuarioModel atualizar(
			@RequestBody UsuarioModel usuario,
			@PathVariable("id") int id){
		
			for (UsuarioModel usuarioModel : usuarios) {
				if(usuarioModel.getId() == id) 
				{
					usuarioModel.setName(usuario.getName());
					usuarioModel.setEmail(usuario.getEmail());
				}
			}
			return usuario;
		}

	@DeleteMapping(value = "{id}")
	public void excluir (@PathVariable("id") int id) throws Exception{
		
		try {
			this.usuarios.remove(id);	
		}
		catch(IndexOutOfBoundsException e) {
			throw new Exception("Índice não encontrado");
		}
		catch(Exception e) {
			throw new Exception("Erro no servidor");
		}
	}
	
	private void adicionarUsuario() {
		for (int i=0; i<5; i++) {
			UsuarioModel usuario = new UsuarioModel(i, "Usuário " + i, "usuario_"+ i +"@gmail.com");
			this.usuarios.add(usuario);
		}
	}

}
