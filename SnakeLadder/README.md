# Snake & Ladder — LLD Assignment

A console-based Snake & Ladder game built for an LLD assignment. The focus is on clean design using standard design patterns and SOLID principles.

---

## Folder Structure

```
src/
├── Main.java
├── board/
│   ├── BoardEntity.java      # abstract base for Snake and Ladder
│   ├── Snake.java
│   ├── Ladder.java
│   ├── Board.java            # n×n grid, places n snakes and n ladders randomly
│   └── BoardFactory.java
├── dice/
│   ├── Dice.java
│   └── DiceFactory.java
├── player/
│   ├── Player.java
│   └── PlayerFactory.java
├── rules/
│   ├── GameRules.java        # interface (Strategy Pattern)
│   ├── EasyRules.java
│   ├── HardRules.java
│   └── RulesFactory.java
├── difficulty/
│   └── Difficulty.java       # enum: EASY, HARD
├── observer/
│   ├── Observer.java         # interface
│   └── ConsoleNotifier.java
└── game/
    ├── Game.java
    └── GameFactory.java
```

---

## Design Patterns Used

| Pattern | Where |
|---|---|
| Factory | `BoardFactory`, `DiceFactory`, `PlayerFactory`, `RulesFactory`, `GameFactory` |
| Strategy | `GameRules` interface → `EasyRules` / `HardRules` |
| Observer | `Observer` interface → `ConsoleNotifier` |
| Template Method | `BoardEntity` abstract class → `Snake` / `Ladder` |

---

## Game Rules

- Board has cells **1 to n²**. Players start outside at position **0**.
- Each turn a player rolls a **6-sided dice** and moves forward.
- Landing on a **snake's head** → slide down to its tail.
- Landing on a **ladder's start** → climb up to its top.
- If a move would go **beyond the last cell**, the player stays put.
- A player **wins** by landing exactly on the last cell.
- The game ends when only **one player** has not yet won.

### Difficulty Modes

| Mode | Rule |
|---|---|
| **Easy** | Standard rules only |
| **Hard** | Rolling 6 three consecutive times causes that player to **lose their turn** |

---

## How to Run

```bash
# From the project root (Snake & Ladder/)
mkdir out

javac -d out -sourcepath src \
  src/Main.java \
  src/board/*.java \
  src/dice/*.java \
  src/player/*.java \
  src/rules/*.java \
  src/observer/*.java \
  src/difficulty/*.java \
  src/game/*.java

java -cp out Main
```

You will be prompted to enter:
1. Board size `n` (creates an n×n board with n snakes and n ladders)
2. Number of players
3. Player names
4. Difficulty (Easy / Hard)
