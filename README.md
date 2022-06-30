# Eriantys Board Game

<img src="https://rubik.chegiochi.it/GIO/420/291/8034055582916.jpg" width=192px height=192 px align="right" />

Eriantys Board Game is the final test of **"Software Engineering"**, course of **"Computer Science and Engineering"** held at Polytechnic University of Milan (2021/2022).

**Teacher** San Pietro Pierluigi

## The Team
* [Elia Lazzeri](https://github.com/elialaz) (10716571)
* [Filiberto Ingrosso](https://github.com/filibertoingrosso) (10682019)
* [Litovchenko Nikita](https://github.com/litovn) (10656602)

## Project specification
The project consists of a Java version of the board game *Eriantys*, made by Cranio Creations.

You can find the full game rules [here](https://www.craniocreations.it/wp-content/uploads/2021/11/Eriantys_ITA_bassa.pdf).

The final version includes:
* Initial UML diagram;
* Final UML diagram, generated from the code by automated tools;
* Working game implementation, which has to be rules compliant;
* Source code of the implementation;
* Source code of unity tests.

## Implemented Functionalities
| Functionality |                                                                                                                                          Status                                                                                                                                           |
|:-----------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Basic rules |                                                                                  [✅](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Model)                                                                                   |
| Complete rules |                                                                                  [✅](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Model)                                                                                   |
| Socket |                                                                                  [✅](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Server)                                                                                  |
| GUI |                                                                                                                                          [⚠️]()                                                                                                                                           |
| CLI |                                                                             [✅️](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Client/View/cli)                                                                              |
| All charachter cards |                                                                           [✅](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Model/CharacterCard)                                                                            |
| 4 player game |                                                                                  [✅️](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/tree/main/src/main/java/it/polimi/ingsw/Model)                                                                                  |
| Multiple games |                                                                [✅](https://github.com/elialaz/ing-sw-2022-lazzeri-ingrosso-litovchenko/blob/67c2f4935901df4e9343b7842e2be8f7bbd4d0fa/src/main/java/it/polimi/ingsw/Server)                                                                |
| Persistence |                                                                                                                                           [⛔]()                                                                                                                                           |
| Resilience |                                                                                                                                           [⛔]()                                                                                                                                           |

#### Legend
[⛔]() Not Implemented &nbsp;&nbsp;&nbsp;&nbsp;[⚠️]() Implementing&nbsp;&nbsp;&nbsp;&nbsp;[✅]() Implemented


<!--
[![RED](http://placehold.it/15/f03c15/f03c15)](#)
[![YELLOW](http://placehold.it/15/ffdd00/ffdd00)](#)
[![GREEN](http://placehold.it/15/44bb44/44bb44)](#)
-->

## Test cases
All tests in model and controller has a classes' coverage at 100%.

**Coverage criteria: code lines.**

| Package | Tested Class        | Coverage |
|:-----------------------|:--------------------|:------------------------------------:|
| Controller | ActionPhase         | 19/19 (100%)
| Controller | ControlEventManager | 11/11 (100%)
| Controller | Controller          | 50/54 (92%)
| Controller | EndPhase            | 8/11 (72%)
| Controller | PlanningPhase       | 22/22 (100%)
| Model | Global Package      | 540/599 (90%)


## Software used

**Diagrams.net** - Initial UML and sequence diagrams

**Intellij IDEA Ultimate** - main IDE 

## Copyright

Eriantys Board Game is copyrighted 2021.
