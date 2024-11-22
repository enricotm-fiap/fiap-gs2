package br.com.fiap.gs2.gs2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.gs2.gs2.model.Bateria;
import br.com.fiap.gs2.gs2.repository.BateriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BateriaService {
    private final BateriaRepository bateriaRepository;

    public List<Bateria> list() {
        return bateriaRepository.findAll();
    }

    public Bateria save(Bateria bateria) {        
        return bateriaRepository.save(bateria);
    }

    public boolean existsById(Long id) {        
        return bateriaRepository.existsById(id);
    }

    public void delete(Long id) {
        bateriaRepository.deleteById(id);
    }

    public Optional<Bateria> findById(Long id) {
        return bateriaRepository.findById(id);
    }
   
}
