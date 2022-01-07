
HANGMAN GAME CODE
====================


1) INTRODUCTION 
_________________

Hangman is a popular word guessing game where the player attempts to build a missing word by guessing one letter at a time. After a certain number of incorrect guesses, the game ends and the player loses. The game also ends if the player correctly identifies all the letters of the missing word.





2) RULES OF THE GAME 
______________________

=> Random word is selected from a list of words using API https://random-word-api.herokuapp.com/word?number=10

=> The user will get only 5 lives to play the game

=> Initially the user is shown “_” (underscore) in the console for each letter in the word. For example, if the word is “banana”, user will see “_ _ _ _ _ _”
For every correct letter guess, replace the “_” (underscore) with the letter. For example, if user guessed “n”, user will see “_ _ n _ n _”

=> If the user guesses the same letter again, don’t do anything and continue with the game. For example, if the user guessed “n” again, show “_ _ n _ n _” and continue.

=> For every incorrect guess, reduce the number of lives by 1

=>The game ends when the user guesses the word correctly or there are no lives remaining.





3) LANGUAGE USED
_____________________________

The game is build in java language.



4) FLOW OF THE PROGRAM
_____________________________

=> first welcome message is displayed and the rules are displayed.

=> then the blank spaces are shown and the user is told to input his choice.

=> the game continues till the chances are over or the player wins.

=> user is asked to enter yes to continue playing or any other string to exit the game. 


5) TEST CASES CHECKED 
_____________________________

=> case sensitivity of input. small alphabets and capital alphabets are treated same i.e  'a' is same as 'A'.

=> characters other than alphabets are treated as wrong input.

=> input with length more than 1 is also a wring input and reduces the life of the player.

=> if the user inputs already entered character that is present in the word it prints the message that that the character is already enter. 

6) DEMO SCREENSHOTS
_____________________________


