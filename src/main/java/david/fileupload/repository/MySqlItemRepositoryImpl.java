package david.fileupload.repository;

import david.fileupload.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MySqlItemRepositoryImpl implements ItemRepository
{
    private final EntityManager entityManager;

    @Override
    public Item save(Item item)
    {
        entityManager.persist(item);
        return item;
    }

    @Override
    public Item findById(Long id)
    {
        return entityManager.find(Item.class, id);
    }
}
