package com.clinic.privateclinic.bank;

public class Bank {
    private static final String name = "Bank of Poland";
    private static final String nip = "PL123-123-12-12";
    private static final String krs = "0009936721";
    private String street = "Streetowa 18";
    private String city = "Warszawa";
    private String postalCode = "00-001";

    static String getName() {
        return name;
    }

    static String getNip() {
        return nip;
    }

    static String getKrs() {
        return krs;
    }

    String getStreet() {
        return street;
    }

    String getCity() {
        return city;
    }

    String getPostalCode() {
        return postalCode;
    }
}
