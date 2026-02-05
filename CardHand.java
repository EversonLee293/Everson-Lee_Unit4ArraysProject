public class CardHand {

    private int [] listOfCards;
    private String handType;
    private int bid;

    public CardHand(int [] cardList, int bidInput) {
        this.listOfCards = cardList;
        this.handType = getHandType(cardList);
        this.bid = bidInput;
    }

    public static int [] convertHandToNumList(String [] originalHandList) {

        int [] numHandList = new int[5];

        for (int i = 0; i < originalHandList.length; i++) {
            if (originalHandList[i].length() > 1) {
                if (originalHandList[i].equalsIgnoreCase("Ace")){
                    numHandList[i] = 14;
                } else if (originalHandList[i].equalsIgnoreCase("King")) {
                    numHandList[i] = 13;
                } else if (originalHandList[i].equalsIgnoreCase("Queen")) {
                    numHandList[i] = 12;
                } else if (originalHandList[i].equalsIgnoreCase("Jack")){
                    numHandList[i] = 11;
                }
            } else {
                int numToAdd = Integer.parseInt(originalHandList[i]);
                numHandList[i] = numToAdd;
            }
        }

        return numHandList;
    }

    private String getHandType(int [] handList) {
        String type = "High Card";

        int[] cardListToInt = new int[5];

        for (int i = 0; i < handList.length; i++) {
            int matchCount = 0;
            for (int c = 0; c < handList.length; c++) {
                if (handList[i] == handList[c]) {
                    matchCount++;
                }
            }
            cardListToInt[i] = matchCount;
        }

        // below prints cardListToInt
//        String toPrint = "";
//        for (int i = 0; i < cardListToInt.length; i++) {
//            toPrint = toPrint + cardListToInt[i] + " ";
//        }
//        System.out.println(toPrint);

        int greatestNumber = 0;
        int dupeCount = 0;
        for (int i = 0; i < cardListToInt.length; i++) {
            if (cardListToInt[i] > greatestNumber) {
                greatestNumber = cardListToInt[i];
            }

            if (cardListToInt[i] == 2) {
                dupeCount++;
            }
        }

//        System.out.println(greatestNumber);
//        System.out.println(dupeCount);

        if (greatestNumber == 5) {
            type = "Five of a Kind";
        } else if (greatestNumber == 4) {
            type = "Four of a Kind";
        } else if (greatestNumber == 3 && dupeCount > 1) {
            type = "Full House";
        } else if (greatestNumber == 3) {
            type = "Three of a Kind";
        } else if (greatestNumber == 2 && dupeCount > 2) {
            type = "Two Pair";
        } else if (greatestNumber == 2) {
            type = "Pair";
        }

        return type;
    }

    public String returnType() {
        return this.handType;
    }

    public int returnBid() {
        return this.bid;
    }
}
