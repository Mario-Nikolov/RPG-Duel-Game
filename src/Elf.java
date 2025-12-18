public class Elf extends Character implements Playable{

    private boolean isAbilityOn;

    final int maxHealth = 100;
    public Elf(String name){
        this.setName(name);
        this.setDamage(16);
        this.setHealth(maxHealth);
        this.isAbilityOn=false;
        this.type="elf";
    }
    public boolean getIsAbilityOn() {return isAbilityOn;}
    public String getType(){return type;}

    @Override
    void takeDamage(int damage){
        int currentHealth = this.getHealth();
        if(isAbilityOn) {
            this.setHealth(reduceEnemyDamage(damage));
            isAbilityOn=false;
        }
        else
            this.setHealth(currentHealth-=damage);
    }
    @Override
    boolean isAlive(){
        return this.getHealth() > 0;
    }
    @Override
    void useAbility(Character target){  //Включва абилити
        if(!isAbilityOn)
            isAbilityOn=true;
    }
    int reduceEnemyDamage(int enemyDamage){
        return enemyDamage-4;
    }
    @Override
    void showInfo(){
        System.out.println("Elf{" +
                "Name: " + this.getName() +
                "   Health: " + this.getHealth()+
                "   Damage: " + this.getDamage() +
                '}');
    }
    @Override
    public void attack(Character target){
        target.takeDamage(this.getDamage());
    }



}
