/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.dal;

import java.sql.Connection;
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
    
    public void remove(Movies selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Song WHERE SongId=" + selectedMovie.getId());
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
