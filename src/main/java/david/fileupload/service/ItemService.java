package david.fileupload.service;

import david.fileupload.controller.form.ItemForm;
import david.fileupload.domain.ImageFile;
import david.fileupload.domain.InfoFile;
import david.fileupload.domain.Item;
import david.fileupload.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService
{
    private final FileService fileService;
    private final ItemRepository itemRepository;

    @Transactional
    public Item save(ItemForm form) throws IOException
    {
        String itemName = form.getItemName();
        MultipartFile itemInfo = form.getItemInfo();
        List<MultipartFile> itemImages = form.getItemImages();

        //서버의 파일시스템에 itemInfo 파일 저장.
        InfoFile uploadItemInfo = fileService.saveInfo(itemInfo);

        //서버의 파일시스템에 itemImages 파일 저장.
        List<ImageFile> uploadItemImages = fileService.saveImages(itemImages);

        //DB에 Item데이터 저장.
        Item item = new Item(itemName, uploadItemInfo, uploadItemImages);
        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Item findById(Long itemId)
    {
        return itemRepository.findById(itemId);
    }
}
