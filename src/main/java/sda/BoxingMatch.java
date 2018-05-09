package sda;

import java.util.Random;
import java.util.Scanner;

public class BoxingMatch implements IFight {

    private IFighter boxer1;
    private IFighter boxer2;

    public BoxingMatch(IFighter boxer1, IFighter boxer2) {
        this.boxer1 = boxer1;
        this.boxer2 = boxer2;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void fight() throws InterruptedException {

        IFighter first;
        IFighter second;

        Random random = new Random();
        if (random.nextInt() % 2 == 0) {
            first = boxer1;
            second = boxer2;
        } else {
            first = boxer2;
            second = boxer1;
        }
        System.out.println("First boxer is: " + first.getName());

        boolean isFightOver = false;

        IFighter winner = null;
        while (!isFightOver) {
            Thread.sleep(2000);
            AttackType attack_f1 = first.getAttackAction();
            BlockType block_f2 = second.getBlockAction();
            HitOutcome hitOutcome= isHitSuccessfull(attack_f1, block_f2);
            int damage = checkDamage(first, second, hitOutcome);
            System.out.println(first.getName() + ": " + attack_f1 + " | " + block_f2 + " :" + second.getName() + ". Damage is: " + damage);

//            System.out.println(first.getName() + ": " + first.getHp() + " | " + second.getHp() + " :" + second.getName());
            System.out.println(first.getName() + ": " + showActualHp(first) + " # " + showActualHp(second) + " :" + second.getName());
            if (!second.isAlive()) {
                winner = first;
                break;
            }
            Thread.sleep(2000);
            AttackType attack_f2 = second.getAttackAction();
            BlockType block_f1 = first.getBlockAction();
            hitOutcome = isHitSuccessfull(attack_f2, block_f1);
            damage = checkDamage(second, first, hitOutcome);

            System.out.println(second.getName() + ": " + attack_f2 + " | " + block_f1 + " :" + first.getName() + ". Damage is: " + damage);
            System.out.println(first.getName() + ": " + showActualHp(first) + " # " + showActualHp(second) + " :" + second.getName());
            if (!first.isAlive()) {
                winner = second;
                break;
            }
            first.decreaseStamina(-2);
            second.decreaseStamina(-2);
        }
        System.out.println("The winner is " + winner.getName());
    }

    private String showActualHp(IFighter fighter) {
        int index = 0;
        StringBuilder result = new StringBuilder();
        if (fighter.getHp() > 0.9 * fighter.getMaxHP()) index = 10;
        else if (fighter.getHp() > 0.8 * fighter.getMaxHP()) index = 9;
        else if (fighter.getHp() > 0.7 * fighter.getMaxHP()) index = 8;
        else if (fighter.getHp() > 0.6 * fighter.getMaxHP()) index = 7;
        else if (fighter.getHp() > 0.5 * fighter.getMaxHP()) index = 6;
        else if (fighter.getHp() > 0.4 * fighter.getMaxHP()) index = 5;
        else if (fighter.getHp() > 0.3 * fighter.getMaxHP()) index = 4;
        else if (fighter.getHp() > 0.2 * fighter.getMaxHP()) index = 3;
        else if (fighter.getHp() > 0.1 * fighter.getMaxHP()) index = 2;
        else if (fighter.getHp() > 0) index = 1;

        result.append("|");
        for (int i = 0; i < index; i++) {
            result.append("X");
        }
        while (result.length() < 10) {
            result.append(" ");
        }
        result.append("|");
        return result.toString();
    }

    private int checkDamage(IFighter attacker, IFighter casualty, HitOutcome hitOutcome) {
        int damage = 0;
        int stamina = 0;
        int killer = 2;

        if (HitOutcome.FULL.equals(hitOutcome)) {
            damage = 5;
            stamina = 2;
        }
        if (HitOutcome.PARTIPAL.equals(hitOutcome)) {
            damage = 2;
            stamina = 3;
        }
        if (HitOutcome.DODGED.equals(hitOutcome)) {
            damage = 0;
            stamina = 5;
        }
        if (attacker.getStamina() < 15) damage /= 2;
        if (casualty.getHp() < 10) damage += killer;
        damage += attacker.getStrange() / 10;
        casualty.decreaseHp(damage);
        attacker.decreaseStamina(stamina);
        return damage;
    }

    private HitOutcome isHitSuccessfull(AttackType attack_f1, BlockType block_f2) {
        if ((AttackType.JAB.equals(attack_f1) || AttackType.HOOK.equals(attack_f1)) && BlockType.HIGH.equals(block_f2)) return HitOutcome.PARTIPAL;
        if (AttackType.UPPERCUT.equals(attack_f1) && BlockType.LOW.equals(block_f2)) return HitOutcome.PARTIPAL;

        if ((AttackType.JAB.equals(attack_f1) || AttackType.HOOK.equals(attack_f1)) && BlockType.LOW.equals(block_f2)) return HitOutcome.FULL;
        if (AttackType.UPPERCUT.equals(attack_f1) && BlockType.HIGH.equals(block_f2)) return HitOutcome.FULL;

        return HitOutcome.DODGED;
    }
}
