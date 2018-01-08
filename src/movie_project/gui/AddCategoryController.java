/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movie_project.be.Category;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class AddCategoryController implements Initializable
{

    @FXML
    private TextField categoryName;
    @FXML
    private Button saveCategoryBtn;
    @FXML
    private Button cancelCategoryBtn;
    
    private CategoryModel categoryModel;
    private Category editCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    /**
     * Gemmer den indskrevne "category" til databasen.
     * @param event 
     */
    @FXML
    private void saveCategoryOnAction(ActionEvent event)
    {
        if (editCategory == null)
        {
            
            Category c = new Category();
            c.setCatName(categoryName.getText());
            categoryModel.saveCategory(c);
        }
//else
//        {
//            editCategory.setCatName(catName);
//            categoryModel.saveCategory(editCategory);
//        }
    }

    /**
     * Annulerer indtastningen af "category", og vender tilbage til brugerFladeMain.
     * @param event 
     */
    @FXML
    private void cancelCategoryOnAction(ActionEvent event)
    {
        
        Stage stage = (Stage) cancelCategoryBtn.getScene().getWindow();
        stage.close();
        
    }
    
    
    

}
