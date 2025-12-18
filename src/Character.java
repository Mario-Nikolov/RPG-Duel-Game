abstract   class Character implements Playable {
    protected String type;
    private String name;  //Името на героя
    private int health;     //живот на героя
    private int damage;     //щетата която нанася

    public String getType(){return type;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    //Meтод за поемане на щета
    abstract void takeDamage( int damage);

    //Метод за проверка дали героят е жив
    abstract boolean isAlive();

    //Метод за използване на специално умение
    abstract void useAbility(Character target);

    //Метод за показвеане на информацията за героя
    abstract void showInfo();


}
