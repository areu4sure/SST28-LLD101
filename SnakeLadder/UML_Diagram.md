# Snake & Ladder UML Class Diagram

This diagram visualizes the structural and architectural design of the Snake and Ladder game implementation. It highlights the use of Design Patterns such as Factory Pattern, Strategy Pattern, and Observer Pattern.

```mermaid
classDiagram

    class Main {
        +main(args: String[])
    }

    class Game {
        -board: Board
        -dice: Dice
        -rules: GameRules
        -activePlayers: Deque~Player~
        -observers: List~Observer~
        ~Game(board: Board, dice: Dice, rules: GameRules, players: List~Player~)
        +addObserver(observer: Observer)
        -notifyObservers(message: String)
        -printDivider()
        -displayCurrentPositions()
        +play()
        -rotateTurn()
    }

    class GameFactory {
        -GameFactory()
        +createGame(n: int, playerNames: List~String~, difficulty: Difficulty)$ Game
    }

    class Board {
        -size: int
        -n: int
        -entityMap: Map~Integer, BoardEntity~
        -snakes: List~BoardEntity~
        -ladders: List~BoardEntity~
        ~Board(n: int)
        ~placeEntitiesRandomly()
        +getEntityAt(position: int) BoardEntity
        +getSize() int
        +getSideLength() int
        +display()
    }

    class BoardFactory {
        -BoardFactory()
        +createBoard(n: int)$ Board
    }

    class BoardEntity {
        <<abstract>>
        #startPosition: int
        #endPosition: int
        #BoardEntity(startPosition: int, endPosition: int)
        +getStartPosition() int
        +getEndPosition() int
        +getEntityType()* String
        +describe()* String
    }

    class Snake {
        +Snake(headPosition: int, tailPosition: int)
        +getEntityType() String
        +describe() String
    }

    class Ladder {
        +Ladder(startPosition: int, endPosition: int)
        +getEntityType() String
        +describe() String
    }

    class Dice {
        -faces: int
        -random: Random
        ~Dice(faces: int)
        +roll() int
        +getFaces() int
    }

    class DiceFactory {
        -DiceFactory()
        +createStandardDice()$ Dice
        +createCustomDice(faces: int)$ Dice
    }

    class Player {
        -id: int
        -name: String
        -position: int
        -consecutiveSixes: int
        -wins: int
        ~Player(id: int, name: String)
        +getId() int
        +getName() String
        +getPosition() int
        +getConsecutiveSixes() int
        +getWins() int
        +setPosition(position: int)
        +incrementConsecutiveSixes()
        +resetConsecutiveSixes()
        +incrementWins()
        +toString() String
    }

    class PlayerFactory {
        -PlayerFactory()
        +createPlayer(id: int, name: String)$ Player
        +createPlayers(names: List~String~)$ List~Player~
    }

    class GameRules {
        <<interface>>
        +isValidMove(currentPosition: int, diceValue: int, boardSize: int) boolean
        +calculateNewPosition(currentPosition: int, diceValue: int, board: Board) int
        +handleConsecutiveSix(player: Player, diceValue: int) boolean
        +hasWon(position: int, boardSize: int) boolean
    }

    class EasyRules {
        +isValidMove(currentPosition: int, diceValue: int, boardSize: int) boolean
        +calculateNewPosition(currentPosition: int, diceValue: int, board: Board) int
        +handleConsecutiveSix(player: Player, diceValue: int) boolean
        +hasWon(position: int, boardSize: int) boolean
    }

    class HardRules {
        -CONSECUTIVE_SIX_LIMIT: int$
        -SIX: int$
        +isValidMove(currentPosition: int, diceValue: int, boardSize: int) boolean
        +calculateNewPosition(currentPosition: int, diceValue: int, board: Board) int
        +handleConsecutiveSix(player: Player, diceValue: int) boolean
        +hasWon(position: int, boardSize: int) boolean
    }

    class RulesFactory {
        -RulesFactory()
        +createRules(difficulty: Difficulty)$ GameRules
    }

    class Difficulty {
        <<enumeration>>
        EASY
        HARD
    }

    class Observer {
        <<interface>>
        +update(message: String)
    }

    class ConsoleNotifier {
        +update(message: String)
    }

    %% Relationships

    Main --> GameFactory : uses
    Main --> ConsoleNotifier : creates
    Main --> Difficulty : uses
    
    GameFactory ..> Game : creates >
    GameFactory ..> BoardFactory : uses >
    GameFactory ..> DiceFactory : uses >
    GameFactory ..> PlayerFactory : uses >
    GameFactory ..> RulesFactory : uses >

    Game --> Board : has >
    Game --> Dice : has >
    Game --> GameRules : has >
    Game --> Player : activePlayers 1..*
    Game --> Observer : observers 0..*

    BoardFactory ..> Board : creates >
    Board *-- BoardEntity : contains >

    BoardEntity <|-- Snake : inherits
    BoardEntity <|-- Ladder : inherits

    DiceFactory ..> Dice : creates >

    PlayerFactory ..> Player : creates >

    RulesFactory ..> GameRules : creates >
    RulesFactory ..> EasyRules : creates >
    RulesFactory ..> HardRules : creates >
    RulesFactory ..> Difficulty : uses >

    GameRules <|.. EasyRules : implements
    GameRules <|.. HardRules : implements

    Observer <|.. ConsoleNotifier : implements
```
