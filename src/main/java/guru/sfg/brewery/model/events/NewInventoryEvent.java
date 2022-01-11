package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
