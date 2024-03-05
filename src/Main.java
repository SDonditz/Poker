import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Utils.initializationCardValue();
        Utils.initializationComboValue();
        Utils.initializationResultValue();

        CardsHands hands = new CardsHands();

        hands.setHands("inputOutput/input.txt");
        //hands.setHands("inputOutput/input2.txt");

        System.out.println("Hands : ");
        System.out.println(hands);

        hands.getSortedHands();
    }
}