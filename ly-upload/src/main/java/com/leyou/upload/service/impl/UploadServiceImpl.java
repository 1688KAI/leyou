package com.leyou.item.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.config.UploadProperties;
import com.leyou.item.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    UploadProperties prop;


    @Override
    public String upload(MultipartFile file) {

        try {
            // 1、图片信息校验
            // 1)校验文件类型
            String type = file.getContentType();
            if (!prop.getAllowTypes().contains(type)) {
                logger.info("上传失败，文件类型不匹配：{}", type);
                throw new LyException(ExceptionEnum.PICTURE_FORMAT_ERROR);
            }
            // 2)校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                throw new LyException(ExceptionEnum.PICTURE_FORMAT_ERROR);
            }
            // 2、保存图片
            // 2.1、生成保存目录
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 2.3、拼接图片地址
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (Exception e) {
            return null;
        }
    }

}
