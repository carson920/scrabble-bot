public enum PremiumSqaure {
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

    PremiumSqaure(int letterMultiply, int wordMultiply) {
        this.letterMultiply = letterMultiply;
        this.wordMultiply = wordMultiply;
    }
}
