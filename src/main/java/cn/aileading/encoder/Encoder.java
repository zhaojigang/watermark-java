/*
 * Copyright (c) 2020 ww23(https://github.com/ww23/BlindWatermark).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.aileading.encoder;

import cn.aileading.converter.Converter;
import cn.aileading.helper.WatermarkHelper;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author ww23
 */
public abstract class Encoder {

    protected final Converter converter;

    public Encoder(Converter converter) {
        this.converter = converter;
    }

    public void encode(String inputFile, String textWatermark, String outputFile) {
        Mat src = WatermarkHelper.read(inputFile, CvType.CV_8S);

        List<Mat> channel = new ArrayList<>(3);
        List<Mat> newChannel = new ArrayList<>(3);
        Core.split(src, channel);

        for (int i = 0; i < 3; i++) {
            Mat com = this.converter.start(channel.get(i)).clone();
            this.addWatermark(com, textWatermark);
            this.converter.inverse(com);
            newChannel.add(i, com);
        }

        Mat res = new Mat();
        Core.merge(newChannel, res);

        if (res.rows() != src.rows() || res.cols() != src.cols()) {
            res = new Mat(res, new Rect(0, 0, src.width(), src.height()));
        }

        Imgcodecs.imwrite(outputFile, res);
    }

    public abstract void addWatermark(Mat com, String watermark);
}
