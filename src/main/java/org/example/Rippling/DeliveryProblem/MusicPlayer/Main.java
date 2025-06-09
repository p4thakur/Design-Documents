package org.example.Rippling.DeliveryProblem.MusicPlayer;

public class Main {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        int s1 = player.addSong("Shape of You");
        int s2 = player.addSong("Believer");
        int s3 = player.addSong("Counting Stars");
        int s4 = player.addSong("Thunder");

        player.playSong(s1, 101);
        player.playSong(s2, 101);
        player.playSong(s3, 101);
        player.playSong(s1, 102);
        player.playSong(s4, 101);

        System.out.println("Most Played Songs:");
        player.printMostPlayedSongs();

        System.out.println("\nLast 3 songs played by user 101:");
        System.out.println(player.getLastThreeSongs(101));
    }
}
