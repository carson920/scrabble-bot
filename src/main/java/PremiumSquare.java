public enum PremiumSquare {

    //Triple Word Square
    TW (1,3),

    //Double Word Square
    DW (1,2),

    //Triple Letter Square
    TL (3,1),

    //Double Letter Square (including centre star)
    DL (2,1),

    //Normal Square
    N (1,1);

    public int getLetterMultiply() {
        return letterMultiply;
    }

    public int getWordMultiply() {
        return wordMultiply;
    }

    private final int letterMultiply;
    private final int wordMultiply;

    PremiumSquare(int letterMultiply, int wordMultiply) {
        this.letterMultiply = letterMultiply;
        this.wordMultiply = wordMultiply;
    }
}
