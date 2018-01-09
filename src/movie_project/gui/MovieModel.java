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
import movie_project.bll.SearchFilter;
import movie_project.dal.MovieDAO;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieModel
{
    private SearchFilter searchFilter = new SearchFilter();
    
    private MovieDAO movieDao = new MovieDAO(); // FY FY skal flyttes
    
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
    
     /*
    Søger på det der er skrevet i søgefeltet
    skal flyttes til dal laget
     */
    void search(String searchText)
    {
        List<Movies> allMovies = movieDao.getAllMovies();
        List<Movies> searchResults = searchFilter.searchByMovieName(allMovies, searchText);
        movies.clear();
        movies.addAll(searchResults);
    }
}
