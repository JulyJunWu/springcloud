package provider.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Jun
 * data  2019-10-16 22:46
 */

@RestController
public class UploadController {

    /**
     * 上传文件
     *  在linux上使用 curl -F "file=@文件名" http://localhost:8082/upload
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file)throws IOException{

        byte[] bytes = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        File fileToSave = new File(originalFilename);

        FileCopyUtils.copy(bytes,fileToSave);
        return fileToSave.getAbsolutePath();
    }

}
