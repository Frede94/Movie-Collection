/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import movie_project.be.Movies;
import movie_project.dal.MovieDAO;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieManager
{

    private static MovieDAO movieDAO = new MovieDAO();
    
    public static void remove(Movies selectedMovie)
    {
        movieDAO.remove(selectedMovie);
    }

    public void saveMovie(Movies m)
    {
        movieDAO.saveMovie(m);
    }
    
}
