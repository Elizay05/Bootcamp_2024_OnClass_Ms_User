package com.example.bootcamp_2024_onclass_ms_user.domain.model;

public class User {

    private final Long id;

    private final String name;

    private final String lastName;

    private final String identificationDocument;

    private final String cellphoneNumber;

    private final String email;

    private final Long rolId;

    private String rolName;

    private String password;

    public User(Long id, String name, String lastName, String identificationDocument, String cellphoneNumber, String email, Long rolId, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identificationDocument = identificationDocument;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.rolId = rolId;
        this.rolName = "";
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Long getRolId() {
        return rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public String getPassword() {
        return password;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
