/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movie_project.be.Category;
import movie_project.be.Movies;

/**
 *
 * @author Frederik Bærbar
 */
public class BrugerFladeMainController implements Initializable
{

    private Label label;
    @FXML
    private TableView<Movies> movieView;
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
    @FXML
    private Button btnDeleteMovie;

    private CategoryModel catModel;

    private MovieModel movieModel = new MovieModel();

    private Movies selectedMovie;
    @FXML
    private ListView<?> catList;
    @FXML
    private Button newCatBtn;
    @FXML
    private TableColumn<Movies, String> tableColumnName;
    @FXML
    private TableColumn<Movies, Integer> tableColumnRating;
    @FXML
    private TableColumn<Movies, String> tableColumnLastView;
    @FXML
    private TableColumn<Movies, Integer> tableColumnOwnRating;

    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
        tableColumnName.setCellValueFactory(new PropertyValueFactory("Name"));
        tableColumnRating.setCellValueFactory(new PropertyValueFactory("RatingIMDB"));
        tableColumnLastView.setCellValueFactory(new PropertyValueFactory("LastView"));
        tableColumnOwnRating.setCellValueFactory(new PropertyValueFactory("RatingOwn"));
        
        
     
      movieView.setItems(movieModel.getMovies());
      movieModel.loadMovies();
        
      
      
    }

    /**
     * on action once a movie is selected.
     *
     * @param event
     */
    @FXML
    private void onSelectedMovie(MouseEvent event)
    {
    }

    /**
     * Opens a new window when you press new movie
     *
     * @param event
     */
    @FXML
    private void newMovieOnAction(ActionEvent event)
    {

        movieModel.loadMovies();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            AddWindowController ewc = fxmlLoader.getController();
            // ewc.setSongModel(songModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("New Movie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * opens a new window when you press edit movie.
     *
     * @param event
     */
    @FXML
    private void editMovieOnAction(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            AddWindowController ewc = fxmlLoader.getController();
            // ewc.setSongModel(songModel);
            //   ewc.setEditSong(songsList.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit Movie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Plays the selected movie
     *
     * @param event
     */
    @FXML
    private void playMovieOnAction(ActionEvent event)
    {
movieModel.loadMovies();
    }

    
    
    /**
     * Searches in the tableview.
     *
     * @param event
     */
    @FXML
    private void searchAction(ActionEvent event)
    {
    }

    /**
     * adds a new categori to the list.
     *
     * @param event
     */
    @FXML
    private void NewCatAction(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            AddCategoryController categoryController = fxmlLoader.getController();
            //categoryController.setPlaylistModel(CategoryModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("New Playlist");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Category c = new Category();
        catModel.addNewCat(c);

    }

    /**
     * deletes the selected movie from the list
     *
     * @param event
     */
    @FXML
    private void clickDeleteAction(ActionEvent event)
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {

            Movies selectredMovie = movieView.getSelectionModel().getSelectedItem();

            movieModel.remove(selectedMovie);

            // ... user chose OK
        } else
        {
            // ... user chose CANCEL or closed the dialog
        }

    }
}
