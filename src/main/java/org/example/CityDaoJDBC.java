package org.example;

import java.sql.*;

public class CityDaoJDBC {

    private static String URL = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static String USERNAME = "root";
    private static String PASSWORD = "mysql";

//    City findById(int id);
//    List<City> findByCode(String code);
//    List<City> findByName(String name);
//    List<City> findAll();
//
//    City add(City city);
//    City update(City city);
//    int delete(City city);

    public static City add(City city) {
        return city;
    }

    public static City findById(int id) {
        City city = new City();
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM city WHERE id = " + id)
        ) {
            if (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setCountryCode(resultSet.getString("countryCode"));
                city.setDistrict(resultSet.getString("district"));
                city.setPopulation(resultSet.getInt("population"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }

}
