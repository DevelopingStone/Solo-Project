package stone.weatherdiary.error;

public class InvalidDate extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 년도 입니다.";

    public InvalidDate() {
        super(MESSAGE);
    }
}
