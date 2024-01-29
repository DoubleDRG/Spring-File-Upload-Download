package david.fileupload.controller;

import david.fileupload.domain.Item;
import david.fileupload.service.FileService;
import david.fileupload.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Controller
public class InfoController
{
    private final ItemService itemService;
    private final FileService fileService;

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadInfo(@PathVariable Long itemId) throws MalformedURLException
    {
        Item item = itemService.findById(itemId);
        String userInfoName = item.getInfo().getUserInfoName();
        String serverInfoName = item.getInfo().getServerInfoName();

        UrlResource urlResource = new UrlResource("file:" + fileService.getServerFilePath(serverInfoName));

        //한글 제목은 허용 안되는듯.
        //한글데이터가 깨지지 않도록.
        String encodedUploadFileName = UriUtils.encode(userInfoName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + serverInfoName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(urlResource);
    }
}
