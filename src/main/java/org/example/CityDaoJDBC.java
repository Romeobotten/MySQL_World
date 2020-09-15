package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC {

    private static String URL = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static String USERNAME = "root";
    private static String PASSWORD = "mysql";

//    All methods implemented, but Chaos if closing connections and statements or not.
//    Need to clean up try / catch / final
//    Need to change statement to prepared statment

//    City findById(int id);
//    List<City> findByCode(String code);
//    List<City> findByName(String name);
//    List<City> findAll();
//    City add(City city);
//    City update(City city);
//    int delete(City city);

    public static City add(City city) {

//        int rowsAffected = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            String insertvalues = "(" + city.getId() + ",'" + city.getName() + "','" + city.getCountryCode()
                    + "','" + city.getDistrict() + "'," + city.getPopulation() + ")";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.createStatement();
//            rowsAffected = statement.executeUpdate("INSERT INTO city VALUES ?");
            PreparedStatement sqlAddCity = connection.prepareStatement("INSERT INTO city VALUES ?");
            // sqlAddCity.setString(1, insertvalues);
            sqlAddCity.executeUpdate("INSERT INTO city (id, name, CountryCode, district, population) " +
                    "VALUES" + insertvalues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return city;
    }

    public static City update(City city) {

//        int rowsAffected = 0;
        Connection connection = null;
//        Statement statement = null;
        try {
            String insertValues = "UPDATE city SET name = '" + city.getName() + "', CountryCode = '" + city.getCountryCode() +
                    "', district = '" + city.getDistrict() + "', population = " + city.getPopulation() +
                    " WHERE id = ?";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.createStatement();
//            rowsAffected = statement.executeUpdate("INSERT INTO city VALUES ?");
            PreparedStatement sqlAddCity = connection.prepareStatement(insertValues);
            sqlAddCity.setInt(1, city.getId());
            sqlAddCity.executeUpdate();
//            System.out.println(insertValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (statement != null){
//                try {
//                    statement.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return city;
    }

    public static int delete(City city) {
        int rowsAffected = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate("DELETE FROM city WHERE id = " + city.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return rowsAffected;
    }

    public static City findById(int id) {
        City city = new City();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement sqlFindById = connection.prepareStatement("SELECT * FROM city WHERE id = ?");
            sqlFindById.setInt(1, id);
            ResultSet resultSet = sqlFindById.executeQuery();

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
    public static List<City> findByName(String name) {

        List<City> cityList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement sqlFindByName = connection.prepareStatement("SELECT * FROM city WHERE name LIKE ?");
            sqlFindByName.setString(1, name);
            ResultSet resultSet = sqlFindByName.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public static List<City> findByCode(String code) {

        List<City> cityList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement sqlFindByCode = connection.prepareStatement("SELECT * FROM city WHERE CountryCode = ?");
            sqlFindByCode.setString(1, code);
            ResultSet resultSet = sqlFindByCode.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }


    public static List<City> findAll() {

        List<City> cityList = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            String SELECT_CITY = "SELECT * FROM city LIMIT 3045, 25"; // Mostly Swedish cities

            ResultSet resultSet = statement.executeQuery(SELECT_CITY);


            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")));
            }

//            cityList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

}
