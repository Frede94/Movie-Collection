/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

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
    
    private ObservableList<Movies> movies = FXCollections.observableArrayList();

    void remove(Movies selectedMovie)
    {
        movies.remove(selectedMovie);
        MovieManager.remove(selectedMovie);
    }
    
}
