/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Category;
import movie_project.be.Movie;
import movie_project.dal.MovieDAO;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieManager
{
    
    private SearchFilter searchFilter = new SearchFilter();
    private static MovieDAO movieDAO = new MovieDAO();
    private static ObservableList<Movie> movies = FXCollections.observableArrayList();

    /**
     * Sletter den valgte film
     * MovieManageren ligger i BLL laget, og er et led mellem GUI og DAL laget, 
     * som vi skal igennem, for at kunne slette filmen nede i databasen.
     *
     * @param selectedMovie
     */
    public static void remove(Movie selectedMovie)
    {
        movieDAO.remove(selectedMovie);
    }

    /**
     * Gemmer den film, som man taster ind i databasen
     *
     * @param m
     */
    public void saveMovie(Movie m)
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
    public List<Movie> getAllMovies()
    {
        return movieDAO.getAllMovies();
    }
    
    /**
     * Søger på det der er skrevet i søgefeltet
     *
     * @param searchText
     */
    public List<Movie> search(String searchText)
    {
        List<Movie> allMovies = movieDAO.getAllMovies();
        List<Movie> searchResults = searchFilter.searchByMovieName(allMovies, searchText);
        return searchResults;
    }
    
    /*
    Henter informationerne som allerede er i en film og sætter dem ind i
    txtfelterne som er i vores AddWindow, så man kan ændre i dem.
    Når man ændre så sletter programmet den gamle film som man har valgt at edit.
     */
    public void saveEdit(Movie editMovie)
    {
        movieDAO.saveEdit(editMovie); //sender Editmovie variabel længere ned i lagene, her sender den til MovieDAO klassen i dal laget.
    }

    /**
     * Denne metode kalder en metode inde i SQL, (GETDATE) hvor den sætter date,
     * på den valgte film.
     *
     * @param selectedMovie
     */
    public void lastViewed(Movie selectedMovie)
    {
        movieDAO.lastViewed(selectedMovie);
    }

    /**
     * This method sends the category movie relation to the DAL layer
     *
     * @param selectedCats
     * @param selectedMovie
     */
    public void addCats(ObservableList<Category> selectedCats, Movie selectedMovie)
    {
        movieDAO.addCats(selectedCats, selectedMovie);
    }

    /**
     * fjerner film fra en kategori uden at slette de fra Movie tabel i
     * databasen. den sletter relationer fra CatMovie tabelen i databasen.
     *
     * @param selectedCatMovie
     */
    public void removeCatMovie(Movie selectedCatMovie)
    {
        movieDAO.removeCatMovie(selectedCatMovie);
    }

}
