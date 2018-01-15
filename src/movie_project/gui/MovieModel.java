/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import movie_project.be.Category;
import movie_project.be.Movies;
import movie_project.bll.CategoryManager;
import movie_project.bll.MovieManager;
import movie_project.bll.SearchFilter;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieModel
{

    private SearchFilter searchFilter = new SearchFilter();


    MovieManager movieManager = new MovieManager();
    CategoryManager catManager = new CategoryManager();
    private ObservableList<Movies> movies = FXCollections.observableArrayList();
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<Movies> movieList = FXCollections.observableArrayList();

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
     * Og tjekker listen igennem med et loop, og hvis den finder en film med
     * samme navn som, en film som allerede er i listen, så åbner programmet et
     * fejl vindue hvor den siger filmen allerede er i listen, den går ikke ind
     * i databasen før den er sikker på at der ikke er en dublicate. Hvis den
     * ikke finder en film med samme navn, i listen så inserter den filmen i
     * databasen.
     *
     * @param m
     */
    void saveMovie(Movies m)
    {

        int totalElements = movies.size();
        boolean isInList = false;
        for (int index = 0; index < totalElements && !isInList; index++)
        {
            if (movies.get(index).getName().equalsIgnoreCase(m.getName()))
            {
                isInList = true;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Duplicate Warning");
                alert.setHeaderText(null);
                alert.setContentText("You allready have a movie with this name!");

                alert.showAndWait();

            }

            System.out.println(movies.get(index));
        }
        if (!isInList)
        {
            movies.add(m);
            movieManager.saveMovie(m);
        }

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
    
    public ObservableList<Movies> getSelectedMovies()
    {
        return movieList;
    }

    /**
     * Søger på det der er skrevet i søgefeltet skal FLYTTES til DAL laget
     *
     * @param searchText
     */
    void search(String searchText)
    {
        List<Movies> allMovies = movieManager.getAllMovies();
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

    void lastViewed(Movies selectedMovie)
    {
        movieManager.lastViewed(selectedMovie);
    }

    /**
     * This method sends the category movie relation to the BLL layer
     *
     * @param selectedCats
     * @param selectedMovie
     */
    void addCats(ObservableList<Category> selectedCats, Movies selectedMovie)
    {
        selectedMovie.getCatsList().addAll(selectedCats);
        movieManager.addCats(selectedCats, selectedMovie);
    }

    void playMovie(Movies selectedMovie)
    {
        movieManager.playMovie(selectedMovie);
    }

//    void setMovieByRelation(int MovieId)
//    {
//        List<Movies> movies = movieManager.getMovieByRelation(MovieId);
//        if (movies != null)
//        {
//            movieList.setAll(movies);
//        }
//    }

    /**
     * Sender den ny kategori vidre til BLL laget.
     *
     * @param c
     */
    void saveCategory(Category c)
    {
        categories.add(c);
        catManager.save(c);
    }

    /**
     * Metoden laver listen med kategorier som den henter fra DAL laget via BLL
     * Laget
     */
    void loadCategories()
    {
        List<Category> loadedCategories = catManager.getAllCategories();
        categories.clear();

        categories.addAll(loadedCategories);
    }

    /**
     * This method recives the selecteed categories from the MainController. The
     * for loop runs through the list of selectedcategories and removes them
     * from both the listview, and the database, one at a time.
     *
     * @param selectedCategories
     */
    void removeCat(ObservableList<Category> selectedCategories)
    {
        for (int i = 0; i < selectedCategories.size(); i++)
        {
            catManager.removeCat(selectedCategories.get(i));
            categories.remove(selectedCategories.get(i));
        }

    }

    public void setMoviesByRelation(ObservableList<Category> selectedCategories)
    {
        for (int i = 0; i < selectedCategories.size(); i++)
        {
            List<Movies> movies = catManager.setRelation(selectedCategories.get(i));
            movieList.addAll(movies);

        }
    }

}
