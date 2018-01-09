/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movie_project.be.Category;
import movie_project.bll.CategoryManager;

/**
 *
 * @author Mikkel
 */
public class CategoryModel
{
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    
    CategoryManager catManager = new CategoryManager();  
    
    
    void saveCategory(Category c)
    {
        categories.add(c);
        catManager.save(c);
    }
            
}
