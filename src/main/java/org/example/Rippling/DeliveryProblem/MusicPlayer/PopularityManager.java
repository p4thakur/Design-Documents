package org.example.Rippling.DeliveryProblem.MusicPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//import java.util.Set;

public class PopularityManager implements PlayObserver {
    private final Map<Integer, Set<Integer>> songToUsers = new HashMap<>();
    private final Map<Integer, Integer> songToCount = new HashMap<>();
    private final Map<Integer, DoublyLinkedList> freqMap = new HashMap<>();
    private final Map<Integer, ListNode> songToNode = new HashMap<>();
    private final Map<Integer, Song> songMap;
    private int maxFreq = 0;

    public PopularityManager(Map<Integer, Song> songMap) {
        this.songMap = songMap;
    }

    @Override
    public void onSongPlayed(int songId, int userId) {
        if (!songMap.containsKey(songId)) return;

        songToUsers.putIfAbsent(songId, new HashSet<>());
        Set<Integer> users = songToUsers.get(songId);

        if (users.add(userId)) {
            int oldCount = songToCount.getOrDefault(songId, 0);
            int newCount = oldCount + 1;
            songToCount.put(songId, newCount);
            maxFreq = Math.max(maxFreq, newCount);

            if (oldCount > 0) {
                DoublyLinkedList oldList = freqMap.get(oldCount);
                ListNode oldNode = songToNode.get(songId);
                oldList.remove(oldNode);
                if (oldList.isEmpty()) freqMap.remove(oldCount);
            }

            ListNode newNode = new ListNode(songId);
            freqMap.computeIfAbsent(newCount, k -> new DoublyLinkedList()).addFirst(newNode);
            songToNode.put(songId, newNode);
        }
    }

    public void printMostPlayedSongs() {
        for (int freq = maxFreq; freq > 0; freq--) {
            if (!freqMap.containsKey(freq)) continue;
            ListNode curr = freqMap.get(freq).getFirst();
            while (curr != null && curr.value != -1) {
                System.out.println(songMap.get(curr.value).title + " → " + freq + " unique plays");
                curr = curr.next;
                if (curr == null || curr.value == -1) break;
            }
        }
    }
}