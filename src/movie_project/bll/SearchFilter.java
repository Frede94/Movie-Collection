/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.bll;

import java.util.ArrayList;
import java.util.List;
import movie_project.be.Movies;

/**
 * Den søger efter specifikke elementer i vores Lister og Databasen
 *
 * @author Jens Karlskov
 */
public class SearchFilter
{

    public List<Movies> searchByMovieName(List<Movies> movies, String query)
    {
        List<Movies> searResult = new ArrayList<Movies>();

        for (Movies movie : movies)
        {
            if (movie.getName().toLowerCase().equalsIgnoreCase(query.toLowerCase())|| movie.getName().toLowerCase().contains(query.toLowerCase()))
            {
                searResult.add(movie);
            } else if (movie.getFileLink().toLowerCase().equalsIgnoreCase(query.toLowerCase()))
            {
                searResult.add(movie);
            }
        }

        return searResult;
    }

}
