package stone.weatherdiary.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stone.weatherdiary.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary findByDate(LocalDate date);
}
