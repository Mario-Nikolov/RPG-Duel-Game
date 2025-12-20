public class Orc extends Character implements Playable{

    final int maxHealth = 85;
    private boolean isAbilityOn;
    public Orc(String name){
        setName(name);
        setHealth(maxHealth);
        setDamage(18);
        isAbilityOn=false;
        type="orc";
    }
    public String getType(){return type;}
    public boolean getIsAbilityOn() {return isAbilityOn;}

    @Override
    void takeDamage(int damage){
        int currentHealth = getHealth();
        setHealth(currentHealth-=damage);
    }

    @Override
    void useAbility(){  //Увеличава нанесена щета
        if(!isAbilityOn) isAbilityOn=true;
        System.out.println(getName() + " increased it's damage!");
    }

    @Override
    boolean isAlive(){
        return getHealth() > 0;
    }

    @Override
    void showInfo(){
        System.out.println("Orc{" +
                "Name: " + getName()  +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }

    @Override
    public void attack(Character target){
        if(isAbilityOn) {
            target.takeDamage(getDamageAfterAbility());
            System.out.println(getName() + " dealt "+ getDamageAfterAbility()+ " HP!");
            isAbilityOn = false;
        }
        else {
            target.takeDamage(getDamage());
            System.out.println(getName() + " dealt " + getDamage() + " HP!");
        }
    }
    int getDamageAfterAbility(){
        return this.getDamage()*2 + 4;
    }

}
