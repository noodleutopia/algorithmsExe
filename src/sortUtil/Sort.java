package sortUtil;

import linked_list.ListNode;

public class Sort {

	/**
	 * pop sort for array.
	 * 
	 * @param array
	 */
	public static void popSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * quick sort for linked list.
	 * 
	 * @param head
	 * @param rear
	 */
	public static void quickSort(ListNode head, ListNode rear) {

		if(head == rear) {
			return;
		}
		
		int m = head.val;
		ListNode i = head;
		ListNode j = i.next;
		
		while(j != rear) {
			if(j.val < m) {
				i = i.next;
				int temp = i.val;
				i.val = j.val;
				j.val = temp;
			}
			j = j.next;
		}
		head.val = i.val;
		i.val = m;
		
		quickSort(head, i);
		quickSort(i.next, null);
	}

	/**
	 * quick sort for array.
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] array, int low, int high) {

		if (low >= high) {
			return;
		}

		int m = low;
		int i = low + 1;
		int j = high;

		while (i < j) {
			while(i < j && array[j] >= array[m]) {
				j--;
			}
			
			while(i < j && array[i] <= array[m]) {
				i++;
			}
			
			if(i < j) {
				int t = array[i];
				array[i] = array[j];
				array[j] = t;
			}
		}
		int temp = array[m];
		array[m] = array[j];
		array[j] = temp;

		quickSort(array, low, j - 1);
		quickSort(array, j + 1, high);
	}
	
//	public static void quickSort(int[] array, int low, int high) {
//
//		if (low >= high) {
//			return;
//		}
//
//		int m = low;
//		int i = low + 1;
//		int j = high;
//
//		while (i < j) {
//			if (array[j] > array[m]) {
//				j--;
//			} else if (array[i] <= array[m]) {
//				i++;
//			} else {
//				int temp = array[i];
//				array[i] = array[j];
//				array[j] = temp;
//				j--;
//				i++;
//			}
//		}
//		int temp = array[m];
//		array[m] = array[j];
//		array[j] = temp;
//
//		quickSort(array, low, j - 1);
//		quickSort(array, j + 1, high);
//	}
	
	
	
}
