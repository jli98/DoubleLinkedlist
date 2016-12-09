import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DoubleLinkedlist {
	private static class Node {
		private int val;
		Node next;
		Node prev;
		Node(int v, Node next, Node prev) {
			val = v; this.next = next; this.prev = prev;
		}
	}
	private Node head;

	public void addStart(int v) {
		head = new Node(v, head, null);
		if (head.next != null)
			head.next.prev = head;
	}

	public void addEnd(int v) {
		if (head == null) {
			head = new Node(v, null, null);
			return;
		}
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
    p.next = new Node(v, null, p);
	}
	
	public void removeStart() {
		if (head == null)
			return;
		head = head.next;
		head.prev = null;
	}
	
	public void removeEnd() {
		if (head == null)
			return;
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.prev.next = null;
	}

	public int get(int i) {
		Node p = head;
		for (int j = 0; j < i; j++) {
			p = p.next;
		}
		return p.val;
	}
	
  public static class Iterator {
		private Node p;
		private Iterator(Node p) {
			this.p = p;
		}
		public Iterator(DoubleLinkedlist list) {
			p = list.head;
		}
		
		public boolean hasNext() {
			return p != null;
		}
		public void next() {
			p = p.next;
		}
		public void prev() {
			p = p.prev;
		}
		public int get() {
			return p.val;
		}
	}
  
/*		public Iterator end() {
		if (head == null)
			return new Iterator((Node)null);
		Node p;
		for (p = head; p.next != null; p = p.next)
			;
		return new Iterator(p);
	} */

	public static void main(String[] args) throws IOException{
		DoubleLinkedlist list = new DoubleLinkedlist();
		int v = 0;
		Scanner s = new Scanner(new FileReader("HW4b.txt"));
		while (s.hasNextLine()){
			String ins = s.next();
			if (ins.equals("ADD_FRONT")){
				int size = 0;
				int num = 0;
				int[] p = new int[size];
				while(s.hasNextInt()){
					size++;
					int[] temp = new int[size];
					for (int i = 0; i < size-1; i++)
						temp[i] = p[i];
					v = s.nextInt();
					temp[num] = v;
					num++;
					p = temp;
				}
				for (int i = size-1; i >=0; i--){
				list.addStart(p[i]);}
			}else
			
			if (ins.equals("ADD_BACK")){
				while(s.hasNextInt()){
					v = s.nextInt();
					list.addEnd(v);
				}
			}else
			
			if (ins.equals("REMOVE_FRONT")){	
				v = s.nextInt();
				for(int i = 0; i < v; i++)
					list.removeStart();
			}else
			
			if (ins.equals("REMOVE_BACK")){	
				v = s.nextInt();
				for(int i = 0; i < v; i++)
					list.removeEnd();
			}else
			
			if (ins.equals("OUTPUT")){	
				for (DoubleLinkedlist.Iterator i = new DoubleLinkedlist.Iterator(list) ; i.hasNext(); i.next())
					System.out.print(i.get() + " ");
					System.out.println();
			}
		}
	    s.close();
	}
}
