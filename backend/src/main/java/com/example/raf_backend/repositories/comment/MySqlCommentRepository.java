package com.example.raf_backend.repositories.comment;

import com.example.raf_backend.entities.Category;
import com.example.raf_backend.entities.Comment;
import com.example.raf_backend.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {

    @Override
    public boolean addComment(Comment comment) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO comments (id , text , author, date) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1,  comment.getId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setString(3, comment.getAuthor());
            preparedStatement.setLong(4, comment.getDate());
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

    @Override
    public List<Comment> findComment(Integer id) {

        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM comments WHERE id = " + id);

            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getString("author"), resultSet.getLong("date")));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }



}
