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
public class Movies
{

    private int Id;
    private String Name;
    private float rating;
    private String FileLink;
    private String LastView;
    private String PersonalRating;

    /**
     * Get the value of PersonalRating
     *
     * @return the value of PersonalRating
     */
    public String getPersonalRating()
    {
        return PersonalRating;
    }

    /**
     * Set the value of PersonalRating
     *
     * @param PersonalRating new value of PersonalRating
     */
    public void setPersonalRating(String PersonalRating)
    {
        this.PersonalRating = PersonalRating;
    }

    /**
     * Get the value of LastView
     *
     * @return the value of LastView
     */
    public String getLastView()
    {
        return LastView;
    }

    /**
     * Set the value of LastView
     *
     * @param LastView new value of LastView
     */
    public void setLastView(String LastView)
    {
        this.LastView = LastView;
    }

    /**
     * Get the value of FileLink
     *
     * @return the value of FileLink
     */
    public String getFileLink()
    {
        return FileLink;
    }

    /**
     * Set the value of FileLink
     *
     * @param FileLink new value of FileLink
     */
    public void setFileLink(String FileLink)
    {
        this.FileLink = FileLink;
    }

    /**
     * Get the value of rating
     *
     * @return the value of rating
     */
    public float getRating()
    {
        return rating;
    }

    /**
     * Set the value of rating
     *
     * @param rating new value of rating
     */
    public void setRating(float rating)
    {
        this.rating = rating;
    }

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name)
    {
        this.Name = Name;
    }

    /**
     * Get the value of Id
     *
     * @return the value of Id
     */
    public int getId()
    {
        return Id;
    }

    /**
     * Set the value of Id
     *
     * @param Id new value of Id
     */
    public void setId(int Id)
    {
        this.Id = Id;
    }

    @Override
    public String toString()
    {
        return "Movies{" + "Id=" + Id + ", Name=" + Name + ", rating=" + rating + ", FileLink=" + FileLink + ", LastView=" + LastView + ", PersonalRating=" + PersonalRating + '}';
    }

}
