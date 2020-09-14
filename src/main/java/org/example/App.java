package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {  // Hello Cities in the WORLD

    public static void main(String[] args) {
        try {
            // first step
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin",
                    "root",
                    "mysql");
            // second step
            Statement statement = connection.createStatement();
            // write query
            String SELECT_CITY = "SELECT * FROM city LIMIT 3045, 25"; // Mostly Swedish cities
            //third step
            ResultSet resultSet = statement.executeQuery(SELECT_CITY);

            List<City> cityList = new ArrayList<>();
            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")));
            }

            cityList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(CityDaoJDBC.findById(3048).toString());
        City vxo = new City(4599, "Vaxjoe","SWE","Kronoberg", 95477);
        CityDaoJDBC.add(vxo);
    }
}
