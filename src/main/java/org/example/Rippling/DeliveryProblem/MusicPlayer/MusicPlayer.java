package org.example.Rippling.DeliveryProblem.MusicPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicPlayer {
    private int songIdCounter = 1;
    private final Map<Integer, Song> songs = new HashMap<>();
    private final List<PlayObserver> observers = new ArrayList<>();

    private final PopularityManager popularityManager;
    private final PlayHistoryManager historyManager;

    public MusicPlayer() {
        this.popularityManager = new PopularityManager(songs);
        this.historyManager = new PlayHistoryManager(songs);
        observers.add(popularityManager);
        observers.add(historyManager);
    }

    public int addSong(String songTitle) {
        int id = songIdCounter++;
        Song song = new Song(id, songTitle);
        songs.put(id, song);
        return id;
    }

    public void playSong(int songId, int userId) {
        for (PlayObserver observer : observers) {
            observer.onSongPlayed(songId, userId);
        }
    }

    public void printMostPlayedSongs() {
        popularityManager.printMostPlayedSongs();
    }

    public List<String> getLastThreeSongs(int userId) {
        return historyManager.getLastThreeSongs(userId);
    }
}
