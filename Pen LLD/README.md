# Pen LLD

Low Level Design of a Pen system in Java, following SOLID principles.

## Features

- Open / Close a pen
- Write with a pen (validates pen is open and has ink)
- Refill ink
- Two pen types: **ClickablePen** (click to toggle) and **NonClickablePen** (cap-based)
- Colour selection via `InkColor` enum: `RED`, `BLUE`, `BLACK`, `GREEN`, `VIOLET`

## Project Structure

```
src/
├── enums/
│   └── InkColor.java
├── interfaces/
│   ├── Writable.java
│   ├── Refillable.java
│   └── Openable.java
├── models/
│   └── Ink.java
├── pens/
│   ├── Pen.java
│   ├── ClickablePen.java
│   └── NonClickablePen.java
├── factory/
│   └── PenFactory.java
└── Main.java
```

## Run

```bash
cd src
javac -d ../out enums/*.java models/*.java interfaces/*.java pens/*.java factory/*.java Main.java
java -cp ../out Main
```

## UML Diagram

```mermaid
classDiagram
    class Writable {
        <<interface>>
        +write(text: String)
    }

    class Refillable {
        <<interface>>
        +refill()
    }

    class Openable {
        <<interface>>
        +open()
        +close()
    }
    
    class InkColor {
        <<enumeration>>
        RED
        BLUE
        BLACK
        GREEN
        VIOLET
    }
    
    class Ink {
        -color: InkColor
        -level: int
        +Ink(color: InkColor)
        +refill()
        +isEmpty() boolean
        +getColor() InkColor
        +getLevel() int
    }
    
    class Pen {
        <<abstract>>
        #brand: String
        #ink: Ink
        #isOpen: boolean
        +Pen(brand: String, color: InkColor)
        +write(text: String)
        +refill()
    }
    
    class ClickablePen {
        +ClickablePen(brand: String, color: InkColor)
        +click()
        +open()
        +close()
        +toString() String
    }
    
    class NonClickablePen {
        +NonClickablePen(brand: String, color: InkColor)
        +open()
        +close()
        +toString() String
    }
    
    class PenFactory {
        +createClickablePen(brand: String, color: InkColor)$ ClickablePen
        +createNonClickablePen(brand: String, color: InkColor)$ NonClickablePen
    }

    Writable <|.. Pen
    Refillable <|.. Pen
    Pen *-- Ink : contains
    Ink --> InkColor : uses
    Pen <|-- ClickablePen
    Pen <|-- NonClickablePen
    Openable <|.. ClickablePen
    Openable <|.. NonClickablePen
    PenFactory ..> ClickablePen : creates
    PenFactory ..> NonClickablePen : creates
```
