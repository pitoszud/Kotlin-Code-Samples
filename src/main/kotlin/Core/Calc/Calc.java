package Core.Calc;


import java.util.ArrayList;
import java.util.List;

public class Calc {

    private static int moduleSize = 0;

    private static final int MIN_CREDITS_L2 = 120;
    private static final int MIN_CREDITS_L3 = 120;
    private static final int MIN_CREDITS = MIN_CREDITS_L2 + MIN_CREDITS_L3;

    private static List<Integer> creditList = new ArrayList();
    private static List<Integer> levelList = new ArrayList();
    private static List<Integer> passList = new ArrayList();

    private static int totalWeight = 0;
    private static int totalCredits = 0;
    private static int totalCreditsL2 = 0;
    private static int totalCreditsL3 = 0;

    public Calc(){
    }


    // if the total points are greater than 360
        // if L3 > 180:
            // drop the lowest grade module
    // else drop the lowest grade in L2

    public void calculateGrade(){
        buildLists();
        loadAllCredits();
        getFinalGrade(getWeightedScore());
    }




    private int getWeightedScore(){
        if (totalCredits > 240){
            removeWorstGrades();
        }

        int weightGr = 0;

        for (int i = 0; i < creditList.size(); i++) {
            int level = levelList.get(i);
            int pass = passList.get(i);
            int credit = creditList.get(i);

            if (level == 3){
                weightGr += (2 * pass) * credit;
            } else{
                weightGr += pass * credit;
            }
        }
        if (weightGr > 1440){
            System.out.println("Weighted score: unknown");
        }else{
            System.out.println("Weighted score: " + weightGr);
        }

        return weightGr;
    }

    public void removeWorstGrades(){
        if (totalCreditsL3 > 120){
            // current extra credits in L3 that can be removed with wost pass(es)
            int remNum = totalCreditsL3 - 120;

            while (remNum >= 30){
                List<Integer> c3List = new ArrayList();
                List<Integer> l3List = new ArrayList();
                List<Integer> p3List = new ArrayList();
                // load all L3 modules to the temporary lists
                for (Integer i : levelList) {
                    if (i == 3){
                        c3List.add(i);
                        l3List.add(creditList.get(i));
                        p3List.add(passList.get(i));
                    }
                }
                // check an index with the worst pass, but with the grade no greater than the extra credits to remove
                int worstPassIndex = getWorstPassIndex(p3List, c3List, remNum);

                // credits of the worst module that has been indicated
                int creditsWorstMod = creditList.get(worstPassIndex);

                // update remaining credits
                // if there are 60 remaining and the worst module credits had 30 credits, there are still 30 to remove
                remNum = remNum - creditsWorstMod;

                // remove the worst module from the lists
                creditList.remove(worstPassIndex);
                passList.remove(worstPassIndex);
                levelList.remove(worstPassIndex);
            }
            // remove credits in L2 now

        }
        //System.out.println("Modules removed from the final grade: ...");
    }


    public int getWorstPassIndex(List<Integer> p3List, List<Integer> c3List, int remNum){
        int wpi = 0;
        int worstPass = 1;
        for (Integer i : p3List) {
            if (p3List.get(i) > worstPass){
                // if there are 30 credits to remove, but the worst module's credit is 60, it will be ignored
                if(remNum >= c3List.get(i)){
                    worstPass = i;
                    wpi++;
                }
            }
        }
        return wpi;
    }


    /* 1. separate L3 and L2 by creating two lists */
    private void loadAllCredits(){
        for (int i = 0; i < creditList.size(); i++) {
            if (levelList.get(i) == 3){
                totalCreditsL3 += creditList.get(i);
            } else{
                totalCreditsL2 += creditList.get(i);
            }
        }
        totalCredits = totalCreditsL2 + totalCreditsL3;
        if (totalCredits < 240){
            int rem = 240 - totalCredits;
            System.out.println("Total credits: " + totalCredits + ", you need extra" + rem);
        }else{
            System.out.println("Total credits: " + totalCredits);
        }
        if (totalCreditsL3 < 120){
            int rem = 120 - totalCreditsL3;
            System.out.println("Total credits in L3: " + totalCreditsL3 + ", you need extra" + rem);
        }else{
            System.out.println("Total credits in L3: " + totalCreditsL3);
        }
        if (totalCreditsL2 < 120){
            int rem = 120 - totalCreditsL2;
            System.out.println("Total credits in L2: " + totalCreditsL2 + ", you need extra" + rem);
        }else{
            System.out.println("Total credits in L2: " + totalCreditsL3);
        }
        System.out.println("Total credits in L2: " + totalCreditsL2);

    }


    private String getFinalGrade(int wgr){
        String str = "";
        if (wgr > 0 && wgr <= 630){
            str = "First";
        } else if (wgr >= 631 && wgr <= 900){
            str = "UpperSecond";
        }else if (wgr >= 901 && wgr <= 1170){
            str = "LowerSecond";
        }else if (wgr >= 1171 && wgr <= 1440){
            str = "Third";
        } else{
            str = "unknown";
        }
        System.out.println("Final grade is: " + str);
        return str;
    }




    public void buildLists(){
        creditList.add(60);
        levelList.add(2);
        passList.add(2);

        creditList.add(30);
        levelList.add(2);
        passList.add(4);

        creditList.add(30);
        levelList.add(3);
        passList.add(3);

        creditList.add(30);
        levelList.add(2);
        passList.add(3);

        creditList.add(30);
        levelList.add(3);
        passList.add(3);

        creditList.add(30);
        levelList.add(3);
        passList.add(2);

        //creditList.add(30);
        //levelList.add(3);
        //passList.add(1);

        System.out.println(creditList);
        System.out.println(levelList);
        System.out.println(passList);
    }




    public static void main(String[] args) {
        Calc c = new Calc();
        c.buildLists();
        c.loadAllCredits();
        int fg = c.getWeightedScore();
        c.getFinalGrade(fg);



    }



}
