import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import binary_tree.TreeNode;
import linked_list.ListNode;

public class Attempt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// System.out.print("Enter a Number:");
		// int i = (int) System.in.read();
		// String result = canWin(i) ? "win" : "lose";
		// System.out.println("you will " + result);
//		System.out.println(isAnagram(s, t));
		char[][] board = {"7...4....".toCharArray(),"...865...".toCharArray(),".1.2.....".toCharArray(),
				".....9...".toCharArray(),"....5.5..".toCharArray(),".........".toCharArray(),"......2..".toCharArray(),".........".toCharArray(),".........".toCharArray()};
		System.out.println("you will " + isValidSudoku(board));
	}

	/**
	 * PROBLEM 292
	 */
	public static boolean canWin(int n) {
		if (n % 4 != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * PROBLEM 258
	 */
	public int addDigits(int num) {
		if (num == 0) {
			return 0;
		} else {
			return (num % 9) == 0 ? 9 : (num % 9);
		}
	}

	/**
	 * PROBLEM 104
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
	 * PROBLEM 237
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

	/**
	 * PROBLEM 283
	 */
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

	/**
	 * PROBLEM 100
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		} else {
			if (p.val == q.val) {
				return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
			} else {
				return false;
			}
		}
	}

	/**
	 * PROBLEM 226
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		} else if (root.left == null && root.right == null) {
			return root;
		} else {
			// left or right is not null
			TreeNode newleft = invertTree(root.left);
			TreeNode newright = invertTree(root.right);
			if (newleft == null) {
				root.left = newright;
				root.right = null;
			} else if (newright == null) {
				root.right = newleft;
				root.left = null;
			} else {
				// left and right are both not null
				root.left = newright;
				root.right = newleft;
			}
		} //
		return root;
	}

	/**
	 * PROBLEM 242
	 */
	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] array1 = new int[26];
		int[] array2 = new int[26];
		for (int j = 0; j < s.length(); j++) {
			int key = (int) (s.charAt(j) - 'a');
			int key2 = (int) (t.charAt(j) - 'a');
			array1[key]++;
			array2[key2]++;
		}
		for (int i = 0; i < 26; i++) {
			if (array1[i] > 0) {
				if (array1[i] != array2[i]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * PROBLEM 217
	 */
	public boolean containsDuplicate(int[] nums) {
		if (nums.length < 2) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * PROBLEM 171
	 */
	public int titleToNumber(String s) {
		int result = 0;
		int lenth = s.length();
		for (int i = lenth - 1; i > -1; i--) {
			result += Math.pow(26, (lenth - 1 - i)) * convert(s.charAt(i));
		}
		return result;
	}

	int convert(char c) {
		return ((int) (c - 'A') + 1);
	}

	/**
	 * PROBLEM 168
	 */
	// TODO: 这题竟然想了很久，虽然AC但是还是没做出完美答案（事实上是错的）。
	// 其实是思路有问题。我总是想求出位数然后由高位向低位输出。其实反向输出比较好，不用求位数。
	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder("");
		while (n > 0) {
			sb.insert(0, (char) ((n - 1) % 26 + 'A'));
			n = (n - 1) / 26;
		}
		return sb.toString();
	}

	/**
	 * PROBLEM 169
	 */
	// TODO:这道题没有做出来。思路有问题。想用分治思想，但该题不能分成小块来做。找到这个比较好的答案，是用成对淘汰思想做的。
	public int majorityElement(int[] nums) {
		int candidate = 0;
		int count = 0; // 某个候选值的个数。当count 为0 的时候，换下一个候选值。
		for (int i = 0; i < nums.length; i++) {
			if (count == 0) {
				candidate = nums[i];
				count = 1;
			} else {
				if (nums[i] == candidate)
					count++;
				else
					count--;
			}
		}
		return candidate;
	}

	/**
	 * PROBLEM 191
	 */
	// TODO:充分理解位运算！此题是说一个无符号整数，如果直接按求余统计，会把最高位的1当做正负号从而出错。
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			count += n & 1;
			n >>>= 1;
		}
		return count;
		// 竟然已经有现成的方法了：直接return Integer.bitCount(n);
	}

	/**
	 * PROBLEM 235
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		int temp = (root.val - p.val) * (root.val - q.val);
		if (temp <= 0) {
			return root;
		} else {
			return (root.val - p.val) > 0 ? lowestCommonAncestor(root.left, p, q)
					: lowestCommonAncestor(root.right, p, q);
		}
	}

	/**
	 * PROBLEM 13
	 */
	// 罗马数字共有七个，即I(1)，V(5)，X(10)，L(50)，C(100)，D(500)，M(1000)。按照下面的规则可以表示任意正整数。
	// 重复数次：一个罗马数字重复几次，就表示这个数的几倍。
	// 右加左减：在一个较大的罗马数字的右边记上一个较小的罗马数字，表示大数字加小数字。在一个较大的数字的左边记上一个较小的罗马数字，表示大数字减小数字。但是，左减不能跨越等级。比如，99不可以用IC表示，用XCIX表示。
	// 加线乘千：在一个罗马数字的上方加上一条横线或者在右下方写M，表示将这个数字乘以1000，即是原数的1000倍。同理，如果上方有两条横线，即是原数的1000000倍。
	// 单位限制：同样单位只能出现3次，如40不能表示为XXXX，而要表示为XL。
	public int romanToInt(String s) {
		int i = s.length() - 1;
		int res = 0;
		char temp = 0;
		while (i >= 0) {
			if (i == s.length()) {
				res += charToInt(s.charAt(i));
			} else {
				int x = charToInt(s.charAt(i));
				if (x < charToInt(temp)) {
					res -= x;
				} else {
					res += x;
				}
			}
			temp = s.charAt(i);
			--i;
		}
		return res;
	}

	int charToInt(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	/**
	 * PROBLEM 206
	 */
	// 递归做法
	public ListNode reverseListRecursive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode res = reverseListRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}

	// 非递归做法
	public ListNode reverseList(ListNode head) {
		int i = 0;
		ListNode res = head;
		if (res != null) {
			ListNode temp = head.next;
			while (temp != null) {
				res = temp;
				temp = temp.next;
				res.next = head;
				if (i == 0) {
					++i;
					head.next = null;
				}
				head = res;
			}
		}
		return res;
	}

	/**
	 * PROBLEM 83
	 */
	// TODO:注意“删除”的做法。链表中的“删除”通常是next指针越过它。
	public ListNode deleteDuplicates(ListNode head) {
		if (head != null && head.next != null) {
			ListNode temp = head;
			ListNode p = head.next;
			while (p != null) {
				if (p.val == temp.val) {
					temp.next = p.next;
				} else {
					temp = p;
				}
				p = p.next;
			}
		}
		return head;
	}

	/**
	 * PROBLEM 70
	 */
	int climbStairs(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int[] array = new int[n + 1];
		array[1] = 1;
		array[2] = 2;
		for (int i = 3; i <= n; i++) {
			array[i] = array[i - 1] + array[i - 2];
		}
		return array[n];
	}

	/**
	 * PROBLEM 263
	 */
	public boolean isUgly(int num) {
		if (num < 1) {
			return false;
		}
		if (num == 1) {
			return true;
		}
		if (num % 2 == 0) {
			return isUgly(num / 2);
		} else if (num % 3 == 0) {
			return isUgly(num / 3);
		} else if (num % 5 == 0) {
			return isUgly(num / 5);
		} else {
			return false;
		}
	}

	/**
	 * PROBLEM 202
	 */
	// TODO: 本题学习Map的用法。当检查可能出现的循环或重复值时，可以用map作为存储结构，便于快速检索。
	public boolean isHappy(int n) {
		if (n <= 0) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(n, n);
		while (n != 1) {
			n = addDigitSquare(n);
			if (!map.containsValue(n)) {
				map.put(n, n);
			} else {
				return false;
			}
		}
		return true;
	}

	int addDigitSquare(int num) {
		int res = 0;
		while (num > 0) {
			res += (num % 10) * (num % 10);
			num /= 10;
		}
		return res;
	}

	/**
	 * PROBLEM 21
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode p1 = l1;
		ListNode p2 = l2;
		ListNode res = l1.val < l2.val ? l1 : l2;
		ListNode p = res;
		if (l1.val < l2.val) {
			p1 = p1.next;
		} else {
			p2 = p2.next;
		}
		while (p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				p.next = p1;
				p = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p = p2;
				p2 = p2.next;
			}
		}
		if (p1 != null) {
			p.next = p1;
		} else {
			p.next = p2;
		}
		return res;
	}

	/**
	 * PROBLEM 232
	 */
	class MyQueue {
		public Stack<Integer> stack1 = new Stack<Integer>();
		public Stack<Integer> stack2 = new Stack<Integer>();

		// Push element x to the back of queue.
		public void push(int x) {
			stack1.push(x);
		}

		// Removes the element from in front of queue.
		public void pop() {
			while (stack1.size() > 1) {
				stack2.push(stack1.pop());
			}
			stack1.pop();
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		}

		// Get the front element.
		public int peek() {
			while (stack1.size() > 1) {
				stack2.push(stack1.pop());
			}
			return stack1.peek();
		}

		// Return whether the queue is empty.
		public boolean empty() {
			return stack1.isEmpty();
		}
	}

	/**
	 * PROBLEM 137 Single Number II
	 */
	// TODO: 这题涉及位操作，太神奇，自己完全不会。在讨论区找到了这个非常牛B的答案。基本思路是，遍历数组，统计每个数位上 1 的个数。当
	// 计数到3个时，清0。也就是模3操作。
	public int singleNumber(int[] nums) {
		// we need to implement a tree-time counter(base 3) that if a bit
		// appears three time ,it will be zero.
		// #curent income ouput
		// # ab c/c ab/ab
		// # 00 1/0 01/00
		// # 01 1/0 10/01
		// # 10 1/0 00/10
		// a=~abc+a~b~c;
		// b=~a~bc+~ab~c;
		int a = 0;
		int b = 0;
		for (int c : nums) {
			int ta = (~a & b & c) | (a & ~b & ~c);
			b = (~a & ~b & c) | (~a & b & ~c);
			a = ta;
		}
		// we need find the number that is 01,10 => 1, 00 => 0.
		return a | b;
	}

	/**
	 * PROBLEM 231 Power of Two
	 */
	// 也是位操作的小技巧。n & (n - 1) 可以判断n是否只有一位为1.
	public boolean isPowerOfTwo(int n) {
		return n > 0 && ((n & (n - 1)) == 0);
	}

	/**
	 * PROBLEM 110. Balanced Binary Tree
	 */
	//深度优先搜索
	public boolean isBalanced(TreeNode root) {
		if (checkHeight(root) == -1) {
			return false;
		} else {
			return true;
		}
	}
	int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftHeight = checkHeight(root.left);
		if (leftHeight == -1) {
			return -1;
		}

		int rightHeight = checkHeight(root.right);
		if (rightHeight == -1) {
			return -1;
		}

		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return -1;
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
	/**
	 * PROBLEM 101. Symmetric Tree
	 */
	//迭代做法
	public boolean isSymmetric(TreeNode root) {
        if(root == null) {
        	return true;
        }
        if(root.left == null && root.right == null) {
        	return true;
        }
        Stack<TreeNode> stackLeft = new Stack<TreeNode>();
        Stack<TreeNode> stackRight = new Stack<TreeNode>();
        if(root.left != null) {
            stackLeft.push(root.left);
        }
        if(root.right != null) {
        	stackRight.push(root.right);
        }
        TreeNode left = null;
        TreeNode right = null;
        
        while(!stackLeft.isEmpty() && !stackRight.isEmpty()) {
        	left = stackLeft.pop();
        	right = stackRight.pop();
        	if(left.val != right.val) {
        		return false;
        	}
        	if(left.left != null) {
        		if(right.right == null) {
        			return false;
        		}
        		stackLeft.push(left.left);
        		stackRight.push(right.right);
        		
        	} else if(right.right != null) {
        		return false;
        	}
        	if(left.right != null) {
        		if(right.left == null) {
        			return false;
        		}
        		stackLeft.push(left.right);
        		stackRight.push(right.left);
        	} else if(right.left != null) {
        		return false;
        	}
        }
        return stackLeft.empty() && stackRight.empty();
    }
	//递归做法
	//TODO: 没做出来。多看看递归吧。
    public boolean isSymmetricRecursively(TreeNode root) {
        if(root == null) {
        	return true;
        }
        return ifSymmetric(root.left, root.right);
    }
    
    boolean ifSymmetric(TreeNode nodeA, TreeNode nodeB) {
    	if(nodeA == null && nodeB == null) {
    		return true;
    	}
    	if(nodeA == null || nodeB == null) {
    		return false;
    	}
    	if(nodeA.val != nodeB.val)
            return false;
        else
            return (ifSymmetric(nodeA.left, nodeB.right) && ifSymmetric(nodeA.right, nodeB.left));
    }
    /**
	 * PROBLEM 27. Remove Element
	 * 真神奇。第27道题居然刚好编号27。
	 */
    public int removeElement(int[] nums, int val) {
    	int i = 0;
    	int j = nums.length - 1;

    	while(i <= j) {
    		if(nums[j] == val) {
    			--j;
    		}else if(nums[i] == val) {
    			nums[i] = nums[j];
    			--j;
    			++i;
    		} else {
    			++i;
    		}
    	}
    	if(j < 0) {
    		return 0;
    	}
    	return j + 1;
    }
    /**
   	 * PROBLEM 107. Binary Tree Level Order Traversal II
   	 */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	 List<List<Integer>> container = new ArrayList<List<Integer>>();
         if (root == null) return container;
         TreeNode cur = null;
         Queue<TreeNode> sameLevel = new LinkedList<TreeNode>();
         sameLevel.add(root);
         while (!sameLevel.isEmpty()) {
             List<Integer> oneLevel = new ArrayList<Integer>();
             Queue<TreeNode> temp = new LinkedList<TreeNode>();
             while(!sameLevel.isEmpty()) {
                 cur = sameLevel.remove();
                 oneLevel.add(cur.val);
                 if (cur.left != null)  temp.add(cur.left); 
                 if (cur.right != null) temp.add(cur.right);
             }
             container.add(0,oneLevel);
             sameLevel = temp;
         }
         return container;
    }
    /**
   	 * PROBLEM 26. Remove Duplicates from Sorted Array
   	 */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
        	return 0;
        }
        int i = 0;
        int next = 1;
        while(next < nums.length) {
        	if(nums[next] > nums[i]) {
        		nums[++i] = nums[next];
        	}
        	++next;
        }
        return i + 1;
    }
    /**
   	 * PROBLEM 66. Plus One
   	 */
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;
	}
    
    /**
   	 * PROBLEM 118	Pascal's Triangle
   	 */
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	if(numRows <= 0) {
    		return res;
    	}
    	List<Integer> oneRow = new ArrayList<Integer>();
    	List<Integer> lastRow = new ArrayList<Integer>();
    	oneRow.add(1);
    	res.add(oneRow);
    	int i = 1;
    	while(i < numRows) {
    		oneRow = new ArrayList<Integer>(i+1);
    		lastRow = res.get(i - 1);
    		for(int j = 0; j <= i; j++) {
    			if(j == 0 || j == i) {
    				oneRow.add(1);
    			} else {
    				oneRow.add(lastRow.get(j) + lastRow.get(j-1));
    			}
    		}
    		res.add(oneRow);
    		++i;
    	}
    	return res;
    }
    /**
   	 * PROBLEM 172. Factorial Trailing Zeroes
   	 */
    //TODO:遇到数学题就有点怵。这道题其实难点是，所有的尾0都来自于 2*5.而阶乘中，5的个数总是小于2的个数，即
    //每出现一个5，由于是阶乘，所以肯定要至少出现两个2.因此，只要统计5的个数就可以了。
    public int trailingZeroes(int n) {
    	return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
    /**
   	 * PROBLEM 102. Binary Tree Level Order Traversal
   	 */
    //TODO:下面是自己做出的非常蠢的方法。空间复杂度太高了。后面接了一个利用队列的做法，非常好。
    public List<List<Integer>> levelOrderByMyself(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	List<List<TreeNode>> resNode = new ArrayList<List<TreeNode>>();
        if(root == null) {
        	return res;
        }
        List<TreeNode> oneRowNode = new ArrayList<TreeNode>();
        List<TreeNode> tempRowNode = new ArrayList<TreeNode>();
        List<Integer> oneRow = new ArrayList<Integer>();
        oneRowNode.add(root);
        oneRow.add(root.val);
        res.add(oneRow);
        
        tempRowNode = oneRowNode;
        while(!tempRowNode.isEmpty()) {
        	resNode.add(tempRowNode);
        	res.add(oneRow);
        	oneRowNode = new ArrayList<TreeNode>();
        	oneRow = new ArrayList<Integer>();
        	for(TreeNode node : tempRowNode) {
        		if(node.left != null) {
        			oneRowNode.add(node.left);
            		oneRow.add(node.left.val);
        		}
        		if(node.right != null) {
        			oneRowNode.add(node.right);
            		oneRow.add(node.right.val);
        		}
        	}
        	tempRowNode = oneRowNode;
        }
        
        return res;
    }
    //这个解，利用队列的先进先出，每轮循环访问一层，每次循环结束时队列中只有下一层的元素。妙。
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();//这里很巧妙地获得了队列长度，限定了每一层的个数；
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);//这里加入本层的值，而且将其退出队列；
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
    /**
   	 * PROBLEM 119. Pascal's Triangle II
   	 */
    //TODO:这个题又挂了。思路是，下一行比上一行多一个元素，而每个元素除了两端为1，其他的都是上一行元素相邻相加得出的。
    //因此，可以每次在列表尾部加入一个1，然后从后向前，每个元素都由自身和前一个元素相加得出。当然，最前端仍然为1.
    public List<Integer> getRow(int rowIndex) {
    	 List<Integer> list = new ArrayList<Integer>();
    	    if (rowIndex < 0)
    	        return list;

    	    for (int i = 0; i < rowIndex + 1; i++) {
    	        list.add(1);
    	        for (int j = list.size() - 2; j > 0; j--) {
    	        	//这个范围就是掐头去尾的范围。
    	            list.set(j, list.get(j) + list.get(j - 1));
    	        }
    	        list.set(0, 1);
    	    }
    	    return list;
    }
    /**
   	 * PROBLEM 112. Path Sum
   	 */
    //TODO:该题似乎只有用递归做法。想用非递归没做出来。还是要好好看一下递归！！！
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null) return sum == root.val;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    /**
   	 * PROBLEM 225. Implement Stack using Queues
   	 */
    class MyStack {
    	Queue<Integer> queue1 = new LinkedList<Integer>();//只保存头节点。
    	Queue<Integer> queue2 = new LinkedList<Integer>();
    	
        // Push element x onto stack.
        public void push(int x) {
        	if(!queue1.isEmpty()) {
        		queue2.add(queue1.poll());
        	}
            queue1.add(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
        	queue1.poll();
        	while(!queue2.isEmpty()) {
            	queue1.add(queue2.poll());
            }
            while(queue1.size() > 1) {
            	queue2.add(queue1.poll());
            }
        }

        // Get the top element.
        public int top() {
            return queue1.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
    /**
   	 * PROBLEM 9. Palindrome Number
   	 */
    //TODO:没做出来。注意“翻转一个数”的做法！即：每次取原数最低一位，加上本身全体升一位。
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
    }
    /**
   	 * PROBLEM 160. Intersection of Two Linked Lists
   	 */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
        	return null;
        }
        int sizeA = 1;
        int sizeB = 1;
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa.next != null) {
        	pa = pa.next;
        	++sizeA;
        }
        while(pb.next != null) {
        	pb = pb.next;
        	++sizeB;
        }
        //尾部对齐，长度相等。
        pa = headA;
        pb = headB;
        while(sizeA > sizeB) {
        	pa = pa.next;
        	--sizeA;
        }
        while(sizeA < sizeB) {
        	pb = pb.next;
        	--sizeB;
        }
        //若此时已重合
        if(pa == pb) {
        	return pa;
        }
        //找后续是否重合
        while(pa.next != null && pa.next != pb.next) {
        	pa = pa.next;
        	pb = pb.next;
        }
        return pa.next;
    }
    /**
   	 * PROBLEM 8. String to Integer (atoi)
   	 */
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(str.charAt(index) == ' ' && index < str.length())
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }
    /**
   	 * PROBLEM 7. Reverse Integer
   	 */
    public int reverse(int x) {
        int res = 0;
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        int temp = x;
        while(temp > 0) {
        	if(Integer.MAX_VALUE/10 < res || Integer.MAX_VALUE/10 == res && Integer.MAX_VALUE%10 < temp%10) {
        		return 0;
        	}
        	res = res * 10 + temp % 10;
        	temp = temp / 10;
        }
        return res * sign;
    }

	/**
	 * PROBLEM 88. Merge Sorted Array
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;
		while (i > -1 && j > -1) {
			nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		}
		while (j > -1) {
			nums1[k--] = nums2[j--];
		}
	}
	/**
	 * PROBLEM 190. Reverse Bits
	 */
	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result += n & 1;
			n >>>= 1; // CATCH: must do unsigned shift
			if (i < 31) // CATCH: for last digit, don't shift!
				result <<= 1;
		}
		return result;
	}
	/**
	 * PROBLEM 111. Minimum Depth of Binary Tree
	 */
	public int minDepth(TreeNode root) {
		if(root == null) {
    		return 0;
    	}
        int res = 1;
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        if(minLeft * minRight == 0) {
        	res += minLeft == 0 ? minRight : minLeft;
        } else {
        	res += minLeft > minRight ? minRight : minLeft;
        }
        return res;
    }
	/**
	 * PROBLEM 36. Valid Sudoku
	 */
    public static boolean isValidSudoku(char[][] board) {
        //row check;
        int i = 0;
        int j = 0;
        Map<Character, Integer> rowMap = new HashMap<Character, Integer>();
        for(; i < 9; i++) {
        	for(j = 0; j < 9; j++) {
        		if(board[i][j] != '.') {
            		if(rowMap.containsKey(board[i][j])) {
            			return false;
            		} else {
            			rowMap.put(board[i][j], 1);
            		}
            	}
        	}
        	rowMap.clear();
        }
        //column check;
        for(i = 0; i < 9; i++) {
        	for(j = 0; j < 9; j++) {
        		if(board[j][i] != '.') {
            		if(rowMap.containsKey(board[j][i])) {
            			return false;
            		} else {
            			rowMap.put(board[j][i], 1);
            		}
            	}
        	}
        	rowMap.clear();
        }
        //unit check;
        rowMap = new HashMap<Character, Integer>();
        for(int iu = 0; iu < 7; iu += 3) {
        	for(int ju = 0; ju < 7; ju += 3) {
        		for(i = iu; i < iu + 3; i++) {
        			for(j = ju; j < ju + 3; j++) {
        				if(board[i][j] != '.') {
                    		if(rowMap.containsKey(board[i][j])) {
                    			return false;
                    		} else {
                    			rowMap.put(board[i][j], 1);
                    		}
                    	}
        			}
        		}
        		rowMap.clear();
        	}
        }
        
        return true;
    }
    /**
	 * PROBLEM 223. Rectangle Area
	 */
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int redunt = 0;
		if (E >= C || A >= G || F >= D || B >= H || ((A - C) * (B - D) * (E - G) * (F - H) == 0)) {
			redunt = 0;
		} else {
			int top = D < H ? D : H;
			int bottom = B > F ? B : F;
			int left = A > E ? A : E;
			int right = C < G ? C : G;
			redunt = (top - bottom) * (right - left);
		}

		return (C - A) * (D - B) + (G - E) * (H - F) - redunt;
    }
	/**
	 * PROBLEM 19. Remove Nth Node From End of List
	 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        ListNode lat = pre;
        int i = 0;
        while(i < n && lat.next != null) {
        	lat = lat.next;
        	++i;
        }
        if(i < n) {
        	//head will be deleted.
        	if(i == 0) {
        		return null;
        	} else {
        		ListNode temp = pre.next;
        		pre.next = null;
        		return temp;
        	}
        }
        while(pre.next.next != null && lat.next != null) {
        	pre = pre.next;
        	lat = lat.next;
        }
        
        ListNode temp = pre.next;
        pre.next = temp.next;
        temp.next = null;
        
        return head;
    }
    /**
	 * PROBLEM 20. Valid Parentheses
	 */
    public boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
        	return true;
        }
        Stack<Character> stack = new Stack<Character>();
        int i = 0;
        char c = s.charAt(0);
        while(i < s.length()) {
        	c = s.charAt(i);
        	switch(c) {
        		case '(':
        			stack.push(c);
        			break;
        		case '[':
        			stack.push(c);
        			break;
        		case '{':
        			stack.push(c);
        			break;
        		case ')':
        			if(stack.isEmpty() || stack.pop() != '(') {
        				return false;
        			}
        			break;
        		case ']':
        			if(stack.isEmpty() || stack.pop() != '[') {
        				return false;
        			}
        			break;
        		case '}':
        			if(stack.isEmpty() || stack.pop() != '{') {
        				return false;
        			}
        			break;
        	}
        	i++;
        }
        return stack.isEmpty() ? true : false;
    }
}
