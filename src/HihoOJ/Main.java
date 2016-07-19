package HihoOJ;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Main {
//
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		String line = new String();
//		while(in.hasNextLine()) {
//			line = in.nextLine();
//			if(line.length() < 1) {
//				continue;
//			}
//			String lines = work(line);
//			String[] array = lines.split("\\.");
//			if(array.length == 1) {
//				System.out.println(lines);
//			} else {
//				StringBuilder sb = new StringBuilder();
//				int i = 0;
//				for(i = 0; i < array.length-1; ++i){
//					sb.append(work(array[i])).append(". ");
//				}
//				sb.append(work(array[i])).append(".");
//				System.out.println(sb.toString());
//			}
//		}
//	}
//	
//	public static String work(String line) {
//		return repair(removeSpace(line));
//	}
//	//修改字母
//	public static String repair(String line) {
//		StringBuilder sb = new StringBuilder();
//		if(line == null || line.length() <= 1) {
//			return line;
//		}
//		String[] words = line.split(" ");
//		sb.append(Character.toUpperCase(line.charAt(0)));
//		for(int i = 1; i < line.length(); ++i) {
//			char temp = line.charAt(i);
//			sb.append(Character.toLowerCase(line.charAt(i)));
//		}
//		return sb.toString();
//	}
//	
//	public static String removeSpace(String line) {
//		line = line.trim();
//		StringBuilder sb = new StringBuilder();
//		char temp;
//		int i = 0;
//		while(i < line.length()) {
//			temp = line.charAt(i);
//			if(temp == ' ') {
//				sb.append(temp);
//				int j = i + 1;
//				while(j < line.length() && line.charAt(j) == ' ') {
//					j++;
//				}
//				if(j < line.length() && !Character.isAlphabetic(line.charAt(j))) {
//					sb.deleteCharAt(sb.length() - 1);
//				}
//				if(i>0 && line.charAt(i - 1) == '.') {
//					sb.deleteCharAt(sb.length() - 1);
//				}
//				i = j;
//			} else {
//				sb.append(temp);
//				++i;
//			}
//		}
//		return sb.toString();
//	}
//	
//}

