package services.winStrategies;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(WinningStrategyType winningStrategyType, int dimension){

        switch (winningStrategyType){
            case CORNER:
                return new CornerWinningStrategy(dimension);
            case NORMAL:
                return new NormalWinningStrategy(dimension);
        }
        return null;
    }
}
