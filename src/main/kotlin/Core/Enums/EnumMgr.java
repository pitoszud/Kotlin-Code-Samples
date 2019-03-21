package Core.Enums;

public class EnumMgr {
    private String code;
    private int id;
    private BLUE shade;

    public enum BLUE{lightBlue, darkBlue, navy, turcouse}

    public BLUE getBlue(){
        return shade;
    }

    public void setColor(String shade){
        if (shade.equals("lb")) {
            this.shade = BLUE.lightBlue;
        }
    }

    public static void main(String[] args) {
        EnumP ej = EnumP.BLUE;
        System.out.println(ej);
        System.out.println(EnumP.RED);

        BLUE b1 = BLUE.lightBlue;

        EnumMgr emgr = new EnumMgr();

    }
}
