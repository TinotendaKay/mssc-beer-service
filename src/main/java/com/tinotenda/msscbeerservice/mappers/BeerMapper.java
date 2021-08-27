package com.tinotenda.msscbeerservice.mappers;

import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoNoInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
