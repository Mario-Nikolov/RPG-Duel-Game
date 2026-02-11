package GameModes.SinglePlayer;

import Characters.*;

import java.io.*;
import java.util.Random;

public class Plays {
    public static final BufferedReader in;
    public static final Random random  =new Random();
    static {
        try {
            in = new BufferedReader(new InputStreamReader(SinglePlayer.clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(SinglePlayer.clientSocket.getOutputStream()), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Characters.Character chooseCharacter(BufferedReader in, PrintWriter out){

        //Scanner scanner  = new Scanner(System.in);
        out.println(" Characters.Human | Characters.Orc | Characters.Elf ");
        out.print("Choose character: ");

        while(true) {
            try {
                switch (in.readLine().toLowerCase()) {
                    case "human":
                        out.print("Enter it's name: ");
                        return new Human(in.readLine());

                    case "orc":
                        out.print("Enter it's name: ");
                        return new Orc(in.readLine());

                    case "elf":
                        out.print("Enter it's name: ");
                        return new Elf(in.readLine());

                    default:
                        throw new IllegalArgumentException("Invalid character type!");
                }
            } catch (IllegalArgumentException e) {
                out.println("Error: " + e.getMessage());
                out.println("Choose from: Characters.Human | Characters.Orc | Characters.Elf");
                out.print("Choose character: ");
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
        }
   }

   public static Characters.Character botCharacter(){
       Random random = new Random();
       int randomCharacter = random.nextInt(1,4);

       switch(randomCharacter){
           case 1 ->{return new Human(CharacterNames.NAMES_BY_TYPE.get("Characters.Human").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Characters.Human").size())));}
           case 2 -> {return new Orc(CharacterNames.NAMES_BY_TYPE.get("Characters.Orc").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Characters.Orc").size())));}
           case 3 -> {return new Elf(CharacterNames.NAMES_BY_TYPE.get("Characters.Elf").get(random.nextInt(CharacterNames.NAMES_BY_TYPE.get("Characters.Elf").size())));}
       }
       return null;
   }

   public static void yourTurn(Characters.Character yourCharacter, Characters.Character enemyCharacter) {

       //Scanner scanner = new Scanner(System.in);
       out.println("It's your turn: ");
       if(yourCharacter.getIsAbilityOn())
           out.println("Ability is already on! \n 1.Attack ");
       else
           out.println("1.Attack\n2.Special ability");
       out.print("Your choice: ");

       while (true) {
           try  {
               int choice = Integer.parseInt(in.readLine());

               switch (choice) {
                   case 1 -> {
                       out.println(" ");
                       yourCharacter.attack(enemyCharacter);
                       return;
                   }
                   case 2 -> {
                       out.println(" ");
                       if (yourCharacter.getIsAbilityOn())  throw new IllegalArgumentException("\nInvalid choice! ");
                       else yourCharacter.useAbility();
                       return;
                   }
                   default -> {
                       out.println(" ");
                       throw new IllegalArgumentException("\nInvalid choice! ");
                   }


               }
           } catch (Exception e) {
               out.println(e.getMessage());
               if(yourCharacter.getIsAbilityOn())
                   out.println("Ability is already on! \n 1.Attack ");
               else
                   out.println("1.Attack\n2.Special ability");
               System.out.print("Your choice: ");
           }
       }
   }

   public static boolean CheckIsAlive(Characters.Character character){
        if(!character.isAlive()) {
            out.println(character.getName() + " lost!");
            return false;
        }
        return true;
   }

   public static void showBothInfo(Characters.Character yourCharacter, Characters.Character enemyCharacter){
        yourCharacter.showInfo(out);
        enemyCharacter.showInfo(out);
   }

   public static void startGame(Characters.Character realPlayer, Characters.Character bot){

       while (true) {
           Plays.yourTurn(realPlayer, bot);
           if (!Plays.CheckIsAlive(bot)) break;

           out.println(" ");

           bot.botTurn(realPlayer,random);
           if (!Plays.CheckIsAlive(realPlayer)) break;

           out.println(" ");
           Plays.showBothInfo(realPlayer, bot);
           out.println(" ");
       }

   }

}
