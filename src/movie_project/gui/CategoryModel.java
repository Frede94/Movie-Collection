/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import javafx.collections.ObservableList;
import movie_project.be.Category;
import movie_project.bll.CategoryManager;

/**
 *
 * @author Mikkel
 */
public class CategoryModel
{
    private ObservableList<Category> categories;
    
    CategoryManager catManager = new CategoryManager();
    
    /**
     * Adds a new category
     * @param c 
     */
    void addNewCat(Category c)
    {
        categories.add(c);
        catManager.addNewCat(c);
    }
    
    
    
    void saveCategory(Category c)
    {
        categories.add(c);
        catManager.save(c);
    }
}
