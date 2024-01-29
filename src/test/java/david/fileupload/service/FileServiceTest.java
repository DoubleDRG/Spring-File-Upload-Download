package david.fileupload.service;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest
{

    @Test
    void saveFile()
    {
        String userFileName = "hello.jpg";
        int indexOfDots = userFileName.indexOf(".");
        String extension = userFileName.substring(indexOfDots);

        String uuid = UUID.randomUUID().toString();
        String serverFileName = uuid + extension;
//        System.out.println("serverFileName = " + serverFileName);
    }
}