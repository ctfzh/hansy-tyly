package com.hansy.tyly.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 图片压缩工具类
 * @author qiub
 *
 */
public class ReduceImgUtil {

	/**
	 * 图片压缩
	 * @param imgsrc 图片流
	 * @param imgSuffix 图片后缀
	 * @param widthdist 宽度
	 * @param heightdist 高度
	 * @param rate 旋转
	 * @return
	 */
	public static InputStream reduceImg(InputStream imgsrc, String imgSuffix, int widthdist, int heightdist, Float rate) {
        try {
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(imgsrc);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            ByteArrayOutputStream bs =new ByteArrayOutputStream(); 
    		ImageOutputStream imOut =ImageIO.createImageOutputStream(bs); 
    		ImageIO.write(tag,imgSuffix,imOut); 
    		//将图片转为inputStream流 
    		return new ByteArrayInputStream(bs.toByteArray());
    		
        } catch (Exception ef) {
            ef.printStackTrace();
        }
        return null;
    }
}
