import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Desktop;
import java.io.File;

public class CardsHands {

    private List<PokerHand> hands;

    public CardsHands() {
        this.hands = new ArrayList<>();
    }

    public List<PokerHand> getHands() {
        return hands;
    }

    public void setHands(String fileName) {
        String line;
        try {
            //FileReader pour lire le fichier qui se trouve à l'adresse en paramètre
            FileReader fileReader = new FileReader(fileName);

            //BufferReader avec Filereader en paramètre pour lire ligne par ligne dans le fichier
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Instancition d'une String ligne et lecture du fichier tant qu'elle est différente de null
            while((line = bufferedReader.readLine()) != null) {
                List<String> buffTab = new ArrayList<>();
                this.hands.add(new PokerHand(line));
            }
            //Fermeture du BufferReader
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Fichier inexistant");
        }
    }

    public void getSortedHands() throws IOException {
        Collections.sort(this.hands);
        try {
            PrintWriter writer = new PrintWriter("inputOutput/output.txt", StandardCharsets.UTF_8);
            for(PokerHand hand: this.hands) {
                writer.println(hand.getHand());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Fichier inexistant");
        }
        System.out.println("Fichier output.txt mis à jour dans le dossier inputOutput.");

        File file = new File("inputOutput/output.txt");
        if(Desktop.isDesktopSupported()) {
            if(Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'ouverture du fichier");
                }
            }
        }
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
