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
import javafx.collections.ObservableList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
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

    private CategoryModel catModel = new CategoryModel();

    private MovieModel movieModel = new MovieModel();

    private Movies selectedMovie;
    @FXML
    private ListView<Category> catList;
    @FXML
    private Button newCatBtn;
    @FXML
    private TableColumn<Movies, String> tableColumnName;
    @FXML
    private TableColumn<Movies, String> tableColumnRating;
    @FXML
    private TableColumn<Movies, String> tableColumnLastView;
    @FXML
    private TableColumn<Movies, String> tableColumnOwnRating;
    @FXML
    private Button btnDeleteCat;

    private Movies moviePlaying;

    private MediaPlayer mp;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        catList.setItems(catModel.getCategories());
        catModel.loadCategories();
        catList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        // tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("RatingIMDB"));
        tableColumnLastView.setCellValueFactory(new PropertyValueFactory<>("LastView"));
        tableColumnOwnRating.setCellValueFactory(new PropertyValueFactory<>("PersonalRating"));

        tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        movieView.setItems(movieModel.getMovies());
        movieModel.loadMovies();

    }

    /**
     * on action once a movie is selected.
     *
     * @param event
     */
    @FXML
    public void onSelectedMovie(MouseEvent event)
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
            ewc.setMovieModel(movieModel);
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
     * Henter informationerne som allerede er i en film og sætter dem ind i
     * txtfelterne som er i vores AddWindow, så man kan ændre i dem. Når man
     * ændre så sletter programmet den gamle film som man har valgt at edit.
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
            ewc.setMovieModel(movieModel);
            ewc.setEditMovie(movieView.getSelectionModel().getSelectedItem());
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

        Movies selectedMovie = movieView.getSelectionModel().getSelectedItem();

        movieModel.lastViewed(selectedMovie);
        movieModel.loadMovies();

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MediaPlayer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MediaPlayerController ewc = fxmlLoader.getController();
//            ewc.setMovieModel(movieModel);
//            ewc.setEditMovie(movieView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Play Movie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //        if (selectedMovie != null && selectedMovie.equals(moviePlaying))
        //        {
        //            if (mp.getStatus() == MediaPlayer.Status.PLAYING)
        //            {
        //                mp.pause();
        //            }
        //        } else
        //        {
        //            mp.play();
        //        }


        
         if ( selectedMovie != null && selectedMovie.equals(moviePlaying))
         {
             mp.pause();
         } else
         {
             mp.play();
         }
        
        
//        if (selectedMovie != null && selectedMovie.equals(moviePlaying))
//        {
//            if (mp.getStatus() == MediaPlayer.Status.PLAYING)
//            {
//                mp.pause();
//            }
//        } else
//        {
//            mp.play();
//        }


    }

    /**
     * Searches in the tableview.
     *
     * @param event
     */
    @FXML
    private void searchAction(ActionEvent event)
    {
        String searchText = filterField.getText().trim();
        if (!searchText.isEmpty())
        {
            movieModel.search(searchText);
        } else
        {
            movieModel.loadMovies();
        }
    }

    /**
     * adds a new category to the list.
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
//            categoryController.setCategoryModel(catModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("New Category");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

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
        alert.setHeaderText("Warning");
        alert.setContentText("Are you sure you want to delete this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {

            Movies selectedMovie = movieView.getSelectionModel().getSelectedItem();

            movieModel.remove(selectedMovie);

            // ... user chose OK
        } else
        {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void catSelectClick(MouseEvent event)
    {

    }

    /**
     * This method deletes the selected categories by sending the selected items
     * to the Categorymodel
     *
     * @param event
     */
    @FXML
    private void deleteCat(ActionEvent event)
    {
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                ObservableList<Category> selectedCategory = catList.getSelectionModel().getSelectedItems();

                catModel.removeCat(selectedCategory);
            } else
            {

            }
        }
    }
}
