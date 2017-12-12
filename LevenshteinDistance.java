/**
 * Created by HGIS on 2017/11/30.
 * 编辑距离算法  比较两个字符串的相似度
 * 两个字符串之间 由一个转换成另一个所需要的最少操作次数 距离越大越是不同
 */
public class LevenshteinDistance {
    //取最小的一个数字
    private int getMin(int first,int second,int third){
        int minnum=Math.min(first,second);
        return Math.min(minnum,third);
    }
    private int levenshtein(String s1,String s2){
        int n=s1.length();
        int m=s2.length();
        //1 s1或s2长度为0 返回另一个字符串的长度
        if(n==0){
            return m;
        }
        if(m==0){
            return n;
        }
        //2 初始化(n+1)*(m+1)的矩阵,并让其第一列从0开始增长
        int [][] matrix=new int[n+1][m+1];
        for (int i=0;i<=n;i++){
            matrix[i][0]=i;
        }
        for (int j=0;j<=m;j++){
            matrix[0][j]=j;
        }
        int tmp=0;
        //3 开始遍历字符串
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                //判断两个字符串是否一样
                if (s1.charAt(i-1)==s2.charAt(j-1)){
                  tmp=0;
                }else {
                    tmp=1;
                }
                matrix[i][j]=getMin(matrix[i-1][j-1]+tmp,matrix[i-1][j]+1,matrix[i][j-1]+1);
            }
        }
        return matrix[n][m];
    }
    //计算字符串相似度
    public double levenshteindistancepercent(String s1,String s2){
        int val=levenshtein(s1,s2);
        return 1-val*1.0/Math.max(s1.length(),s2.length());
    }
}
