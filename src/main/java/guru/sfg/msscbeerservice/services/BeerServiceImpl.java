package guru.sfg.msscbeerservice.services;

import guru.sfg.msscbeerservice.domain.Beer;
import guru.sfg.msscbeerservice.mappers.BeerMapper;
import guru.sfg.msscbeerservice.repositories.BeerRepository;
import guru.sfg.msscbeerservice.web.controller.NotFoundException;
import guru.sfg.msscbeerservice.web.model.BeerDto;
import guru.sfg.msscbeerservice.web.model.BeerPageList;
import guru.sfg.msscbeerservice.web.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand==false")
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

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand==false")
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

    @Override
    @Cacheable(cacheNames = "beerUpcCache", key = "#upc")
    public BeerDto getByUpc(String upc) {

        return mapper.beerToBeerDtoNoInventory(
                beerRepository.findByUpc(upc).orElseThrow(() -> new NotFoundException()));
    }

}
