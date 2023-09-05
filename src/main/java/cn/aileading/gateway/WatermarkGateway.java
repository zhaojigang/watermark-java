package cn.aileading.gateway;

import cn.aileading.converter.DctConverter;
import cn.aileading.decoder.Decoder;
import cn.aileading.encoder.Encoder;
import cn.aileading.encoder.TextEncoder;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;

/**
 * 水印使用入口
 *
 * @author zhaojigang
 */
public class WatermarkGateway {
    /**
     * 增加暗水印
     * 注意：
     * Loader.load(opencv_java.class); 首次执行是一个耗时操作，windows11 本机测试需要 1.5s 左右。（二次加载的时候 40ms 左右）
     * encode 增加水印操作：耗时约 200 ms左右
     * decode 获取水印图片，耗时约 35ms 左右
     *
     * @param inputFile     输入图片
     * @param textWatermark 水印
     * @param outputFile    输出图片
     * @return
     */
    public static String addBlindWatermark(String inputFile, String textWatermark, String outputFile) {
        Loader.load(opencv_java.class);
        Encoder encoder = new TextEncoder(new DctConverter());
        encoder.encode(inputFile, textWatermark, outputFile);
        return outputFile;
    }

    /**
     * 从 inputFile 中解析出按水印图片
     *
     * @param inputFile  带水印的图片
     * @param outputFile 水印图片
     * @return
     */
    public static String getBlindWatermark(String inputFile, String outputFile) {
        Loader.load(opencv_java.class);
        Decoder decoder = new Decoder(new DctConverter());
        decoder.decode(inputFile, outputFile);
        return outputFile;
    }

//    public static void main(String[] args) {
//        String inputFile = "C:\\Users\\xxx\\Desktop\\unify\\needRepair\\1\\linqingxia.jpeg";
//        String textWatermark = "测试暗水印";
//        String outputFileWithWatermark = "C:\\Users\\xxx\\Desktop\\unify\\needRepair\\1\\linqingxia-with-watermark.jpeg";
//        addBlindWatermark(inputFile, textWatermark, outputFileWithWatermark);
//
//        String waterMarkImage = "C:\\Users\\xxx\\Desktop\\unify\\needRepair\\1\\linqingxia-without-watermark.jpeg";
//        getBlindWatermark(outputFileWithWatermark, waterMarkImage);
//    }
}
