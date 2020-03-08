package NavalBattleJava.Domain.Entities;

import NavalBattleJava.Domain.Entities.NavalBattleJava.Domain.Entities.Enums.AttackFeedback;
import java.util.List;

public class Player {
    private String[][] map = new String[][]{
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }
    };
    private int[][] hitMap = new int[10][10];
    public List<Ship> ships;

    public Player(List<Ship> ships) {
        this.ships = ships;
    }

    public boolean positionShip(Ship ship){
        if (checkPositions(ship)){
            int size = 0;
            while (size < ship.getSize()){
                int actualPosition = ship.getPositionY()+size;
                markPosition(ship.getPositionX(), actualPosition, ship.getDescription());
                size++;
            }
            return true;
        }
        return false;
    }

    public String checkAttackFeedback(int positionX, int positionY, AttackFeedback feedback){
        if (feedback == AttackFeedback.SUCCESS){
            hitMap[positionX][positionY] = 1;
            String description = map[positionX][positionY];
            map[positionX][positionY] = "";
            if (isShipDestroyed(description))
                return "You destroyed a ship !!";
            return "You hit a ship !!";
        }
        return "You hit the ocean :(";
    }

    public boolean checkGameOver(){
        for (int i = 0; i < 10; i++) {
            for(int j =0; j < 10; j++) {
                if (!map[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidPosition(int position) {
        return position < 10 && position >= 0;
    }

    public AttackFeedback receiveAttack(int positionX, int positionY){
        if (hitMap[positionX][positionY] == 1)
            return AttackFeedback.REPEAT;

        if (!map[positionX][positionY].equals(""))
            return AttackFeedback.SUCCESS;

        return AttackFeedback.FAILED;
    }

    public boolean attackIsValid(int positionX, int  positionY){
        return positionX >= 0 && positionX < 10 && positionY >= 0 && positionY < 10;
    }

    public boolean isShipDestroyed (String description){
        for (int i = 0; i < 10; i++) {
            for(int j =0; j < 10; j++) {
                if (map[i][j].equals(description)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void showMap(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("["+map[i][j]+"] ");
            }
            System.out.println();
        }
    }
    private void markPosition(int positionX, int positionY, String descriptor){
        map[positionX][positionY] = descriptor;
    }
    private boolean isPositionAvailable(int positionX, int positionY){
        if(!isValidPosition(positionY))
            return false;
        return map[positionX][positionY].equals("");
    }
    private boolean checkPositions (Ship ship){
        int size = 0;
        while (size < ship.getSize()){
            int actualPosition = ship.getPositionY()+size;
            if(!isPositionAvailable(ship.getPositionX(), actualPosition))
                return false;
            size++;
        }
        return true;
    }
}
