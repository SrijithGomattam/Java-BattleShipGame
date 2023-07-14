# Java BattleSalvo game

## Game Description
The game board consists of two grids, typically marked by letters for columns and numbers for rows. The grids are often represented by 10x10 squares, but other variations exist. Each player's grid is divided into rows and columns, allowing players to strategically place their ships on their board. The types and sizes of ships can vary, but common examples include the carrier, battleship, destroyer, submarine, and patrol boat.

Players take turns calling out coordinates on the opponent's grid, attempting to hit their ships. The opponent responds with "hit" or "miss" based on the accuracy of the guess. If a hit is recorded, the attacking player marks the location on their own tracking grid to keep track of successful hits.

As the game progresses, players use deduction and logic to narrow down the possible locations of the opponent's ships based on the hits and misses they've observed. Once all the squares of a ship have been hit, it is considered sunk. The first player to sink all of the opponent's ships wins the game.

## Skils Used
This game is built using the Model-View-Controller (MVC) architectural pattern and adheres to the SOLID principles. The project utilizes JSONs for data storage and includes proxies to communicate with a server. Additionally, the game features an AI class that employs machine learning algorithms to make intelligent decisions and provide a challenging opponent for the player.
