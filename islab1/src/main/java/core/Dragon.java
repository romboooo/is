package core;

public class Dragon {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private DragonCave cave; //Поле может быть null
    private Person killer; //Поле может быть null
    private int age; //Значение поля должно быть больше 0
    private Float weight; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;

    public Dragon(long id,
                  String name,
                  Coordinates coordinates,
                  DragonCave cave,
                  Person killer,
                  int age,
                  Float weight,
                  Color color,
                  DragonCharacter character,
                  DragonHead head) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.cave = cave;
        this.killer = killer;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.character = character;
        this.head = head;
        this.creationDate = java.time.LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public DragonCave getCave() {
        return cave;
    }
    public void setCave(DragonCave cave) {
        this.cave = cave;
    }
    public Person getKiller() {
        return killer;
    }
    public void setKiller(Person killer) {
        this.killer = killer;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Float getWeight() {
        return weight;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public DragonHead getHead() {
        return head;
    }
    public void setHead(DragonHead head) {
        this.head = head;
    }
    public DragonCharacter getCharacter() {
        return character;
    }
    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }
    public static class Checker{
        public boolean isIDValid(long id){
            return id > 0;
        }
        public boolean isNameValid(String name){
            return name != null && !name.isEmpty();
        }
        public boolean isCoordinatesValid(Coordinates coordinates){
            return coordinates != null;
        }
        public boolean isAgeValid(int age){
            return age > 0;
        }
        public boolean isWeightValid(Float weight){
            return weight > 0;
        }
    }
}
