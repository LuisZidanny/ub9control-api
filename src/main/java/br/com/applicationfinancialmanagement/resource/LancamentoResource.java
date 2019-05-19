package br.com.applicationfinancialmanagement.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.applicationfinancialmanagement.model.Lancamento;
import br.com.applicationfinancialmanagement.model.dtos.LancamentoDto;
import br.com.applicationfinancialmanagement.repository.LancamentoRepository;
import br.com.applicationfinancialmanagement.repository.filter.LancamentoFilter;
import br.com.applicationfinancialmanagement.resource.exception.LancamentoExistenteException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@GetMapping
	public List<LancamentoDto> findAll(LancamentoFilter lancamentoFilter){
		List<Lancamento> lancamentos = lancamentoRepository.filtrar(lancamentoFilter);
		Collections.sort(lancamentos);
		List<LancamentoDto> lancamentoDtos = lancamentos.stream().map(lancamento -> new LancamentoDto(lancamento)).collect(Collectors.toList());
		return lancamentoDtos;
	}
	
	@GetMapping("/semanal/{codigo}")
	public List<LancamentoDto> findAllLancamentoSemanal(@PathVariable Long codigo){
		List<Lancamento> lancamentos = lancamentoRepository.findBylancamentoSemanal(codigo);
		if(lancamentos == null) {
			lancamentos = new ArrayList<Lancamento>();
		}
		List<LancamentoDto> lancamentoDtos = lancamentos.stream().map(lancamento -> new LancamentoDto(lancamento)).collect(Collectors.toList());
		return lancamentoDtos;
	}

	@GetMapping("/mensal/{codigo}")
	public List<LancamentoDto> findAllLancamentoMensal(@PathVariable Long codigo){
		List<Lancamento> lancamentos = lancamentoRepository.findBylancamentoMensal(codigo);
		if(lancamentos == null) {
			lancamentos = new ArrayList<Lancamento>();
		}
		List<LancamentoDto> lancamentoDtos = lancamentos.stream().map(lancamento -> new LancamentoDto(lancamento)).collect(Collectors.toList());
		return lancamentoDtos;
	}
	
	@GetMapping("/diario/{codigo}")
	public LancamentoDto findAllLancamentoDiario(@PathVariable Long codigo){
		Lancamento lancamentos = lancamentoRepository.findBylancamentoDiario(codigo);
        if(lancamentos == null) {
        	lancamentos = new Lancamento();
		}
		LancamentoDto lancamentoDtos = new LancamentoDto(lancamentos);
		return lancamentoDtos;
	}

	@GetMapping("/{codigo}")
	public Lancamento buscarPeloCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		return lancamento;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Lancamento> save(@RequestBody Lancamento lancamento){
		Optional<Lancamento> lancament = lancamentoRepository.findByDataTrabalhoAndUsuario(lancamento.getDataTrabalho() , lancamento.getUsuario());
		if(lancament.isPresent()) {
			throw new LancamentoExistenteException();
		}
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
						.buildAndExpand(lancamentoSalvo.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(lancamentoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @RequestBody Lancamento lancamento) {
		try {
			Lancamento lancamentoSalvo = buscarPeloCodigo(codigo);
			BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
			lancamentoRepository.save(lancamentoSalvo);
			return ResponseEntity.ok(lancamentoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
