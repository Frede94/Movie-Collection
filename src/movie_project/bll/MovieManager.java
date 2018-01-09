/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Movies;
import movie_project.dal.MovieDAO;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieManager
{

    private static MovieDAO movieDAO = new MovieDAO();
    private static ObservableList<Movies> movies = FXCollections.observableArrayList();
    
    public static void remove(Movies selectedMovie)
    {
        movieDAO.remove(selectedMovie);
    }

    public void saveMovie(Movies m)
    {
        movieDAO.saveMovie(m);
    }
    
    public MovieManager()
    {
        movieDAO = new MovieDAO();
    }
    
    public List<Movies> getAllMovies()
    {
        return movieDAO.getAllMovies();
    }
    
}
