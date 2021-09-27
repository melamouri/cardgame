package com.atexo.cardgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atexo.cardgame.Dto.HandDto;
import com.atexo.cardgame.service.CardService;

@RestController
@RequestMapping("/api")
public class CardController {

    /**
     * injection du service
     * de gestion des cartes.
     */
    @Autowired
    private CardService cardService;

    /**
     * permet de renvoyer une main
     * construite aleatoirement.
     * @return une main
     */
    @RequestMapping("/hand")
    @CrossOrigin(origins = "http://localhost:8081")
    public HandDto getHand() {
        return cardService.getHand();
    }

    /**
     * permet de renvoyer une main
     * triee selon deux ordres de couleur
     * et valeur aleatoires.
     * @param hand
     * @return une main triee
     */
    @PostMapping(path = "/sortedHand")
    @CrossOrigin(origins = "http://localhost:8081")
    public HandDto getSortedHand(@RequestBody(required = true) HandDto hand) {
        return cardService.getSortedHand(cardService.getRamdomColorOrder(),
                cardService.getRamdomValueOrder(), hand);
    }

}
