/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import movie_project.be.Category;
import movie_project.be.Movie;

/**
 *
 * @author Frederik Bærbar
 */
public class BrugerFladeMainController implements Initializable
{

    private Label label;
    @FXML
    public TableView<Movie> movieView;
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

    private MovieModel movieModel = new MovieModel();

    private Movie selectedMovie;

    private Movie selectedCatMovie;

    private ObservableList<Category> selectedCats;

    @FXML
    private ListView<Category> catList;
    @FXML
    private Button newCatBtn;
    @FXML
    private TableColumn<Movie, String> tableColumnName;
    @FXML
    private TableColumn<Movie, String> tableColumnRating;
    @FXML
    private TableColumn<Movie, String> tableColumnLastView;
    @FXML
    private TableColumn<Movie, String> tableColumnOwnRating;
    @FXML
    private Button btnDeleteCat;

    private Movie moviePlaying;

    @FXML
    private TableView<Movie> movieCatList;
    @FXML
    private TableColumn<Movie, String> tableColumnNameCat;
    @FXML
    private TableColumn<Movie, String> tableColumnImdbCat;
    @FXML
    private TableColumn<Movie, String> tableColumnLastViewCat;
    @FXML
    private TableColumn<Movie, String> tableColumnMyRatingCat;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        catList.setItems(movieModel.getCategories());
        movieModel.loadCategories();
        catList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        // tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("RatingIMDB"));
        tableColumnLastView.setCellValueFactory(new PropertyValueFactory<>("LastView"));
        tableColumnOwnRating.setCellValueFactory(new PropertyValueFactory<>("PersonalRating"));

        tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        movieView.setItems(movieModel.getMovies());
        movieCatList.setItems(movieModel.getSelectedMovies());
        tableColumnNameCat.setCellValueFactory(new PropertyValueFactory<>("Name"));

        tableColumnLastViewCat.setCellValueFactory(new PropertyValueFactory<>("LastView"));
        tableColumnMyRatingCat.setCellValueFactory(new PropertyValueFactory<>("PersonalRating"));

        tableColumnImdbCat.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieModel.loadMovies();

        rememberToDelete();

    }

    public void rememberToDelete()
    {
        //Når programmet starter, så kommer der først en informations boks frem, 
        //som siger, at man skal huske at slette de film med en personlig rating
        //under 6, og som man ikke har set i 2 år
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Remember to delete movies with a personal rating under 6,"
                + "\nand movies that haven't been viewed in 2 years. ");

        alert.showAndWait();
    }

    /*
    fjerner en film fra CatMovie listen i databasen, når den gør dette sletter den
    ikke filmen, den fjerne relationer mellem kategori og film.
     */
    @FXML
    private void removeMovieCats(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Movie selectedCatMovie = movieCatList.getSelectionModel().getSelectedItem();
            movieModel.removeCatMovie(selectedCatMovie);
        } else
        {
            //on cancel...
            //DO NOTHING...
        }
    }

    /**
     * Opens a new window when you press new movie Det vindue den åbner har
     * samme model som MainVinduet
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Crash");
            alert.setHeaderText("Crash Report");
            alert.setContentText("No movie selected!"
                    + "\nPlease selected a movie");

            alert.showAndWait();
        }

    }

    /**
     * Plays the selected movie in system default program.
     *
     * @param event
     */
    @FXML
    private void playMovieOnAction(ActionEvent event)
    {
        Movie selectedCatMovie = movieCatList.getSelectionModel().getSelectedItem();
        Movie selectedMovie = movieView.getSelectionModel().getSelectedItem();
        if (selectedCatMovie != null)
        {
            try
            {
                movieModel.lastViewed(selectedCatMovie);
                movieModel.loadMovies();

                File movieFile = new File(selectedCatMovie.getFileLink());
                Desktop.getDesktop().open(movieFile);

                selectedCats = catList.getSelectionModel().getSelectedItems();
                movieModel.setMoviesByRelation(catList.getSelectionModel().getSelectedItems());
            } catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Crash");
                alert.setHeaderText("Crash Report");
                alert.setContentText("No movie selected!"
                        + "\nPlease selected a movie");

                alert.showAndWait();
            }
        } else if (selectedCatMovie == null)
        {
            try
            {
                movieModel.lastViewed(selectedMovie);
                movieModel.loadMovies();

                File movieFile = new File(selectedMovie.getFileLink());
                Desktop.getDesktop().open(movieFile);

                selectedCats = catList.getSelectionModel().getSelectedItems();
                movieModel.setMoviesByRelation(catList.getSelectionModel().getSelectedItems());
            } catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Crash");
                alert.setHeaderText("Crash Report");
                alert.setContentText("No movie selected!"
                        + "\nPlease selected a movie");

                alert.showAndWait();
            }
        }

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
            movieModel.loadCategories();
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
            categoryController.setMovieModel(movieModel);
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
        Movie selectedMovie = movieView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Warning");
            alert.setContentText("Are you sure you want to delete this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {

                movieModel.remove(selectedMovie);

                // ... user chose OK
            } else
            {
                // ... user chose CANCEL or closed the dialog
            }

        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Crash");
            alert.setHeaderText("Crash Report");
            alert.setContentText("No movie selected!"
                    + "\nPlease selected a movie");

            alert.showAndWait();
        }
    }

    /**
     * Sets the movies with a relation to the selected categories, to the middel
     * list in the program.
     *
     * @param event
     */
    @FXML
    private void catSelectClick(MouseEvent event
    )
    {
        try
        {
            selectedCats = catList.getSelectionModel().getSelectedItems();
            movieModel.setMoviesByRelation(catList.getSelectionModel().getSelectedItems());
        } catch (Exception ex)
        {
            System.out.println("No relations yet");
        }
    }

    /**
     * This method deletes the selected categories by sending the selected items
     * to the Categorymodel.
     *
     * @param event
     */
    @FXML
    private void deleteCat(ActionEvent event
    )
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

                movieModel.removeCat(selectedCategory);
            } else
            {

            }
        }
    }

    /**
     * This method allows the user to set the categories in relation to a movie.
     * Because of the multi seletion model for the listview contaning the
     * Categories selecting one or more Categories creates a list of selected
     * Categories.
     *
     * @param event
     */
    @FXML
    private void setMovieCats(ActionEvent event
    )
    {

        selectedMovie = movieView.getSelectionModel().getSelectedItem();

        selectedMovie = movieView.getSelectionModel().getSelectedItem();
        selectedMovie.getCatsList().addAll(selectedCats);
        movieModel.addCats(selectedCats, selectedMovie);

        selectedCats = catList.getSelectionModel().getSelectedItems();
        movieModel.setMoviesByRelation(catList.getSelectionModel().getSelectedItems());

    }

}
