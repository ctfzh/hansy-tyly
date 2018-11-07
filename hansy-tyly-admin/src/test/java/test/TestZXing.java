package test;

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
import com.hansy.tyly.admin.utils.AliyunOssUtil;

public class TestZXing {
	public static void main(String[] args) throws Exception {
		int width=300;
		int height=300;
		String format="png";
		String contents="http://ysp.hansyinfo.com/index.html#/registerview?salesId=121fwg3";
		HashMap map=new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8");
		map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
		map.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bm = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);
			Path file=new File("D:/img.png").toPath();
			MatrixToImageWriter.writeToPath(bm, format, file);
			OutputStream stream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bm, format, stream);
			InputStream swapStream = parse(stream);
			AliyunOssUtil.uploadImg("cust/mer/erweima/test.png", swapStream);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ByteArrayInputStream parse(OutputStream out) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = (ByteArrayOutputStream) out;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }
}
