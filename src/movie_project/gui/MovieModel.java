/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Movies;
import movie_project.bll.MovieManager;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieModel
{
    
    MovieManager movieManager = new MovieManager();
    
    private ObservableList<Movies> movies = FXCollections.observableArrayList();

    
    public MovieModel()
    {
        this.movies = FXCollections.observableArrayList();
        movies.addAll(movieManager.getAllMovies());
        
        loadMovies();
    }
    
    void remove(Movies selectedMovie)
    {
        movies.remove(selectedMovie);
        MovieManager.remove(selectedMovie);
    }

    void saveMovie(Movies m)
    {
        movies.add(m);
        movieManager.saveMovie(m);
    }
    
    void loadMovies()
    {
        List<Movies> loadedMovies = movieManager.getAllMovies();
        
        movies.clear();
        movies.addAll(loadedMovies);
    }
    
    public ObservableList<Movies> getMovies()
    {
        return movies;
    }
}
