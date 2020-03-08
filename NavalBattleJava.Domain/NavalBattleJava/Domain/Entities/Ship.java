package NavalBattleJava.Domain.Entities;

public abstract class Ship {
    private int size;
    private boolean destroyed = false;
    private int positionX;
    private int positionY;

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    public String getName() {
        return name;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) throws Exception {
        if(size > 5)
            throw new Exception("Invalid size");
        this.size = size;
    }

    public void setDestroyed() {
        this.destroyed = true;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) throws Exception {
        if (positionX > 9 || positionX < 0)
            throw new Exception("Invalid position");
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) throws Exception {
        if (positionY > 9 || positionY < 0)
            throw new Exception("Invalid position");
        this.positionY = positionY;
    }
}
