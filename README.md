# Car-Dealership CLI

Menu-driven Java application that lets a dealership:

* view employees (ranked by sales),
* list unsold cars,
* sell a car through a chosen employee,
* accept a new car from a customer,
* save all updates back to text files.

> Built for an Object-Oriented Programming course – kept and cleaned up as a showcase of OOP, custom exceptions and java.nio file handling.

---

## ✨ Features
| Concept                           | Where it appears |
|-----------------------------------|------------------|
| **OOP domain model**              | `Car`, `Employee`, `CarDealerShip`, custom exceptions |
| **Enum-based menu**               | `CarDealerShip.MenuOption` |
| **Generics + Comparable**         | `sortList(ArrayList<T>)` generic helper |
| **Persistence via `java.nio`**    | Reads seed data, appends sales logs (`car_sales.txt`, `NewCarDealerShip.txt`) |
| **Robust validation**             | Integrity checks in `Car` (`checkID`, `checkPrice`, …) & `Employee` (`CheckId`, …) |
| **Exception handling demo**       | Tailored messages for user input errors |

---
