package com.api.developercontroller.models;

import java.util.Objects;

public class Developer {
    // Atributes
    private int id;
    private String nome;
    private String mainLanguage;
    private String favoriteAnimal;

    // Constructor
    public Developer () {}
    public Developer (int id, String nome, String mainLanguage, String favoriteAnimal) {
        this.id = id;
        this.nome = nome;
        this.mainLanguage = mainLanguage;
        this.favoriteAnimal = favoriteAnimal;
    }

    // Methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developer developer)) return false;
        return getId() == developer.getId() && getNome().equals(developer.getNome())
                && getMainLanguage().equals(developer.getMainLanguage())
                && getFavoriteAnimal().equals(developer.getFavoriteAnimal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getMainLanguage(), getFavoriteAnimal());
    }

    @Override
    public String toString() {
        return String.format("<%s - %s>", getNome(), getMainLanguage());
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }
}
