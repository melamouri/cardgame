package com.atexo.cardgame.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atexo.cardgame.Dto.CardDto;
import com.atexo.cardgame.Dto.HandDto;
import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;
import com.atexo.cardgame.service.CardService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CardServiceImplTest {

    @Autowired
    private CardService cardService;

    /**
     * verifie qu'on recupere des cartes distinctes.
     */
    @Test
    public void getHandTest() {
        HandDto hand = cardService.getHand();
        Set<CardDto> setCards = new HashSet<CardDto>(hand.getCards());
        assertEquals(hand.getCards().size(),
                CardService.HAND_CARD_NUMBER.intValue());
        assertEquals(hand.getCards().size(), setCards.size());
    }

    /**
     * verifie le bon fonctionnement de l'algorithme de trie.
     */
    @Test
    public void getSortedHandTest() {
        List<CardColorEnum> colors = new ArrayList<CardColorEnum>(
                List.of(CardColorEnum.PIQUE, CardColorEnum.CARREAUX,
                        CardColorEnum.TREFLE, CardColorEnum.COEUR));

        List<CardValueEnum> values = new ArrayList<CardValueEnum>(List.of(
                CardValueEnum.AS, CardValueEnum.CINQ, CardValueEnum.DIX,
                CardValueEnum.HUIT, CardValueEnum.SIX, CardValueEnum.SEPT,
                CardValueEnum.QUATRE, CardValueEnum.DEUX, CardValueEnum.TROIS,
                CardValueEnum.NEUF, CardValueEnum.DAME, CardValueEnum.ROI,
                CardValueEnum.VALET));

        HandDto hand = new HandDto();
        hand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.SIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.DIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.AS));
        hand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.VALET));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.ROI));
        hand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DEUX));

        HandDto expectedSortedHand = new HandDto();
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DEUX));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DAME));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.DIX));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.SIX));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.AS));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.DAME));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DIX));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DAME));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.ROI));
        expectedSortedHand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.VALET));

        HandDto sortedHand = cardService.getSortedHand(colors, values, hand);
        assertEquals(expectedSortedHand.getCards(), sortedHand.getCards());

    }

    @Test
    public void getRamdomColorOrderTest() {
        List<CardColorEnum> cardColors = cardService.getRamdomColorOrder();
        assertNotNull(cardColors);
        Set<CardColorEnum> cardColorSet = new HashSet<CardColorEnum>(
                cardColors);
        assertEquals(cardColorSet.size(), cardColors.size());
    }
    
    @Test
    public void getRamdomValueOrderTest() {
        List<CardValueEnum> cardvalues = cardService.getRamdomValueOrder();
        assertNotNull(cardvalues);
        Set<CardValueEnum> cardValueSet = new HashSet<CardValueEnum>(
                cardvalues);
        assertEquals(cardValueSet.size(), cardvalues.size());
    }
}
