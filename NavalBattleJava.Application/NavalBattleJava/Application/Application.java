package NavalBattleJava.Application;
import NavalBattleJava.Domain.Entities.*;
import NavalBattleJava.Domain.Entities.NavalBattleJava.Domain.Entities.Enums.AttackFeedback;

import java.util.*;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        List<Ship> ships = new ArrayList(Arrays.asList(new AircraftCarrier(), new Battleship(), new Destroyer(), new Submarine(), new PatrolBoat()));
        Player player1 = new Player(ships);
        Player player2 = new Player(ships);
        System.out.println("Game started !! Player 1, please position your ships");
        for ( Ship ship : player1.ships) {
            boolean shipIsPositioned = false;

            while (!shipIsPositioned){
                try {
                    System.out.println("Chose a line to put the " + ship.getName());
                    int positionX = scanner.nextInt()-1;
                    System.out.println("Chose a column to put the " + ship.getName());
                    int positionY = scanner.nextInt()-1;

                    if (player1.isValidPosition(positionX) && player1.isValidPosition(positionY)){
                        ship.setPositionX(positionX);
                        ship.setPositionY(positionY);
                    }else{
                        System.out.println("Position is invalid !! chose another position");
                        continue;
                    }
                    shipIsPositioned = player1.positionShip(ship);
                    player1.showMap();
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

        for ( Ship ship : player2.ships) {
            boolean shipIsPositioned = false;
            System.out.println("Player 2, please position your ships");

            while (!shipIsPositioned){
                try {
                    System.out.println("Chose a line to put the " + ship.getName());
                    int positionX = scanner.nextInt()-1;
                    System.out.println("Chose a column to put the " + ship.getName());
                    int positionY = scanner.nextInt()-1;
                    if (player2.isValidPosition(positionX) && player2.isValidPosition(positionY)) {
                        ship.setPositionX(positionX);
                        ship.setPositionY(positionY);
                    } else {
                        System.out.println("Position is invalid or is in use !! chose another position");
                        continue;
                    }
                    shipIsPositioned = player2.positionShip(ship);
                    player2.showMap();
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
                System.out.println("Player 1, please choose a position to attack !");
                System.out.println("Chose a line to attack: ");
                int positionX = scanner.nextInt()-1;
                System.out.println("Chose a column to attack: ");
                int positionY = scanner.nextInt()-1;

                if (player1.attackIsValid(positionX, positionY)) {
                    AttackFeedback feedback = player2.receiveAttack(positionX, positionY);
                    if (feedback == AttackFeedback.REPEAT) {
                        System.out.println("You already hit this position. Try again.");
                        continue;
                    }
                    System.out.println(player2.checkAttackFeedback(positionX, positionY, feedback));
                    turn = 1;
                    gameOver = player2.checkGameOver();
                }else {
                    System.out.println("Attack is not valid, please chose another attack !");
                }

                if (gameOver)
                    winner = "Player 1";
            }else{
                System.out.println("Player 2, please choose a position to attack !");
                System.out.println("Chose a line to attack: ");
                int positionX = scanner.nextInt() -1;
                System.out.println("Chose a column to attack: ");
                int positionY = scanner.nextInt() -1;
                if (player2.attackIsValid(positionX, positionY)) {
                    AttackFeedback feedback = player1.receiveAttack(positionX, positionY);
                    if (feedback == AttackFeedback.REPEAT){
                        System.out.println("You already hit this position. Try again.");
                        continue;
                    }
                    System.out.println(player1.checkAttackFeedback(positionX, positionY, feedback));
                    turn = 0;
                    gameOver = player1.checkGameOver();
                }else {
                    System.out.println("Attack is not valid, please chose another attack !");
                }

                if (gameOver)
                    winner = "Player 2";
            }
        }
        System.out.println("Congratulations " + winner + " you are the winner !!");
    }
}
