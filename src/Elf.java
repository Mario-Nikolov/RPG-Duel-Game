public class Elf extends Character implements Playable{

    private boolean isAbilityOn;

    final int maxHealth = 100;
    public Elf(String name){
        setName(name);
        setDamage(16);
        setHealth(maxHealth);
        isAbilityOn=false;
        type="elf";
    }
    public boolean getIsAbilityOn() {return isAbilityOn;}
    public String getType(){return type;}

    @Override
    void takeDamage(int damage){
        int currentHealth = getHealth();
        if(isAbilityOn) {
            setHealth(reduceEnemyDamage(damage));
            isAbilityOn=false;
        }
        else
            setHealth(currentHealth-=damage);
    }
    @Override
    boolean isAlive(){
        return getHealth() > 0;
    }
    @Override
    void useAbility(){//Включва абилити
        if(isAbilityOn)
            isAbilityOn=true;
        System.out.println(this.getName() + " reduced damage taken from the hext hit!");
    }
    int reduceEnemyDamage(int enemyDamage){
        return enemyDamage-4;
    }
    @Override
    void showInfo(){
        System.out.println("Elf{" +
                "Name: " + getName() +
                "   Health: " + getHealth()+
                "   Damage: " + getDamage() +
                '}');
    }
    @Override
    public void attack(Character target){
        target.takeDamage(getDamage());
    }



}
