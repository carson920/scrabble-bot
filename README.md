# scrabble-bot

Find the highest scoring play with a given Scrabble board and rack.

Just play 1 game:

    java -jar scrabble-bot.jar

Play more than 1 game (e.g. 10):

    java -jar scrabble-bot.jar 10

To direct console output to a file, specify a file name:

    java -jar scrabble-bot.jar 10 output.txt

The jar file can also be deployed as AWS lambda function.
It takes the following sample JSON as HTTP request, where "board" is a 15x15 array of array of characters, and "rack" is a string of tiles of player's rack with ? representing blanks.

{"board":[[" "," "," "," "," ","F"," ","K","O"," "," ","F","A","I","R"],[" "," "," "," "," ","L"," ","O","X","Y"," ","O","P","T"," "],[" "," "," "," "," ","O","B","A"," "," ","E","R","E","S"," "],[" "," "," "," "," ","C","A","N"," ","C","A","A","S"," ","D"],[" "," "," "," "," "," ","G"," "," "," ","N"," "," ","P","I"],[" "," "," "," "," ","U","G"," "," ","Q","I"," "," ","A","R"],[" "," "," "," "," "," ","I"," ","D","I","N"," ","Z","I","T"],[" "," "," "," "," "," ","E","M","O","N","G"," ","O","N","Y"],[" "," "," "," "," "," ","s","O","M"," "," ","L","E","T"," "],[" "," "," "," "," "," "," ","U"," "," "," "," ","A","E"," "],[" "," "," "," "," ","V","I","t","A","L","I","S","E","R"," "],[" "," "," ","L"," "," "," ","H"," "," "," "," "," "," "," "],[" "," "," ","U"," "," ","W","E","B","S","T","E","R"," "," "],[" "," ","V","A","W","T","E","D"," ","H","O","N","E","R"," "],[" "," "," ","U"," "," ","E"," "," "," "," ","D","I","E","T"]],"rack":"ABCDE??"}



Sample output (extracted):

    Current Player: Player 1
    Rack to play: [A, A, A, I, N, P, R]
    Top 10 moves: [D5 PLANARIA 72, 5A PIRANA 29, 5C NAPA 26, 5C RAP 22, 5C NAP 22, D1 PARIAL 22, 7A PIRANA 21, 5C PAN 20, 5H WARP 20, 5G AWARN 20]
    Played Tiles: [P, A, N, A, R, I, A]
    Chosen turn: [D5 PLANARIA 72] with rack: [A, A, A, I, N, P, R]
    Current total score of Player 1 is 92
    Rack before replenishment: []
    Rack after replenishment: [E, E, N, O, O, O, V]
    No. of tiles in bag: 68
    Current board:
       A.B.C.D.E.F.G.H.I.J.K.L.M.N.O
      _______________________________
     1| . . . . . . . . . . . . . . |
     2| . . . . . . . . . . . . . . |
     3| . . . . . . . . . . . . . . |
     4| . . . . . . . . . . . . . . |
     5| . . .P. . . .W. . . . . . . |
     6| .D.E.L.A.T.I.O.N. . . . . . |
     7| . . .A. . . .O. . . . . . . |
     8| . . .N. . . .F. . . . . . . |
     9| . . .A. . . . . . . . . . . |
    10| . . .R. . . . . . . . . . . |
    11| . . .I. . . . . . . . . . . |
    12| . . .A. . . . . . . . . . . |
    13| . . . . . . . . . . . . . . |
    14| . . . . . . . . . . . . . . |
    15| . . . . . . . . . . . . . . |
      -------------------------------
    Current Player: Player 2
    Rack to play: [D, E, E, H, I, L, N]
    Top 10 moves: [12B HEADLINE 63, C9 HELED 41, E4 HEALED 35, E8 EHED 31, 8A HENNED 30, C6 EHED 30, E9 HEND 30, E4 NEALED 29, E4 LEADEN 29, E4 LEANED 29]
    Played Tiles: [H, E, D, L, I, N, E]
    Chosen turn: [12B HEADLINE 63] with rack: [D, E, E, H, I, L, N]
    Current total score of Player 2 is 128
    Rack before replenishment: []
    Rack after replenishment: [G, M, O, R, S, S, Y]
    No. of tiles in bag: 61
    Current board:
       A.B.C.D.E.F.G.H.I.J.K.L.M.N.O
      _______________________________
     1| . . . . . . . . . . . . . . |
     2| . . . . . . . . . . . . . . |
     3| . . . . . . . . . . . . . . |
     4| . . . . . . . . . . . . . . |
     5| . . .P. . . .W. . . . . . . |
     6| .D.E.L.A.T.I.O.N. . . . . . |
     7| . . .A. . . .O. . . . . . . |
     8| . . .N. . . .F. . . . . . . |
     9| . . .A. . . . . . . . . . . |
    10| . . .R. . . . . . . . . . . |
    11| . . .I. . . . . . . . . . . |
    12| .H.E.A.D.L.I.N.E. . . . . . |
    13| . . . . . . . . . . . . . . |
    14| . . . . . . . . . . . . . . |
    15| . . . . . . . . . . . . . . |
      -------------------------------
