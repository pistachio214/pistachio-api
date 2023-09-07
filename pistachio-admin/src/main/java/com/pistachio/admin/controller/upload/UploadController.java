package com.pistachio.admin.controller.upload;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.framework.minio.IMinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName UploadController
 * @auther songyang peng
 * @date 2023/9/6 21:20
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "上传模块")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IMinioService iMinioService;

    @OperLog(operModul = "上传模块 - 单文件图片上传", operType = OperationLogConst.UPLOAD, operDesc = "单文件图片上传")
    @Operation(summary = "上传模块 - 单文件图片上传", description = "单文件图片上传")
    @PostMapping("/file")
    public R<Object> uploadFile(@RequestParam(name = "file", required = true) MultipartFile file) {

        return R.success(UserConstants.DEFAULT_AVATAR);
//        try {
//            //得到文件流
//            InputStream is = file.getInputStream();
//            //文件名
//            String fileName = file.getOriginalFilename();
//            String newFileName = UUID.randomUUID() + "-" + System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
//            //类型
//            String contentType = file.getContentType();
//            iMinioService.uploadObject(is, newFileName, contentType);
//
//            String url = iMinioService.getObjectUrl(newFileName);
//            return R.success(newFileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.error("上传失败");
//        }
    }

}
