**MVP**
- Map
- Random bomb placement (populate)
- Reveal field
- Detect bomb reveal and end/restart
- Bombs in proximity
- Win notification (winner winner chicken dinner)

**Wenn wir mehr Zeit haben**
- Restart button
- Timer
- Red flags
- Remaining bombs
- Design and GUI (+ Menu)
- Highsore
- Leaderboard (+ Usernames)
- Reveal all neighboring free fields
- Map size selection (in Menu)
- Help/Tutorial Button

**TODO**
- map //2 Dimensional boolean array - 9x9 10
- void populate() //place bombs (randomly, 10)
- boolean hasBomb(int x, int y) //check for bomb
- void reveal(int x, int y, boolean bomb) //reveal bomb or empty field
- byte proximityBombNumber(int x, int y) //byte is smaller int, get number of bombs in proximity
- byte remainingBombs //Number of remaining bombs, decrement (--) if bomb uncovered
- void showEndScreen(boolean hasWon) //Display game over dialogue, maybe if patrick wants cat pic add cat pic