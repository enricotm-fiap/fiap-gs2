package br.com.fiap.gs2.gs2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs2.gs2.dtos.BateriaRequestCreateDto;
import br.com.fiap.gs2.gs2.dtos.BateriaRequestUpdateDto;
import br.com.fiap.gs2.gs2.dtos.BateriaResponseDto;
import br.com.fiap.gs2.gs2.mapper.BateriaMapper;
import br.com.fiap.gs2.gs2.repository.BateriaRepository;
import br.com.fiap.gs2.gs2.service.BateriaService;
import br.com.fiap.gs2.gs2.views.BateriaFullView;
import br.com.fiap.gs2.gs2.views.BateriaSimpleView;
import br.com.fiap.gs2.gs2.views.BateriaViewType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/baterias")
@RequiredArgsConstructor
public class BateriaController {    
    
    private final BateriaService bateriaService;
    private final BateriaMapper bateriaMapper;
    private final BateriaRepository bateriaRepository;

    @GetMapping
    public ResponseEntity<List<BateriaResponseDto>> list() {
        List<BateriaResponseDto> dtos = bateriaService.list()
            .stream()
            .map(e -> bateriaMapper.toDto(e))
            .toList();
        
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<BateriaResponseDto> create(@RequestBody BateriaRequestCreateDto dto) {        

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(
        			bateriaMapper.toDto(
        					bateriaService.save(bateriaMapper.toModel(dto)))
        			);
    }

    @PutMapping("{id}")
    public ResponseEntity<BateriaResponseDto> update(
                        @PathVariable Long id, 
                        @RequestBody BateriaRequestUpdateDto dto) {
        if (! bateriaService.existsById(id)){
            throw new RuntimeException("Id inexistente");
        }                
        return ResponseEntity.ok()
        		.body(
        			bateriaMapper.toDto(
        				bateriaService.save(bateriaMapper.toModel(id, dto)))
        		);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (! bateriaService.existsById(id)){
        	throw new RuntimeException("Id inexistente");
        }

        bateriaService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<BateriaResponseDto> findById(@PathVariable Long id) {    	
    	return ResponseEntity.ok()
    			.body(
    				bateriaService
    					.findById(id)
    					.map(e -> bateriaMapper.toDto(e))
    					.orElseThrow(() -> new RuntimeException("Id inexistente"))
    			);    	  		     
    }
    
    @GetMapping("/find")
    public  ResponseEntity<?> findByNome(
                @RequestParam String nome, 
                BateriaViewType type) { 

        switch (type) {
            case FULL:
                return ResponseEntity.ok().body(bateriaRepository.findAllByNomeContains(nome, BateriaFullView.class));                
            case SIMPLE:
                return ResponseEntity.ok().body(bateriaRepository.findAllByNomeContains(nome, BateriaSimpleView.class));            
        }
        return ResponseEntity.noContent().build();
    }
}
