USE CASE UC1: Play Game

Scope: Chewy Lokum Legend

Level: User Goal

Primary Actor: Player

Stakeholders and Interests:
-	Player: Wants an entertaining, easy to play game with no errors. Wants cute, colorful design. 

Preconditions:  Game is successfully loaded.
 
Success Guarantee: High score for each played level is recorded. If necessary, game state is saved.

Main Success Scenario:
1.	Player starts the game.
2.	System returns level list.
3.	Player selects a level.
4.	The game board is loaded according to the selected level.
5.	Player swaps position of two adjacent lokums horizontally, vertically, or diagonally.
6.	Group of three or more lokums of the same color are deleted, score is updated, and if a time lokum is deleted time will also be updated.
7.	The lokums above the deleted lokums move down and spaces at the top is filled with new random lokums.
Steps 5-6 are repeated until there are no groups of three or more lokums and there is still time, if the level is a time based level.
(After first iteration, the score is increased by 2x points gained in the iteration).
Player repeats steps 4 until the goal of the level is reached.
8.	n x individual rows or columns where “n” is the number of extra moves are deleted automatically.
9.	Next level is loaded.
Player continues with step 4.

Extensions:
*a. At any time, player quits the game.
1.	System saves the game state.
2.	System exits.
*b. At any time, player goes back to main menu.
1.	System saves the game state.
2.	System returns the main menu.
*c. At any time, system fails.
1.	System tries to save the game state.
2.	System restarts.
3a. Selected level is locked. 
1. System asks for selecting a different level.
2. System continues with step 2.
5a. There are no remaining moves.
1.	Player is given two choices: restarting the same level or returning to main menu.
5b. Swapped lokums are both special lokums.
1.	System identifies lokums.
1a. One is striped lokum and one is color bomb.
1.	System changes all lokums with the same color of the striped lokum to striped lokums (random direction).
1b. One is wrapped lokum and one is striped lokum.
1.	System deletes 3 horizontal and 3 vertical lines including swapped special lokums’ rows and columns.
1c. Both are striped lokums.
1.	System deletes 1 horizontal and 1 vertical lines including swapped special lokums.
1d. Both are wrapped lokums.
1.	System increases the score by 3600 points.
1e. One is wrapped lokum and one is color bomb.
1.	System deletes all lokums with the same color as the wrapped lokum and another random color.
1f. Both are color bombs.
1.	System increases the score by n^2 x 100 points where “n” is the number of lokums on the entire board.
5c. One of the swapped lokums is color bomb.
1.	All lokums with the same color as the colored swapped lokum are deleted and score is increased by n^2 x 60 points, where “n” is the number of lokums which are deleted.
6a. Swapping lokums cannot generate any groups of three or more lokums.
1.	System checks special swap count.
1a. Special swap count is greater than zero.
1.	Lokums are swapped.
1b. Special swap count is zero.
1.	Lokums are returned to their old positions.
2.	System continues with the step 4.
6b. At least one of the deleted lokums is special lokum.
1.	System identifies special lokum.
1a. At least one lokum is striped lokum.
1.	System deletes the rows of striped lokums if it is horizontally striped, and deletes the columns of the striped lokums if it is vertically striped. The score is increased by n x 60 points, where “n” is the number of lokums cleared.
1b. At least one lokum is wrapped lokum.
1.	System increases the score by 1080 points for each wrapped lokum.
6c. Group consists of four or more lokums.
1.	System identifies which special lokum to create.
1a. Group consists of five lokums in a row.
1.	Color bomb is created in the middle of the group and score is increased by 200 points.
	      	1b. Group consists of four lokums.
1.	Striped lokum is created and score is increased by 120 points.
     		 1c. Group consists of five lokums not in a row.
1.	Wrapped lokum is created and score is increased by 200 points.
6d. There are two groups consisting of three or more lokums.
1.	System calculates scores of both groups individually and score is increased by 4 x sum of scores.


UML Use Case Diagram

 







Domain Model
 
 
     
OPERATION CONTRACTS 


Contract CO1: start

Operation:		start()
Cross References:	Use Cases: Play Game
Preconditions:		
Game is successfully loaded. 
Postconditions:	
-	Unlocked and locked levels are shown.


Contract CO2: selectLevel

Operation:		selectLevel(Level: level)
Cross References:	Use Cases: Play Game
Preconditions:		
	Selection window is loaded. 
Postconditions:	
-	A gameBoard instance gb was created according to level.levelOptions.
-	GameState instance gs.score became zero.
-	Gs.remainingMoves became level.movesCount.
-	Gb was displayed to the player.


Contract CO3: swap

Operation:		swap(Lokum: l1, Lokum: l2)
Cross References:	Use Cases: Play Game
Preconditions:
	Game is successfully loaded. 		
	The player selected a level.
	GameBoard is created. 
Postconditions:
-	GameState instance gs.score was updated according to scoringPolicy.
-	Gs.remainingMoves was decreased by 1.
-	GameBoard was updated.
