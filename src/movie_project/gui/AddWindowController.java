/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import movie_project.be.Category;
import movie_project.be.Movie;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class AddWindowController implements Initializable
{

    private ComboBox<Category> comboCategory;
    @FXML
    private Button saveMovieBtn;
    @FXML
    private Button cancelMovieBtn;
    @FXML
    public TextField txtImdbRating;
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtImdbRating1;
    @FXML
    public TextField txtPath;

    public Movie editMovie;

    private MovieModel movieModel = new MovieModel();
    private BrugerFladeMainController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //txtTitle.getText(movieModel.getTitle());
    }

    /*
    Lægger det tastede fra txtField ind i databasen
     */
    @FXML
    private void clickSaveAction(ActionEvent event)
    {

        if (editMovie == null) // hvis editmovie ikke har nogle værdier tilknyttet så laver den en ny.
        {
            Movie m = new Movie();
            m.setName(txtTitle.getText());
            m.setFileLink(txtPath.getText());
            m.setRating(Float.parseFloat(txtImdbRating.getText()));
            m.setPersonalRating(Float.parseFloat(txtImdbRating1.getText()));
            movieModel.saveMovie(m);

        } else
        {

            //Henter informationerne som allerede er i en film og sætter dem ind i
            //txtfelterne som er i vores AddWindow, så man kan ændre i dem.
            //Når man ændre så sletter programmet den gamle film som man har valgt at edit.
            editMovie.setName(txtTitle.getText()); // sætter navn på edit movie variabel
            editMovie.setFileLink(txtPath.getText()); // sætter filsti på editmovie variabel
            editMovie.setRating(Float.parseFloat(txtImdbRating.getText())); // sætter rating på editmovie, ved at lave værdien om til en string.
            editMovie.setPersonalRating(Float.parseFloat(txtImdbRating1.getText()));// sætter personlig rating på editmovie, ved at lave værdien om til en string.
            movieModel.saveEdit(editMovie); //sender editmovie videre ned i lagene, her sender den til movieModel
            movieModel.loadMovies(); // som det sidste inden den lukker vinduet indlæser den film.
        }
        closeMovieAddBox(); //lukker vinduet.

    }

    /**
     * setter stage, og tager så den satte stage og lukker det.
     */
    @FXML
    private void closeMovieAddBox()
    {
        Stage stage = (Stage) cancelMovieBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * En filechooser som kan genkende .mp4, .mpeg4, og .mkv, der er også en
     * valgmulighed i filechooseren som hedder alle, det betyder at
     * filechooseren kan se og vælge alle filtyper.
     *
     * @param event
     */
    @FXML
    private void clickChooseFile(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("MKV", "*.mkv"),
                new FileChooser.ExtensionFilter("MPEG4", "*.mpeg4"));
        File file = chooser.showOpenDialog(new Stage());
        try
        {

            String fullPath = file.getCanonicalPath();
            txtPath.setText(fullPath);
        } catch (IOException ex)
        {
            System.out.println("heaedada");
            Logger.getLogger(AddWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Sætter relation til movieModel i denne klasse.
     *
     * @param movieModel
     */
    void setMovieModel(MovieModel movieModel)
    {
        this.movieModel = movieModel;
    }

    /**
     * Henter data som er i den valgte film.
     * @param selectedItem
     */
    void setEditMovie(Movie selectedItem)
    {
        editMovie = selectedItem; // sætter editmovie til at være det object du har valgt.
        txtTitle.setText(editMovie.getName()); //henter titlen fra det valgte objekt.
        txtImdbRating.setText(String.valueOf(editMovie.getRating())); // henter de intastede værdier og laver dem om til string.
        txtImdbRating1.setText(String.valueOf(editMovie.getPersonalRating())); // henter de intastede værdier og laver dem om til string.
        txtPath.setText(editMovie.getFileLink()); // henter filstien fra det valgte objekt, hvis den har en og sætter det ind.

    }

}
