import java.util.Random;
import java.util.Scanner;

public class Plays {

    public static Character chooseCharacter(){

        Scanner scanner  = new Scanner(System.in);
        System.out.println(" Human | Orc | Elf ");
        System.out.print("Choose character: ");

        while(true) {
            try {
                switch (scanner.nextLine().toLowerCase()) {
                    case "human":
                        System.out.print("Enter it's name: ");
                        return new Human(scanner.nextLine());

                    case "orc":
                        System.out.print("Enter it's name: ");
                        return new Orc(scanner.nextLine());

                    case "elf":
                        System.out.print("Enter it's name: ");
                        return new Elf(scanner.nextLine());

                    default:
                        throw new IllegalArgumentException("Invalid character type!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Choose from: Human | Orc | Elf");
                System.out.print("Choose character: ");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
   }

   public static Character botCharacter(){
       Random random = new Random();
       int randomCharacter = random.nextInt(1,4);

       switch(randomCharacter){
           case 1 ->{return new Human(CharacterNames.NAMES_BY_TYPE.get("Human").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Human").size())));}
           case 2 -> {return new Orc(CharacterNames.NAMES_BY_TYPE.get("Orc").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Orc").size())));}
           case 3 -> {return new Elf(CharacterNames.NAMES_BY_TYPE.get("Elf").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Elf").size())));}
       }
       return null;
   }

   public static void yourTurn(Character yourCharacter,Character enemyCharacter) {

       Scanner scanner = new Scanner(System.in);
       System.out.println("It's your turn: ");
       System.out.println("1.Attack\n2.Special ability");
       System.out.print("Your choice: ");

       while (true) {
           try  {
               int choice = scanner.nextInt();

               switch (choice) {
                   case 1 -> {
                       System.out.println(yourCharacter.getName()+" attacked! \n");
                       yourCharacter.attack(enemyCharacter);
                       return;
                   }
                   case 2 -> {
                       yourCharacter.useAbility();
                       return;
                   }
                   default -> {
                       throw new IllegalArgumentException("\nInvalid choice! ");
                   }


               }
           } catch (Exception e) {
               System.out.println(e.getMessage());
               System.out.println("1.Attack\n2.Special ability");
               System.out.print("Your choice: ");
               scanner.nextLine();
           }
       }
   }
   public static void botTurn(Character realPlayer, Character bot){
        Random random = new Random();


   }

   public static boolean CheckIsAlive(Character character){
        if(!character.isAlive()) {
            System.out.println(character.getName() + " lost!");
            return false;
        }
        return true;
   }

   public static void showBothInfo(Character yourCharacter, Character enemyCharacter){
        yourCharacter.showInfo();
        enemyCharacter.showInfo();
   }

   public static void startGame(Character realPlayer, Character bot){

        switch(bot.getType()){
            case "elf" -> {
                while(true){
                    Plays.yourTurn(realPlayer,bot);
                    if(!Plays.CheckIsAlive(bot))
                        break;

                    System.out.println(" ");

                    BotMoves.elfTurn((Elf) bot,realPlayer);
                    if(!Plays.CheckIsAlive(realPlayer))
                        break;

                    System.out.println(" ");
                    Plays.showBothInfo(realPlayer,bot);
                    System.out.println(" ");
                }
            }
            case "human" ->{
                while (true){
                    Plays.yourTurn(realPlayer,bot);
                    if(!Plays.CheckIsAlive(bot)) break;

                    System.out.println(" ");

                    BotMoves.humanTurn((Human) bot,realPlayer);
                    if(!Plays.CheckIsAlive(realPlayer)) break;

                    System.out.println(" ");
                    Plays.showBothInfo(realPlayer, bot);
                    System.out.println(" ");
                }
            }
            case "orc" -> {
                while (true){
                    Plays.yourTurn(realPlayer,bot);
                    if(!Plays.CheckIsAlive(bot)) break;

                    System.out.println(" ");

                    BotMoves.orcTurn((Orc) bot,realPlayer);
                    if(!Plays.CheckIsAlive(realPlayer)) break;

                    System.out.println(" ");
                    Plays.showBothInfo(realPlayer, bot);
                    System.out.println(" ");
                }
            }
        }
   }

}
