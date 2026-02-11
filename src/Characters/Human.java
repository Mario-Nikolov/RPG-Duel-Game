package Characters;

import java.io.PrintWriter;
import java.util.Random;

public class Human extends Character implements Playable{

    public final int maxHealth = 120;
    public Human(String name){
        setHealth(maxHealth);
        setName(name);
        setDamage(12);
        type=CharacterType.HUMAN;

    }

    @Override
    void takeDamage(int damage){
        System.out.println(getName()+ " -"+ damage + "HP");
        int currentHealth = getHealth();
        setHealth(currentHealth-=damage);
    }

    @Override
    public boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    public void useAbility(){  //Healing
        setHealth(increaseHealth());
    }
    public int increaseHealth(){
        int currentHealth = getHealth();
        System.out.println(getName() + " healed! (+12HP) ");
        return currentHealth+=12;
    }

    @Override
    public void showInfo(PrintWriter out){
        out.println("Characters.Human{" +
                "Name: " + getName()  +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }
    @Override
    public void attack(Character target){
        target.takeDamage(getDamage());
    }

    @Override
    public void botTurn(Character enemy, Random random){
        //Ако ботът може да убиe истинският играч в един ход -> атака
        if(this.getDamage()>enemy.getHealth()){
            if(random.nextDouble()<0.75) this.attack(enemy);
            else this.useAbility();
            return;
        }

        //Ако животът на ботът е над 75% -> атака
        else if ((double) this.maxHealth / this.getHealth()>=0.75) {
            if(random.nextDouble()<0.8)this.attack(enemy);
            else this.useAbility();
            return;
        }

        //Ако хийл ще спаси бота от смърт при 2 удара на играча -> абилити
        else if(this.increaseHealth()>enemy.getDamage()*2){
            if(random.nextDouble()<0.9) this.useAbility();
            else this.attack(enemy);
            return;
        }

        else this.attack(enemy);
    }

}
