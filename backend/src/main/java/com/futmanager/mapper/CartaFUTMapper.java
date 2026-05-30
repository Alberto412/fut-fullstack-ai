package com.futmanager.mapper;

import com.futmanager.dto.CartaFUTDTO;
import com.futmanager.entity.CartaFUT;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = JugadorMapper.class)
public interface CartaFUTMapper {

    CartaFUTDTO toDTO(CartaFUT cartaFUT);

    CartaFUT toEntity(CartaFUTDTO cartaFUTDTO);

    List<CartaFUTDTO> toDTOList(List<CartaFUT> cartasFUT);
}
