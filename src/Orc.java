public class Orc extends Character implements Playable{

    final int maxHealth = 85;
    private boolean isAbilityOn;
    public Orc(String name){
        this.setName(name);
        this.setHealth(maxHealth);
        this.setDamage(18);
        this.isAbilityOn=false;
        this.type="orc";
    }
    public String getType(){return type;}
    public boolean getIsAbilityOn() {return isAbilityOn;}

    @Override
    void takeDamage(int damage){
        int currentHealth = this.getHealth();
        this.setHealth(currentHealth-=damage);
    }

    @Override
    void useAbility(Character target){  //Увеличава нанесена щета
        isAbilityOn=true;
    }

    @Override
    boolean isAlive(){
        return this.getHealth() > 0;
    }

    @Override
    void showInfo(){
        System.out.println("Orc{" +
                "Name: " + this.getName()  +
                "   Health: " + this.getHealth()+
                "   Damage: " + this.getDamage() +
                '}');
    }

    @Override
    public void attack(Character target){
        if(isAbilityOn) {
            target.takeDamage(getDamageAfterAbility());
            isAbilityOn = false;
        }
        else
            target.takeDamage(this.getDamage());
    }
    int getDamageAfterAbility(){
        return this.getDamage()*2 + 4;
    }

}
