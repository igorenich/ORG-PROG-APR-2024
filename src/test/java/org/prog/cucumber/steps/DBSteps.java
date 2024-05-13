package org.prog.cucumber.steps;

import com.mysql.cj.jdbc.Driver;
import io.cucumber.java.en.Given;
import org.prog.dto.PersonDto;
import org.testng.Assert;

import java.sql.*;
import java.util.List;

public class DBSteps {
    private final static String INSERT_SQL =
            "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES (?, ?, ?, ?, ?)";

    private final static String SELECT_RANDOM =
            "SELECT * FROM Persons ORDER BY RAND() LIMIT 1";

    public static String randomUserName;

    @Given("i store random people from randomuser.me")
    public void requestAndStoreRandomUsers() throws SQLException {
        Assert.assertNotNull(RestSteps.randomPersons,
                "Please, use 'requestRandomPeople' step before storing random users to DB");
        Connection connection = null;
        Statement statement = null;
        try {
            List<PersonDto> randomPersons = RestSteps.randomPersons;

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "user", "password");

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

    @Given("i pick random user from DB")
    public void pickRandomUser() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "user", "password");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_RANDOM);
            while (resultSet.next()) {
                randomUserName =
                        resultSet.getString("FirstName") +
                                " " +
                                resultSet.getString("LastName");
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
