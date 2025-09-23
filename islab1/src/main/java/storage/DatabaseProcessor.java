package storage;

import core.*;
import conf.DBConfig;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseProcessor {
    Connection connection;

    public DatabaseProcessor() {
        this.connection = connectToDB();
    }

    private static final String URL = DBConfig.getDbUrl();
    private static final String USER = DBConfig.getDbUser();
    private static final String PASSWORD = DBConfig.getDbPassword();

    public Connection connectToDB() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connected to PostgreSQL database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

        return connection;
    }

    public ArrayList<Dragon> getDragons() {
        ArrayList<Dragon> dragons = new ArrayList<>();
        String query = "SELECT * FROM dragon";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // Проверяем, есть ли данные
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No dragons found in database!");
                return dragons;
            }

            while (resultSet.next()) {
                try {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    Coordinates coordinates = getCoordinatesByID(id);
                    DragonCave cave = getDragonCaveByID(id);
                    Person person = getPersonByID(id);

                    // Проверяем обязательные поля
                    if (name == null || coordinates == null || cave == null) {
                        System.out.println("Skipping dragon with id " + id + " due to missing required data");
                        continue;
                    }

                    int age = resultSet.getInt("age");
                    Float weight = resultSet.getFloat("weight");

                    Color color = null;
                    String colorStr = resultSet.getString("color");
                    if (colorStr != null) {
                        try {
                            color = Color.valueOf(colorStr.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid color value for dragon " + id + ": " + colorStr);
                        }
                    }

                    DragonCharacter dragonCharacter = null;
                    String dragonCharacterStr = resultSet.getString("character");
                    if (dragonCharacterStr != null) {
                        try {
                            dragonCharacter = DragonCharacter.valueOf(dragonCharacterStr.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid character value for dragon " + id + ": " + dragonCharacterStr);
                        }
                    }

                    DragonHead head = getDragonHead(id);
                    java.time.LocalDateTime date = resultSet.getTimestamp("creation_date").toLocalDateTime();

                    Dragon dragon = new Dragon(id, name, coordinates, cave, person, age, weight, color, dragonCharacter, head, date);
                    dragons.add(dragon);
                    System.out.println("Successfully loaded dragon: " + name + " (ID: " + id + ")");

                } catch (Exception e) {
                    System.out.println("Error processing dragon record: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while getting dragons: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Total dragons loaded: " + dragons.size());
        return dragons;
    }

    public DragonHead getDragonHead(long id) {
        String query = "SELECT * FROM dragon_head WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Float headId = resultSet.getFloat("tooth_count");
                return new DragonHead(headId);
            }
        }catch (SQLException e){
            System.out.println("Error while getting dragon head by ID: " + e.getMessage());
        }
        return null;
    }

    public Person getPersonByID(long id) {
        String query = "SELECT * FROM person WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Color eyeColor = null;

                String eyeColorStr = resultSet.getString("eye_color");
                if (eyeColorStr != null && !resultSet.wasNull()) {
                    try {
                        eyeColor = Color.valueOf(eyeColorStr.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid eye color value: " + eyeColorStr);
                    }
                }

                Color hairColor = null;
                String hairColorStr = resultSet.getString("hair_color");
                try {
                    hairColor = Color.valueOf(hairColorStr.trim().toUpperCase());
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println("Invalid hair color value: " + hairColorStr);
                }

                long locationId = resultSet.getLong("location_id");
                Location location = getLocationByID(locationId);
                if (location == null) {
                    System.out.println("Location not found for ID: " + locationId);
                }

                String passportID = resultSet.getString("passport_id");

                Country nationality = null;
                String nationalityStr = resultSet.getString("nationality");
                if (nationalityStr != null && !resultSet.wasNull()) {
                    try {
                        nationalityStr = nationalityStr.trim().toUpperCase().replace(" ", "_");
                        nationality = Country.valueOf(nationalityStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid nationality value: " + nationalityStr);
                    }
                }

                return new Person(name, eyeColor, hairColor, location, passportID, nationality);
            }
        }catch (SQLException e){
            System.out.println("Error while getting person by ID: " + e.getMessage());
        }
        return null;



    }

    public DragonCave getDragonCaveByID(long id) {
        String query = "SELECT * FROM dragon_cave WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new DragonCave(resultSet.getInt("number_of_treasures"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting Dragon Cave by ID: " + e.getMessage());
        }
        return null;
    }

    public Coordinates getCoordinatesByID(long id) {
        String query = "SELECT * FROM coordinates WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int x = resultSet.getInt("x");
                long y = resultSet.getLong("y");
                return new Coordinates(x, y);
            }

        } catch (SQLException e) {
            System.out.println("Error while getting coordinates by ID: " + e.getMessage());
        }
        return null;
    }

    public Location getLocationByID(long id) {
        String query = "SELECT * FROM location WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long x = resultSet.getLong("x");
                double y = resultSet.getDouble("y");
                long z = resultSet.getLong("z");
                return new Location(x, y, z);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting location by ID: " + e.getMessage());
        }
        return null;
    }
}
