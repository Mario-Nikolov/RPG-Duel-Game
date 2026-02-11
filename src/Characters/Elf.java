package Characters;

import java.io.PrintWriter;
import java.util.Random;

public class Elf extends Character implements Playable{

    final int maxHealth = 100;
    public Elf(String name){
        setName(name);
        setDamage(16);
        setHealth(maxHealth);
        setAbilityOn(false);
        type=CharacterType.ELF;
    }
    @Override
    void takeDamage(int damage){
        int currentHealth = getHealth();
        if(getIsAbilityOn()) {
            System.out.println(getName()+ " -"+ reduceEnemyDamage(damage) + "HP");
            setHealth(currentHealth-reduceEnemyDamage(damage));
           setAbilityOn(false);
        }
        else{
            System.out.println(getName()+ " -"+ damage + "HP");
            setHealth(currentHealth-=damage);
        }

    }
    @Override
    public boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    public void useAbility(){//Включва абилити
        if(!getIsAbilityOn())
            setAbilityOn(true);
        System.out.println(this.getName() + " reduced damage taken from the hext hit!");
    }

    @Override
    public void showInfo(PrintWriter out){
        out.println("Characters.Elf{" +
                "Name: " + getName() +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }
    @Override
    public void attack(Character target){
        target.takeDamage(getDamage());
    }

    public int reduceEnemyDamage(int enemyDamage){
        return enemyDamage-4;
    }

    //Бот логика
    @Override
    public void botTurn(Character enemy, Random random) {
        //Ако абилитито е вече включено -> атака
        if (this.getIsAbilityOn()) {
            this.attack(enemy);
            return;
        }

        //Ако животът на истинският играч е по-нисък от демиджа на бота -> атака
        boolean canKillNow = this.getDamage() >= enemy.getHealth();
        if (canKillNow) {
            //Добавяме малък шанс за грешка на бота
            if (random.nextDouble() < 0.90) this.attack(enemy);
            else this.useAbility();
            return;
        }

        //Ако избегне удар и може да убие в следващ ход -> абилити
        else if (this.getHealth() > this.reduceEnemyDamage(enemy.getDamage()) && this.getDamage() >= enemy.getHealth()) {
            if (random.nextDouble() < 0.8) this.useAbility();
            else this.attack(enemy);
            return;
        }
        else this.attack(enemy);

    }

}
