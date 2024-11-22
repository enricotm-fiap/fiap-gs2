package br.com.fiap.gs2.gs2.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.gs2.gs2.dtos.BateriaRequestCreateDto;
import br.com.fiap.gs2.gs2.dtos.BateriaRequestUpdateDto;
import br.com.fiap.gs2.gs2.dtos.BateriaResponseDto;
import br.com.fiap.gs2.gs2.model.Bateria;

@Component
public class BateriaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BateriaResponseDto toDto(Bateria bateria){
        return modelMapper.map(bateria, BateriaResponseDto.class);
    }

    public Bateria toModel(BateriaRequestCreateDto dto) {
        return modelMapper.map(dto, Bateria.class);
    }

    public Bateria toModel(Long id, BateriaRequestUpdateDto dto) {
        Bateria result = modelMapper.map(dto, Bateria.class);
        result.setId(id);
        return result;
    } 
}
