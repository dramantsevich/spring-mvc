package org.example.daos;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }

    public Person getPersonById(final int id) {
//        return personList.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);
        return null;
    }

    public void save(Person person) {
        try{
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES (" + 1 + ",'" + person.getName() + "'" +
                    "," + person.getAge() + ",'" + person.getEmail() + "')";

           statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = getPersonById(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        personList.removeIf(p -> p.getId() == id);
    }
}
