package com.hansy.tyly.common.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;

/**
 * 图片上传方法
 * @author qiub
 *
 */
public class AliyunOssUtil {

	private static final String OSS_ENDPOINT = AppPropertiesUtil.getValue("oss.endpoint");
	private static final String OSS_ACCESSKEYID = AppPropertiesUtil.getValue("oss.accesskeyid");
	private static final String OSS_ACCESSKEYSECRET = AppPropertiesUtil.getValue("oss.accesskeysecret");
	private static final String OSS_BUCKET_NAME = AppPropertiesUtil.getValue("oss.bucket_name");
	/**
	 * 上传图片到oss并且获取访问URL
	 * @param fileName
	 * @param inputStream
	 * @return
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public static String uploadImg(String fileName,InputStream inputStream) throws FileNotFoundException, ParseException{
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = OSS_ENDPOINT;
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
		String accessKeyId = OSS_ACCESSKEYID;
		String accessKeySecret = OSS_ACCESSKEYSECRET;
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
//		InputStream inputStream = new FileInputStream("D://img.png");
		ossClient.putObject(OSS_BUCKET_NAME, fileName, inputStream);
		
		Date expiration = DateUtil.parseRfc822Date("Wed, 18 Mar 2050 14:20:00 GMT");
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(OSS_BUCKET_NAME, fileName, HttpMethod.GET);
		// 设置过期时间。
		request.setExpiration(expiration);
		// 生成签名URL（HTTP GET请求）。
		URL signedUrl = ossClient.generatePresignedUrl(request);
		// 使用签名URL发送请求。
		Map<String, String> customHeaders = new HashMap<String, String>();
		// 添加GetObject请求头。
		customHeaders.put("Range", "bytes=100-1000");
		OSSObject object = ossClient.getObject(signedUrl,customHeaders);
		// 关闭OSSClient。
		ossClient.shutdown();
		System.out.println("signed url for getObject: " + signedUrl);
		return signedUrl.toString();
	}
	public static void main(String[] args) {
		String cddString = AppPropertiesUtil.getValue("oss.endpoint");
		System.out.println(cddString);
	}
}
