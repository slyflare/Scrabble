# SYSC-3110-Scrabble
By Ashwitha Ala, Matthew Gaudet, Riya Rawat, Vimal Gunasegaran

## Project Description:
This is a reproduced simplified version of the classic boardgame Scrabble coded in Java. The project is part of a group evaluation for the SYSC 3110 Software Development Project course. The ultimate goal of the project is to create a complete game with good GUI and competent AI opponents.

## How to Play:
The current implementation of the game has a complete GUI. To place a letter on the board, select the letter in your hand then click on the square that you wish to place it on.

BUtton commands:
> "RESET" - *reset your placements*

> "PASS" - *passes turn*

> "DRAW" - *get new letter(s) from the bag*

> "SUBMIT" - *confirm your word to be p*

## Changelist v0.50:
Changes will be implemented in versions going up by .25, presenting each of the 4 milestones. Here are the changes for v0.50:
- Design
  - Implemented Model View Control design pattern into the product.
- Replaced Parser with ScrabbleController.
  - Extends ActionListener and is responsible for controlling GUI inputs\
- Added ScrabbleFrame
  - Extends JFrame and implements ScrabbleView.
  - Is the GUI for the game.
  - implemented update method to update GUI whenever a ScrabbleEvent occurs.
- Added ScrabbleView
  - Interface with an update method.
- Added ScrabbleEvent
  - Extends EventObject
  - Responsible for keeping track of the most recent change in the model.
- Refactored Scrabble
  - Added enum for each type of command
  - split Play() into smaller functions for each command
    - Added Draw(), draw letters
    - Added Submit(), performs word check then updates job
    - Added Pass(), passes turn
    - ADded Reset(), resets temporary word placements
  - Play() now calls the split functions based on command input
  - Added necessary global variables for the functions
- Added ScrabbleTest class, responsible for the unit tests
  - pointScored() tests point system
  - checkWord() tests multiple word placements, vertical, horizontal, and parallel.
- Refactored Board
  - updateBoard() takes new parameters to reflect model refactored code.

## Changelist v0.25:
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
In the next v0.75 we plan to completely implement scoring, add special tiles, and add competent AI to play against. 
