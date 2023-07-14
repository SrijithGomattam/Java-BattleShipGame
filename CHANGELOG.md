AbstractPlayer
- Changed body of setup method to return a list of ShipAdapter instead of Ship to communicate with the server

GameResult
- Changed constants from WON,LOST,DREW to WIN,LOST,DRAW to match the Server

Coord
- Made Coord a JsonCreator to communicate with Server
