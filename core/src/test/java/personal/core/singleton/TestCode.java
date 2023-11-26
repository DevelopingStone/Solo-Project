package personal.core.singleton;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCode {

    private static TestCode instance;

    private TestCode() {
    }

    public static TestCode createInstance() {
        if (instance == null) {
            System.out.println("인스턴스 생성");
            instance = new TestCode();
        }
        return instance;
    }

    static class Start {
        @Test
        void instanceTest() {
            System.out.println("1");
            TestCode instance = TestCode.createInstance();
            System.out.println("2");
            TestCode instance1 = TestCode.createInstance();
            System.out.println("3");
            TestCode instance2 = TestCode.createInstance();
            System.out.println("4");

            Assertions.assertThat(instance).isEqualTo(instance1).isEqualTo(instance2);
        }

    }

}


