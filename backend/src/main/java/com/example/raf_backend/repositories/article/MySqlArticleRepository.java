package com.example.raf_backend.repositories.article;

import com.example.raf_backend.entities.Article;
import com.example.raf_backend.repositories.MySqlAbstractRepository;
import com.example.raf_backend.repositories.tag.MySqlTagRepository;
import com.example.raf_backend.requests.EditArticleRequest;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository {

    @Override
    public boolean addArticle(Article article) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;





        try {

            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO articles (category, title, text, date, visits, author) VALUES(?, ?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, article.getCategory());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getText());
            preparedStatement.setLong(4,article.getDate());
            preparedStatement.setInt(5, article.getVisits());
            preparedStatement.setString(6, article.getAuthor());
            preparedStatement.setString(7 , article.getTags());

            preparedStatement.executeUpdate();

            System.out.println(Arrays.toString(generatedColumns));


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return true;
    }

    @Override
    public List<Article> fetchArticles(int page) {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `articles` ORDER BY `articles`.`date` DESC LIMIT " +
                    (page - 1)* PAGE_SIZE +
                    ", " +
                    page * PAGE_SIZE);

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }

    @Override
    public List<Article> fetchAllArticles() {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from articles");

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        System.out.println(articles);
        return articles;
    }


    @Override
    public List<Article> categoryFilterArticles(String category) {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM articles WHERE category =  '" +
                    category +
                    "'" );

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }


        return articles;
    }

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        MySqlArticleRepository repository = new MySqlArticleRepository();


    }



    @Override
    public List<Article> fetchMostReaded() {

        List<Article> articles = new ArrayList<>();
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime(); // ovo koristimo


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;



        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `articles`  " +
                    "WHERE `articles`.`date` > "+ today30.getTime() +
                    " ORDER by visits DESC " +
                    " LIMIT 1,10");



            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            System.out.println(statement);

            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }


        return articles;
    }

    @Override
    public List<Article> tagFilterArticles(Integer id) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM articles WHERE id =  '" +
                    id +
                    "'" );

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;

    }

    @Override
    public List<Article> searchByText(String text) {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            SELECT * FROM Accounts WHERE Username LIKE '%query%'
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM articles WHERE text LIKE '" +
                    "%" +
                    text +
                    "%" +
                    "'" +
                    " OR " +
                    "title LIKE " +
                    "'" +
                    "%" +
                    text +
                    "%" +
                    "'");

            System.out.println("SELECT * FROM articles WHERE text LIKE '" +
                    "%" +
                    text +
                    "%" +
                    "'" +
                    " OR " +
                    "title LIKE " +
                    "'" +
                    "%" +
                    text +
                    "%" +
                    "'");

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public Article fetchSingleArticle(int id) {

        List<Article> articles = new ArrayList<>();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;



        try {


            connection = this.newConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM articles WHERE id = " + id);

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getLong("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("author"),
                        resultSet.getString("tags")));
            }




        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println(articles.toString());
        return articles.get(0);
    }


    @Override
    public boolean editArticle(EditArticleRequest request) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Article article = new Article();

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE articles SET category = '" +
                    request.getCategory() +
                    "' ," +
                    "title = '" +
                    request.getTitle() +
                    "' ," +
                    "text = '" +
                    request.getText() +
                    "' ," +
                    "visits = '" +
                    request.getVisits() +
                    "' ," +
                    "author = '" +
                    request.getAuthor() +

                    "' WHERE id = " + request.getId());
            System.out.println(preparedStatement.toString());

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
    public int getTotal() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        int articles = 0;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM articles");
            resultSet.next();
            articles = resultSet.getInt("count(*)");




        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }


        return articles;
    }

    @Override
    public void removeArticle(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM articles WHERE id = " + "\'" + id + "\'");
            preparedStatement.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }



    }

    @Override
    public void increaseVisit(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE articles SET visits = visits + 1 WHERE id = " + id);
            preparedStatement.executeUpdate();



        }catch (Exception e ){
            e.printStackTrace();
        }        finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }


}





/*
    public List<Article> parseResult(ResultSet resultSet) throws SQLException {

        List<Article> articles = new ArrayList<>();

        while (resultSet.next()) {
            articles.add(new Article(resultSet.getInt("id"),
                    resultSet.getString("category"),
                    resultSet.getString("title"),
                    resultSet.getString("text"),
                    resultSet.getLong("date"),
                    resultSet.getInt("visits"),
                    resultSet.getString("author")));
        }

        return articles;

    }
 */
