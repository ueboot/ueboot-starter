/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:ZipUtils.java   2018-08-17 下午2:47 andy
 */
package com.ueboot.starter.frontend.utils;

import com.ueboot.core.exception.BusinessException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: XiQiao
 * </p>
 *
 * @author: andy
 * @create: 2018-08-17 下午2:47
 * @version：1.0
 */
@Slf4j
public class ZipUtils {


    private static final int BUFFER_SIZE = 2 * 1024;


    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException {

        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    log.error("创建ZIP文件失败",e);
                    throw new BusinessException("关闭创建ZIP文件失败 ===>> {}", e.toString());
                }
            }
        }
    }


    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    log.error("创建ZIP文件失败",e);
                    throw new BusinessException("关闭创建ZIP文件失败 ===>> {}", e.toString());
                }
            }
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + File.separator + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }

    /**
     * 创建ZIP文件
     * @param sourcePath 文件或文件夹路径
     * @param zipPath 生成的zip文件存在路径（包括文件名）
     */
    public static void toZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            log.error("创建ZIP文件失败",e);
            throw new BusinessException("创建ZIP文件失败 ===>> {}", e.toString());
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                log.error("创建ZIP文件失败",e);
                throw new BusinessException("关闭创建ZIP文件失败 ===>> {}", e.toString());
            }

        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                if(files.length != 0){
                    for(File f:files){
                        writeZip(f, parentPath, zos);
                    }
                }else{       //空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    log.error("创建ZIP文件失败",e);
                    throw new BusinessException("创建ZIP文件失败 ===>> {}", e.toString());
                } catch (IOException e) {
                    log.error("创建ZIP文件失败",e);
                    throw new BusinessException("创建ZIP文件失败 ===>> {}", e.toString());
                }finally{
                    try {
                        if(fis!=null){
                            fis.close();
                        }
                    }catch(IOException e){
                        log.error("创建ZIP文件失败",e);
                        throw new BusinessException("创建ZIP文件失败 ===>> {}", e.toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        /** 测试压缩方法1  */
//        FileOutputStream fos1 = new FileOutputStream(new File("./mytest01.zip"));
//        ZipUtils.toZip("./log", fos1, true);
//
        /** 测试压缩方法2  */
        List<File> fileList = new ArrayList<>();
        File file1 = new File("./a1.text");
        file1.createNewFile();
        File file2 = new File("./a2.text");
        file2.createNewFile();
        fileList.add(file1);
        fileList.add(file2);
        FileOutputStream fos2 = new FileOutputStream(new File("./mytest02.zip"));
        ZipUtils.toZip(fileList, fos2);


//        List<Map<String, File>> files = new ArrayList<>();
//        Map<String, File> map1 = new HashMap<>();
//        File a1 = new File("./" + File.separator + "测试" + File.separator + "A");
//        a1.mkdirs();
//        File a1t = new File("./" + File.separator + "测试" + File.separator + "A" + File.separator + "a1.text");
//        a1t.createNewFile();
//        map1.put("测试"+File.separator+"A"+File.separator+"a1.text", a1);
//        File a2 = new File("./a2.text");
//        a2.createNewFile();
//        map1.put("测试"+File.separator+"A"+File.separator+"a2.text", a2);
//        Map<String, File> map2 = new HashMap<>();
//        File a3 = new File("./a3.txt");
//        a3.createNewFile();
//        map2.put("测试"+File.separator+"B"+File.separator+"aa.txt", a3);
//        files.add(map1);
//        files.add(map2);
//        File zipFile = new File("./测试.zip");
//        FileOutputStream out = new FileOutputStream(zipFile);
//        ZipOutputStream fos = new ZipOutputStream(out);
//        ZipUtils.toZip(files, fos);
//        ZipUtils.toZip("./" + File.separator + "测试", out, true);
//        ZipUtils.toZip("./测试/A","./测试/"+ System.currentTimeMillis() +".zip");

    }
}
