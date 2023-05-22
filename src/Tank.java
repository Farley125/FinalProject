public class Tank {
    private int health;
    private int damage;
    private String type;
    private String name;
    private int x;
    private int y;

    public Tank(String type, String name, int x, int y) {
        this.type = type;
        this.name = name;
        this.x = x;
        this.y = y;
        if (type.equals("MBT")) {
            health = 300;
            damage = 50;
        } else if (type.equals("Light")) {
            health = 250;
            damage = 75;
        } else {
            health = 200;
            damage = 100;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
