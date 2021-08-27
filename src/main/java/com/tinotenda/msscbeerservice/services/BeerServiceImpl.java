package com.tinotenda.msscbeerservice.services;

import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.mappers.BeerMapper;
import com.tinotenda.msscbeerservice.repositories.BeerRepository;
import com.tinotenda.msscbeerservice.web.controller.NotFoundException;
import com.tinotenda.msscbeerservice.web.model.BeerDto;
import com.tinotenda.msscbeerservice.web.model.BeerPageList;
import com.tinotenda.msscbeerservice.web.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {

        BeerDto beerDto;

        if (showInventoryOnHand) {
            beerDto = mapper.beerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));

        } else {
            beerDto = mapper.beerToBeerDtoNoInventory(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
        }


        return beerDto;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return mapper.beerToBeerDto(
                beerRepository.save(mapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto, UUID beerId) {

        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException());
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerPageList listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, PageRequest pageRequest) {
        BeerPageList beerPageList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand) {
            beerPageList = new BeerPageList(beerPage
                    .getContent()
                    .stream()
                    .map(mapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else {
            beerPageList = new BeerPageList(beerPage
                    .getContent()
                    .stream()
                    .map(mapper::beerToBeerDtoNoInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }


        return beerPageList;
    }
}
