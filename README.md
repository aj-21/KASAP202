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



## Our design:
### Software:
- Our game is implemented on Greenfoot application, you can learn more about greenfoot and download the application from their website (http://www.greenfoot.org).
- To gain the most insight of our design, one needs to visit our UML diagram before proceeding to go through design patterns:

### State pattern:
- To start with, our game has 4 main greenfoot worlds, which can be seen as outermost states of the game. They are WelcomeScreen, ChooseCharacterScreen,GuessWho,
GuessWho, and ResultScreen, all of which a player needs to go through and in the order as listed.
- We also have GameState interface which is can be used as inner states. In particular, ChooseCharState, MatchingState, StartingState, GuessWhoState, and ScoreState
implement GameState, and are used in StatefulWorld classes ChooseCharacterScreen and GuessWho.
![alt tag](https://github.com/aj-21/SPAAK202/blob/master/Design%20Pattern%20and%20astah%20files/State%20pattern.png)

### Chain of Responsibility pattern (CoR):
- There are two way we use to handle a press event, check for a pressing in a greenfoot Actor, 
and check for valid pressing coordinates via CoR.
- We use CoR for WelcomeScreen for SkipIntro, Intro, or Quit button. 
CoR is also used in PressHandlerState as decorator for GameStates ChooseCharState and GuessWhoState, as well as in IDisplayCanvas class.
![alt tag](https://github.com/aj-21/SPAAK202/blob/master/Design%20Pattern%20and%20astah%20files/Chain%20of%20Responsibility%20Intro%20-%20Class%20Diagram.png)

### Decorator pattern:
- This came up when we want to extends the ability of a GameState without desire extending related classes.
We have two extensions for GameStateDecorator TimeState and PressHandlerState.
- TimeState enables a GameState to exit after a period of time, and PressHandlerState is used in combination with Chain of Responsibility pattern
to enables a state to detect press events.
![alt tag](https://github.com/aj-21/SPAAK202/blob/master/Design%20Pattern%20and%20astah%20files/Decorator%20pattern.png)

### Command pattern:
- In our main game screen GuessWho, we have three main display canvas, option button canvas, suboption button canvas, and character canvas. 
A command is invoked from within an option button (LButton class) when it is selected/deselected and it call display/undisplay on DisplayReceiver UpdSubOptRcv
to dynamically display/remove suboption buttons based on which option button is selected/deselected.
![alt tag](https://github.com/aj-21/SPAAK202/blob/master/Design%20Pattern%20and%20astah%20files/Command%20pattern.png)

### Observer pattern:
- We use Observer pattern for the ease of communication between classes. We have two classes Observable and Observer which mimic built-in java classes with the same names.
- EnableButton and IDisplayCanvas are our two main observable objects, and observers include ChooseCharState, MatchingState, ResultScreen, UniqueSelection, and ShowProperty classes.
- EnableButton and IDisplayCanvas are also two observers, so they play two roles both observable and observer.
![alt tag](https://github.com/aj-21/SPAAK202/blob/master/Design%20Pattern%20and%20astah%20files/Observer%20pattern.png)

### Other pattern:
We also implemented StringImageFactory as Factory pattern, PropertyCriteria, MatchPropertyValue, and NotMatchPropertyValue as Filter pattern,
and InstructionRepository and Iterator as Iterator pattern. 

## Server deployment:
### Prerequisite:
Knowledge of connecting to AWS using SSH and creation of EC2 instance
The docker image of the server implementation is on docker hub. To deploy and run the server implementation in AWS, follow the below steps:
### Delpoyment
1. Create a new Linux instance on AWS EC2.
2. Connect to the AWS EC2 instance using ssh.
<<<<<<< HEAD
3. Update the installed packages and package cache on your instance:
 sudo yum update -y
4. Install Docker on EC2:
 sudo yum install -y docker
5. Start the Docker service:
 sudo service docker start
6. Pull the latest image from Docker Hub:
 docker pull azhad/guesswho:1.12
7. Run the docker container:
 docker run azhad/guesswho -td -p 8080:8080 azhad/guesswho:1.12
8. Check the successful deployment using the EC2 instance public IP:
=======
3. Update the installed packages and package cache on your instance.
 sudo yum update -y
4. Install Docker on EC2
 sudo yum install -y docker
5. Start the Docker service
 sudo service docker start
6. Pull the latest image from Docker Hub
 docker pull azhad/guesswho:1.12
7. Run the docker container
 docker run azhad/guesswho -td -p 8080:8080 azhad/guesswho:1.12
8. Check the successful deployment using the EC2 instance public IP
>>>>>>> 04795ab151f58a641bf9e1d2c23cd1d4f8e0c153
 http://{Your EC2 public IP}/GuessWho:8080/

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
