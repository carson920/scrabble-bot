public class Board {
    private static PremiumSquare[][] premiumSquares = new PremiumSquare[15][15];

    static {
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                premiumSquares[i][j] = PremiumSquare.N;
            }
        }
        premiumSquares[0][0] = PremiumSquare.TW;
        premiumSquares[0][7] = PremiumSquare.TW;
        premiumSquares[0][14] = PremiumSquare.TW;
        premiumSquares[7][0] = PremiumSquare.TW;
        premiumSquares[7][14] = PremiumSquare.TW;
        premiumSquares[14][0] = PremiumSquare.TW;
        premiumSquares[14][7] = PremiumSquare.TW;
        premiumSquares[14][14] = PremiumSquare.TW;
        premiumSquares[0][3] = PremiumSquare.DL;
        premiumSquares[0][11] = PremiumSquare.DL;
        premiumSquares[2][6] = PremiumSquare.DL;
        premiumSquares[2][8] = PremiumSquare.DL;
        premiumSquares[3][0] = PremiumSquare.DL;
        premiumSquares[3][7] = PremiumSquare.DL;
        premiumSquares[3][14] = PremiumSquare.DL;
        premiumSquares[6][2] = PremiumSquare.DL;
        premiumSquares[6][6] = PremiumSquare.DL;
        premiumSquares[6][8] = PremiumSquare.DL;
        premiumSquares[6][12] = PremiumSquare.DL;
        premiumSquares[7][3] = PremiumSquare.DL;
        premiumSquares[7][11] = PremiumSquare.DL;
        premiumSquares[8][2] = PremiumSquare.DL;
        premiumSquares[8][6] = PremiumSquare.DL;
        premiumSquares[8][8] = PremiumSquare.DL;
        premiumSquares[8][12] = PremiumSquare.DL;
        premiumSquares[11][0] = PremiumSquare.DL;
        premiumSquares[11][7] = PremiumSquare.DL;
        premiumSquares[11][14] = PremiumSquare.DL;
        premiumSquares[12][6] = PremiumSquare.DL;
        premiumSquares[12][8] = PremiumSquare.DL;
        premiumSquares[14][3] = PremiumSquare.DL;
        premiumSquares[14][11] = PremiumSquare.DL;
        premiumSquares[1][1] = PremiumSquare.DW;
        premiumSquares[1][13] = PremiumSquare.DW;
        premiumSquares[2][2] = PremiumSquare.DW;
        premiumSquares[2][12] = PremiumSquare.DW;
        premiumSquares[3][3] = PremiumSquare.DW;
        premiumSquares[3][11] = PremiumSquare.DW;
        premiumSquares[4][4] = PremiumSquare.DW;
        premiumSquares[4][10] = PremiumSquare.DW;
        premiumSquares[7][7] = PremiumSquare.DW;
        premiumSquares[10][4] = PremiumSquare.DW;
        premiumSquares[10][10] = PremiumSquare.DW;
        premiumSquares[11][3] = PremiumSquare.DW;
        premiumSquares[11][11] = PremiumSquare.DW;
        premiumSquares[12][2] = PremiumSquare.DW;
        premiumSquares[12][12] = PremiumSquare.DW;
        premiumSquares[13][1] = PremiumSquare.DW;
        premiumSquares[13][13] = PremiumSquare.DW;
        premiumSquares[1][5] = PremiumSquare.TL;
        premiumSquares[1][9] = PremiumSquare.TL;
        premiumSquares[5][1] = PremiumSquare.TL;
        premiumSquares[5][5] = PremiumSquare.TL;
        premiumSquares[5][9] = PremiumSquare.TL;
        premiumSquares[5][13] = PremiumSquare.TL;
        premiumSquares[9][1] = PremiumSquare.TL;
        premiumSquares[9][5] = PremiumSquare.TL;
        premiumSquares[9][9] = PremiumSquare.TL;
        premiumSquares[9][13] = PremiumSquare.TL;
        premiumSquares[13][5] = PremiumSquare.TL;
        premiumSquares[13][9] = PremiumSquare.TL;
    }
    public static PremiumSquare[][] getPremiumSquares() {
        return premiumSquares;
    }

    public static void setPremiumSquares(PremiumSquare[][] premiumSquares) {
        Board.premiumSquares = premiumSquares;
    }

}
