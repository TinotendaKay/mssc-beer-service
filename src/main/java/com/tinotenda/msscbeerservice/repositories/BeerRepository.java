package com.tinotenda.msscbeerservice.repositories;

import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {


    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest);

    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, PageRequest pageRequest);
}
