package sortUtil;

import java.util.ArrayList;
import java.util.List;

import linked_list.ListNode;

public class Sort {

	public static void main(String[] args) {
		int[] array = { 2, 6, 3, 1 };
		mergeSort(array, 0, array.length - 1);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

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

		if (head == rear) {
			return;
		}

		int m = head.val;
		ListNode i = head;
		ListNode j = i.next;

		while (j != rear) {
			if (j.val < m) {
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
	 * 
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
			while (i < j && array[j] >= array[m]) {
				j--;
			}

			while (i < j && array[i] <= array[m]) {
				i++;
			}

			if (i < j) {
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

	/**
	 * merge sort for array.
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void mergeSort(int[] array, int low, int high) {
		int mid = (high + low) / 2;
		if (low < mid || mid + 1 < high) {
			mergeSort(array, low, mid);
			mergeSort(array, mid + 1, high);
		}
		merge(array, low, mid, high);
	}

	public static void merge(int[] array, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int s1 = low;
		int s2 = mid + 1;
		int i = 0;
		int j = low;
		while (s1 <= mid && s2 <= high) {
			if (array[s1] <= array[s2]) {
				temp[i++] = array[s1++];
			} else {
				temp[i++] = array[s2++];
			}
		}
		while (s1 <= mid) {
			temp[i++] = array[s1++];
		}
		while (s2 <= high) {
			temp[i++] = array[s2++];
		}

		for (j = low, i = 0; j <= high; j++, i++) {
			array[j] = temp[i];
		}
	}

}
