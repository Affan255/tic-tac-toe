import controllers.GameController;
import models.*;
import services.winStrategies.WinningStrategy;
import services.winStrategies.WinningStrategyFactory;
import services.winStrategies.WinningStrategyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController();
        Game game;
        do {
            System.out.println("Enter the board size:");
            int dim = scanner.nextInt();
            int numPlayers = dim - 1;

            System.out.println("Does Game have a bot? Y/N");
            char choice = scanner.next().charAt(0);
            List<Player> players = new ArrayList<>();

            if (choice == 'Y')
                numPlayers--;

            for (int i = 0; i < numPlayers; i++) {
                System.out.println("Enter name of player " + (i + 1));
                String name = scanner.next();
                System.out.println("Enter the symbol for player " + (i + 1));
                char symbol = scanner.next().charAt(0);
                Player player = new Player(i, name, symbol);
                players.add(player);
            }
            if (choice == 'Y')
                players.add(new Bot(numPlayers, 'B', BotDifficulty.RANDOM));

            List<WinningStrategy> winningStrategies = Arrays.asList(WinningStrategyFactory.getWinningStrategy(WinningStrategyType.NORMAL, dim));

            game = controller.newGame(dim, players, winningStrategies);
        } while (game==null);

        while (game.getStatus().equals(GameStatus.IN_PROGRESS)){
            Player player = controller.getCurrentPlayer(game);
            Move move;
            do {
                move = controller.move(game, player);
            }while (move==null);

            if(player.getPlayerType().equals(PlayerType.HUMAN)) {
                System.out.println("Do you want to undo last move? Y/N");
                char isUndo = scanner.next().charAt(0);
                if(isUndo=='Y') {
                    controller.undo(game, move);
                    continue;
                }
            }
            controller.updateGameStatus(game, move);
        }

        System.out.println("Do you want the game replay? Y/N");
        char isReplayReq = scanner.next().charAt(0);
        if(isReplayReq=='Y') {
            System.out.println("========================================================");
            System.out.println();
            controller.replay(game);
        }
    }
}
