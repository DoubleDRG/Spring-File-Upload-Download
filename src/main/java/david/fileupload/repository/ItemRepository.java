package david.fileupload.repository;

import david.fileupload.domain.Item;

public interface ItemRepository
{
    public Item save(Item item);

    public Item findById(Long id);
}
