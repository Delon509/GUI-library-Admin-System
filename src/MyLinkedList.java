//Cheung Tin Long 19055971d Intellij

public class MyLinkedList<E> implements MyList<E> {
	protected Node<E> head, tail;
	protected int size = 0; // Number of elements in the list

	/** Create an empty list */
	public MyLinkedList() {
	}

	/** Create a list from an array of objects */
	public MyLinkedList(E[] objects) {
		// Left as an exercise
		Node<E> newHead= new Node(objects[0]);
		Node<E> newTail= new Node(objects[objects.length-1]);
		head=newHead;
		tail=newTail;
		Node<E> previous = head;
		for(int i=1;i<(objects.length-1);++i){
			Node<E> temp= new Node(objects[i]);
			previous.next=temp;
			previous=temp;
		}
		previous.next=tail;
		size+= objects.length;
	}

	/** Return the head element in the list */
	public E getFirst() {
		// Left as an exercise
		return head.element;
	}

	/** Return the last element in the list */
	public E getLast() {
		// Left as an exercise
		return tail.element;
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		// Left as an exercise
		Node<E> newHead= new Node(e);
		if(size==0){
			head=newHead;
			tail=newHead;
		}
		else{
			Node<E> temp= head;
			head= newHead;
			head.next=temp;
		}

		++size;
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		// Left as an exercise
		Node<E> temp= new Node(e);
		if(size==0){
			head=temp;
			tail=temp;
		}
		else if(size==1){
			Node<E> current = head;
			current.next= temp;
			tail=temp;
		}
		else {
			tail.next=temp;
			tail=temp;
		}

		this.size++;

	}

	@Override /** Add a new element at the specified index 
	 * in this list. The index of the head element is 0 */
	public void add(int index, E e) {
		// Left as an exercise
		if(size==0){
			Node<E> temp = new Node(e);
			head=temp;
			tail=temp;
			size++;
		}
		else {
			if(index==0){
				//System.out.println("addFirst");
				this.addFirst(e);
			}
			else if(index>=size){
				//System.out.println("addLast");
				//System.out.println(this.toString());
				this.addLast(e);
				//System.out.println("after addLast the size is"+size);
				//System.out.println(this.toString());
			}
			else {
				//System.out.println("Just Add");
				Node<E> temp = new Node(e);
				Node<E> current = head;
				for (int i = 1; i < index; ++i) {
					current = current.next;
				}
				Node<E> store = current.next;
				current.next = temp;
				temp.next = store;
				++size;
			}
		}


	}

	/** Remove the head node and
	 *  return the object that is contained in the removed node. */
	public E removeFirst() {
		// Left as an exercise
		Node<E> temp =head;
		if(size==1){
			head=null;
			tail=null;
		}
		else {
			head=head.next;
		}


		size--;
		//System.out.println(this.toString());
		return temp.element;
	}

	/** Remove the last node and
	 * return the object that is contained in the removed node. */
	public E removeLast() {
		// Left as an exercise
		Node<E> current = head;
		if(size==1){
			head=null;
			tail=null;
			size--;
			//System.out.println(this.toString());
			return current.element;
		}

		else {
			while ( current.next.next !=null){
				current=current.next;
			}
			Node<E> temp= current.next;
			current.next=null;
			tail=current;
			size--;
			//System.out.println(this.toString());
			return temp.element;
		}

	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {   
		// Left as an exercise
		Node<E> temp;
		if(index==0){
			E store=this.removeFirst();
			return store;
		}
		else if(index>=size){
			E store=this.removeLast();
			return store;
		}
		else {
			Node<E> current = head;
			for(int i=1;i<index;++i){
				current=current.next;
			}
			temp=current.next;
			current.next=current.next.next;
			--size;
			//System.out.println(this.toString());
		}

		return temp.element;
	}

	@Override /** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); // Separate two elements with a comma
			}
			else {
				result.append("]"); // Insert the closing ] in the string
			}
		}

		return result.toString();
	}

	@Override /** Clear the list */
	public void clear() {
		// Left as an exercise
		head=null;
		tail=null;
		size=0;
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(Object e) {
		// Left as an exercise
		Node<E> current = head;
		while(current.next != null){
			if(current.element.equals(e) ){
				return  true;
			}
			else current=current.next;
		}
		return false;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		// Left as an exercise
		Node<E> current = head;
		for (int i = 0; i < index; ++i) {
			current = current.next;
		}
		System.out.println("get function using Index:"+ index);
		return current.element;
	}

	@Override /** Return the index of the first matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(Object e) {
		// Left as an exercise
		if(this.contains(e)){
			int index=0;
			Node<E> current = head;
			while(!current.element.equals(e)){
				current=current.next;
				index++;
			}
			return index;
		}
		else return -1;

	}

	@Override /** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		// Left as an exercise
			if(tail.element.equals(e)){return size-1;}
			if(this.contains(e)){
				int index=0,last=-1;
				Node<E> current = head;
				while(current.next !=null){
					if(current.element.equals(e)){last=index;}
					current=current.next;
					index++;
				}
				return last;
			}
			else  return  -1;

	}

	@Override /** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public E set(int index, E e) {
		// Left as an exercise
		Node<E> current = head;
		for (int i = 0; i < index; ++i) {
			current = current.next;
		}
		E temp= current.element;
		current.element=e;
		return temp;
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator 
	implements java.util.Iterator<E> {
		private Node<E> current = head; // Current node 
		private int index=-1; // initial index before head

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			index++;	
			current = current.next;
			return e;
		}

		@Override
		// remove the last element returned by the iterator
		public void remove() {
			MyLinkedList.this.remove(index);	
		}
	}

	protected static class Node<E> {
		// Left as an exercise
		protected E element;
		protected Node<E> next;

		public Node(E e){
			element=e;
		}
	}

	@Override /** Return the number of elements in this list */
	public int size() {
		// Left as an exercise
		return size;
	}
}