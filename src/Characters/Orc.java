package Characters;

import java.io.PrintWriter;
import java.util.Random;

public class Orc extends Character implements Playable{

    final int maxHealth = 85;
    public Orc(String name){
        setName(name);
        setHealth(maxHealth);
        setDamage(18);
        setAbilityOn(false);
        type=CharacterType.ORC;
    }

    @Override
    void takeDamage(int damage){
        System.out.println(getName()+ " -"+ damage + "HP");
        int currentHealth = getHealth();
        setHealth(currentHealth-=damage);
    }

    @Override
    public void useAbility(){  //Увеличава нанесена щета
        if(!getIsAbilityOn()) setAbilityOn(true);
        System.out.println(getName() + " increased it's damage!");
    }

    @Override
    public boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    public void showInfo(PrintWriter out){
        out.println("Orc{" +
                "Name: " + getName()  +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }

    @Override
    public void attack(Character target){
        if(getIsAbilityOn()) {
            target.takeDamage(getDamageAfterAbility());
            setAbilityOn(false);
        }
        else {
            target.takeDamage(getDamage());
        }
    }
    public int getDamageAfterAbility(){
        return this.getDamage()*2 + 4;
    }

    @Override
    public void botTurn(Character enemy, Random random){
        //Ако ботът може да убиe истинският играч в един ход -> атака
        if(this.getDamage()>=enemy.getHealth()){
            if(random.nextDouble()<0.75)this.attack(enemy);
            else this.useAbility();
            return;
        }

        //Ако абилитито е вече включено -> атака
        else if(this.getIsAbilityOn()) {
            this.attack(enemy);
            return;
        }

        //Ако ботът ще умре при следващ удар на противника -> атака
        else if(this.getHealth()<= enemy.getDamage()) {
            this.attack(enemy);
            return;
        }

        //Ако ботът може да убие след като ползва абилити -> абилити
        else if(this.getDamageAfterAbility()>=enemy.getHealth() &&
                this.getDamage()*2<enemy.getHealth()){

            //80% шанс това да се случи за да бъде бота малко по-реалистичен
            if(random.nextDouble()<0.8) this.useAbility();
            else this.attack(enemy);
            return;
        }

        else this.attack(enemy);
    }

}
