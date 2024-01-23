package stone.weatherdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stone.weatherdiary.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
