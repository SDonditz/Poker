import jdk.jshell.execution.Util;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Utils.initializationCardValue();
        Utils.initializationComboValue();
        Utils.initializationResultValue();

        CardsHands hands = new CardsHands();

        hands.setHands(new PokerHand("JS JH KS 7S QH"));
        hands.setHands(new PokerHand("2C 3C AC 4C 5C"));
        hands.setHands(new PokerHand("KS 2H 5C JD TD"));
        hands.setHands(new PokerHand("JS JH KS 7S QH"));
        hands.setHands(new PokerHand("JS JH 2S 7S 3H"));
        hands.setHands(new PokerHand("2S 4S 6S 8S TS"));

        System.out.println(hands.getHands().get(0).compareTo(hands.getHands().get(2)));

        System.out.println("Hands : ");
        System.out.println(hands);

        Collections.sort(hands.getHands());

        System.out.println("Hands after sort : ");
        System.out.println(hands);
    }
}