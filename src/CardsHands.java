import java.util.ArrayList;
import java.util.List;

public class CardsHands {

    private List<PokerHand> hands;

    public CardsHands() {
        this.hands = new ArrayList<>();
    }

    public List<PokerHand> getHands() {
        return hands;
    }

    public void setHands(PokerHand hand) {
        this.hands.add(hand);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(PokerHand pokerHand: this.getHands()) {
             result.append(pokerHand.getHand()).append("\n");
        }
        return result.toString();
    }
}
