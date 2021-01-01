package practice.DP;

class Step{

    int x,y;

    public Step(int x,int y){
        this.x = x;
        this.y = y;
    }

}
public class KnightProbability {

    public double findInsideProb(int n , int x, int y, int k){


        double [][][] prob = new double[n+1][n+1][k+1];

        for (int i =0;i<n;i++){
            for (int j =0;j<n;j++){
                prob[i][j][0] = 1;
            }
        }

        for (int step = 1;step<=k;step++){


            for(int i =0;i<n;i++){
                for (int j =0;j<n;j++){
                    Step[] steps = getSteps();
                    for (Step s : steps){
                        if (isInRange(n, i+s.x,j+s.y)){
                            prob[i][j][step]+= prob[i+s.x][j+s.y][step-1]/8;
                        }
                    }
                }
            }
        }

        return prob[x][y][k];

    }


    private boolean isInRange(int n, int x, int y){
        if (x<0 || x>=n){
            return false;
        }
        if (y<0||y>=n){
            return false;
        }
        return true;

    }
    private Step[] getSteps(){
        Step[] steps = new Step[8];
        steps[0] = new Step(1,2);
        steps[1] = new Step(1,-2);
        steps[2] = new Step(-1,2);
        steps[3] = new Step(-1,-2);

        steps[4] = new Step(2,1);
        steps[5] = new Step(2,-1);
        steps[6] = new Step(-2,1);
        steps[7] = new Step(-2,-1);
        return steps;
    }

    public static void main(String[] args) {


        KnightProbability knightProbability = new KnightProbability();
        System.out.println(knightProbability.findInsideProb(8,0,0,3));


    }


}
