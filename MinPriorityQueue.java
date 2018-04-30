/**
 * This class creates a min priority queue implemented using a heap. 
 * @author Jennifer Storozum
 * COSI 21a PA3
 * 8/817
 */

public class MinPriorityQueue {
	
	private Process[] queue;
	private int size;
	
	/**
	 * Constructor
	 * @param capacity
	 */
	public MinPriorityQueue(int capacity){
		queue = new Process[capacity + 1];
		size = -1;
	}
	
	/**
	 * returns false if the queue is empty
	 * @return
	 */
	public boolean isEmpty(){
		return size == -1;
	}
	
	/**
	 * enqueue adds a process p to the end of the queue
	 * @param p
	 * @throws Exception
	 */
	public void enqueue(Process p) throws Exception{
		minHeapInsert(p);
	}

	/**
	 * dequeue removes and returns the first element in the queue
	 * by calling the extractMin method on the heap
	 * @return
	 * @throws Exception
	 */
	public Process dequeue() throws Exception{
		if(this.isEmpty()){
			throw new Exception("Empty priority queue");
		}
		return extractMin();
	}
	
	/**
	 * returns how many elements are on the heap
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * returns the minimum element in the queue
	 * @return
	 */
	public int getMinimum(){
		return queue[0].getPriority();
	}
	
	/**
	 * inserts a new process into the heap. will resize the queue if the 
	 * queue is too short (arbitrarily set at len/2) just in case. after
	 * inserting at the last index, heapify to maintain heap properties
	 * @param p
	 * @throws Exception
	 */
	public void minHeapInsert(Process p) throws Exception{
		size++;
		if(size >= queue.length/2){
			this.resize();
		}
		queue[size] = p;
		heapifyUp(size);
	}
	
	/**
	 * returns the process with the minimum value, the heapifies
	 * to maintain heap property
	 * @return
	 * @throws Exception
	 */
	public Process extractMin() throws Exception{
		if(size < 0){
			throw new Exception("Heap underflow");
		}
		Process min = queue[0];
		queue[0] = queue[size];
		size--;
		heapifyDown(0);
		return min;
	}

	/**
	 * Resize the queue to accomodate more processes by making a new array
	 * with 2x capacity and then copying the values from the old array into
	 * the new one.
	 */
	private void resize(){
		Process[] oldQ = queue;
		Process[] newQ = new Process[this.queue.length * 2];
		for(int i = 0; i < this.size; i++){
			newQ[i] = oldQ[i];
		}
		queue = newQ;
	}
	
	/**
	 * helper function to get the index of the parent of A[i]
	 * @param i
	 * @return index of the parent of A[i]
	 */
	private int parent(int i){
		return (i-1)/2;
	}
	
	/**
	 * helper function to get the index of the left child of A[i]
	 * @param i
	 * @return index of the left child of A[i]
	 */
	private int left(int i){
		return 2 * i + 1;
	}
	
	/**
	 * helper function to get the index of the right child of A[i]
	 * @param i
	 * @return index of the right child of A[i]
	 */
	private int right(int i){
		return (2 * i) + 2;
	}
	
	/**
	 * restores the heap property by percolating down
	 * @param i
	 */
	private void heapifyDown(int i){
		int l = left(i);
		int r = right(i);
		int smallest = i;
		//if prioritiy of a[i] > a[left], set smallest = left, otherwise do nothing
		if(l <= size && queue[l].getPriority() < queue[i].getPriority()){
			smallest = l;
		} else {
			smallest = i;
		}
		//if priority of a[smallest] > a[right], set smallest = right
		if(r <= size && queue[r].getPriority() < queue[smallest].getPriority()){
			smallest = r;
		}
		//if a[i] isn't the smallest element, swap a[i] and a[smallest] and heapify down again
		if(smallest != i){
			Process temp = queue[i];
			queue[i] = queue[smallest];
			queue[smallest] = temp;
			heapifyDown(smallest);
		}
	}
	
	/**
	 * maintain the heap property by percolating up
	 * while A[i] has parent, compare the priorities of A[i] and its parent,
	 * and swap a[i] with its parent if the parent is larger
	 * @param i
	 */
	private void heapifyUp(int i){
		while(i > 0 && queue[i].getPriority() < queue[parent(i)].getPriority()){
			Process temp = queue[i];
			queue[i] = queue[parent(i)];
			queue[parent(i)] = temp;
			i = parent(i);
		}
	}
}
	
	 




