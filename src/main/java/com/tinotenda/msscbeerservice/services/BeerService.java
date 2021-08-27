package com.tinotenda.msscbeerservice.services;

import com.tinotenda.msscbeerservice.web.model.BeerDto;
import com.tinotenda.msscbeerservice.web.model.BeerPageList;
import com.tinotenda.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(BeerDto beerDto, UUID beerId);

    BeerPageList listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, PageRequest of);

    BeerDto getByUpc(String upc);
}
