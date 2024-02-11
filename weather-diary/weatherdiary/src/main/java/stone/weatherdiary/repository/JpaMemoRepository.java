package stone.weatherdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stone.weatherdiary.domain.Memo;

@Repository
public interface JpaMemoRepository extends JpaRepository<Memo, Long> {

}
