package com.hansy.tyly.admin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeUtil {

	public static String getQrCode(String url,int width,int height,String path)  throws Exception {
		String format="png";
		HashMap map=new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8");
		map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
		map.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bm = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
			OutputStream stream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bm, format, stream);
			InputStream inputStream = parse(stream);
			return AliyunOssUtil.uploadImg(path, inputStream);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ByteArrayInputStream parse(OutputStream out) throws Exception
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = (ByteArrayOutputStream) out;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }
}
