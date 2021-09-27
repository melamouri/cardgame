package com.atexo.cardgame.Dto;

import java.util.ArrayList;
import java.util.List;


public class HandDto {

    /**
     * the hand card list.
     */
    private List<CardDto> cards = new ArrayList<CardDto>();
    /**
     * default constructor.
     */
    public HandDto() {
    }
    /**
     * the getter of cards.
     * @return liste de cartes
     */
    public List<CardDto> getCards() {
        return cards;
    }
    /**
     * the setter of cards.
     * @param cards
     */
    public void setCards(List<CardDto> cards) {
        this.cards = cards;
    }
}
