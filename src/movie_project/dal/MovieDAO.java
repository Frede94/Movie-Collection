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
import javafx.collections.ObservableList;
import movie_project.be.Movies;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieDAO
{

    private ObservableList<Movies> moviesInList;
    private MovieDAO movieDAO;
    private DataBaseConnector dataBaseConnector;
    
    DataBaseConnector dbc = new DataBaseConnector();
    
/**
 * Gets a list from the "Movie" database with the Movies, Id, Name, RatingIMDB,
 * FileLink, LastView and RatingOwn
 * 
 * @return 
 */
     public List<Movies> getAllMovies()
    {

        List<Movies> movies = new ArrayList();

        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
            while (rs.next())
            {
                Movies currentMovie = new Movies();
                currentMovie.setId(rs.getInt("Id"));
                currentMovie.setName(rs.getString("Name"));
                currentMovie.setRating(rs.getFloat("RatingIMDB"));
                currentMovie.setFileLink(rs.getString("FileLink"));
                currentMovie.setLastView(rs.getString("LastView"));
                currentMovie.setPersonalRating(rs.getFloat("RatingOwn")); // PersonalRating

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
    public void remove(Movies selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Movie WHERE id=" + selectedMovie.getId());
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    gemmer de tastede data fra txtfelterne
     */
    public void saveMovie(Movies m)
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
  
    
}
