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

    /**
     * Sletter den valgte film
     *
     * @param selectedMovie
     */
    public static void remove(Movies selectedMovie)
    {
        movieDAO.remove(selectedMovie);
    }

    /**
     * Gemmer den film, som man taster ind i databasen
     *
     * @param m
     */
    public void saveMovie(Movies m)
    {
        movieDAO.saveMovie(m);
    }

    /**
     * Kalder vores BLL MovieManager klasse
     */
    public MovieManager()
    {
        movieDAO = new MovieDAO();
    }

    /**
     * En liste af alle film i databasen
     *
     * @return
     */
    public List<Movies> getAllMovies()
    {
        return movieDAO.getAllMovies();
    }

    /*
    Henter informationerne som allerede er i en film og sætter dem ind i
    txtfelterne som er i vores AddWindow, så man kan ændre i dem.
    Når man ændre så sletter programmet den gamle film som man har valgt at edit.
     */
    public void saveEdit(Movies editMovie)
    {
        movieDAO.saveEdit(editMovie);
    }

}
