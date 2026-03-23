# Design a Pen - LLD Assignment

## Problem Statement
Design a Pen using Object-Oriented Principles. The pen should support different functionalities such as:
1. `start()`
2. `write()`
3. `close()`
4. `refill()`

## Requirements & Assumptions
- **Entities**: We are modeling different types of Pens (e.g., Ball Pen, Gel Pen, Fountain Pen).
- **Attributes**: A Pen has a `color` (from Color enum), `type` (BALL_PEN, GEL_PEN, FOUNTAIN_PEN), and `state` (NEW, OPEN, CLOSED, EMPTY).
- **Functionalities**:
  - `start()`: Opens the pen (transitions state from CLOSED/NEW to OPEN).
  - `write()`: Checks if the pen is OPEN and not EMPTY. If both conditions are met, it simulates writing.
  - `close()`: Closes the pen.
  - `refill(Color newColor)`: Refill mechanism applies to pens that are refillable. It allows changing the color of the pen.

## Design Explanation
1. **Enums (`PenType`, `PenState`, `Color`)**: Provides type safety indicating the functional state and physical attributes of the pen.
2. **Interface (`Refillable`)**: Interface Segregation Principle specifies that only refillable pens (not Use & Throw pens) implement this contract.
3. **Abstract Class (`Pen`)**: Holds common state and shared behaviors (like `start()`, `close()`), allowing code reuse.
4. **Concrete Classes (`BallPen`, `GelPen`, `FountainPen`, `UseAndThrowPen`)**: Provide explicit details of different types of pens. `UseAndThrowPen` specifically does not allow refilling.

## UML / Class Diagram

```plantuml
@startuml
enum PenType {
  BALL_PEN
  GEL_PEN
  FOUNTAIN_PEN
}

enum PenState {
  NEW
  OPEN
  CLOSED
  EMPTY
}

enum Color {
  RED
  BLUE
  GREEN
  BLACK
}

interface Refillable {
  + void refill(Color newColor)
}

abstract class Pen {
  # Color color
  # PenType type
  # PenState state
  + Pen(Color color, PenType type)
  + abstract void write(String content)
  + void start()
  + void close()
}

class BallPen extends Pen implements Refillable {
  - double inkLevel
  + BallPen(Color color)
  + void write(String content)
  + void refill(Color newColor)
}

class GelPen extends Pen implements Refillable {
  - double inkLevel
  + GelPen(Color color)
  + void write(String content)
  + void refill(Color newColor)
}

class FountainPen extends Pen implements Refillable {
  - double inkLevel
  + FountainPen(Color color)
  + void write(String content)
  + void refill(Color newColor)
}

class UseAndThrowPen extends Pen {
  - double inkLevel
  + UseAndThrowPen(Color color)
  + void write(String content)
}

Pen -right-> PenType
Pen -left-> PenState
Pen -up-> Color
@enduml
```

## How to Run
From the root of this project (`design-pen`):
1. Navigate to the `src` directory:
   ```bash
   cd src
   ```
2. Compile the Java files:
   ```bash
   javac com/example/pen/*.java
   ```
3. Run the application:
   ```bash
   java com.example.pen.App
   ```
