package com.tinotenda.msscbeerservice.mappers;

import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeetDtoToBeer(BeerDto beerDto);
}
