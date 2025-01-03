# scrabble-bot

[2024-12-24] Added support that jar file can be deployed as AWS Lambda function.

Find the highest scoring play with a given Scrabble board and rack.

Just play 1 game:

    java -jar scrabble-bot.jar

Play more than 1 game (e.g. 10):

    java -jar scrabble-bot.jar 10

To direct console output to a file, specify a file name:

    java -jar scrabble-bot.jar 10 output.txt

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

The jar file can also be deployed to AWS Lambda.
It can take the following sample JSON as HTTP request, where "board" is a 15x15 array of array of characters, and "rack" is a string of tiles of player's rack with underscores representing blanks.

    {"board":[[" "," "," "," "," "," "," ","t"," "," "," ","B","U","t","T"],[" "," "," "," "," "," "," ","R"," ","V"," "," "," ","A"," "],[" "," "," "," "," "," ","K","I","N","E","T","I","C","S"," "],[" "," "," "," ","G"," "," ","T"," ","G"," "," "," ","T"," "],[" "," ","F","O","Y"," "," ","O"," "," "," "," "," ","I"," "],[" "," ","O","U","P"," "," ","N"," "," ","B","R","I","E"," "],[" "," ","X","I"," ","M","Z","E","E"," "," "," "," ","R"," "],[" "," "," ","J","I","A","O","S"," "," "," "," "," "," "," "],[" "," ","D","A"," ","E","L"," "," "," "," "," "," "," "," "],[" ","Y","O"," "," "," "," "," "," ","H","O","L","D"," "," "],[" "," ","W"," ","W"," "," ","P","I","A","N","O"," "," "," "],["C","I","N","E","A","S","T","E"," "," "," "," "," "," "," "],["H"," ","E"," ","Q"," "," "," "," "," "," ","E","G","A","L"],["I"," ","D"," ","F"," "," ","O","R","N","A","T","E","R"," "],["V"," "," "," ","S","A","L","U","E"," ","N","A","M","E","D"]],
    "rack":"ABCDE__"}


