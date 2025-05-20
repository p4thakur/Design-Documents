package org.example.Rippling.DeliveryProblem.MusicPlayer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
class Song{
    static Integer songid;
    String  title;

     public Song(String title){
         songid++;
         this.title= title;
     }

}

public class MusicPlayerManager {

    Map<String,Song> songIdTOSongMap;
    Map<String, List<String>>  userIdSongMap;

    public MusicPlayerManager(){
        this.songIdTOSongMap= new HashMap<>();
        this.userIdSongMap= new HashMap<>();
    }


    public int addSong(String title){
        Song song = new Song(title);
        songIdTOSongMap.put(title,song) ;
        return  Song.songid;///check this

    }

    public void playSong(String userId, String songId){
        if(!songIdTOSongMap.containsKey(songId)){
            throw new IllegalArgumentException("no song");
        }

        userIdSongMap.computeIfAbsent(userId, k-> new ArrayList<>()).add(songId);


    }

    public  void printTopSongs(){
        //user id to song mapping..

    }


}
