package david.fileupload.service;

import david.fileupload.domain.ImageFile;
import david.fileupload.domain.InfoFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService
{
    @Value("${file.dir}")
    String defaultFilePath;

    public InfoFile saveInfo(MultipartFile file) throws IOException
    {
        String userFileName = file.getOriginalFilename();
        String serverFileName = createServerFileName(userFileName);
        InfoFile infoFile = new InfoFile(userFileName, serverFileName);

        file.transferTo(new File(getServerFilePath(serverFileName)));

        return infoFile;
    }

    public ImageFile saveImage(MultipartFile file) throws IOException
    {
        String userFileName = file.getOriginalFilename();
        String serverFileName = createServerFileName(userFileName);
        ImageFile imageFile = new ImageFile(userFileName, serverFileName);

        file.transferTo(new File(getServerFilePath(serverFileName)));

        return imageFile;
    }

    public List<ImageFile> saveImages(List<MultipartFile> files) throws IOException
    {
        List<ImageFile> uploadFiles = new ArrayList<>();

        for (MultipartFile file : files)
        {
            uploadFiles.add(saveImage(file));
        }
        return uploadFiles;
    }


    //==qwe-qwe-qwe123.jpg 서버 저장 파일명 생성==//
    private static String createServerFileName(String userFileName)
    {
        String extension = extractExtension(userFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + extension;
    }

    //==.jpg 혹은 .png 확장자 추출==//
    private static String extractExtension(String userFileName)
    {
        int indexOfDots = userFileName.indexOf(".");
        return userFileName.substring(indexOfDots);
    }

    //==전체 경로 qwe-qwe-qwe123.jpg 얻기==//
    public String getServerFilePath(String serverFileName)
    {
        return defaultFilePath + serverFileName;
    }
}
