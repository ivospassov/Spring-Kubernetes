package com.resellerapp.controller;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.service.interfaces.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute("offerDTO")
    public OfferDTO offerDTO() {
        return new OfferDTO();
    }

    @GetMapping("/offer/add")
    public String renderAddOfferForm() {
        return "/offer-add";
    }

    @PostMapping("/offer/add")
    public String handleOfferForm(@Valid OfferDTO offerDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerDTO", offerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);

            return "redirect:/offer/add";
        }

        this.offerService.addOffer(offerDTO);
        return "redirect:/home";
    }

    @GetMapping("/offer/remove/{id}")
    public String removeOffer(@PathVariable("id") Long offerId) {
        this.offerService.removeOffer(offerId);
        return "redirect:/home";
    }

    @GetMapping("/offer/buy/{id}")
    public String buyOffer(@PathVariable("id") Long otherOfferId) {
        this.offerService.buyOffer(otherOfferId);

        return "redirect:/home";
    }
}
