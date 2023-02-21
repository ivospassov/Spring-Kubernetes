package com.resellerapp.service;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.interfaces.ConditionService;
import com.resellerapp.service.interfaces.OfferService;
import com.resellerapp.util.UserLoginSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ConditionService conditionService;
    private final UserRepository userRepository;
    private final UserLoginSession userLoginSession;

    public OfferServiceImpl(OfferRepository offerRepository, ConditionService conditionService, UserRepository userRepository, UserLoginSession userLoginSession) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.userRepository = userRepository;
        this.userLoginSession = userLoginSession;
    }

    @Override
    public void addOffer(OfferDTO offerDTO) {
        Offer offer = this.mapOfferDTO(offerDTO);
        this.offerRepository.save(offer);
    }

    private Offer mapOfferDTO(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setDescription(offerDTO.getDescription());
        offer.setPrice(offerDTO.getPrice());
        offer.setCondition(findCondition(offerDTO.getConditionName()));
        offer.setOfferor(this.findUserById());

        return offer;
    }

    @Override
    public List<Offer> retrieveAllOffers() {
        return this.offerRepository.findAllByOfferorId(this.userLoginSession.getId());
    }

    @Override
    public List<Offer> retrieveOtherOffers() {
        return this.offerRepository.findAllByOfferorIdNot(this.userLoginSession.getId());
    }

    @Override
    public List<Offer> retrieveBoughtOffers() {
        return this.offerRepository.findAllByOffereeId(this.userLoginSession.getId());
    }

    @Override
    public void removeOffer(Long offerId) {
        this.offerRepository.deleteById(offerId);
    }

    @Override
    public void buyOffer(Long otherOfferId) {
        Offer soldOffer = this.offerRepository.findById(otherOfferId).orElse(null);
        Offer boughtOffer = new Offer();
        User offeree = this.userRepository.findById(this.userLoginSession.getId()).orElse(null);

        boughtOffer.setDescription(soldOffer.getDescription());
        boughtOffer.setCondition(soldOffer.getCondition());
        boughtOffer.setPrice(soldOffer.getPrice());
        boughtOffer.setOfferee(offeree);

        this.offerRepository.deleteById(soldOffer.getId());
        this.offerRepository.save(boughtOffer);
    }

    private Condition findCondition(ConditionName conditionName) {
        return this.conditionService.findConditionByName(conditionName);
    }

    private User findUserById() {
        return this.userRepository.findById(this.userLoginSession.getId()).orElse(null);
    }
}
