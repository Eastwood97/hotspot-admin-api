package com.jsc.hotspot.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huixing
 * @description FFmpeg进行视频转码、裁剪等操作
 * @date 2019/11/10
 */
@Slf4j
public class FFmpegUtil {

    /**
     * 进行视频裁剪
     * @param videoFile
     * @param outputFile
     * @param startTime
     * @param timeLength
     */
    public static void cutVideo(File videoFile, File outputFile, int startTime, int timeLength) {
        if (videoFile == null || !videoFile.exists()) {
            throw new RuntimeException("视频文件不存在：");
        }
        if (null == outputFile) {
            throw new RuntimeException("转换后的视频路径为空，请检查转换后的视频存放路径是否正确");
        }
       // VideoMetaInfo info = getVideoMetaInfo(videoFile);
//        if (null == info) {
//            throw new RuntimeException("未解析到视频信息");
//        }
//        if (startTime+ timeLength > info.getDuration()) {
//            throw new RuntimeException("截取时间不合法：" + startTime + "，因为截取时间大于视频的时长");
//        }
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            List<String> command = new ArrayList<String>();
            command.add("-ss");
            command.add("00:30");//startTime
            command.add("-i");
            command.add(videoFile.getAbsolutePath());
            command.add("-t");
            command.add("45" );//timeLength s
            command.add("-c:v");
            command.add("copy");
            command.add("-c:a");
            command.add("copy");
            command.add(outputFile.getAbsolutePath());
            // commond.add("-ss 00:30 -i D:\\Data\\study.mp4 -t 45 -c:v copy -c:a copy D:\\Data\\studyoutput3.mp4");
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);

            builder.redirectErrorStream(true);

            Process process = builder.start();
            StringBuffer sbf = new StringBuffer();
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(" ");
            }
            String resultInfo = sbf.toString();
            System.out.println(resultInfo);

        } catch (IOException e) {
            log.error("--- 视频截取过程出错 ---");
        }
    }

    /**
     * 通过FFmpeg进行转码操作
     * @param videoInputPath
     * @param videoOutputPath
     * @throws Exception
     */
    public void convertor(String videoInputPath, String videoOutputPath) throws Exception {
//		ffmpeg -i input.mp4 -y output.avi
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");

        command.add("-i");
        command.add(videoInputPath);
        command.add("-y");
        command.add(videoOutputPath);

        for (String c : command) {
            System.out.print(c + " ");
        }

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

    }

    /**
     * 音视频合成
     * @param videoInputPath
     * @param mp3InputPath
     * @param seconds
     * @param videoOutputPath
     * @throws Exception
     */
    public void convertor(String videoInputPath, String mp3InputPath,
                          double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i A.avi -i bgm.mp3 -t 7 -y new.avi
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutputPath);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }

    /**
     * 通过ffmpeg命令获取时间
     * @param video_path
     * @param ffmpeg_path
     * @return
     */
    public static int getVideoTime(String video_path, String ffmpeg_path) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                log.info(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 视频时长格式转化
     * @param timelen
     * @return
     */
    private static int getTimelen(String timelen){
        int min=0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min+=Integer.valueOf(strs[0])*60*60;//秒
        }
        if(strs[1].compareTo("0")>0){
            min+=Integer.valueOf(strs[1])*60;
        }
        if(strs[2].compareTo("0")>0){
            min+=Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }
}
