/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import java.util.List;
import movie_project.be.Category;
import movie_project.dal.CategoryDAO;

/**
 *
 * @author Mikkel
 */
public class CategoryManager
{
    
    private CategoryDAO catDAO = new CategoryDAO();
    
    /**
     * Sender den ny kategori til DAL laget
     * @param c 
     */
    public void save (Category c)
    {
       catDAO.save(c);
    }

    public List<Category> getAllCategories()
    {
        return catDAO.getAllCategories();
    }
    
}
