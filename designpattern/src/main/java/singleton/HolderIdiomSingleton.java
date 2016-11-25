package singleton;

public class HolderIdiomSingleton {
    private HolderIdiomSingleton() {}

    private static class SingleHolder {
        private static final HolderIdiomSingleton INSTANCE = new HolderIdiomSingleton();
    }

    public static HolderIdiomSingleton getInstance() {
        return SingleHolder.INSTANCE;
    }

}
