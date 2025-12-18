public class Human extends Character implements Playable{

    final int maxHealth = 120;
    public Human(String name){
        this.setHealth(maxHealth);
        this.setName(name);
        this.setDamage(12);
        this.type="human";

    }
    public String getType(){return type;}
    @Override
    void takeDamage(int damage){
        int currentHealth  =this.getHealth();
        this.setHealth(currentHealth-=damage);
    }

    @Override
    boolean isAlive(){
        return this.getHealth() > 0;
    }

    @Override
    void useAbility(Character target){  //Healing
        this.setHealth(increaseHealth());
    }
    int increaseHealth(){
        int currentHealth = this.getHealth();
        return currentHealth+=12;
    }

    @Override
    void showInfo(){
        System.out.println("Human{" +
                "Name: " + this.getName()  +
                "   Health: " + this.getHealth()+
                "   Damage: " + this.getDamage() +
                '}');
    }
    @Override
    public void attack(Character target){
        target.takeDamage(this.getDamage());
    }

}
