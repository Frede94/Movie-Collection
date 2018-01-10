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

    public CategoryModel()
    {
        this.categories = FXCollections.observableArrayList();
        categories.addAll(catManager.getAllCategories());
        loadCategories();
    }

    /**
     * Sender den ny kategori vidre til BLL laget.
     *
     * @param c
     */
    void saveCategory(Category c)
    {
        categories.add(c);
        catManager.save(c);
    }

    /**
     * Metoden gør det muligt for vores contoller at hente listen med kategorier
     *
     * @return
     */
    ObservableList<Category> getCategories()
    {
        return categories.sorted();

    }

    /**
     * Metoden laver listen med kategorier som den henter fra DAL laget via BLL
     * Laget
     */
    void loadCategories()
    {
        List<Category> loadedCategories = catManager.getAllCategories();
        categories.clear();
        categories.addAll(loadedCategories);
    }

    /**
     * This method recives the selecteed categories from the MainController. The
     * for loop runs through the list of selectedcategories and removes them
     * from both the listview, and the database, one at a time.
     *
     * @param selectedCategories
     */
    void removeCat(ObservableList<Category> selectedCategories)
    {
        for (int i = 0; i < selectedCategories.size(); i++)
        {
            catManager.removeCat(selectedCategories.get(i));
            categories.remove(selectedCategories.get(i));
        }

    }

}
