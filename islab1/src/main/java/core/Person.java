package core;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null
    private String passportID; //Длина строки должна быть не меньше 8, Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Country nationality; //Поле может быть null
}
