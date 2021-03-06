/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import movie_project.be.Category;
import movie_project.be.Movie;

/**
 *
 * @author Frederik Bærbar
 */
public class MovieDAO
{

    private ObservableList<Movie> moviesInList;
    private MovieDAO movieDAO;
    private DataBaseConnector dataBaseConnector;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    DataBaseConnector dbc = new DataBaseConnector();

    /**
     * Gets a list from the "Movie" database with the Movie, Id, Name,
     * RatingIMDB, FileLink, LastView and RatingOwn
     *
     * @return
     */
    public List<Movie> getAllMovies()
    {

        List<Movie> movies = new ArrayList();

        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
            while (rs.next())
            {
                Movie currentMovie = new Movie();

                currentMovie.setId(rs.getInt("id"));
                currentMovie.setName(rs.getString("name"));
                currentMovie.setRating(rs.getFloat("ratingIMDB"));
                currentMovie.setFileLink(rs.getString("filelink"));
                currentMovie.setLastView(rs.getString("lastview"));

                currentMovie.setId(rs.getInt("Id"));
                currentMovie.setName(rs.getString("Name"));
                currentMovie.setRating(rs.getFloat("ratingIMDB"));
                currentMovie.setFileLink(rs.getString("fileLink"));
                currentMovie.setLastView(rs.getString("lastView"));

                currentMovie.setPersonalRating(rs.getFloat("ratingOwn")); // PersonalRating

                movies.add(currentMovie);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;
    }

    /*
    Sletter elementer fra databasen
    Dette er ende stationen for 'remove/delete'. Det er her, den foretager sletningen af
    filmen helt nede i databasen. 
    Først opretter den forbindelse til databasen.
    Statement - ordre til programmet "Gør dette"
    Vi skriver en statement til databasen, at den både skal slette den valgte film 
    i movieCatList (midten) og i movieView (højre). Den tager den valgte film og ID'et
    Hvis der ikke er forbindelse mellen Netbeans og databasen - i form af f.eks.
    forskellige navne, så smider den en SQLException, som bliver sendt op til 
    GUI i form af en dialog boks.
     */
    public void remove(Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE MovieId =" + selectedMovie.getId() + ";DELETE FROM Movie WHERE id=" + selectedMovie.getId());
        } catch (SQLException ex) //Hvis der er problemer mellem netbeans og databasen, så smider den denne catch
        {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Crash");
            alert.setHeaderText("Crash Report");
            alert.setContentText("Problem with communication"
                    + "\nbetween program and database");

            alert.showAndWait();

        }
    }

    /*
    gemmer de tastede data fra txtfelterne
     */
    public void saveMovie(Movie m)
    {
        try (Connection con = dbc.getConnection())
        {

            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Movie (name, ratingIMDB, filelink, ratingOwn) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);     //, stmt.RETURN_GENERATED_KEYS 

            st.setString(1, m.getName());
            st.setFloat(2, m.getRating());
            st.setString(3, m.getFileLink());
            st.setFloat(4, m.getPersonalRating());

            st.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Henter informationerne som allerede er i en film og sætter dem ind i
    txtfelterne som er i vores AddWindow, så man kan ændre i dem.
    Når man ændre så sletter programmet den gamle film som man har valgt at edit.
     */
    public void saveEdit(Movie editMovie)
    {
        try (Connection con = dbc.getConnection()) // opretter forbindelse til databasen ved hjælp af DataBaseConnector klassen
        {
            // create the java mysql update preparedstatement
            // her bruger vi editMovie variabel som vi har sendt ned fra de ovre lag
            // Vi laver en String med given navn, som vi giver en SQL statement som den skal køre.
            // f.eks. den første queryTitle, giver vi en statement som skal update en given position i
            // vores database tabel.
            String queryTitle = "Update Movie set name = ? where id =" + editMovie.getId(); // i dette statement skal den update Titel hvor ID passer som den ID som valgt i Editmovie.
            String queryRating = "Update Movie set ratingIMDB = ? where id =" + editMovie.getId();
            String queryPRating = "Update Movie set RatingOwn = ? where id =" + editMovie.getId();
            String queryPath = "Update Movie set filelink = ? where id =" + editMovie.getId();

            // her sætter vi prepared statments, som bruger queries som vi definere ovenfor
            PreparedStatement preparedStmtTitle = con.prepareStatement(queryTitle);
            PreparedStatement preparedStmtRating = con.prepareStatement(queryRating);
            PreparedStatement preparedStmtPRating = con.prepareStatement(queryPRating);
            PreparedStatement preparedStmtPath = con.prepareStatement(queryPath);

            // Her sætter vi datatyper til det samme som de er i Vores Database
            preparedStmtTitle.setString(1, editMovie.getName());
            preparedStmtRating.setFloat(1, editMovie.getRating());
            preparedStmtPRating.setFloat(1, editMovie.getPersonalRating());
            preparedStmtPath.setString(1, editMovie.getFileLink());

            // execute the java preparedstatement
            // kør de statements som vi har gjort klar til brug.
            preparedStmtTitle.executeUpdate();
            preparedStmtRating.executeUpdate();
            preparedStmtPRating.executeUpdate();
            preparedStmtPath.executeUpdate();

        } catch (SQLException ex) // hvis der er problemer med kommunikationen meller program og database
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Crash");
            alert.setHeaderText("Crash Report");
            alert.setContentText("Problem with communication"
                    + "\nbetween program and database");

            alert.showAndWait();
            //Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Denne metode kalder en metode inde i SQL, (GETDATE) hvor den sætter date,
     * på den valgte film.
     *
     * @param selectedMovie
     */
    public void lastViewed(Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {

            String queryLastView = "Update Movie SET lastView = GETDATE ( ) WHERE id = " + selectedMovie.getId();

            PreparedStatement preparedStmtLastView = con.prepareStatement(queryLastView);

            preparedStmtLastView.executeUpdate();

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method adds the relation between the selceted movie and the selected
     * categories to the database. The loop saves the categories one at a time,
     * this keeps the connection open until all categories are saved, instead of
     * opening a new connection for each category.
     *
     * @param selectedCats
     * @param selectedMovie
     */
    public void addCats(ObservableList<Category> selectedCats, Movie selectedMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            for (Category selectedCat : selectedCats)
            {
                String sql = "INSERT INTO CatMovie (CategoryId, MovieId) VALUES (?, ?);";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, selectedCat.getCatId());
                stmt.setInt(2, selectedMovie.getId());
                System.out.println(stmt.toString());
                stmt.execute();
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Bruges ikke endnu
     *
     * @param selectedCat
     * @param selectedMovie
     */
    public void addCat(Category selectedCat, Movie selectedMovie)
    {
        ObservableList<Category> cats = FXCollections.observableArrayList();
        cats.add(selectedCat);
        addCats(cats, selectedMovie);
    }

    /**
     * fjerner film fra en kategori uden at slette de fra Movie tabel i
     * databasen. den sletter relationer fra CatMovie tabelen i databasen.
     *
     * @param selectedCatMovie
     */
    public void removeCatMovie(Movie selectedCatMovie)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE MovieId =" + selectedCatMovie.getId());
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
