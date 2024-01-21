package stone.weatherdiary;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import stone.weatherdiary.domain.Memo;
import stone.weatherdiary.repository.JpaMemoRepository;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;


    @Test
    void insertMemoTest() {
        //given
        Memo newMemo = new Memo(10L, "this is jpa memo");

        //when
        jpaMemoRepository.save(newMemo);
        List<Memo> memoList = jpaMemoRepository.findAll();

        //then
        assertTrue(memoList.size() > 0);
    }
}
