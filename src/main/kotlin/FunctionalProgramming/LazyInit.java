package FunctionalProgramming;

public class LazyInit{
    private LazyInit() {}

    private static class LazyHolder{
        public static final LazyInit INSTANCE = new LazyInit();
    }

    public static LazyInit getInstance(){
        return LazyHolder.INSTANCE;
    }

}
