# LLD 101: SOLID Refactoring Assignment

This repository contains my refactored solutions for the **SOLID Principles** exercises as part of the SST28-LLD101 coursework.

## Overview
The goal of this assignment was to refactor existing legacy code to achieve full SOLID compliance while keeping the original behavior intact. No external design patterns or libraries were used; the focus was strictly on core Object-Oriented Programming (OOP) mechanisms such as interfaces, composition, and inheritance.

## Repository Structure
The repository is structured as requested, with each exercise in its own directory containing the refactored source code:

- `SOLID/ex01/src/Demo01.java`: Exercise 1 (Single Responsibility Principle)
- `SOLID/ex02/src/Demo02.java`: Exercise 2 (Single Responsibility Principle)
- `SOLID/ex03/src/Demo03.java`: Exercise 3 (Open/Closed Principle)
- `SOLID/ex04/src/Demo04.java`: Exercise 4 (Open/Closed Principle)
- `SOLID/ex05/src/Demo05.java`: Exercise 5 (Liskov Substitution Principle)
- `SOLID/ex06/src/Demo06.java`: Exercise 6 (Liskov Substitution Principle)
- *(Includes exercises 7-10 as per the original codebase)*

## How to Run
To compile and run any of the exercises, navigate to the specific exercise's `src` directory, compile the Java files, and run the `DemoXX` class.

For example, to run Exercise 1:
```bash
cd SOLID/ex01/src
javac *.java
java Demo01
```

## Principles Applied
*   **Single Responsibility Principle (SRP):** Extracted parsing, validation, and storage logic into dedicated classes to ensure each class has only one reason to change.
*   **Open/Closed Principle (OCP):** Introduced rule interfaces (e.g., `PricingRule`, `EligibilityRule`) to allow extending functionality without modifying core calculation logic.
*   **Liskov Substitution Principle (LSP):** Refactored class hierarchies using composition to ensure subclasses do not violate base class constraints or silently change expected behavior.
