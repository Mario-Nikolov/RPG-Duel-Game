public class Human extends Character implements Playable{

    final int maxHealth = 120;
    public Human(String name){
        setHealth(maxHealth);
        setName(name);
        setDamage(12);
        type="human";

    }
    public String getType(){return type;}
    @Override
    void takeDamage(int damage){
        System.out.println(getName()+ " -"+ damage + "HP");
        int currentHealth = getHealth();
        setHealth(currentHealth-=damage);
    }

    @Override
    boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    void useAbility(){  //Healing
        setHealth(increaseHealth());
    }
    int increaseHealth(){
        int currentHealth = getHealth();
        System.out.println(getName() + " healed! (+12HP) ");
        return currentHealth+=12;
    }

    @Override
    void showInfo(){
        System.out.println("Human{" +
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
