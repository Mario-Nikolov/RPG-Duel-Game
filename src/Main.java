public class Main{
    public static void main(String[] args){

        //Human human1= new Human("Ivancho");
        //Elf elf1 = new Elf("Dragancho");
        //Orc orc = new Orc("Urod");

        Character bot = Plays.botCharacter();
        Character myCharacter = Plays.chooseCharacter();

        System.out.println("\nYou character: ");
        myCharacter.showInfo();

        System.out.println("\nOpponent character: ");
        bot.showInfo();
        System.out.println(" ");

        //Start game
        Plays.startGame(myCharacter,bot);
    }

}
