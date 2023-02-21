package com.resellerapp.service.interfaces;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Offer;

import java.util.List;

public interface OfferService {

    void addOffer(OfferDTO offerDTO);

    List<Offer> retrieveAllOffers();

    List<Offer> retrieveOtherOffers();

    List<Offer> retrieveBoughtOffers();

    void removeOffer(Long offerId);

    void buyOffer(Long otherOfferId);
}
