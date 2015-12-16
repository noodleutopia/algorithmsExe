import java.io.IOException;

import binary_tree.TreeNode;
import linked_list.ListNode;

public class Attempt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Enter a Number:");
		int i = (int) System.in.read();
		String result = canWin(i) ? "win" : "lose";
		System.out.println("you will " + result);
	}

	private static boolean canWin(int n) {
		if (n % 4 != 0) {
			return true;
		} else {
			return false;
		}
	}

	public int addDigits(int num) {
		if (num % 9 == 0) {
			return 9;
		}
		return num % 9;
	}

	/**
	 * Definition for a binary tree node. public class TreeNode { int val;
	 * TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 */
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int res = 1;
		int lnodes = maxDepth(root.left);
		int rnodes = maxDepth(root.right);
		res += Math.max(lnodes, rnodes);
		return res;
	}

	/**
	 * Definition for singly-linked list. public class ListNode { int val;
	 * ListNode next; ListNode(int x) { val = x; } }
	 */
	public void deleteNode(ListNode node) {
		if (node.next != null) {
			node.val = node.next.val;
			if (node.next.next != null) {
				deleteNode(node.next);
			} else {
				node.next = null;
			}
		}
	}

	public void moveZeroes(int[] nums) {
		int p1 = 0;
		int p2 = p1;
		while (p1 < nums.length && p2 < nums.length) {
			if (nums[p1] != 0) {
				p1++;
			} else {
				p2 = p1 + 1;
				while (p2 < nums.length && nums[p2] == 0) {
					p2++;
				}
				if (p2 < nums.length) {
					nums[p1] = nums[p2];
					nums[p2] = 0;
					p1++;
					p2++;
				} else {
					break;
				}
			}
		}
	}

}
