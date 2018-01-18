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

        if (editMovie == null)
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
            editMovie.setName(txtTitle.getText());
            editMovie.setFileLink(txtPath.getText());
            editMovie.setRating(Float.parseFloat(txtImdbRating.getText()));
            editMovie.setPersonalRating(Float.parseFloat(txtImdbRating1.getText()));
            movieModel.saveEdit(editMovie);
            movieModel.loadMovies();
        }
        closeMovieAddBox();

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
     * Sætter movieModel i denne klasse.
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
        editMovie = selectedItem;
        txtTitle.setText(editMovie.getName());
        txtImdbRating.setText(String.valueOf(editMovie.getRating()));
        txtImdbRating1.setText(String.valueOf(editMovie.getPersonalRating()));
        txtPath.setText(editMovie.getFileLink());

    }

}
