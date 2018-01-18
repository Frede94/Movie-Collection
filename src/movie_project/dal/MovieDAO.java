/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Category;
import movie_project.be.Movie;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieDAO
{

    private ObservableList<Movie> moviesInList;
    private MovieDAO movieDAO;
    private DataBaseConnector dataBaseConnector;

    DataBaseConnector dbc = new DataBaseConnector();

    /**
     * Gets a list from the "Movie" database with the Movie, Id, Name,
     * RatingIMDB, FileLink, LastView and RatingOwn
     *
     * @return
     */
    public List<Movie> getAllMovies()
    {

        List<Movie> movies = new ArrayList();

        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
            while (rs.next())
            {
                Movie currentMovie = new Movie();

                currentMovie.setId(rs.getInt("id"));
                currentMovie.setName(rs.getString("name"));
                currentMovie.setRating(rs.getFloat("ratingIMDB"));
                currentMovie.setFileLink(rs.getString("filelink"));
                currentMovie.setLastView(rs.getString("lastview"));

                currentMovie.setId(rs.getInt("Id"));
                currentMovie.setName(rs.getString("Name"));
                currentMovie.setRating(rs.getFloat("ratingIMDB"));
                currentMovie.setFileLink(rs.getString("fileLink"));
                currentMovie.setLastView(rs.getString("lastView"));

                currentMovie.setPersonalRating(rs.getFloat("ratingOwn")); // PersonalRating

                movies.add(currentMovie);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;
    }

    /*
    Sletter elementer fra databasen og listen
     */
    public void remove(Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE MovieId =" + selectedMovie.getId() + ";DELETE FROM Movie WHERE id=" + selectedMovie.getId());
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    gemmer de tastede data fra txtfelterne
     */
    public void saveMovie(Movie m)
    {
        try (Connection con = dbc.getConnection())
        {

            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Movie (name, ratingIMDB, filelink, ratingOwn) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);     //, stmt.RETURN_GENERATED_KEYS 

            st.setString(1, m.getName());
            st.setFloat(2, m.getRating());
            st.setString(3, m.getFileLink());
            st.setFloat(4, m.getPersonalRating());

            st.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Henter informationerne som allerede er i en film og sætter dem ind i
    txtfelterne som er i vores AddWindow, så man kan ændre i dem.
    Når man ændre så sletter programmet den gamle film som man har valgt at edit.
     */
    public void saveEdit(Movie editMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            // create the java mysql update preparedstatement
            String queryTitle = "Update Movie set name = ? where id =" + editMovie.getId();
            String queryRating = "Update Movie set ratingIMDB = ? where id =" + editMovie.getId();
            String queryPRating = "Update Movie set RatingOwn = ? where id =" + editMovie.getId();
            String queryPath = "Update Movie set filelink = ? where id =" + editMovie.getId();

            PreparedStatement preparedStmtTitle = con.prepareStatement(queryTitle);
            PreparedStatement preparedStmtRating = con.prepareStatement(queryRating);
            PreparedStatement preparedStmtPRating = con.prepareStatement(queryPRating);
            PreparedStatement preparedStmtPath = con.prepareStatement(queryPath);

            preparedStmtTitle.setString(1, editMovie.getName());
            preparedStmtRating.setFloat(1, editMovie.getRating());
            preparedStmtPRating.setFloat(1, editMovie.getPersonalRating());
            preparedStmtPath.setString(1, editMovie.getFileLink());

            // execute the java preparedstatement
            preparedStmtTitle.executeUpdate();
            preparedStmtRating.executeUpdate();
            preparedStmtPRating.executeUpdate();
            preparedStmtPath.executeUpdate();

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lastViewed(Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
//            PreparedStatement stmt = con.prepareStatement("Update Movie SET lastView = GETDATE ( ) WHERE id = " + selectedMovie.getId());

            String queryLastView = "Update Movie SET lastView = GETDATE ( ) WHERE id = " + selectedMovie.getId();

            PreparedStatement preparedStmtLastView = con.prepareStatement(queryLastView);

            preparedStmtLastView.executeUpdate();

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method adds the relation between the selceted movie and the selected
     * categories to the database. The loop saves the categories one at a time,
     * this keeps the connection open until all categories are saved, instead of
     * opening a new connection for each category.
     *
     * @param selectedCats
     * @param selectedMovie
     */
    public void addCats(ObservableList<Category> selectedCats, Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            for (Category selectedCat : selectedCats)
            {
                String sql = "INSERT INTO CatMovie (CategoryId, MovieId) VALUES (?, ?);";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, selectedCat.getCatId());
                stmt.setInt(2, selectedMovie.getId());
                System.out.println(stmt.toString());
                stmt.execute();
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Bruges ikke endnu
     *
     * @param selectedCat
     * @param selectedMovie
     */
    public void addCat(Category selectedCat, Movie selectedMovie)
    {
        ObservableList<Category> cats = FXCollections.observableArrayList();
        cats.add(selectedCat);
        addCats(cats, selectedMovie);
    }

    public void removeCatMovie(Movie selectedCatMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE MovieId =" + selectedCatMovie.getId());
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
