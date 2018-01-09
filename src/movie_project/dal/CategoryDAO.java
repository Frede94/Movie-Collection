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

/**
 *
 * @author Mikkel
 */
public class CategoryDAO
{

    DataBaseConnector dbc = new DataBaseConnector();

    /**
     * Gemmer den ny kategori til databasen
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
}
