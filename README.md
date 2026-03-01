# LLD 101: SOLID Refactoring Assignment

This repository contains my refactored solutions for the **SOLID Principles** exercises as part of the SST28-LLD101 coursework.

## Overview
The goal of this assignment was to refactor existing legacy code to achieve full SOLID compliance while keeping the original behavior intact. No external design patterns or libraries were used; the focus was strictly on core Object-Oriented Programming (OOP) mechanisms such as interfaces, composition, and inheritance.

## Repository Structure
The repository is structured as requested, with each exercise in its own directory containing the refactored source code:

- `SOLID/Ex1/src/Main.java`: Exercise 1 (Single Responsibility Principle)
- `SOLID/Ex2/src/Main.java`: Exercise 2 (Single Responsibility Principle)
- `SOLID/Ex3/src/Main.java`: Exercise 3 (Open/Closed Principle)
- `SOLID/Ex4/src/Main.java`: Exercise 4 (Open/Closed Principle)
- `SOLID/Ex5/src/Main.java`: Exercise 5 (Liskov Substitution Principle)
- `SOLID/Ex6/src/Main.java`: Exercise 6 (Liskov Substitution Principle)
- `SOLID/Ex7/src/Main.java`: Exercise 7 (Interface Segregation Principle)
- `SOLID/Ex8/src/Main.java`: Exercise 8 (Interface Segregation Principle)
- `SOLID/Ex9/src/Main.java`: Exercise 9 (Dependency Inversion Principle)
- `SOLID/ex10/src/Main.java`: Exercise 10 (Dependency Inversion Principle)

## How to Run
To compile and run any of the exercises, navigate to the specific exercise's `src` directory, compile the Java files, and run the `Main` class.

For example, to run Exercise 7:
```bash
cd SOLID/Ex7/src
javac *.java
java Main
```

## Principles Applied
*   **Single Responsibility Principle (SRP):** Extracted parsing, validation, and storage logic into dedicated classes to ensure each class has only one reason to change.
*   **Open/Closed Principle (OCP):** Introduced rule interfaces (e.g., `PricingRule`, `EligibilityRule`) to allow extending functionality without modifying core calculation logic.
*   **Liskov Substitution Principle (LSP):** Refactored class hierarchies using composition to ensure subclasses do not violate base class constraints or silently change expected behavior.
*   **Interface Segregation Principle (ISP):** Split fat interfaces into smaller, capability-specific interfaces so that no class implements methods irrelevant to it. Clients depend only on the interfaces they actually use.
*   **Dependency Inversion Principle (DIP):** Introduced abstractions (interfaces) and injected dependencies via constructors so that high-level modules depend on abstractions, not concrete implementations.
