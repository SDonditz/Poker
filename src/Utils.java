import java.util.*;

public class Utils {
    public static LinkedHashMap<String, Integer> cardValue;
    public static LinkedHashMap<ECombo, Integer> comboValue;
    public static HashMap<EResult, Integer> resultValue;

    public static void initializationCardValue() {
        Utils.cardValue = new LinkedHashMap<>();
        Utils.cardValue.put("2", 2);
        Utils.cardValue.put("3", 3);
        Utils.cardValue.put("4", 4);
        Utils.cardValue.put("5", 5);
        Utils.cardValue.put("6", 6);
        Utils.cardValue.put("7", 7);
        Utils.cardValue.put("8", 8);
        Utils.cardValue.put("9", 9);
        Utils.cardValue.put("T", 10);
        Utils.cardValue.put("J", 11);
        Utils.cardValue.put("Q", 12);
        Utils.cardValue.put("K", 13);
        Utils.cardValue.put("A", 14);
    }

    public static void initializationComboValue() {
        Utils.comboValue  = new LinkedHashMap<>();
        Utils.comboValue.put(ECombo.CARTE_HAUTE, 1);
        Utils.comboValue.put(ECombo.PAIRE, 2);
        Utils.comboValue.put(ECombo.DOUBLE_PAIRE, 3);
        Utils.comboValue.put(ECombo.BRELAN, 4);
        Utils.comboValue.put(ECombo.SUITE, 5);
        Utils.comboValue.put(ECombo.COULEUR, 6);
        Utils.comboValue.put(ECombo.FULL, 7);
        Utils.comboValue.put(ECombo.CARRE, 8);
        Utils.comboValue.put(ECombo.QUINTE_FLUSH, 9);
        Utils.comboValue.put(ECombo.QUINTE_FLUSH_ROYALE, 10);
    }

    public static void initializationResultValue() {
        Utils.resultValue = new HashMap<>();
        Utils.resultValue.put(EResult.LOSS, -1);
        Utils.resultValue.put(EResult.TIE, 0);
        Utils.resultValue.put(EResult.WIN, 1);
    }

    public static String[] turnHandToCards(PokerHand pokerHand) {
        return pokerHand.getHand().split(" ");
    }

    public static int highestValue(PokerHand pokerHand) {
        int result = 0;
        String[] cards = Utils.turnHandToCards(pokerHand);
        for (String card : cards) {
            if(cardValue.get(card.substring(0, 1))  > result) {
                result = cardValue.get(card.substring(0, 1));
            }
        }
        return result;
    }

    public static boolean isSameColor(PokerHand pokerHand) {
        boolean sameColor = true;
        String[] cards = Utils.turnHandToCards(pokerHand);
        String color = cards[0].substring(1);

        for(int i = 1; i < cards.length; i++) {
            if(!cards[i].substring(1).equals(color)) {
                sameColor = false;
                break;
            }
        }
        return sameColor;
    }

    public static boolean isPaire(PokerHand pokerHand) {
        boolean result = false;
        String[] cards = Utils.turnHandToCards(pokerHand);
        for(int i = 0; i < cards.length - 1; i++) {
            for(int j = i + 1; j < cards.length; j++) {
                if(cards[i].substring(0, 1).equals(cards[j].substring(0, 1))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isSuite(PokerHand pokerHand) {
        boolean suite = true;
        int min = 15;
        int max = 0;
        String[] cards = Utils.turnHandToCards(pokerHand);
        for (String card : cards) {
            if (cardValue.get(card.substring(0, 1)) < min) {
                min = cardValue.get(card.substring(0, 1));
            }
            if (cardValue.get(card.substring(0, 1)) > max) {
                max = cardValue.get(card.substring(0, 1));
            }
        }
        // Comme l'AS peut avoir deux valeur, on teste dans le cas où il est présent avec le 2
        if(min == Utils.cardValue.get("2") && max == Utils.cardValue.get("A")) {
            max = 0;
            for (String card : cards) {
                if(Objects.equals(cardValue.get(card.substring(0, 1)), Utils.cardValue.get("A"))) {
                    continue;
                } else {
                    if (cardValue.get(card.substring(0, 1)) < min) {
                        min = cardValue.get(card.substring(0, 1));
                    }
                    if (cardValue.get(card.substring(0, 1)) > max) {
                        max = cardValue.get(card.substring(0, 1));
                    }
                }
            }
            min = 1;
        }
        if(max - min != 4) {
            suite = false;
        }
        return suite;
    }

    public static ECombo whichSuite(PokerHand pokerHand) {
        ECombo result = ECombo.SUITE;
        if(isSameColor(pokerHand)) {
            int min = 15;
            int max = 0;
            String[] cards = Utils.turnHandToCards(pokerHand);
            for (String card : cards) {
                if (cardValue.get(card.substring(0, 1)) < min) {
                    min = cardValue.get(card.substring(0, 1));
                }
                if (cardValue.get(card.substring(0, 1)) > max) {
                    max = cardValue.get(card.substring(0, 1));
                }
            }
            if(Utils.isSameColor(pokerHand)) {
                if(min == Utils.cardValue.get("T") && max == Utils.cardValue.get("A")) {
                    result = ECombo.QUINTE_FLUSH_ROYALE;
                } else {
                    result = ECombo.QUINTE_FLUSH;
                }
            }
        }
        return result;
    }

    public static ECombo iterationCombo(PokerHand pokerHand) {
        ECombo result = ECombo.CARTE_HAUTE;
        int iteration = 1;
        int sameNumber = 1;
        String[] cards = Utils.turnHandToCards(pokerHand);

        List<Integer> values = new ArrayList<>();
        for (String card : cards) {
            values.add(Utils.cardValue.get(card.substring(0,1)));
        }

        for(int i = 0; i < values.size() - 1; i++) {
            int sameNumberTemp = 1;
            for(int j = i + 1; j < values.size(); j++) {
                if(values.get(i) == values.get(j)) {
                    iteration++;
                    sameNumberTemp++;
                    values.remove(j);
                    j--;
                }
            }
            if(sameNumberTemp > sameNumber) sameNumber = sameNumberTemp;
        }

        if(sameNumber == 4) result = ECombo.CARRE;
        if(sameNumber == 3) {
            if(iteration / sameNumber == 1 && iteration % sameNumber == 0) {
                result = ECombo.BRELAN;
            } else {
                result = ECombo.FULL;
            }
        }
        if(sameNumber == 2) {
            if(iteration / sameNumber == 1 && iteration % sameNumber == 0) {
                result = ECombo.PAIRE;
            } else {
                result = ECombo.DOUBLE_PAIRE;
            }
        }
        return result;
    }

    public static ECombo handCombo(PokerHand pokerHand) {
        ECombo result = ECombo.CARTE_HAUTE;
        if(isPaire(pokerHand)) {
            result = Utils.iterationCombo(pokerHand);
        } else if(isSuite(pokerHand)) {
                result = Utils.whichSuite(pokerHand);
        } else if (isSameColor(pokerHand)) {
            result = ECombo.COULEUR;
        }
        return result;
    }
}
