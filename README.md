# Java 版水印工具
## 明水印
可使用 thumbnailator，具体使用查询 thumbnailator github 官网
## 暗水印
```
暗水印部分是在 https://github.com/ww23/BlindWatermark 项目的基础上 做了部分修改：
1. 从 gradle 改为 maven
2. 支持 jdk17
3. 提供水印添加和查询接口 API
```
使用该项目的 WatermarkGateway 中的方法：
```
addBlindWatermark：为图片增加暗水印
getBlindWatermark：从有暗水印的图片中解析出暗水印
```
具体使用见 WatermarkGateway.main 方法。
