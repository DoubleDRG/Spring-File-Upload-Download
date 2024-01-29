package david.fileupload.controller;

import david.fileupload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class ImageController
{
    private final FileService fileService;

    //이미지를 src태그로 보내기
    @ResponseBody
    @GetMapping("/images/{serverImageName}")
    public Resource downloadImage(@PathVariable String serverImageName) throws MalformedURLException
    {
        return new UrlResource("file:" + fileService.getServerFilePath(serverImageName));
    }
}
