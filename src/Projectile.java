public class Projectile {
    private int power;
    private int damage;
    private double angle;
    private int x;
    private int y;

    public Projectile(int pow, int dam, double ang, int startX, int startY) {
        power = pow;
        damage = dam;
        angle = ang;
        x = startX;
        y = startY;
    }
}
