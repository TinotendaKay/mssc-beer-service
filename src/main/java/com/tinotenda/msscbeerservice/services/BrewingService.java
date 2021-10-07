package com.tinotenda.msscbeerservice.services;

import com.tinotenda.msscbeerservice.config.JmsConfig;
import com.tinotenda.msscbeerservice.domain.Beer;
import com.tinotenda.msscbeerservice.events.BrewBeerEvent;
import com.tinotenda.msscbeerservice.mappers.BeerMapper;
import com.tinotenda.msscbeerservice.repositories.BeerRepository;
import com.tinotenda.msscbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 500) // every 5 seconds
    public void checkForLowInventory() {
        final List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Min OnHand: " + beer.getMinOnHand());
            log.debug("Inventory is : {}", invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });


    }
}
