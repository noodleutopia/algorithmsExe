
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
    
    
    
}
