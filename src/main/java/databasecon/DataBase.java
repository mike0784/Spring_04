package databasecon;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static Connection connect;
    public static Statement statmt;
    public static ResultSet resSet;
    private String tableName = "users";
    private String[] fields = {"id", "surname", "name", "patronymic", "telephone", "dateofbirth"};
    private String fileDB;

    public DataBase(String file) {
        this.fileDB = file;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
            statmt = connect.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load JDBC driver");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("Could not connect to database");
            throw new RuntimeException(e);
        }

        System.out.println("Подключение к БД успешно");
    }

    public List<User> selectAll() {
        List<User> result = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", this.tableName);
        try {
            resSet = statmt.executeQuery(query);
            while(resSet.next()) {
                int id = resSet.getInt(fields[0]);
                String surname = resSet.getString(fields[1]);
                String name = resSet.getString(fields[2]);
                String patronymic = resSet.getString(fields[3]);
                String telephone = resSet.getString(fields[4]);
                Timestamp dateofbirth = resSet.getTimestamp(fields[5]);
                result.add(new User(id, surname, name, patronymic, telephone, dateofbirth));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
