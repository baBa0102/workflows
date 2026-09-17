import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private class DoubleLinkedList {
        Node head;
        Node tail;
        int size;
        
        public DoubleLinkedList() {
            head = new Node(0, 0); 
            tail = new Node(0, 0); 
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        
        public void addNode(Node node) {
            Node nextNode = head.next;
            head.next = node;
            node.prev = head;
            node.next = nextNode;
            nextNode.prev = node;
            size++;
        }
        
        public void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
        
        public Node removeTail() {
            if (size > 0) {
                Node lru = tail.prev;
                removeNode(lru);
                return lru;
            }
            return null;
        }
    }
    
    private final int capacity;
    private int size;
    private int minFreq;
    private final Map<Integer, Node> keyMap;
    private final Map<Integer, DoubleLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        updateFreq(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) return;
        
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }
        
        if (size >= capacity) {
            DoubleLinkedList minFreqList = freqMap.get(minFreq);
            Node lruNode = minFreqList.removeTail();
            keyMap.remove(lruNode.key);
            size--;
        }
        

        Node newNode = new Node(key, value);
        keyMap.put(key, newNode);
        
        freqMap.putIfAbsent(1, new DoubleLinkedList());
        freqMap.get(1).addNode(newNode);
        
        minFreq = 1; 
        size++;
    }
    
    private void updateFreq(Node node) {
        int oldFreq = node.freq;
        DoubleLinkedList oldList = freqMap.get(oldFreq);
        oldList.removeNode(node);
        
        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }
        
        node.freq++;
        freqMap.putIfAbsent(node.freq, new DoubleLinkedList());
        freqMap.get(node.freq).addNode(node);
    }
}
