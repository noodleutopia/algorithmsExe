import java.util.HashSet;
import java.util.Set;

import linked_list.ListNode;

public class MediumProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
}

