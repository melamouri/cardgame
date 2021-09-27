package com.atexo.cardgame.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.atexo.cardgame.Dto.CardDto;
import com.atexo.cardgame.Dto.HandDto;
import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;
import com.atexo.cardgame.service.CardService;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {
    /**
     * mvc controller
     */
    @Autowired
    private MockMvc mvc;

    /**
     * le mock du service des cartes.
     */
    @MockBean
    private CardService cardService;

    /**
     * verifie que le retour est bien un json.
     * 
     * @throws Exception
     */
    @Test
    public void getHand_thenReturnJsonArray() throws Exception {
        HandDto hand = initHand();
        Mockito.when(cardService.getHand()).thenReturn(hand);

        mvc.perform(get("/api/hand").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cards[0].color",
                        is(CardColorEnum.CARREAUX.toString())))
                .andExpect(jsonPath("$.cards[0].value",
                        is(CardValueEnum.SIX.toString())));
    }

    @Test
    public void getSortedHand_thenReturnJsonArray() throws Exception {
        String handPram = new String(getClass().getClassLoader()
                .getResourceAsStream("hand.json").readAllBytes());
        HandDto sortedHand = initSortedHand();
        Mockito.when(cardService.getSortedHand(Mockito.any(), Mockito.any(),
                Mockito.any())).thenReturn(sortedHand);
        mvc.perform(post("/api/sortedHand").content(handPram)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.cards[0].color",
                        is(CardColorEnum.PIQUE.toString())))
                .andExpect(jsonPath("$.cards[0].value",
                        is(CardValueEnum.DEUX.toString())));
    }

    private HandDto initHand() {
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
        return hand;
    }

    private HandDto initSortedHand() {
        HandDto hand = new HandDto();
        hand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DEUX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.PIQUE, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.DIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.CARREAUX, CardValueEnum.SIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.AS));
        hand.getCards()
                .add(new CardDto(CardColorEnum.TREFLE, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DIX));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.DAME));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.ROI));
        hand.getCards()
                .add(new CardDto(CardColorEnum.COEUR, CardValueEnum.VALET));

        return hand;
    }
}