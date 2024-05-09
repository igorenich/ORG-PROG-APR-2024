package org.prog.sql;

import com.mysql.cj.jdbc.Driver;
import io.restassured.RestAssured;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.prog.web.GooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

public class DbTests {

    private final static String INSERT_SQL =
            "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES (?, ?, ?, ?, ?)";

    private final static String SELECT_SQL = "SELECT * FROM Persons";

    @Test
    public void testUserWrite() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            List<PersonDto> persons = getPersons(20);

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "user", "password");

            statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

            for (PersonDto person : persons) {
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

    @Test
    public void testReadFromDB() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "user", "password");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
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

    private List<PersonDto> getPersons(int amount) {
        ResponseDto responseDto = RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .as(ResponseDto.class);
        Assert.assertFalse(responseDto.getResults().isEmpty(),
                "At least one user must be retrieved!");
        return responseDto.getResults();
    }
}
