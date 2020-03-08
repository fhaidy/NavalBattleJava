package NavalBattleJava.Domain.Entities;

public class Battleship extends Ship {
    public Battleship () throws Exception {
        this.setSize(4);
        setDescription("BS");
        setName("BattleShip");
    }
}
