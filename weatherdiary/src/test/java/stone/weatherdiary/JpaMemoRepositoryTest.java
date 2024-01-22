package stone.weatherdiary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
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
        Memo memo = new Memo(1L, "안녕하세요.");
        //when
        jpaMemoRepository.save(memo);
        //then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest() {
        //given
        Memo newMemo = new Memo(null, "안녕하세요, 2L입니다.");
        //when
        Memo memo = jpaMemoRepository.save(newMemo);
        //then
        Optional<Memo> byId = jpaMemoRepository.findById(memo.getId());
        assertEquals(byId.get().getText(), "안녕하세요, 2L입니다.");
    }


}
