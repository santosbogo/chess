# Chess System Design

📚 **Course:** System Design  
👤 **Author:** Santos Bogo (https://github.com/santosbogo)  
🎓 **University:** Austral University

## 📖 Repository Overview
This project is a highly modular and extensible chess engine that allows for the easy addition of new rules and chess variants. The same core architecture was also used to implement a checkers game. The entire system is immutable, enabling robust **undo/redo** functionality without risk of state corruption.

Additionally, a **client-server architecture** was implemented, allowing two clients to play chess asynchronously over a network. The server processes all game logic, ensuring no shared memory communication between clients.

## 🛠 Key Features

### 🔹 Modularity & Extensibility
- Easily **add new rules** and **create chess variants** with minimal modifications.
- The same system was extended to implement **Checkers**.

### 🔹 Immutability
- The entire game state is **immutable**, allowing for:
  - **Undo/Redo** operations without errors.
  - **Thread safety** and easy debugging.

### 🔹 Client-Server Architecture
- Implemented a **UI-based** chess client.
- Uses **asynchronous communication** between clients.
- **All processing is done on the server**, avoiding in-memory client interactions.

## 🎯 Applied Software Design Principles

### 🔹 SOLID Principles
- **Single Responsibility:** Each class has a well-defined role.
- **Open/Closed:** New rules and variants can be added without modifying existing code.
- **Liskov Substitution:** Different game components can be interchanged seamlessly.
- **Interface Segregation:** Clients depend only on the interfaces they need.
- **Dependency Inversion:** High-level modules depend on abstractions rather than concrete implementations.

### 🔹 Design Patterns
- **Composite:** Used for structuring board elements and move handling.
- **Strategy:** Allows dynamic selection of movement rules and game variations.

## 🚀 Installation & Usage
```sh
git clone https://github.com/yourusername/chess-system-design.git
cd chess-system-design
````

#### 👷Build the project
```sh
./gradlew build
```

### 🔹 Running the Games
- **Play Chess (Standalone):**
  ```sh
  ./gradlew playChess
  ```
- **Play Checkers (Standalone):**
  ```sh
  ./gradlew playCheckers
  ```

### 🔹 Running the Server-Client Mode
To play using a server, first start the server of the desired game and then launch clients. The first two clients connected will be assigned a color automatically, while any additional clients will act as spectators.

- **Start Chess Server:**
  ```sh
  ./gradlew startChessServer
  ```
- **Start Checkers Server:**
  ```sh
  ./gradlew startCheckersServer
  ```
- **Start Client:**
  ```sh
  ./gradlew startClient
  ```