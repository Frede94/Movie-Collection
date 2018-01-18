/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movie_project.be.Category;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class AddCategoryController implements Initializable
{

    
    @FXML
    private TextField categoryName;
    @FXML
    private Button saveCategoryBtn;
    @FXML
    private Button cancelCategoryBtn;

    private MovieModel movieModel = new MovieModel();
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }


    /**
     * Gemmer den indskrevne "category" til databasen.
     *
     * @param event
     */
    @FXML
    private void saveCategoryOnAction(ActionEvent event)
    {
        Category c = new Category();
        c.setCatName(categoryName.getText());
        movieModel.saveCategory(c);
        categoryName.clear();
               
        movieModel.loadCategories();
        
    }    

    /**
     * Annulerer indtastningen af "category", og vender tilbage til
     * brugerFladeMain.
     *
     * @param event
     */
    @FXML
    private void cancelCategoryOnAction(ActionEvent event)
    {

        Stage stage = (Stage) cancelCategoryBtn.getScene().getWindow();
        stage.close();
        
        movieModel.getCategories();

    }

    /**
     * sætter movieModel i denne klasse.
     * @param movieModel 
     */
    void setMovieModel(MovieModel movieModel)
    {
        this.movieModel = movieModel;
    }

}
