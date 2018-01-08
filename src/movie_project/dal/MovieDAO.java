/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import movie_project.be.Movies;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieDAO
{

    DataBaseConnector dbc = new DataBaseConnector();

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
