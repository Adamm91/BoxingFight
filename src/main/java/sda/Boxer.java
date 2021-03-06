package sda;

import java.util.Random;

public class Boxer implements IFighter {
    private String name;
    private int hp;
    private final double maxHp;
    private int strange;
    private int stamina = 30;

    public Boxer(String name, int hp, int strange) throws WrongParameterException {
        validateParameters(name, hp, strange);
        this.name = name;
        this.hp = hp;
        this.strange = strange;
        this.maxHp = hp;
    }

    private void validateParameters(String name, int hp, int strange) throws WrongParameterException {
        if (name == null || name.length() == 0) throw new WrongParameterException("Wrong name");
        if (hp <= 0) throw new WrongParameterException("Wrong HP value");
        if (strange <= 0) throw new WrongParameterException("Wrong Strange value");
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public int getStrange() {
        return strange;
    }

    @Override

    public int getStamina() {
        return stamina;
    }

    @Override
    public void decreaseStamina(int stamina) {
        setStamina(getStamina() - stamina);
    }

    @Override
    public double getMaxHP() {
        return maxHp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public AttackType getAttackAction() {
        Random random = new Random();
        int i = random.nextInt(2);
        switch (i) {
            case 1 : return AttackType.HOOK;
            case 2 :return AttackType.JAB;
            default: return AttackType.UPPERCUT;
        }
    }

    @Override
    public BlockType getBlockAction() {
        Random random = new Random();
        int i = random.nextInt(100);
        if (i <= 40) return BlockType.LOW;
        if (i > 40 && i <= 80) return BlockType.HIGH;
        return BlockType.DODGE;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void decreaseHp(int i) {
        setHp(getHp() - i);
    }
}
