package com.example.raf_backend.repositories.user;


import com.example.raf_backend.entities.User;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.repositories.MySqlAbstractRepository;
import com.example.raf_backend.requests.EditStatusRequest;
import com.example.raf_backend.requests.EditUserRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepositry extends MySqlAbstractRepository implements UserRepository {

    @Override
    public List<User> fetchUsers(int page) {

        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users LIMIT " +
                    (page - 1)* PAGE_SIZE +
                    ", " +
                    page* PAGE_SIZE);

            while (resultSet.next()) {
                users.add(new User(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("password")));
            }


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }



        return users;
    }

    @Override
    public int getUserNumber() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int users = 0;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
            resultSet.next();
            users = resultSet.getInt("count(*)");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public boolean addUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("insert into users (email, name, surname, type, status, password) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,getUsername());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setBoolean(5, user.isStatus());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return true;
    }

    @Override
    public boolean editUser(EditUserRequest request) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("update users set email = '" +
                    request.getNewEmail() +
                    "' ," +
                    "name = '" +
                    request.getName() +
                    "' ," +
                    "surname = '" +
                    request.getSurname() +
                    "' ," +
                    "type = '" +
                    request.getUserType() +
                    "' " +
                    "where email = '" +
                    request.getOldEmail() +
                    "'");
            System.out.println("update users set email = '" +
                    request.getOldEmail() +
                    "' ," +
                    "name = '" +
                    request.getName() +
                    "' ," +
                    "surname = '" +
                    request.getSurname() +
                    "' ," +
                    "type = '" +
                    request.getUserType() +
                    "' " +
                    "where email = '" +
                    request.getNewEmail() +
                    "'");
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return true;
    }

    public static void main(String[] args) {
        UserRepository repository = new MySqlUserRepositry();
        EditUserRequest request = new EditUserRequest("email", "emailNovi", "jovan", "zmaj", "USER");
        User user = new User("email2", "ivan", "ursulovic", "ADMIN", true ,"123");
        EditStatusRequest request1 = new EditStatusRequest(false, "email2");

        System.out.println(repository.changeStatus(request1));
    }

    @Override
    public boolean changeStatus(EditStatusRequest request) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("update users set status = " +
                    request.isStatus()+
                    " where email = '" +
                    request.getEmail() +
                    "'");
            System.out.println("update users set status = '" +
                    request.isStatus()+
                    "' where email = '" +
                    request.getEmail() +
                    "'");

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }


        return true;
    }

    @Override
    public User findUser(String email) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        User user = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users where email =  '" +
                    email +
                    "'");

            while (resultSet.next()) {
                 user = new User(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("password"));
            }




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }


}
