package personal.core.singleton;

public class SingletonTest {

    private static final SingletonTest instance = new SingletonTest();

    private SingletonTest() {
    }

    public static SingletonTest getInstance() {

        return instance;
    }


}
