package sda;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException, WrongParameterException {

        IFighter fighter1 = new Boxer(null, 5, 30);
        IFighter fighter2 = new Boxer("Adam Malysz", 40, 5);
        IFighter miszczu = new HumanFighter("Adam", 200, 60);

        IFight fight = new BoxingMatch(miszczu, fighter2);
        fight.fight();
    }
}
