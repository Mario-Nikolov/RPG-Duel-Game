import java.util.Random;

public class BotMoves {
    private static final Random random = new Random();

    static void elfTurn(Elf bot, Character realPlayer){

        //Ако абилитито е вече включено -> атака
        if(bot.getIsAbilityOn()){
            bot.attack(realPlayer);
            return;
        }

        //Ако животът на истинският играч е по-нисък от демиджа на бота -> атака
        boolean canKillNow = bot.getDamage()>= realPlayer.getHealth();
        if(canKillNow){
            //Добавяме малък шанс за грешка на бота
            if(random.nextDouble()<0.90)bot.attack(realPlayer);
            else bot.useAbility();
            return;
        }

        //Ако избегне удар и може да убие в следващ ход -> абилити
        else if(bot.getHealth()> bot.reduceEnemyDamage(realPlayer.getDamage()) && bot.getDamage()>= realPlayer.getHealth()) {
            if (random.nextDouble() < 0.8) bot.useAbility();
            else bot.attack(realPlayer);
            return;
        }

        else bot.attack(realPlayer);
    }

    static void humanTurn(Human bot, Character realPlayer){

        //Ако ботът може да убиe истинският играч в един ход -> атака
        if(bot.getDamage()>realPlayer.getHealth()){
            if(random.nextDouble()<0.75) bot.attack(realPlayer);
            else bot.useAbility();
            return;
        }

        //Ако животът на ботът е над 75% -> атака
        else if ((double) bot.maxHealth / bot.getHealth()>=0.75) {
            if(random.nextDouble()<0.8)bot.attack(realPlayer);
            else bot.useAbility();
            return;
        }

        //Ако хийл ще спаси бота от смърт при 2 удара на играча -> абилити
        else if(bot.increaseHealth()>realPlayer.getDamage()*2){
            if(random.nextDouble()<0.9) bot.useAbility();
            else bot.attack(realPlayer);
            return;
        }

        else bot.attack(realPlayer);
    }

    static void orcTurn(Orc bot, Character realPlayer){

        //Ако ботът може да убиe истинският играч в един ход -> атака
        if(bot.getDamage()>=realPlayer.getHealth()){
            if(random.nextDouble()<0.75)bot.attack(realPlayer);
            else bot.useAbility();
            return;
        }

        //Ако абилитито е вече включено -> атака
        else if(bot.getIsAbilityOn()) {
            bot.attack(realPlayer);
            return;
        }

        //Ако ботът ще умре при следващ удар на противника -> атака
        else if(bot.getHealth()<= realPlayer.getDamage()) {
            bot.attack(realPlayer);
            return;
        }

        //Ако ботът може да убие след като ползва абилити -> абилити
        else if(bot.getDamageAfterAbility()>=realPlayer.getHealth() &&
                bot.getDamage()*2<realPlayer.getHealth()){

                //80% шанс това да се случи за да бъде бота малко по-реалистичен
                if(Math.random()<0.8) bot.useAbility();
                else bot.attack(realPlayer);
                return;
        }

        else bot.attack(realPlayer);
    }
}
