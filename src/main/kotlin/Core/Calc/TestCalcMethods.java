package Core.Calc;

import java.util.ArrayList;
import java.util.List;

public class TestCalcMethods {


    public static List<Integer> creditList = new ArrayList();
    public static List<Integer> levelList = new ArrayList();
    public static List<Integer> passList = new ArrayList();

    public static void main(String[] args) {
        Calc c = new Calc();
        buildLists();
        int wpi = c.getWorstPassIndex(passList, creditList, 60);
        System.out.println("The worst pass index: " + wpi);
    }



    public static void buildLists(){
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
    }




}
