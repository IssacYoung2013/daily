package com.issac.qiniuhelper;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * @author: ywy
 * @date: 2020-02-28
 * @desc:
 */
public class Uploader {
    //设置好账号的ACCESS_KEY和SECRET_KEY 
    String ACCESS_KEY = "QPzZpOf4GFTn8Iub_Z-2xc93Rkt6GBUizfwlWtg3"; //这两个登录七牛 账号里面可以找到
    String SECRET_KEY = "1UV4YcWiVvuXE25MURSWP_MIBkqoDYoO5tXUj0xc";

    //要上传的空间
    String bucketname = "issacyoung"; //填写新建的那个存储空间对象的名称
    //上传到七牛后保存的文件名
    String key = "spring-mvc.png";
    //上传文件的路径
    String FilePath = "/Users/Issac/2020/imageslib/spring-mvc.png"; //本地要上传文件路径

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //创建上传对象
    Configuration configuration = new Configuration();
    UploadManager uploadManager = new UploadManager(configuration);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    //普通上传
    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息

            System.out.println(res.isOK());

            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new Uploader().upload();
    }
}
