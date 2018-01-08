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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import movie_project.be.Movies;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class AddWindowController implements Initializable
{

    @FXML
    private ComboBox<?> comboCategory;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickAddAction(ActionEvent event)
    {
    }

    @FXML
    private void clickSaveAction(ActionEvent event)
    {
//        Movies m = new Movies();
//        m.setName(txtTitle);
//        m.setPersonalRating(Float.parseFloat(txtImdbRating1));
//        m.setRating(Float.parseFloat(txtImdbRating));
//        m.setFileLink(txtPath);
    }

    @FXML
    private void closeMovieAddBox(ActionEvent event)
    {
    }

    @FXML
    private void clickLoadCategoriesBtn(ActionEvent event)
    {
    }

    @FXML
    private void clickChooseFile(ActionEvent event)
    {
    }
    
}
