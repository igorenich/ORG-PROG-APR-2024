package org.prog.cucumber.steps;

import com.mysql.cj.jdbc.Driver;
import io.cucumber.java.en.Given;
import org.prog.dto.PersonDto;
import org.prog.util.DataHolder;
import org.testng.Assert;

import java.sql.*;
import java.util.List;

public class DBSteps {
    private final static String INSERT_SQL =
            "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES (?, ?, ?, ?, ?)";

    private final static String SELECT_RANDOM =
            "SELECT * FROM Persons ORDER BY RAND() LIMIT 1";

    public static String randomUserName;

    @Given("i store group {string} from randomuser.me to DB")
    public void requestAndStoreRandomUsers(String alias) throws SQLException {
        Assert.assertNotNull(DataHolder.dataHolder.get(alias),
                "Please, use 'requestRandomPeople' step before storing random users to DB");
        Connection connection = null;
        Statement statement = null;
        try {
            List<PersonDto> randomPersons = (List<PersonDto>) DataHolder.dataHolder.get(alias);

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql-db-1:3306/db", "user", "password");

            statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

            for (PersonDto person : randomPersons) {
                preparedStatement.setString(1, person.getName().getFirst());
                preparedStatement.setString(2, person.getName().getLast());
                preparedStatement.setString(3, person.getGender());
                preparedStatement.setString(4, person.getName().getTitle());
                preparedStatement.setString(5, person.getNat());

                try {
                    preparedStatement.execute();
                } catch (SQLException e) {
                    System.out.println("failed to store user " + person.getName().getFirst() + " " + person.getName().getLast());
                }
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Given("i pick random user from DB as {string}")
    public void pickRandomUser(String alias) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql-db-1:3306/db", "user", "password");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_RANDOM);
            while (resultSet.next()) {
                DataHolder.dataHolder.put(alias, resultSet.getString("FirstName") +
                        " " + resultSet.getString("LastName"));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
