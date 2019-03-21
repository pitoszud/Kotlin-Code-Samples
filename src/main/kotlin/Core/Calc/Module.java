package Core.Calc;


public class Module {
    private String moduleRef;
    private String level;
    private String points;
    private String grade;


    public Module(){

    }


    public Module(String moduleRef, String level, String points, String grade) {
        this.moduleRef = moduleRef;
        this.level = level;
        this.points = points;
        this.grade = grade;
    }

    public void setModuleRef(String moduleRef) {
        this.moduleRef = moduleRef;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getModuleRef() {
        return moduleRef;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleRef='" + moduleRef + '\'' +
                ", level='" + level + '\'' +
                ", points='" + points + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public static void main(String[] args) {

    }
}
