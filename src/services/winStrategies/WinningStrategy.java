package services.winStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    boolean checkIfWon(Board board, Move move);
}
