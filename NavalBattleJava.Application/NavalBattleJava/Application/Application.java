package NavalBattleJava.Application;
import NavalBattleJava.Domain.Entities.*;
import NavalBattleJava.Domain.Entities.NavalBattleJava.Domain.Entities.Enums.AttackFeedback;

import java.util.*;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        List<Ship> ships = new ArrayList(Arrays.asList(new AircraftCarrier(), new Battleship(), new Destroyer(), new Submarine(), new PatrolBoat()));
        Game game1 = new Game(ships);
        Game game2 = new Game(ships);
        System.out.println("Game started !! Game 1, please position your ships");
        for ( Ship ship : game1.ships) {
            boolean shipIsPositioned = false;

            while (!shipIsPositioned){
                try {
                    System.out.println("Chose a line to put the " + ship.getName());
                    int positionX = scanner.nextInt()-1;
                    System.out.println("Chose a column to put the " + ship.getName());
                    int positionY = scanner.nextInt()-1;

                    if (game1.isValidPosition(positionX) && game1.isValidPosition(positionY)){
                        ship.setPositionX(positionX);
                        ship.setPositionY(positionY);
                    }else{
                        System.out.println("Position is invalid !! chose another position");
                        continue;
                    }
                    shipIsPositioned = game1.positionShip(ship);
                    game1.showMap();
                    System.out.println();
                    System.out.println();
                    if (shipIsPositioned) {
                        System.out.println("Ship positioned in the map !!");
                    } else {
                        System.out.println("Position is invalid or is in use !! chose another position");
                    }
                }catch (Exception ex){
                    System.out.println("ops, something went wrong: " + ex.getMessage());
                }
            }
        }

        for ( Ship ship : game2.ships) {
            boolean shipIsPositioned = false;
            System.out.println("Game 2, please position your ships");

            while (!shipIsPositioned){
                try {
                    System.out.println("Chose a line to put the " + ship.getName());
                    int positionX = scanner.nextInt()-1;
                    System.out.println("Chose a column to put the " + ship.getName());
                    int positionY = scanner.nextInt()-1;
                    if (game2.isValidPosition(positionX) && game2.isValidPosition(positionY)) {
                        ship.setPositionX(positionX);
                        ship.setPositionY(positionY);
                    } else {
                        System.out.println("Position is invalid or is in use !! chose another position");
                        continue;
                    }
                    shipIsPositioned = game2.positionShip(ship);
                    game2.showMap();
                    System.out.println();
                    System.out.println();
                    if (shipIsPositioned) {
                        System.out.println("Ship positioned in the map !!");
                    } else {
                        System.out.println("Position is invalid or is in use !! chose another position");
                    }
                }catch (Exception ex){
                    System.out.println("ops, something went wrong: " + ex.getMessage());
                }
            }


        }
        boolean gameOver = false;
        int turn = 0;
        String winner = "";
        while(!gameOver){
            if (turn == 0){
                System.out.println("Game 1, please choose a position to attack !");
                System.out.println("Chose a line to attack: ");
                int positionX = scanner.nextInt()-1;
                System.out.println("Chose a column to attack: ");
                int positionY = scanner.nextInt()-1;

                if (game1.attackIsValid(positionX, positionY)) {
                    AttackFeedback feedback = game2.receiveAttack(positionX, positionY);
                    if (feedback == AttackFeedback.REPEAT) {
                        System.out.println("You already hit this position. Try again.");
                        continue;
                    }
                    System.out.println(game2.checkAttackFeedback(positionX, positionY, feedback));
                    turn = 1;
                    gameOver = game2.checkGameOver();
                }else {
                    System.out.println("Attack is not valid, please chose another attack !");
                }

                if (gameOver)
                    winner = "Game 1";
            }else{
                System.out.println("Game 2, please choose a position to attack !");
                System.out.println("Chose a line to attack: ");
                int positionX = scanner.nextInt() -1;
                System.out.println("Chose a column to attack: ");
                int positionY = scanner.nextInt() -1;
                if (game2.attackIsValid(positionX, positionY)) {
                    AttackFeedback feedback = game1.receiveAttack(positionX, positionY);
                    if (feedback == AttackFeedback.REPEAT){
                        System.out.println("You already hit this position. Try again.");
                        continue;
                    }
                    System.out.println(game1.checkAttackFeedback(positionX, positionY, feedback));
                    turn = 0;
                    gameOver = game1.checkGameOver();
                }else {
                    System.out.println("Attack is not valid, please chose another attack !");
                }

                if (gameOver)
                    winner = "Game 2";
            }
        }
        System.out.println("Congratulations " + winner + " you are the winner !!");
    }
}
