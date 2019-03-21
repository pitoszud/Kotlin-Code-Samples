package Core.Enums;


public enum EnumP {
    RED(255,0,0),
    BLUE(0,0,255),
    YELLOW(255,255,0);

    private int r;
    private int g;
    private int b;

    private EnumP(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rgb(){
        return (this.r *256 + this.g) * 256 + this.b;
    }

    public static void main(String[] args) {
        System.out.println(EnumP.BLUE.rgb());
    }
}
