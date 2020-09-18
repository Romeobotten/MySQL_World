package org.example;

public class App {  // Hello Cities in the WORLD

    public static void main(String[] args) { // Running all sorts of Querys

        CityDaoJDBC.findAll().forEach(System.out::println); // not ALL, just from 3046 to 3070 LIMIT

        System.out.println("-------------------------------------------");
        System.out.println(CityDaoJDBC.findById(3048).toString()); // Stockholm

        City vxo = new City(4599, "Vaxjoe","SWE","Kronoberg", 94577);

        if (CityDaoJDBC.add(vxo) == vxo) {
            System.out.println("City added to database");
            // Insert Växjö into the World
        }
        System.out.println("-------------------------------------------");
        System.out.println(CityDaoJDBC.findById(4599).toString());
        System.out.println("-------------------------------------------");
        vxo.setName("Växjö");
        vxo.setDistrict("Småland");
        if (CityDaoJDBC.update(vxo) == vxo) {
            System.out.println("Update OK");
        } // ÅÄÖ working ok

        CityDaoJDBC.findByCode("SWE").forEach(System.out::println); // All Swedish towns, including Växjö
        System.out.println("-------------------------------------------");
        CityDaoJDBC.findByName("%ping").forEach(System.out::println); // Some cities in Asia and Sweden

        System.out.println("-------------------------------------------");
        if (CityDaoJDBC.delete(vxo) == 1) {
            System.out.println("City deleted OK");
        } else {
            System.out.println("Error: nothing happened");
        }

        System.out.println(CityDaoJDBC.findById(4599).toString()); // Vaxjo is no more
    }
}
