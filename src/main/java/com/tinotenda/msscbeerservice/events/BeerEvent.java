package com.tinotenda.msscbeerservice.events;

import com.tinotenda.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent {

    static final long serialVersionUID = -8332647549491830351L;

    private BeerDto beerDto;
}
