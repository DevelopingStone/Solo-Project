package stone.weatherdiary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherdiaryApplicationTests {


    @Test
    public void equalsTest() {
        assertEquals(1, 1);
    }

    @Test
    void nullTest() {
        assertNull(null);
    }

    @Test
    void trueTest() {
        Assertions.assertTrue(1 == 1);
    }
}
