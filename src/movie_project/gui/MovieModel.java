/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Category;
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
    private ObservableList<Category> categories;
    private ObservableList<Movies> movieView = FXCollections.observableArrayList();

    /**
     * Constructor til MovieModel klassen
     */
    public MovieModel()
    {
        this.movies = FXCollections.observableArrayList();
        movies.addAll(movieManager.getAllMovies());

        loadMovies();
    }

    /**
     * Fjerner den valgte sang fra listen
     *
     * @param selectedMovie
     */
    void remove(Movies selectedMovie)
    {
        movies.remove(selectedMovie);
        MovieManager.remove(selectedMovie);
    }

    /**
     * Gemmer film i databasen
     *
     * @param m
     */
    void saveMovie(Movies m)
    {
        movies.add(m);
        movieManager.saveMovie(m);
    }

    /**
     * Rydder listen og loader film fra databasen. "getAllMovies"
     */
    void loadMovies()
    {
        List<Movies> loadedMovies = movieManager.getAllMovies();

        movies.clear();
        movies.addAll(loadedMovies);
    }

    /**
     * Retunerer film
     *
     * @return
     */
    public ObservableList<Movies> getMovies()
    {
        return movies;
    }

    /**
     * Søger på det der er skrevet i søgefeltet skal FLYTTES til DAL laget
     *
     * @param searchText
     */
    void search(String searchText)
    {
        List<Movies> allMovies = movieDao.getAllMovies();
        List<Movies> searchResults = searchFilter.searchByMovieName(allMovies, searchText);
        movies.clear();
        movies.addAll(searchResults);
    }

    /*
    returnere categories
     */
    ObservableList<Category> getCategories()
    {
        return categories;
    }

    /*
    Henter informationerne som allerede er i en film og sætter dem ind i
    txtfelterne som er i vores AddWindow, så man kan ændre i dem.
    Når man ændre så sletter programmet den gamle film som man har valgt at edit.
     */
    void saveEdit(Movies editMovie)
    {
        movies.add(editMovie);
        movieManager.saveEdit(editMovie);
    }
}
