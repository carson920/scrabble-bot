public class Board {
    private static PremiumSqaure[][] premiumSquares = new PremiumSqaure[15][15];

    static {
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                premiumSquares[i][j] = PremiumSqaure.N;
            }
        }
        premiumSquares[0][0] = PremiumSqaure.TW;
        premiumSquares[0][7] = PremiumSqaure.TW;
        premiumSquares[0][14] = PremiumSqaure.TW;
        premiumSquares[7][0] = PremiumSqaure.TW;
        premiumSquares[7][14] = PremiumSqaure.TW;
        premiumSquares[14][0] = PremiumSqaure.TW;
        premiumSquares[14][7] = PremiumSqaure.TW;
        premiumSquares[14][14] = PremiumSqaure.TW;
        premiumSquares[0][3] = PremiumSqaure.DL;
        premiumSquares[0][11] = PremiumSqaure.DL;
        premiumSquares[2][6] = PremiumSqaure.DL;
        premiumSquares[2][8] = PremiumSqaure.DL;
        premiumSquares[3][0] = PremiumSqaure.DL;
        premiumSquares[3][7] = PremiumSqaure.DL;
        premiumSquares[3][14] = PremiumSqaure.DL;
        premiumSquares[6][2] = PremiumSqaure.DL;
        premiumSquares[6][6] = PremiumSqaure.DL;
        premiumSquares[6][8] = PremiumSqaure.DL;
        premiumSquares[6][12] = PremiumSqaure.DL;
        premiumSquares[7][3] = PremiumSqaure.DL;
        premiumSquares[7][11] = PremiumSqaure.DL;
        premiumSquares[8][2] = PremiumSqaure.DL;
        premiumSquares[8][6] = PremiumSqaure.DL;
        premiumSquares[8][8] = PremiumSqaure.DL;
        premiumSquares[8][12] = PremiumSqaure.DL;
        premiumSquares[11][0] = PremiumSqaure.DL;
        premiumSquares[11][7] = PremiumSqaure.DL;
        premiumSquares[11][14] = PremiumSqaure.DL;
        premiumSquares[12][6] = PremiumSqaure.DL;
        premiumSquares[12][8] = PremiumSqaure.DL;
        premiumSquares[14][3] = PremiumSqaure.DL;
        premiumSquares[14][11] = PremiumSqaure.DL;
        premiumSquares[1][1] = PremiumSqaure.DW;
        premiumSquares[1][13] = PremiumSqaure.DW;
        premiumSquares[2][2] = PremiumSqaure.DW;
        premiumSquares[2][12] = PremiumSqaure.DW;
        premiumSquares[3][3] = PremiumSqaure.DW;
        premiumSquares[3][11] = PremiumSqaure.DW;
        premiumSquares[4][4] = PremiumSqaure.DW;
        premiumSquares[4][10] = PremiumSqaure.DW;
        premiumSquares[7][7] = PremiumSqaure.DW;
        premiumSquares[10][4] = PremiumSqaure.DW;
        premiumSquares[10][10] = PremiumSqaure.DW;
        premiumSquares[11][3] = PremiumSqaure.DW;
        premiumSquares[11][11] = PremiumSqaure.DW;
        premiumSquares[12][2] = PremiumSqaure.DW;
        premiumSquares[12][12] = PremiumSqaure.DW;
        premiumSquares[13][1] = PremiumSqaure.DW;
        premiumSquares[13][13] = PremiumSqaure.DW;
        premiumSquares[1][5] = PremiumSqaure.TL;
        premiumSquares[1][9] = PremiumSqaure.TL;
        premiumSquares[5][1] = PremiumSqaure.TL;
        premiumSquares[5][5] = PremiumSqaure.TL;
        premiumSquares[5][9] = PremiumSqaure.TL;
        premiumSquares[5][13] = PremiumSqaure.TL;
        premiumSquares[9][1] = PremiumSqaure.TL;
        premiumSquares[9][5] = PremiumSqaure.TL;
        premiumSquares[9][9] = PremiumSqaure.TL;
        premiumSquares[9][13] = PremiumSqaure.TL;
        premiumSquares[13][5] = PremiumSqaure.TL;
        premiumSquares[13][9] = PremiumSqaure.TL;
    }
    public static PremiumSqaure[][] getPremiumSquares() {
        return premiumSquares;
    }

    public static void setPremiumSquares(PremiumSqaure[][] premiumSquares) {
        Board.premiumSquares = premiumSquares;
    }

}
