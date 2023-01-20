package com.example.raf_backend.repositories.tag;

import com.example.raf_backend.entities.Tag;
import com.example.raf_backend.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {


    //RADI
    public static void main(String[] args) {
        Tag tag = new Tag(8, "sport");
        TagRepository repository = new MySqlTagRepository();
        System.out.println("AAA");
        System.out.println(repository.addTag(tag));
        System.out.println("AAAAAA");
    }


    @Override
    public boolean addTag(Tag tag) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO tags (id, tagss) VALUES (?, ?)");
            preparedStatement.setInt(1, tag.getId());
            preparedStatement.setString(2, tag.getTags());
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

    @Override
    public List<String> fetchTags() {
        List<String> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select tagss from tags");
            while (resultSet.next()) {
                tags.add(resultSet.getString("tagss"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        System.out.println(tags);
        return tags;
    }
}
