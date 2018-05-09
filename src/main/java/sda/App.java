package sda;

public class App {
    public static void main(String[] args) throws InterruptedException, WrongParameterException {

        IFighter fighter1 = new Boxer("adsf", 50, 30);
        IFighter fighter2 = new Boxer("Adam Malysz", 40, 5);
        IFighter miszczu = new HumanFighter("Adam", 200, 60);

        IFight fight = new BoxingMatch(fighter1, fighter2);
        fight.fight();
    }
}
