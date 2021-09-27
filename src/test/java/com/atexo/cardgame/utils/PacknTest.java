package com.atexo.cardgame.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PacknTest {

    @Autowired
    private Packn packn;

    /**
     * verifie que le nombre de carte du packet
     * genere correspond aux nombres
     * de couleurs et nombres de valeurs.
     */
    @Test
    public void getCardsTest() {
        assertEquals(packn.getCards().size(),
                CardColorEnum.values().length * CardValueEnum.values().length);
    }

}
