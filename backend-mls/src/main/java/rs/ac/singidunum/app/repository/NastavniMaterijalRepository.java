package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.NastavniMaterijal;

@Repository
public interface NastavniMaterijalRepository extends PagingAndSortingRepository<NastavniMaterijal, Long> {
}
