package sda;

import java.util.Scanner;

public class HumanFighter implements IFighter {
    private String name;
    private int hp;
    private final double maxHp;
    private int strange;
    private int stamina = 30;

    Scanner scanner = new Scanner(System.in);

    public HumanFighter(String name, int hp, int strange) throws WrongParameterException {
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
    public AttackType getAttackAction() {
        while (true) {
            String attackType = scanner.nextLine();
            switch (attackType) {
                case "HOOK": return AttackType.HOOK;
                case "JAB": return AttackType.JAB;
                case "UPPERCUT": return AttackType.UPPERCUT;
                default:
                    System.out.println("Wrong type of attack. You can use HOOK, JAB or UPPERCUT");
            }
        }
    }

    @Override
    public BlockType getBlockAction() {
        while (true) {
            String blockType = scanner.nextLine();
            switch (blockType) {
                case "DODGE": return BlockType.DODGE;
                case "LOW": return BlockType.LOW;
                case "HIGH": return BlockType.HIGH;
                default:
                    System.out.println("Wrong type of block. You can use DODGE, LOW or HIGH");
            }
        }
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
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void decreaseHp(int i) {
        setHp(getHp() - i);
    }
}
