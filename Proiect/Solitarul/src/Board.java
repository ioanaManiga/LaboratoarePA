import java.util.Arrays;

public class Board {
  private int[][] boardMatrix;
  private int dimension;

    public Board(int n) {
        dimension=n+2;
        boardMatrix= new int[n+2][n+2];
        for (int i=0;i<n+2;i++){
            boardMatrix[0][i]=-1;
            boardMatrix[i][0]=-1;
            boardMatrix[n+1][i]=-1;
            boardMatrix[i][n+1]=-1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n;j++){
                if(!(i == n/2+1 && j == n/2+1)){
                boardMatrix[i][j]=1;
                }
                else boardMatrix[i][j]=0;
            }
        }
    }

    public void setValueByPosition(int i, int j){
        if(boardMatrix[i][j]==0){
            boardMatrix[i][j]=1;
        }
        else {
            boardMatrix[i][j]=0;
        }
    }

    public boolean isZero(int i, int j){
        if(boardMatrix[i][j]==0){
            return true;
        }
        return false;
    }


    public void print() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(boardMatrix[i][j]);
            }
            System.out.println();
        }
    }
    public boolean gameOver()
    {
        for(int i=1; i<dimension; i++)
            for(int j=1; j<dimension; j++)
            {
                if(boardMatrix[i][j]==1)
                {
                    if(i>2)
                        if(boardMatrix[i-1][j]==1&&boardMatrix[i-2][j]==0)
                            return false;
                    if(i<6)
                        if(boardMatrix[i+1][j]==1&&boardMatrix[i+2][j]==0)
                            return false;
                    if(j>2)
                        if(boardMatrix[i][j-1]==1&&boardMatrix[i][j-2]==0)
                            return false;
                    if(j<6)
                        if(boardMatrix[i][j+1]==1&&boardMatrix[i][j+2]==0)
                            return false;
                }
            }
        return true;
    }


}
