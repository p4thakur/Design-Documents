package org.example.Rippling.DeliveryProblem.MusicPlayer;

import java.util.*;

public  class PlayHistoryManager implements PlayObserver {
    private final Map<Integer, DoublyLinkedList> userToHistoryList = new HashMap<>();
    private final Map<Integer, Map<Integer, ListNode>> userToNodeMap = new HashMap<>();
    private final Map<Integer, Song> songMap;
    private static final int MAX_HISTORY = 3;

    public PlayHistoryManager(Map<Integer, Song> songMap) {
        this.songMap = songMap;
    }

    @Override
    public void onSongPlayed(int songId, int userId) {
        if (!songMap.containsKey(songId)) return;

        userToHistoryList.putIfAbsent(userId, new DoublyLinkedList());
        userToNodeMap.putIfAbsent(userId, new HashMap<>());
        Map<Integer, ListNode> nodeMap = userToNodeMap.get(userId);
        DoublyLinkedList historyList = userToHistoryList.get(userId);

        if (nodeMap.containsKey(songId)) {
            historyList.remove(nodeMap.get(songId));
        }

        ListNode newNode = new ListNode(songId);
        historyList.addFirst(newNode);
        nodeMap.put(songId, newNode);

        if (nodeMap.size() > MAX_HISTORY) {
            ListNode lru = historyList.tail.prev;
            historyList.remove(lru);
            nodeMap.remove(lru.value);
        }
    }

    public List<String> getLastThreeSongs(int userId) {
        List<String> result = new ArrayList<>();
        if (!userToHistoryList.containsKey(userId)) return result;

        ListNode curr = userToHistoryList.get(userId).getFirst();
        while (curr != null && curr.value != -1 && result.size() < MAX_HISTORY) {
            result.add(songMap.get(curr.value).title);
            curr = curr.next;
        }
        return result;
    }
}
