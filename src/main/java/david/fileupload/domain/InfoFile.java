package david.fileupload.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InfoFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userInfoName;
    private String serverInfoName;

    public InfoFile()
    {
    }

    public InfoFile(String userInfoName, String serverInfoName)
    {
        this.userInfoName = userInfoName;
        this.serverInfoName = serverInfoName;
    }
}
