/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import movie_project.be.Category;
import movie_project.dal.CategoryDAO;

/**
 *
 * @author Mikkel
 */
public class CategoryManager
{
    
    private CategoryDAO catDAO;
    
    /**
     * Adds a new category
     * @param c 
     */
    public void addNewCat(Category c)
    {
        catDAO.addNewCat(c);
    }
    
    public void save (Category c)
    {
       catDAO.save(c);
    }
    
}
