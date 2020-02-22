package com.bitsun.provider.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.bitsun.provider.common.constant.OssConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

@Component
public class OSSClientUtil {
    Logger log = LoggerFactory.getLogger(OSSClientUtil.class);

    private OSSClient ossClient;
    @Value("${biztier.endpoint}")
    private String endpoint ;
    @Value("${biztier.accessKeyId}")
    private String accessKeyId ;
    @Value("${biztier.accessKeySecret}")
    private String accessKeySecret ;
    //空间
    @Value("${biztier.bucketName}")
    private String bucketName ;
    //文件存储目录


    public  OSSClientUtil() {

    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (OssConstants.BMP.equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        if (OssConstants.GIF.equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        if (OssConstants.JPEG.equals(filenameExtension) || OssConstants.JPG.equalsIgnoreCase(filenameExtension) || OssConstants.PNG.equalsIgnoreCase(filenameExtension)) {
            return "image/jpeg";
        }
        if (OssConstants.HTML.equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        if (OssConstants.TXT.equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        if (OssConstants.VSD.equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        if (OssConstants.MP4.equalsIgnoreCase(filenameExtension)) {
            return "audio/mp4";
        }
        if (OssConstants.PPTX.equalsIgnoreCase(filenameExtension) || OssConstants.PPT.equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (OssConstants.DOCX.equalsIgnoreCase(filenameExtension) || OssConstants.DOC.equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        if (OssConstants.XML.equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     *
     * @param url
     */
    public void upload(String url,String filedir) throws Exception {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile(fin, split[split.length - 1],filedir);
        } catch (FileNotFoundException e) {
            throw new Exception("上传失败");
        }
    }

    /**
     * 上传文件到OSS
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadImg(MultipartFile file, String filedir) throws Exception {
        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            String beginstring = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            Random random = new Random();
            String name = beginstring + "-" + random.nextInt(10000) + System.currentTimeMillis() + substring;
            InputStream inputStream = file.getInputStream();
            return this.uploadFile(inputStream, name,filedir);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl,String filedir) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(filedir + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile(InputStream instream, String fileName,String filedir) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = this.getUrl(filedir + fileName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 删除某个Object
     *
     * @param bucketUrl
     * @return
     */
    public boolean deleteObject(String bucketUrl) {
        try {
            // 删除Object.
            ossClient.deleteObject(bucketName, bucketUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
        return true;
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

}
