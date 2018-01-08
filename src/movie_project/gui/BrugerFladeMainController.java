/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Frederik Bærbar
 */
public class BrugerFladeMainController implements Initializable
{
    
    private Label label;
    @FXML
    private TableView<?> movieView;
    @FXML
    private Button newMovieBtn;
    @FXML
    private Button editMovieBtn;
    @FXML
    private Button playMovieBtn;
    @FXML
    private JFXTextField filterField;
    @FXML
    private JFXButton searchBtn;
    
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void onSelectedMovie(MouseEvent event)
    {
    }

    @FXML
    private void newMovieOnAction(ActionEvent event)
    {
    }

    @FXML
    private void editMovieOnAction(ActionEvent event)
    {
    }

    @FXML
    private void playMovieOnAction(ActionEvent event)
    {
    }

    @FXML
    private void searchAction(ActionEvent event)
    {
    }

    @FXML
    private void NewCatAction(ActionEvent event)
    {
    }
    
}
