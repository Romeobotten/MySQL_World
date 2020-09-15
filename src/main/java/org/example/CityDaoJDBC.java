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

}
