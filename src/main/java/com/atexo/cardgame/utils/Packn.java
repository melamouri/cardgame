package com.atexo.cardgame.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.atexo.cardgame.Enum.CardValueEnum;
import com.atexo.cardgame.Dto.CardDto;
import com.atexo.cardgame.Enum.CardColorEnum;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Packn {

    /**
     * la liste des cartes.
     */
    private List<CardDto> cards = new ArrayList<CardDto>();

    /**
     * le constructeur de la liste des cartes
     * possible.
     */
    public Packn() {
        Arrays.stream(CardColorEnum.values()).forEach(c -> {
            Arrays.stream(CardValueEnum.values()).forEach(cv -> {
                cards.add(new CardDto(c, cv));
            });
        });
    }

    /**
     * getter des cartes.
     * @return la liste des cates
     */
    public List<CardDto> getCards() {
        return cards;
    }

}
