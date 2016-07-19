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
}
