package services.botStrategies;

import models.BotDifficulty;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficulty difficulty){
        if (difficulty == BotDifficulty.RANDOM) {
            return new RandomBotPlayingStrategy();
        }
        return null;
    }
}
