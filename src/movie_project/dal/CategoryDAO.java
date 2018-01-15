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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextInputDialog;
import movie_project.be.Category;
import movie_project.be.Movies;

/**
 *
 * @author Mikkel
 */
public class CategoryDAO
{

    DataBaseConnector dbc = new DataBaseConnector();

    /**
     * Gemmer den ny kategori til databasen
     *
     * @param c
     */
    public void save(Category c)
    {

        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Category (catName) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql); //,stmt.RETURN_GENERATED_KEYS
            st.setString(1, c.getCatName());
            st.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * Henter kategorier fra DataBasen og sender dem vidre til BLL laget.
     *
     * @return
     */
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList();

        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Category");
            while (rs.next())
            {
                Category currentCategory = new Category();
                currentCategory.setCatId(rs.getInt("catId"));
                currentCategory.setCatName(rs.getString("catName"));

                categories.add(currentCategory);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    /**
     * Sletter den valgte kategori både fra listen og fra databasen
     *
     * @param selectedCategory
     */
    public void removeCat(Category selectedCategory)
    {
        try (Connection con = dbc.getConnection())
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE CategoryId =" + selectedCategory.getCatId() + ";DELETE FROM Category WHERE catId=" + selectedCategory.getCatId());

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the relations between the selected categories and the movies with a relation.
     * @param catId
     * @return 
     */
    public List<Movies> getRelation(int catId)
    {
        List<Movies> movies = new ArrayList();
        List<Integer> Id = new ArrayList();

        try (Connection con = dbc.getConnection())
        {
            String sql = "SELECT * FROM CatMovie WHERE CategoryId =" + catId;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Id.add(rs.getInt("MovieId"));
            }

            if (!Id.isEmpty())
            {
                sql = "SELECT * FROM Movie WHERE id =" + Id.get(0);

                for (int i = 1; i < Id.size(); i++)
                {
                    sql = sql + "OR id =" + Id.get(i);
                }
            } else
            {
                return null;
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {

                Movies currentMovie = new Movies();

                currentMovie.setId(rs.getInt("id"));
                currentMovie.setName(rs.getString("name"));
                currentMovie.setRating(rs.getFloat("ratingIMDB"));
                currentMovie.setFileLink(rs.getString("filelink"));
                currentMovie.setLastView(rs.getString("lastview"));
                movies.add(currentMovie);

            }
            return movies;
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
