/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.util.List;
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
    
    /**
     * Sender den ny kategori vidre til BLL laget.
     * @param c 
     */
    void saveCategory(Category c)
    {
        categories.add(c);
        catManager.save(c);
    }

    ObservableList<Category> getCategories()
    {
        return categories;
    }

    void loadCategories()
    {
        List<Category> loadedCategories = catManager.getAllCategories();
        categories.clear();
        categories.addAll(loadedCategories);
    }
            
}
