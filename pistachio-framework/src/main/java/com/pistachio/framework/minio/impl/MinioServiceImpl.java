package com.pistachio.framework.minio.impl;

import com.pistachio.framework.config.MinioConfig;
import com.pistachio.framework.minio.IMinioService;
import io.minio.*;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName MinioServiceImpl
 * @auther songyang peng
 * @date 2023/9/6 22:36
 */
@Service(value = "IMinioService")
public class MinioServiceImpl implements IMinioService {

    private final static Logger logger = LoggerFactory.getLogger(MinioServiceImpl.class);

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient minioClient;

    @Override
    public List<String> listObjects() {
        List<String> list = new ArrayList<>();
        try {
            ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .build();

            Iterable<Result<Item>> results = minioClient.listObjects(listObjectsArgs);
            for (Result<Item> result : results) {
                Item item = result.get();
                logger.info(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                list.add(item.objectName());
            }
        } catch (Exception e) {
            logger.error("错误：" + e.getMessage());
        }
        return list;
    }

    @Override
    public void deleteObject(String objectName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(objectName)
                    .build();
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            logger.error("错误：" + e.getMessage());
        }
    }

    @Override
    public void uploadObject(InputStream is, String fileName, String contentType) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(fileName)
                    .contentType(contentType)
                    .stream(is, is.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            is.close();
        } catch (Exception e) {
            logger.error("错误：" + e.getMessage());
        }
    }

    @Override
    public String getObjectUrl(String objectName) {
        try {
            return minioClient.getObjectUrl(minioConfig.getBucketName(), objectName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("错误：" + e.getMessage());
        }
        return null;
    }

    @Override
    public InputStream getObject(String objectName) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(objectName)
                    .build();
            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            logger.error("错误：" + e.getMessage());
        }
        return null;
    }
}
