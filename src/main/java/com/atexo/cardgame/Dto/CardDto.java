package com.atexo.cardgame.Dto;

import com.atexo.cardgame.Enum.CardColorEnum;
import com.atexo.cardgame.Enum.CardValueEnum;

public class CardDto {

    /**
     * the color of the card.
     */
    private CardColorEnum color;
    /**
     * the value of the card.
     */
    private CardValueEnum value;

    /**
     * default constructor.
     */
    public CardDto() {
    }

    /**
     * constructor of a card.
     * @param color
     * @param value
     */
    public CardDto(CardColorEnum color, CardValueEnum value) {
        this.color = color;
        this.value = value;
    }

    /**
     * the getter of color.
     * @return color
     */
    public CardColorEnum getColor() {
        return color;
    }

    /**
     * the setter of color.
     * @param color
     */
    public void setColor(CardColorEnum color) {
        this.color = color;
    }

    /**
     * the getter of value.
     * @return value
     */
    public CardValueEnum getValue() {
        return value;
    }

    /**
     * the setter of value.
     * @param value
     */
    public void setValue(CardValueEnum value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card : {color:" + color + ", value:" + value + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CardDto)) {
            return false;
        }
        CardDto other = (CardDto) obj;
        boolean colorEquals = (this.color == null && other.getColor() == null)
          || (this.color != null && this.color.equals(other.getColor()));
        boolean valueEquals = (this.value == null && other.getValue() == null)
                || (this.value != null && this.value.equals(other.getValue()));
        return valueEquals && colorEquals;
    }
}
