import java.util.HashSet;
import java.util.Set;

import linked_list.ListNode;

public class MediumProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a = new ListNode(4);
		ListNode b = new ListNode(19);
		ListNode c = new ListNode(14);
		ListNode d = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		ListNode p = sortList(a);
		while(p!=null) {
			System.out.print(p.val + " ");
			p = p.next;
		}
	}
	
	/**
	 * 338. Counting Bits
	 */
	//第一个 medium 吓尿了。DP 问题，要找到大问题与子问题的关系(?)
    public int[] countBits(int num) {
    	int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }
    /**
	 * 136. Single Number
	 */
    public int singleNumber(int[] nums) {
    	int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    /**********下面做 Lined-List 相关题目*************/
    /**
	 * 328. Odd Even Linked List
	 */
    public ListNode oddEvenList(ListNode head) {
    	if(head == null || head.next == null || head.next.next == null) {
    		return head;
    	}
        int n = 0; // list length
        ListNode p = head;
        ListNode pre = head;
        ListNode cut = head;
        while(p != null) {
        	++n;
        	p = p.next;
        }
        p = head;
        if(n % 2 == 0) {
        	--n;
        }
        for(int i = 0; i < n-1; i++) {
        	//找到插入点
        	cut = cut.next;
        }
        int j = 1;
        while(j < n) {
        	if(j%2 != 0) {
        		p = p.next;
        	} else {
        		pre.next = p.next;
        		p.next = cut.next;
        		cut.next = p;
        		p = pre.next;
        		pre = p;
        		cut = cut.next;
        	}
        	++j;
        }
        return head;
    }
    /**
     * 260. Single Number III
     */
    public int[] singleNumberIII(int[] nums) {
    	int[] res = new int[2];
    	Set<Integer> set = new HashSet<Integer>();
    	for(int i=0; i<nums.length; ++i) {
    		if(set.contains(nums[i])) {
    			set.remove(nums[i]);
    		} else {
    			set.add(nums[i]);
    		}
    	}
    	int t = 0;
    	for(int num : set) {
    		res[t++] = num;
    	}
    	return res;
    }
    /**
     * 更好的答案
     */
    public int[] singleNumberIII2(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int temp = 0;
        for(int i=0; i<nums.length; ++i) {
        	temp ^= nums[i];
        }
        // Get its last set bit 注意，这是找到最右边的一个1
        temp &= -temp;
        //用temp将数组分为两组，分别在两组中异或得到两个数
        int first = 0, second = 0;
        for(int i=0; i<nums.length; ++i) {
        	if((temp & nums[i]) > 0) {
        		second ^= nums[i];
        	} else {
        		first ^= nums[i];
        	}
        }
        return new int[]{first, second};
    }
    
    /**
     * 376. Wiggle Subsequence
     */
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length < 1) {
        	return 0;
        }
        if(nums.length == 1) {
        	return 1;
        }
        int ans = 1;
        int diff = nums[1] - nums[0];
        if(diff != 0) {
        	++ans;
        }
        for(int i=2; i<nums.length; ++i) {
        	if(nums[i] != nums[i-1]) {
        		if(diff *(nums[i] - nums[i-1]) <= 0) {
            		++ans;
            	}
        		diff = nums[i] - nums[i-1];
        	}
        }
        return ans;
    }
    
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode findRight = head;
        while(findRight.next != null) {
            findRight = findRight.next;
        }
        return quickSort(head, findRight);
    }
    
    public static ListNode quickSort(ListNode left, ListNode right) {
        if(left == right) {
            return left;
        }
        ListNode insert = left;
        ListNode pre = left;
        ListNode p = left.next;
        while(p != null && p != right.next) {
            if(p.val < left.val) {
                if(pre != insert) {
                    pre.next = p.next;
                    p.next = insert.next;
                    insert.next = p;
                    insert = p;
                    p = pre.next;
                } else {
                    insert = insert.next;
                    pre = p;
                    p = p.next;
                }
            } else {
                pre = p;
                p = p.next;
            }
        }
        pre = left;
        if(insert != left) {
            left = left.next;
            pre.next = insert.next;
            insert.next = pre;
            right = pre;
            while(right.next != null) {
            	right = right.next;
            }
        }
        
        if(left != null) {
            left = quickSort(left, insert);
        }
        if(pre.next != null) {
            pre.next = quickSort(pre.next, right); 
        }

        return left;
    }
}

