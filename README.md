# GUESSWHO

## Overview

### SPAAK Team
 Our group repository for our CMPE 202 project.
 Members:
 Member 1: Anthony Bell
 Member 2: Prachi Hada
 Member 3: Khoa Le
 Member 4: Azhad Mahmood
 Member 5: Srinivas Annapantula

### Our mission:
 While building this game, our team always keep in mind three main missions:
 1. To learn OOP from another view - design level; to use UML to present and facilitate the process of designing;
and to apply different design pattern in Java.
 2. To nurture computer science knowledge and thoughtful analysis in children by choosing an algorithm
taken from csunflugged.org and create an attractive yet simple-to-deliver game based on that.
 3. To help peers without computer science background understand how useful a certain algorithm is
and how to apply it in real life.

### Divide and conquer algorithm:
Divide and conquer is an alternative efficient way to a naive method to solve a problem.
It works by recursively breaking down the problem into two or more sub-problems of the same or related type,
until these become simple enough to be solved directly. 

### GuessWho game:
Similarly, the purpose of the Guess Who game is to be able to determine one’s opponent’s character
by eliminating other candidates in chunks each turn. There are many strategies to determining the opponent's character.
Another strategy would be to individually guess their character each turn, but that wouldn’t be efficient
because at the start the player would have a 1 and 9 chance of guessing the correct character,
then 1 and 8 the next turn and so on. So why is using the divide and conquer algorithm the optimal solution
to solving this problem? The game starts out with all the characters displayed,
however if a player is able to use the suboptions efficiently and effectively,
they may reduce the characters displayed by as much as half.
Reducing the amount of characters display each turn creates a smaller subproblem, and so on,
until the player is able to accurately guess their opponent’s character.

## Future Development:
1. Our project is an oppotunity for us to learn and gain more insight of Object Oriented Programming.
and Design Pattern in Java. As a result, the project has multiple success implementation as well as many experiments.
Future developers can enrich the project with better pattern which we had not used due to time limitation as well as
with more featues and animation.
2. Current game only allows two players in one match, this can be changed to allow more than just two.
However, this needs both changes in client and server sides.
3. In game, a player has virtually no real interaction with the other.
Game clients will save and exchange state of one another through a server, and decide what to do next.
This can cause boredom after try the game for a couple of time.
Future developer can improve this by better design allowing real interaction of ask and answer.

## Design:
 Our game is implemented on Greenfoot application, you can visit and download the application from their website (http://www.greenfoot.org).
   

## Server deployment:







