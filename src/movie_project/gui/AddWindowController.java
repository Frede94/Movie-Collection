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
import movie_project.be.Movies;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class AddWindowController implements Initializable
{

    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private Button saveMovieBtn;
    @FXML
    private Button cancelMovieBtn;
    @FXML
    private TextField txtImdbRating;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtImdbRating1;
    @FXML
    private TextField txtPath;

    private Movies editMovie;

    private MovieModel movieModel = new MovieModel();
    private CategoryModel catModel = new CategoryModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }
    
    /**
     * Sætter CategoryModel i denne klasse og henter Categories
     * @param categoryModel 
     */
    public void setCategoryModel(CategoryModel categoryModel)
    {
        this.catModel = categoryModel;
        comboCategory.setItems(categoryModel.getCategories());;
    }

    @FXML
    private void clickAddAction(ActionEvent event)
    {
    }

    /*
    Lægger det tastede fra txtField ind i databasen
     */
    @FXML
    private void clickSaveAction(ActionEvent event)
    {
        if (editMovie == null)
        {
            Movies m = new Movies();
            m.setName(txtTitle.getText());
            m.setFileLink(txtPath.getText());
            m.setRating(Float.parseFloat(txtImdbRating.getText()));
            m.setPersonalRating(Float.parseFloat(txtImdbRating1.getText()));
            movieModel.saveMovie(m);
        } else
        {
            editMovie.setName(txtTitle.getText());
            editMovie.setFileLink(txtPath.getText());
            editMovie.setRating(Float.parseFloat(txtImdbRating.getText()));
            editMovie.setPersonalRating(Float.parseFloat(txtImdbRating1.getText()));
            movieModel.saveEdit(editMovie);
        }
        Stage stage = (Stage) cancelMovieBtn.getScene().getWindow();   
        stage.close();
        
    }

    @FXML
    private void closeMovieAddBox(ActionEvent event)
    {
        Stage stage = (Stage) cancelMovieBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickLoadCategoriesBtn(ActionEvent event)
    {

    }

    @FXML
    private void clickChooseFile(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.mp4", "*.mpeg4"));
        File file = chooser.showOpenDialog(new Stage());
        try
        {
            String fullPath = file.getCanonicalPath();
            txtPath.setText(fullPath);
        } catch (IOException ex)
        {
            Logger.getLogger(AddWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setMovieModel(MovieModel movieModel)
    {
        this.movieModel = movieModel;
        comboCategory.setItems(catModel.getCategories());
    }

    void setEditMovie(Movies selectedItem)
    {
        editMovie = selectedItem;
        //comboCategory.getSelectionModel().select(editMovie.getCatName);
        txtTitle.setText(editMovie.getName());
        txtImdbRating.setText(String.valueOf(editMovie.getRating()));
        txtImdbRating1.setText(String.valueOf(editMovie.getPersonalRating()));
        txtPath.setText(editMovie.getFileLink());
        
        
    }

}
