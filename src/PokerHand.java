public class PokerHand implements Comparable<PokerHand> {

    private String hand;

    public PokerHand(String hand) {
        this.hand = hand;
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

    @Override
    public int compareTo(PokerHand o) {
        return Utils.resultValue.get(this.compareWith(o));
    }
}
