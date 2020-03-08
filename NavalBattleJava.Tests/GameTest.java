import NavalBattleJava.Domain.Entities.AircraftCarrier;
import NavalBattleJava.Domain.Entities.Game;
import NavalBattleJava.Domain.Entities.NavalBattleJava.Domain.Entities.Enums.AttackFeedback;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void shipShouldBeDestroyed(){
        //Arrange
        String[][] map = new String[][]{
                { "AC", "AC", "AC", "AC", "AC", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "SUB", "SUB", "SUB", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "BS", "BS", "BS", "BS", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "D", "D", "D", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" }
        };
        Game game = new Game();
        game.setMap(map);
        //Act
        boolean isDestroyed = game.isShipDestroyed("PB");
        //Assert
        Assert.assertTrue(isDestroyed);
    }
    @Test
    public void shipShouldNotBeDestroyed(){
        //Arrange
        String[][] map = new String[][]{
                { "AC", "AC", "AC", "AC", "AC", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "SUB", "SUB", "SUB", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "BS", "BS", "BS", "BS", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "D", "D", "D", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "PB", "PB", "" }
        };
        Game game = new Game();
        game.setMap(map);
        //Act
        boolean isDestroyed = game.isShipDestroyed("SUB");
        //Assert
        Assert.assertFalse(isDestroyed);
    }
    @Test
    public void attackShouldBeValid(){
        //Arrange
        int positionX = 6;
        int positionY = 3;
        Game game = new Game();
        //Act
        boolean attackIsValid =  game.attackIsValid(positionX, positionY);
        //Assert
        Assert.assertTrue(attackIsValid);
    }
    @Test
    public void attackShouldNotBeValid(){
        //Arrange
        int positionX = 10;
        int positionY = 3;
        Game game = new Game();
        //Act
        boolean attackIsValid =  game.attackIsValid(positionX, positionY);
        //Assert
        Assert.assertFalse(attackIsValid);
    }
    @Test
    public void attackFeedbackShouldBeSuccess(){
        //Arrange
        String[][] map = new String[][]{
                { "AC", "AC", "AC", "AC", "AC", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "SUB", "SUB", "SUB", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "BS", "BS", "BS", "BS", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "D", "D", "D", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "PB", "PB", "" }
        };
        Game game = new Game();
        game.setMap(map);

        int positionX = 5;
        int positionY = 3;
        //Act
        AttackFeedback attackFeedback =  game.receiveAttack(positionX, positionY);
        //Assert
        Assert.assertEquals(AttackFeedback.SUCCESS, attackFeedback);
    }
    @Test
    public void attackFeedbackShouldBeFailed(){
        //Arrange
        Game game = new Game();
        int positionX = 6;
        int positionY = 7;
        //Act
        AttackFeedback attackFeedback =  game.receiveAttack(positionX, positionY);
        //Assert
        Assert.assertEquals(AttackFeedback.FAILED, attackFeedback);
    }
    @Test
    public void attackFeedbackShouldBeRepeat(){
        //Arrange
        int[][] hitMap = new int[][]{
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,1,1,1,1,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 }
        };
        Game game = new Game();
        game.setHitMap(hitMap);

        int positionX = 5;
        int positionY = 3;
        //Act
        AttackFeedback attackFeedback =  game.receiveAttack(positionX, positionY);
        //Assert
        Assert.assertEquals(AttackFeedback.REPEAT, attackFeedback);
    }
    @Test
    public void positionShouldBeValid(){
        //Arrange
        int positionY = 3;
        Game game = new Game();
        //Act
        boolean positionIsValid =  game.isValidPosition(positionY);
        //Assert
        Assert.assertTrue(positionIsValid);
    }
    @Test
    public void positionShouldNotBeValid(){
        //Arrange
        int positionY = 10;
        Game game = new Game();
        //Act
        boolean positionIsValid =  game.isValidPosition(positionY);
        //Assert
        Assert.assertFalse(positionIsValid);
    }
    @Test
    public void gameShouldBeOver(){
        //Arrange
        Game game = new Game();
        //Act
        boolean gameOver =  game.checkGameOver();
        //Assert
        Assert.assertTrue(gameOver);
    }
    @Test
    public void gameShouldNotBeOver(){
        //Arrange
        String[][] map = new String[][]{
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "PB", "PB", "" }
        };
        Game game = new Game();
        game.setMap(map);
        //Act
        boolean gameOver =  game.checkGameOver();
        //Assert
        Assert.assertFalse(gameOver);
    }
    @Test
    public void feedbackMessageShouldBeShipDestroyed(){
        //Arrange
        String[][] map = new String[][]{
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "BS", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "PB", "PB", "", "" }
        };
        int[][] hitMap = new int[][]{
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,1,1,1,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 }
        };
        Game game = new Game();
        game.setMap(map);
        game.setHitMap(hitMap);
        AttackFeedback feedback = game.receiveAttack(5,5);
        //Act
        String feedbackMessage = game.checkAttackFeedback(5,5,feedback);
        //Assert
        Assert.assertEquals("You destroyed a ship !!", feedbackMessage);
    }
    @Test
    public void feedbackMessageShouldBeYouHitTheOcean(){
        //Arrange
        String[][] map = new String[][]{
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "BS", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "PB", "PB", "", "" }
        };
        int[][] hitMap = new int[][]{
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,1,1,1,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 }
        };
        Game game = new Game();
        game.setMap(map);
        game.setHitMap(hitMap);
        AttackFeedback feedback = game.receiveAttack(6,1);
        //Act
        String feedbackMessage = game.checkAttackFeedback(6,1,feedback);
        //Assert
        Assert.assertEquals("You hit the ocean :(", feedbackMessage);
    }
    @Test
    public void shipShouldBePositioned() throws Exception {
        //Arrange
        Game game = new Game();
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        aircraftCarrier.setPositionX(0);
        aircraftCarrier.setPositionY(0);
        //Act
        boolean shipIsPositioned = game.positionShip(aircraftCarrier);
        //Assert
        Assert.assertTrue(shipIsPositioned);
    }
    @Test
    public void shipShouldNotBePositioned() throws Exception {
        //Arrange
        String[][] map = new String[][]{
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "BS", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "PB", "PB", "", "" }
        };
        Game game = new Game();
        game.setMap(map);
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        aircraftCarrier.setPositionX(5);
        aircraftCarrier.setPositionY(3);
        //Act
        boolean shipIsPositioned = game.positionShip(aircraftCarrier);
        //Assert
        Assert.assertFalse(shipIsPositioned);
    }
}
