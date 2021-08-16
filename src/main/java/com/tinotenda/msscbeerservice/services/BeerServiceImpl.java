package com.tinotenda.msscbeerservice.services;

import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.mappers.BeerMapper;
import com.tinotenda.msscbeerservice.repositories.BeerRepository;
import com.tinotenda.msscbeerservice.web.controller.NotFoundException;
import com.tinotenda.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getById(UUID beerId) {

        return mapper.BeerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return mapper.BeerToBeerDto(
                beerRepository.save(mapper.BeetDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto, UUID beerId) {

        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException());
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.BeerToBeerDto(beerRepository.save(beer));
    }
}
