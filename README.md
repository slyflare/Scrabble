# SYSC-3110-Scrabble
By Ashwitha Ala, Matthew Gaudet, Riya Rawat, Vimal Gunasegaran

## Project Description:
This is a reproduced simplified version of the classic boardgame Scrabble coded in Java. The project is part of a group evaluation for the SYSC 3110 Software Development Project course. The ultimate goal of the project is to create a complete game with good GUI and competent AI opponents. Changes in the code will be implemented in versions going up by .25, presenting each of the 4 milestones.

## How to Play:
The current implementation of the game has a complete GUI. To place a letter on the board, select the letter in your hand then click on the square that you wish to place it on.

BUtton commands:
> "RESET" - *reset your placements*

> "PASS" - *passes turn*

> "DRAW" - *get new letter(s) from the bag*

> "SUBMIT" - *confirm your word to be played*

> "UNDO" - *undo your previous moves*

> "REDO" - *redo your previous moves*

> "SAVE" - *save your game*

> "LOAD" - *load a previous game*

## Changelist v1.00
This is the final version of the Scrabble Game. In this version, we implemented multiple level undo/redo functions and save/load functions using Java Serialization.
We created custom boards with alternate premium square placement defined in XML format.

- AIPlayer, Board, LetterTile, Player, and Scrabble now implements Serializable
- Refactored Scrabble
  - Added setAIPlacement() method, setter function for AI placement
  - Added undo() method, undo previous plays
  - Added redo() method, redo previous plays
  - Added serialize() method, makes game serializable
  - Added load() method, loads previous game
  - Added getPlayers() method, returns players
  - New attribute playerPlacementOrder is implemented thoughout Scrabble Class; used for multiple level undo/redo
  - place() method now implements multiple level undo/redo
- Refactored ScrabbleFrame 
  - ScrabbleFrame method now has buttons and implementation for UNDO, REDO, and LOAD methods
- Refactored Board
  - Added boardToXML method, custom board is defined in XML format
  - Added premiumToXML method, alternate placement for premium tiles defined in XML format
- Refactored Player
  - Added toXML method, convert into XML format in the form of a string

## Changelist v0.75
In this version, we implemented an AI Player and updated the Scrabble Frame GUI

- Added AIPlayer
  - Extends Player
  - AI Player for the game
- Refactored ScrabbleFrame 
  - Changed Frame Displays
  - Implemented GUI for premium tiles
  - Added Blank Tiles to list of placeable tiles
- Refactored Scrabble
  - Scrabble method now takes in both num of players and num of AI players
  - Added getPreviousPlayer method, returns the previous player
  - Added getBoard method, returns the board
  - Play() method now also checks if current player is AI
- Refactored Board
  - Added getLetters method, returns an ArrayList of letters
  - Added checkPlaceable method, checks if a letter can be placed in a certain x,y coordinate on the board 
  
## Changelist v0.50:
In this version, we choose to implement the Model View Controller design pattern. With the MVC pattern, we are able to decouple and relegate different classes to manage the game logic, the GUI, and the GUI interactions with the logic. Here are the changes for v0.50:

- Replaced Parser with ScrabbleController.
  - Extends ActionListener and is responsible for controlling GUI inputs
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
    - Added Reset(), resets temporary word placements
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
