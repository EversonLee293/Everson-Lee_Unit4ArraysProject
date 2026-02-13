import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //below is testing of methods

        String [] testHand = {"4", "10", "Jack", "Jack", "10"};
        int[] intList = CardHand.convertHandToNumList(testHand);

        CardHand testhand2 = new CardHand(intList, 0);

        System.out.println(testhand2.returnType());

        System.out.println("---------------------");


        //below is counts for hands
        int fiveOfAKind = 0;
        int fourOfAKind = 0;
        int fullHouse = 0;
        int threeOfAKind = 0;
        int twoPair = 0;
        int pair = 0;
        int highCard = 0;

        int totalBidValues = 0;

        //below is line reader
        int numLinesFile = 0;
        String fileData = "";
        try {
            File f = new File("src/data");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData += line + "\n";
                numLinesFile++;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println(numLinesFile);
        //System.out.println(fileData);
        String[] lines = fileData.split("\n");

        CardHand [] arrayOfObjects = new CardHand[numLinesFile];

        int currentLine = 0;

        for (String line : lines) {
            currentLine += 1;

            String[] numbers = line.split(",");

            String bid = numbers[4].substring(numbers[4].indexOf("|") + 1);

            numbers[4] = numbers[4].substring(0, numbers[4].indexOf("|"));


            int[] cardIntList = CardHand.convertHandToNumList(numbers);
            int bidToInt = Integer.parseInt(bid);


            CardHand cardHandCurrent = new CardHand(cardIntList, bidToInt);

            if (cardHandCurrent.returnType().equalsIgnoreCase("Five Of A Kind")) {
                fiveOfAKind++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("Four Of A Kind")) {
                fourOfAKind++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("Full House")) {
                fullHouse++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("Three Of A Kind")) {
                threeOfAKind++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("Two Pair")) {
                twoPair++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("Pair")) {
                pair++;
            } else if (cardHandCurrent.returnType().equalsIgnoreCase("High Card")) {
                highCard++;
            } else {
                System.out.println("Error when retrieving object CardHand Type.");
            }

            arrayOfObjects[currentLine - 1] = cardHandCurrent;
            //System.out.println(cardHandCurrent.returnType());

        }

        System.out.println("Number of Five Of A Kind hands : " + fiveOfAKind);
        System.out.println("Number of Four Of A Kind hands : " + fourOfAKind);
        System.out.println("Number of Full House hands : " + fullHouse);
        System.out.println("Number of Three Of A Kind hands : " + threeOfAKind);
        System.out.println("Number of Two Pair hands : " + twoPair);
        System.out.println("Number of Pair hands : " + pair);
        System.out.println("Number of High Card hands : " + highCard);
        System.out.println("-----------------------");



        for (int a = 0; a < arrayOfObjects.length; a++){
            //arrayOfObjects[a].setRank(1);
            for (int c = 0 ; c < arrayOfObjects.length; c++) {
                    if (!(c==a)) {
                        if (arrayOfObjects[a].isHigherThan(arrayOfObjects[c])) {
                            arrayOfObjects[a].setRank(arrayOfObjects[a].returnRank() + 1);
                        }
                    }
                }
        }
//        for (int i = 0; i < numLinesFile; i++) {
//            int rank = 1;
//
//            for (int j = 0; j < numLinesFile; j++) {
//                if (arrayOfObjects[i].isHigherThan(arrayOfObjects[j])) {
//                    rank++;
//                }
//            }
//            arrayOfObjects[i].setRank(rank);
//        }

        for(int i = 0; i < arrayOfObjects.length; i++) {
            totalBidValues += arrayOfObjects[i].returnBid() * arrayOfObjects[i].returnRank();
            System.out.print("Bid : ");
            System.out.print(arrayOfObjects[i].returnBid());
            String toPrint = "";
//            for (int g = 0; g < arrayOfObjects[g].getListOfCards().length; g++) {
//                toPrint = toPrint + arrayOfObjects[g].getListOfCards() + " ";
//            }
            System.out.print(toPrint);
            System.out.print(", Rank ");
            System.out.println(arrayOfObjects[i].returnRank());
        }

        System.out.println("----------------");
        System.out.println("Total Bid (part 2) : " + totalBidValues);

    }
}