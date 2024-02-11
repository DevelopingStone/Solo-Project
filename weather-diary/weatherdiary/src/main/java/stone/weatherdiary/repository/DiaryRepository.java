package stone.weatherdiary.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stone.weatherdiary.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {


    List<Diary> findAllByDate(LocalDate date);

    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    Diary findByDate(LocalDate updateDate);

    Diary getFirstByDate(LocalDate date);

    Optional<Diary> findById(Long id);

    void deleteById(Long id);

    Optional<Diary> deleteAllByDate(LocalDate date);
}
