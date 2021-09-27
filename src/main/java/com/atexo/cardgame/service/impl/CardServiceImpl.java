package com.atexo.cardgame.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atexo.cardgame.Dto.CardDto;
import com.atexo.cardgame.Dto.HandDto;
import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;
import com.atexo.cardgame.Enum.IBaseEnum;
import com.atexo.cardgame.service.CardService;
import com.atexo.cardgame.utils.Packn;

@Service
public class CardServiceImpl implements CardService {

    /**
     * injection du packet des cartes.
     */
    @Autowired
    private Packn packn;

    @Override
    public HandDto getHand() {
        List<CardDto> packnCopie = new ArrayList<CardDto>(packn.getCards());
        Random rand = new Random();
        HandDto hand = new HandDto();
        IntStream.rangeClosed(1, HAND_CARD_NUMBER).forEach(cardIndex -> {
            int randomIndex = rand.nextInt(packnCopie.size());
            CardDto c = packnCopie.get(randomIndex);
            hand.getCards().add(c);
            packnCopie.remove(c);
        });
        return hand;
    }

    @Override
    public HandDto getSortedHand(List<CardColorEnum> sortColorList,
            List<CardValueEnum> sortValueList, HandDto hand) {
        Comparator<CardDto> cardDtoComparator = (c1,
                c2) -> c1.getColor().equals(c2.getColor())
                        ? sortValueList.indexOf(c1.getValue())
                                - sortValueList.indexOf(c2.getValue())
                        : sortColorList.indexOf(c1.getColor())
                                - sortColorList.indexOf(c2.getColor());
        hand.getCards().sort(cardDtoComparator);
        return hand;
    }

    @Override
    public List<CardColorEnum> getRamdomColorOrder() {
        List<IBaseEnum> randomIBaseEnumList = getRamdomListForEnumeration(
                new ArrayList<IBaseEnum>(Arrays.asList(CardColorEnum.values())));
        List<CardColorEnum> randomColorCardList = new ArrayList<CardColorEnum>();
        randomIBaseEnumList.forEach(colorCard -> randomColorCardList
                .add((CardColorEnum) colorCard));
        return randomColorCardList;
    }

    @Override
    public List<CardValueEnum> getRamdomValueOrder() {
        List<IBaseEnum> randomIBaseEnumList = getRamdomListForEnumeration(
                new ArrayList<IBaseEnum>(Arrays.asList(CardValueEnum.values())));
        List<CardValueEnum> randomCardValueList = new ArrayList<CardValueEnum>();
        randomIBaseEnumList.forEach(cardValue -> randomCardValueList
                .add((CardValueEnum) cardValue));
        return randomCardValueList;
    }

    private List<IBaseEnum> getRamdomListForEnumeration(
            List<IBaseEnum> enumList) {
        Random rand = new Random();
        List<IBaseEnum> copieOfEnumList = new ArrayList<IBaseEnum>(enumList);
        List<IBaseEnum> randomList = new ArrayList<IBaseEnum>();
        IntStream.rangeClosed(1, enumList.size()).forEach(index -> {
            int randomIndex = rand.nextInt(copieOfEnumList.size());
            IBaseEnum baseEnum = copieOfEnumList.get(randomIndex);
            randomList.add(baseEnum);
            copieOfEnumList.remove(baseEnum);
        });
        return randomList;
    }

}
