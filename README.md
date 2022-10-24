# SYSC-3110-Scrabble
By Ashwitha Ala, Matthew Gaudet, Riya Rawat, Vimal Gunasegaran

## Project Description:
This is a reproduced simplified version of the classic boardgame Scrabble coded in Java. The project is part of a group evaluation for the SYSC 3110 Software Development Project course. The current implementation of the project is text-based however the ultimate goal is create a complete game with good GUI and competent AI opponents.

## How to Play:
As mentioned the current implementation of this project is completely text-based. Every word placed on the board will be printed out within the console.

Valid commands:
> "PLACE (row) (column) (letters with spaces inbetween)" - *places a word* 

> "PASS" - *passes turn*

> "DRAW (letter in hand) (letter in hand) ... " - *get new letter(s) from the bag*

> "QUIT" - *exits game*

To use a letter already on the board place parentheses around the letter you enter with the PLACE command. For example, placing BAD down from 1,1 and reusing an 'a' that is already on the board, PLACE 1 1 DOWN B (A) D

## Changelist v0.25:
Changes will be implemented in versions going up by .25, presenting each of the 4 milestones. Here are the changes for v0.25:
- Added Board.java
  - Added board method, initialises blank board.
  - Added updateBoard method, updates board with places letters.
  - Added printBoard method, prints board to text.
- Added LetterTile.java
  - Added getters for corresponding letter and score for a specific LetterTile.
  - Added createMap, initialises hashmap of letter and score to help with LetterTile construction.
- Added Parser.java
  - Added getCommand method, gets players input, checks validity and turns it into an arraylist to be returned.
  - Added commandCheck method, checks if user calls correct command.
- Added Player.java
  - Added getters and setters for player name, score, and LetterTiles.
  - Added printHand method to print players current LetterTiles.
  - Added hasLetters method that checks if player has correct LetterTiles.
- Added Scrabble.java
  - Added createLetterbag method, creates all the LetterTiles avaible in a normal game of Scarbble.
  - Added getLetterTile method to get a letter from LetterBag.
  - Added addLetterTile method, gives player a LetterTile.
  - Added scorePoints method, allocates points to player based on word.
  - Added wordCheck method, checks if word player places is an actual word.
  - Added handCheck method, checks if player has the Lettertiles to build the word.
  - Added play method, runs the game.
- Added WordBank.txt, contains all the legal words for the Scrabble game.

## Roadmap:
In the next v0.50 we plan to add a full GUI-based version of the game where users can simply play with their mouse rather then type. The board should be able to update scores and conduct word checks based on what the user has done within the GUI.
