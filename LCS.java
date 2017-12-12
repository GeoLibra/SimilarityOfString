import java.util.TreeSet;

/**
 * Created by HGIS on 2017/12/4.
 */
public class LCS {
    private String s1;
    private String s2;
    private int[][] matrix;
    private TreeSet<String> set = new TreeSet<String>();


    public LCS(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    //最长公共子序列
    public int lcSubsequence() {
        //初始化二维数组

        matrix = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < s2.length(); j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
//        for (int i=0;i<=s1.length();i++){
//            for (int j=0;j<=s2.length();j++){
//                System.out.print(matrix[i][j]+" ");
//            }
//            System.out.println();
//        }
        return matrix[s1.length()][s2.length()];
    }
    //最长公共子串
    public int lcSubstring() {
        //最长的公共子串长度
        int max_num=0;
        matrix = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < s2.length(); j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    if (matrix[i][j]>max_num){
                        max_num=matrix[i][j];
                    }
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return max_num ;
    }

    private void lcssubsequence(int i, int j, String lcs_string) {
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs_string += s1.charAt(i - 1);
                i -= 1;
                j -= 1;
            } else {
                if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    i -= 1;
                } else if (matrix[i - 1][j] < matrix[i][j - 1]) {
                    j -= 1;
                } else {
                    lcssubsequence(i - 1, j, lcs_string);
                    lcssubsequence(i, j - 1, lcs_string);
                    return;
                }
            }
        }
        set.add(reverse(lcs_string));
    }

    private String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    public void printLCSubsequence() {
        String lcs_string = "";
        lcssubsequence(s1.length(), s2.length(), lcs_string);
        for (String s : set) {
            System.out.println(s);
        }
    }

}
