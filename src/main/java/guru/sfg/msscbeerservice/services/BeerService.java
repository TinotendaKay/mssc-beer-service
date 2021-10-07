package guru.sfg.msscbeerservice.services;

import guru.sfg.msscbeerservice.web.model.BeerDto;
import guru.sfg.msscbeerservice.web.model.BeerPageList;
import guru.sfg.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(BeerDto beerDto, UUID beerId);

    BeerPageList listBeers(String beerName, BeerStyle beerStyle, Boolean showInventoryOnHand, PageRequest of);

    BeerDto getByUpc(String upc);
}
