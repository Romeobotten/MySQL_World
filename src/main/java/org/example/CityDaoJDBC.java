package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC {

//    All methods implemented

//    City findById(int id);
//    List<City> findByCode(String code);
//    List<City> findByName(String name);
//    List<City> findAll();
//    City add(City city);
//    City update(City city);
//    int delete(City city);

    public static City findById(int id) {

        City city = new City();

        try {
            String SELECT_BY_ID = "SELECT * FROM city WHERE id = ?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_BY_ID);
            sqlFindById.setInt(1, id);
            ResultSet resultSet = sqlFindById.executeQuery();

            if (resultSet.next()) {  // I did not change these columnlabels
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setCountryCode(resultSet.getString("countryCode"));
                city.setDistrict(resultSet.getString("district"));
                city.setPopulation(resultSet.getInt("population"));
            }
        } catch (SQLException | MySqlException e) {
            e.printStackTrace();
        }
        return city;
    }

    public static List<City> findByCode(String code) {

        List<City> cityList = new ArrayList<>();

        try {
            String SELECT_BY_CODE = "SELECT * FROM city WHERE countryCode = ?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlFindByCode = connection.prepareStatement(SELECT_BY_CODE);

            sqlFindByCode.setString(1, code);
            ResultSet resultSet = sqlFindByCode.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public static List<City> findByName(String name) {

        List<City> cityList = new ArrayList<>();

        try {
            String SELECT_BY_NAME = "SELECT * FROM city WHERE name LIKE ?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlFindByName = connection.prepareStatement(SELECT_BY_NAME);
            sqlFindByName.setString(1, name);
            ResultSet resultSet = sqlFindByName.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public static List<City> findAll() {

        List<City> cityList = new ArrayList<>();

        try {
            String SELECT_ALL = "SELECT * FROM city LIMIT ?,?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlFindAll = connection.prepareStatement(SELECT_ALL);

            sqlFindAll.setInt(1, 3040);
            sqlFindAll.setInt(2, 40);
            ResultSet resultSet = sqlFindAll.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public static City add(City city) {

        try {
            String ADD_CITY = "INSERT INTO city VALUES (?,?,?,?,?)";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlAddCity = connection.prepareStatement(ADD_CITY);

            sqlAddCity.setInt(1, city.getId());
            sqlAddCity.setString(2, city.getName());
            sqlAddCity.setString(3, city.getCountryCode());
            sqlAddCity.setString(4, city.getDistrict());
            sqlAddCity.setInt(5, city.getPopulation());
            sqlAddCity.executeUpdate();


        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }

    public static City update(City city) {

        try {
            String UPDATE_CITY = "UPDATE city SET name = ?, CountryCode = ?, district = ?, population = ? WHERE id = ?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlUpdateCity = connection.prepareStatement(UPDATE_CITY);

            sqlUpdateCity.setString(1, city.getName());
            sqlUpdateCity.setString(2, city.getCountryCode());
            sqlUpdateCity.setString(3, city.getDistrict());
            sqlUpdateCity.setInt(4, city.getPopulation());
            sqlUpdateCity.setInt(5, city.getId());
            sqlUpdateCity.executeUpdate();

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }

    public static int delete(City city) {

        int rowsAffected = 0;

        try {
            String DELETE_CITY = "DELETE FROM city WHERE id = ?";
            Connection connection = dbConnection.mySqlConnection();
            PreparedStatement sqlDeleteCity = connection.prepareStatement(DELETE_CITY);

            sqlDeleteCity.setInt(1, city.getId());
            rowsAffected = sqlDeleteCity.executeUpdate();

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return rowsAffected;
    }
}
