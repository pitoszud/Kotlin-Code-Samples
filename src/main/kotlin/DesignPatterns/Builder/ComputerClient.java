package DesignPatterns.Builder;

public class ComputerClient {

    public static void main(String[] args) {
        Computer comp0 = new Computer.CompBuilder("i7", 32, "ssd")
                .screenSize(27)
                .gpu("nvida640")
                .build();
        System.out.println(comp0.toString());

        Computer comp1 = Computer.getMediumComputer();
        System.out.println(comp1.toString());

        Computer comp2 = Computer.setMediumComputer("i5", 32, "ssd");
        System.out.println(comp2.toString());

    }
}

