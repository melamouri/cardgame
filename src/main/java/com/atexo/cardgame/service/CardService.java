package com.atexo.cardgame.service;

import java.util.List;

import com.atexo.cardgame.Dto.HandDto;
import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;


public interface CardService {

    /**
     * nombre de carte dans une main.
     */
    Integer HAND_CARD_NUMBER = 10;

    /**
     * renvoie une main a patir du
     * packet de cartes.
     * @return une main
     */
    HandDto getHand();
    /**
     * renvoie une main triee selon
     * un ordre de couleur et un autre
     * ordre de valeur.
     * @param orderColorList
     * @param orderValueList
     * @param hand
     * @return la main triee
     */
    HandDto getSortedHand(List<CardColorEnum> orderColorList,
            List<CardValueEnum> orderValueList, HandDto hand);
    /**
     * renvoie un ordre de couleur aleatoir.
     * @return liste de couleurs
     */
    List<CardColorEnum> getRamdomColorOrder();
    /**
     * renvoie un ordre de valeur aleatoir.
     * @return liste de valeurs
     */
    List<CardValueEnum> getRamdomValueOrder();

}
