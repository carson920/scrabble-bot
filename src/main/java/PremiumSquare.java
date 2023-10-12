public enum PremiumSquare {
    TW (1,3),
    DW (1,2),
    TL (3,1),
    DL (2,1),
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
