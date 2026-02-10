package Characters;

import java.io.PrintWriter;

public class Orc extends Character implements Playable{

    final int maxHealth = 85;
    private boolean isAbilityOn;
    public Orc(String name){
        setName(name);
        setHealth(maxHealth);
        setDamage(18);
        isAbilityOn=false;
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
        if(!isAbilityOn) isAbilityOn=true;
        System.out.println(getName() + " increased it's damage!");
    }

    @Override
    public boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    public void showInfo(PrintWriter out){
        out.println("Characters.Orc{" +
                "Name: " + getName()  +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }

    @Override
    public void attack(Character target){
        if(isAbilityOn) {
            target.takeDamage(getDamageAfterAbility());
            isAbilityOn = false;
        }
        else {
            target.takeDamage(getDamage());
        }
    }
    int getDamageAfterAbility(){
        return this.getDamage()*2 + 4;
    }

}
