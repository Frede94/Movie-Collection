/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_project.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author morte
 */
public class MediaPlayerController implements Initializable
{

    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    String path = new File ("C:\\Users\\morte\\Desktop\\small.mp4").getAbsolutePath();
   
        
     
   //  String path = new File (file).getAbsolutePath();
     me = new Media(new File (path).toURI().toString());
     mp = new MediaPlayer(me);
     mv.setMediaPlayer(mp);
     mp.setAutoPlay(true);
     
     
     
    }    
    
    

}
