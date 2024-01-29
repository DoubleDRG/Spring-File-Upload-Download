package david.fileupload.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ImageFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userImageName;
    private String serverImageName;

    public ImageFile()
    {
    }

    public ImageFile(String userImageName, String serverImageName)
    {
        this.userImageName = userImageName;
        this.serverImageName = serverImageName;
    }
}
