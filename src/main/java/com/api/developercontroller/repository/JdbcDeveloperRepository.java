package com.api.developercontroller.repository;

import com.api.developercontroller.models.Developer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcDeveloperRepository implements DeveloperRepository {
    private String tableName = "developers";
    public static Connection connection = new ConnectionFactory().getConnection();

    @Override
    public void save(Developer dev) {
        String sql = String.format("INSERT INTO %s (nome, main_language, favorite_animal)" +
                " VALUES (?, ?, ?);", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setString(1, dev.getNome());
            querySql.setString(2, dev.getMainLanguage());
            querySql.setString(3, dev.getFavoriteAnimal());
            querySql.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Developer dev) {
        String sql = String.format("UPDATE %s SET nome=?, main_language=?, favorite_animal=? WHERE id=?;", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setString(1, dev.getNome());
            querySql.setString(2, dev.getMainLanguage());
            querySql.setString(3, dev.getFavoriteAnimal());
            querySql.setInt(4, dev.getId());
            querySql.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Developer deleteById(int id) {
        Developer developer = this.findById(id);
        String sql = String.format("DELETE FROM %s WHERE id=?;", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setInt(1, id);
            querySql.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return developer;
    }
    @Override
    public List<Developer> findAll() {
        List<Developer> developers = new ArrayList<>();

        try {
            PreparedStatement querySql = connection
                    .prepareStatement("SELECT * FROM " + this.tableName + ";");
            ResultSet queryResult = querySql.executeQuery();

            while (queryResult.next()) {
                Developer dev = new Developer();
                dev.setId(queryResult.getInt("id"));
                dev.setNome(queryResult.getString("nome"));
                dev.setMainLanguage(queryResult.getString("main_language"));
                dev.setFavoriteAnimal(queryResult.getString("favorite_animal"));

                developers.add(dev);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return developers;
    }

    public Developer findById(int id) {
        Developer developer;
        String sql = "SELECT * FROM " + this.tableName + " WHERE id=?;";

        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setInt(1, id);
            ResultSet queryResult = querySql.executeQuery();

            queryResult.next();
            developer = new Developer(
                    queryResult.getInt("id"),
                    queryResult.getString("nome"),
                    queryResult.getString("main_language"),
                    queryResult.getString("favorite_animal")
            );
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return developer;
    }
}
