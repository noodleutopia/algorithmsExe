package test;

import linked_list.ListNode;
import sortUtil.Sort;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		int[] array = new int[] {19, 2, 4, 89, 14, 23};
		ListNode list = initList(array);

//		for(int i = 0; i < 1000000; i++) {
//			array = new int[] {19, 2, 4};
////			Sort.popSort(array);
//			Sort.quickSort(array, 0, array.length - 1);
//
//		}
		
		Sort.quickSort(list, null);
		while(list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}
//		for(int i = 0; i < array.length; i++) {
//			System.out.print(array[i] + " ");
//		}
//		System.out.println("totle time: " + (System.currentTimeMillis() - start));
	}

	/**
	 * initialize Linked list by array
	 * @param array
	 * @return
	 */
	static ListNode initList(int[] array) {
		if(array.length < 1) {
			return null;
		}
		ListNode head = new ListNode(array[0]);
		ListNode rear = head;
		for (int i = 1; i < array.length; i++) {
			ListNode node = new ListNode(array[i]);
			rear.next = node;
			rear = node;
		}
		return head;
	}
}
