public class gameOfLife {
    public static void main(String[] args) {


        boolean[][] mat = new boolean[100][100];

        mat[20][30]=true;
        mat[19][30]=true;
        mat[21][30]=true;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(mat[i][j]+ ",");
            }
            System.out.println();

        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                DeedNigh(mat, i, j);
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    public static void DeedNigh(boolean[][] mat, int x, int y) {
        if (x > 100 || x < 0) return;
        if (y > 100 || y < 0) return;
        int countD = 0;
        int countL = 0;
        if (mat[x][y - 1] == false & y > -1) countD++;
        else countL++;

        if (mat[x - 1][y - 1] == false & x > -1 & y > -1) countD++;
        else countL++;

        if (mat[x + 1][y - 1] == false & x < 100 & y > -1) countD++;
        else countL++;

        if (mat[x - 1][y] == false & x > -1) countD++;
        else countL++;

        if (mat[x + 1][y - 1] == false & x < 100) countD++;
        else countL++;

        if (mat[x][y + 1] == false & y < 100) countD++;
        else countL++;

        if (mat[x - 1][y + 1] == false & x < -1 & y < 100) countD++;
        else countL++;

        if (mat[x + 1][y + 1] == false & x < 100 & y < 100) countD++;
        else countL++;

        killCell(mat, x, y, countL, countD);

        return;
    }

    public static boolean killCell(boolean[][] mat, int x, int y, int live, int deed) {
        if (mat[x][y] == true) {
            if (deed <= 1 || live >= 4) return mat[x][y] == false;
        }
        if (live == 3) return mat[x][y] == true;
        return false;

    }


}
