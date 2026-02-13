public class CardHand {

    private int [] listOfCards = new int[5];
    private String handType;
    private int bid;
    private int rank = 1;
    private int handValue = 0;
    private int jacks = 0;



    public CardHand(int [] cardList, int bidInput) {
        for (int i = 0; i < 5; i++) {
            this.listOfCards[i] = cardList[i];
        }
        this.handType = getHandType(cardList);
        this.bid = bidInput;

        for (int i = 0; i < cardList.length; i++) {
            if (cardList[i] == 11) {
                this.jacks++;
            }
        }
    }


    public static int [] convertHandToNumList(String [] originalHandList) {

        int [] numHandList = new int[5];

        for (int i = 0; i < originalHandList.length; i++) {
            if (originalHandList[i].length() > 1 && (!(originalHandList[i].equalsIgnoreCase("10"))) ) {
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


        if (greatestNumber == 5) {
            type = "Five of a Kind";
            this.handValue = 6;
        } else if (greatestNumber == 4) {

            if (this.jacks == 1 || this.jacks == 4) {
                type = "Five of a Kind";
                this.handValue = 6;
            } else {
                type = "Four of a Kind";
                this.handValue = 5;
            }
        } else if (greatestNumber == 3 && dupeCount > 1) {

            if (this.jacks == 2 || this.jacks == 3) {
                type = "Five of a Kind";
                this.handValue = 6;
            } else if (this.jacks == 1){
                type = "Four of a Kind";
                this.handValue = 5;
            } else {
                type = "Full House";
                this.handValue = 4;
            }

        } else if (greatestNumber == 3) {

            if (this.jacks < 3 && this.jacks > 0) {
                if (this.jacks == 2) {
                    type = "Five of a Kind";
                    this.handValue = 6;
                } else {
                    type = "Four of a Kind";
                    this.handValue = 5;
                }
            } else {
                type = "Three of a Kind";
                this.handValue = 3;
            }

        } else if (greatestNumber == 2 && dupeCount > 2) {
            if (this.jacks == 1) {
                type = "Full House";
                this.handValue = 4;
            } else if (this.jacks == 2) {
                type = "Four of a Kind";
                this.handValue = 5;
            } else {
                type = "Two Pair";
                this.handValue = 2;
            }
        } else if (greatestNumber == 2) {

            if (this.jacks == 1) {
                type = "Three of a Kind";
                this.handValue = 3;
            } else {
                type = "Pair";
                this.handValue = 1;
            }
        }


        return type;
    }


    public boolean isHigherThan(CardHand other) {
        if (this.returnWeight() != other.returnWeight()) {
            return this.returnWeight() > other.returnWeight();
        }

        for (int i = 0; i < 5; i++) {

            int num1 = listOfCards[i];
            int num2 = other.listOfCards[i];

            if (num1 > num2) {
                return true;
            } else if (num1 < num2) {
                return false;
            }
        }

        return false;
    }


    public int returnWeight() {
        return this.handValue;
    }

    public String returnType() {
        return this.handType;
    }

    public int returnBid() {
        return this.bid;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int returnRank() {
        return this.rank;
    }

    public int returnJacks() {
        return this.jacks;
    }

}
