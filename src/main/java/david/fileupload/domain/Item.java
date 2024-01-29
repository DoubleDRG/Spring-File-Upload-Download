package david.fileupload.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "info_id")
    private InfoFile info;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private List<ImageFile> images;

    protected Item()
    {
    }

    public Item(String name, InfoFile info, List<ImageFile> images)
    {
        this.name = name;
        this.info = info;
        this.images = images;
    }
}
