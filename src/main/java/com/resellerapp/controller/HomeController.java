package com.resellerapp.controller;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.interfaces.OfferService;
import com.resellerapp.util.UserLoginSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final OfferService offerService;
    private final UserLoginSession userLoginSession;

    public HomeController(OfferService offerService, UserLoginSession userLoginSession) {
        this.offerService = offerService;
        this.userLoginSession = userLoginSession;
    }

    @GetMapping("/")
    public String renderIndexPage() {
        return "/index";
    }

    @GetMapping("/home")
    public String renderHomePage(Model model) {
        List<Offer> offersOfCurrentUser = this.offerService.retrieveAllOffers();
        List<Offer> otherUsersOffers = this.offerService.retrieveOtherOffers();
        List<Offer> boughtOffers = this.offerService.retrieveBoughtOffers();

        model.addAttribute("currentUsername", userLoginSession.getUsername());
        model.addAttribute("offersOfCurrentUser", offersOfCurrentUser);
        model.addAttribute("otherOffers", otherUsersOffers);
        model.addAttribute("boughtOffers", boughtOffers);
        model.addAttribute("currency", "€");

        return "/home";
    }
}
