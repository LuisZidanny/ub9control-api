package br.com.applicationfinancialmanagement.resource;

import java.net.URI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.applicationfinancialmanagement.model.Usuario;
import br.com.applicationfinancialmanagement.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(usuarioSalvo.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(usuarioSalvo);
	}
	
	@GetMapping("/{codigo}")
	public Usuario buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepository.findOne(codigo);
		return usuario;
	} 
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @RequestBody Usuario usuario) {
		try {
			Usuario usuarioSalvo = buscarPeloCodigo(codigo);
			BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo", "senha");
			usuarioRepository.save(usuarioSalvo);
			return ResponseEntity.ok(usuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
