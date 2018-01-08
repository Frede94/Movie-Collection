/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.be;

/**
 *
 * @author Frederik Bærbar
 */
public class Category
{

    private String catName;
    private int catId;

    public Category(int id, String name)
    {
        this.catId = id;
        this.catName = name;
    }

    public Category()
    {
    }

    /**
     * Get the value of catId
     *
     * @return the value of catId
     */
    public int getCatId()
    {
        return catId;
    }

    /**
     * Set the value of catId
     *
     * @param catId new value of catId
     */
    public void setCatId(int catId)
    {
        this.catId = catId;
    }

    /**
     * Get the value of catName
     *
     * @return the value of catName
     */
    public String getCatName()
    {
        return catName;
    }

    /**
     * Set the value of catName
     *
     * @param catName new value of catName
     */
    public void setCatName(String catName)
    {
        this.catName = catName;
    }

    @Override
    public String toString()
    {
        return "Category{" + "catName=" + catName + ", catId=" + catId + '}';
    }

}
