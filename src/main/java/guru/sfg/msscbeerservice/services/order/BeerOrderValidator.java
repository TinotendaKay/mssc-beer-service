package guru.sfg.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {
    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder) {
        AtomicInteger beersNotFound = new AtomicInteger();
        beerOrder.getBeerOrderLines().forEach(beerOrderLine -> {

            if (beerRepository.findByUpc(beerOrderLine.getUpc()).isEmpty()) {
                beersNotFound.getAndIncrement();
            }
        });

        return beersNotFound.get() == 0;
    }
}
