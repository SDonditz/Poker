import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class PokerHand implements Comparable<PokerHand> {

    private String hand;
    private List<PokerHand> hands;


    public PokerHand(String hand) {
        this.hand = hand;
        this.hands = new ArrayList<>();
        this.hands.add(this);
    }

    public EResult compareWith(PokerHand hand) {
        EResult result;

        if(Utils.comboValue.get(Utils.handCombo(this)) > Utils.comboValue.get(Utils.handCombo(hand))) {
            result = EResult.WIN;
        } else if (Utils.comboValue.get(Utils.handCombo(this)) < Utils.comboValue.get(Utils.handCombo(hand))) {
            result = EResult.LOSS;
        } else {
            result = EResult.TIE;
        }

        if(result == EResult.TIE) {
            if(Utils.highestValue(this) > Utils.highestValue(hand)) {
                result = EResult.WIN;
            } else if (Utils.highestValue(this) < Utils.highestValue(hand)) {
                result = EResult.LOSS;
            }
        }
        return result;
    }

    public String getHand() {
        return hand;
    }

    public List<PokerHand> getHands() {
        return hands;
    }

    public void setHands(PokerHand hand) {
        if(this.hands.size() < 2 && Objects.equals(hand.getHand(), "")) {
            this.hands.add(new PokerHand("JS JH KS 7S QH"));
            this.hands.add(new PokerHand("2C 3C AC 4C 5C"));
            this.hands.add(new PokerHand("KS 2H 5C JD TD"));
            this.hands.add(new PokerHand("JS JH KS 7S QH"));
            this.hands.add(new PokerHand("JS JH 2S 7S 3H"));
        }
        this.hands.add(hand);
    }

    @Override
    public int compareTo(PokerHand o) {
        return Utils.resultValue.get(this.compareWith(o));
    }
}
