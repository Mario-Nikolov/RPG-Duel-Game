package Characters;

import java.io.PrintWriter;

public class Human extends Character implements Playable{

    final int maxHealth = 120;
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
    int increaseHealth(){
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

}
