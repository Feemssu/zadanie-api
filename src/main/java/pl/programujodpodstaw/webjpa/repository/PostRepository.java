package pl.programujodpodstaw.webjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programujodpodstaw.webjpa.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
}
