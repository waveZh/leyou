package com.leyou.service;




import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.G;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPE = Arrays.asList("image/jpeg","image/gif");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    //引入FastDFS客户端
    @Autowired
    private FastFileStorageClient storageClient;

    public String upload(MultipartFile file) {

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //校验文件类型
        String contentType = file.getContentType();
        if (! CONTENT_TYPE.contains(contentType)){
            LOGGER.info("文件类型不合法: {} ",originalFilename);
            return null;
        }

        try {
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法: {}",originalFilename);
                return null;

            }

            //保存到服务器
            // file.transferTo(new File(("G:\\Leyou\\image\\") + originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath path = storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            //返回url进行回显
            // return "http://image.leyou.com/" + originalFilename;
            return "http://image.leyou.com/" + path.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误: {}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }

}

