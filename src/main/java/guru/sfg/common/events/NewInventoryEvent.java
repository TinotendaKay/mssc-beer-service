package guru.sfg.common.events;

import guru.sfg.msscbeerservice.events.BeerEvent;
import guru.sfg.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
