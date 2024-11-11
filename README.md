# 🎮 TicTacToeApp

## 📝 Descripción del Proyecto
TicTacToe App es un juego clásico de tres en raya, desarrollado en Kotlin. Esta aplicación permite a los usuarios jugar al Tres en Raya de forma interactiva, ofreciendo una experiencia de usuario fluida y entretenida.

## 🏗️ Estructura del Proyecto
```
TicTacToeApp/
├── app/
│   ├── build.gradle.kts
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/
│   │   │   │   └── com/
│   │   │   │       └── tictactoeapp/
│   │   │   │           ├── game/
│   │   │   │           │   ├── Game.kt
│   │   │   │           │   ├── Player.kt
│   │   │   │           │   ├── Board.kt
│   │   │   │           │   └── TicTacToe.kt
│   │   │   │           └── ui/
│   │   │   │               ├── MainActivity.kt
│   │   │   │               └── GameScreen.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       └── values/
│   │   └── test/
│   └── ...
└── build.gradle.kts
```

## 💻 Implementación de Lineamientos Generales

### 1. 📚 Clases y Herencia
El proyecto define una jerarquía de clases que incluye:
- **Clase Base**: `Player`
  - Representa a un jugador en el juego
- **Clases Derivadas**: `HumanPlayer` y `AIPlayer`
  - `HumanPlayer`: Representa a un jugador humano que interactúa con la interfaz
  - `AIPlayer`: Representa a un jugador controlado por la inteligencia artificial

### 2. 🔄 Polimorfismo
El polimorfismo se implementa a través del método `makeMove()` en la clase `Player`. Cada clase derivada implementa este método de manera diferente:
- `HumanPlayer`: Permite a los usuarios seleccionar una celda en el tablero
- `AIPlayer`: Calcula automáticamente su movimiento basado en una estrategia

### 3. 🔒 Encapsulamiento y Propiedades
Los atributos de las clases están protegidos mediante encapsulamiento. Por ejemplo, en la clase `Board`:

```kotlin
class Board {
    private val cells: Array<Array<Cell?>> = Array(3) { Array(3) { null } }

    fun getCell(row: Int, col: Int): Cell? {
        return cells[row][col]
    }

    fun setCell(row: Int, col: Int, player: Player) {
        // Validaciones para mantener la integridad de los datos
    }
}
```

### 4. 🔗 Agregación y Composición
- **Composición**: La clase `Game` contiene una instancia de `Board`
- **Agregación**: La clase `Game` mantiene una lista de objetos `Player`

### 5. 🎯 Interactividad y Dinámica
La aplicación cuenta con una interfaz gráfica donde los usuarios pueden:
- Seleccionar celdas en el tablero para realizar movimientos
- Alternar entre jugador humano y jugador IA
- Modificar propiedades de los jugadores
- Ejecutar métodos para realizar movimientos

## 🎨 Implementación de MainActivity
La MainActivity implementa varias características clave:

### Herencia y Estructura
- Hereda de `ComponentActivity`
- Permite el uso de composición
- Hereda funciones y permite la sobrescritura de métodos
- Facilita la integración con bibliotecas

### Rutas de Navegación
La función de composición `SetupNavGraph` define las siguientes rutas de navegación:
- `splash_screen`: Pantalla de bienvenida
- `mode_selection`: Selección de modo de juego
- `name_input/{mode}`: Entrada del nombre del jugador para modo IA (con retorno al menú de bienvenida)
- `game_board/{mode}/{player1Name}/{player2Name}`: Entrada de nombres para modo amigo (con opción de retorno)
- `instructions`: Reglas del juego y guía de cómo jugar

## 👥 Equipo de Desarrollo
- Jorge Barreto
- Esteban Quiceno
- Esteban Alvarez Velasquez

## 🛠️ Requisitos Técnicos
- Kotlin
- Android Studio
- SDK Mínimo: [Versión]
- SDK Objetivo: [Versión]

## 📱 Características
- Múltiples modos de juego (Jugador vs IA, Jugador vs Jugador)
- Tablero de juego interactivo
- Interfaz amigable
- Sistema de navegación claro
- Instrucciones del juego
- Pantalla de bienvenida

---
Este proyecto fue desarrollado en Kotlin, Android Studio y usando lo aprendido de Aplicaciones Moviles y Paradigmas de Programacion.
