package com.example.raf_backend.repositories.category;

import com.example.raf_backend.entities.Category;
import com.example.raf_backend.repositories.MySqlAbstractRepository;
import com.example.raf_backend.repositories.article.ArticleRepository;
import com.example.raf_backend.repositories.article.MySqlArticleRepository;
import com.example.raf_backend.requests.EditCategoryRequest;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryRepository extends MySqlAbstractRepository implements CategoryRepository {





    // RADI
    @Override
    public boolean addCategory(Category category) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description) VALUES (? , ?)");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.executeUpdate();

        } catch (Exception e ) {
            e.printStackTrace();
            return false;
        }
        finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }


        return true;
    }

    //RADI
    @Override
    public List<Category> fetchCategories(int page) {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM categories LIMIT " +
                    (page - 1) * 10 +
                    ", " +
                    page * PAGE_SIZE);
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getString("name"), resultSet.getString("description")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public List<String> fetchNames() {
        List<String> names = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select name from categories");

            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }

            }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return names;
    }

    @Override
    public int getPages() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int rows = 0;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM categories");
            resultSet.next();
            rows = resultSet.getInt("count(*)");



        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }


        return rows;
    }

    //RADI
    @Override
    public boolean editCategory(EditCategoryRequest request) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String categoryName = request.getNewName();
        String cateogryDesc = request.getNewDescription();
        Category category = new Category(request.getNewName(), request.getNewDescription());


        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE categories SET name = '" +
                    categoryName +
                    "'," +
                    " description = '" +
                    cateogryDesc +
                    "' WHERE name = '" +
                    request.getOldName() +
                    "' " );
            System.out.println("UPDATE categories SET name = '" +
                    categoryName +
                    "'," +
                    " description = '" +
                    cateogryDesc +
                    "' WHERE name = '" +
                    request.getOldName() +
                    "' " );
            preparedStatement.executeUpdate();



        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return true;
    }

    //RADI
    @Override
    public boolean removeCategory(String name) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE name = " + "\'" + name + "\'");
            preparedStatement.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }


        return true;
    }


}
