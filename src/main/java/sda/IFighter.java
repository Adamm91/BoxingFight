package sda;

public interface IFighter {
    String getName();
    int getHp();
    AttackType getAttackAction();
    BlockType getBlockAction();
    boolean isAlive();
    void decreaseHp(int damage);
    int getStrange();
    int getStamina();
    void decreaseStamina(int Stamina);
    double getMaxHP();
}
