package david.fileupload.controller.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm
{
    private String itemName;
    private MultipartFile itemInfo;
    private List<MultipartFile> itemImages;
}
