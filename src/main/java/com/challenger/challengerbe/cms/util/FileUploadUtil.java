package com.challenger.challengerbe.cms.util;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.cms.util
 * fileName       : FileUploadUtil
 * author         : rhkdg
 * date           : 2024-09-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Component
public class FileUploadUtil {

    public final String[] IMAGE_EXTS = {"bmp","gif","jpg","jpeg","png"};

    public final String[] ALLOW_EXTS = {"bmp","gif","jpg","jpeg","png","xls","xlsx"};

    public boolean validateUploadFile(MultipartFile multiPartFile, String[] exts) {
        return validateUploadFile(multiPartFile.getOriginalFilename(), exts);
    }

    public boolean validateUploadFile(String fileName, String[] exts) {
        boolean result = false;
        if(fileName != null && !fileName.isEmpty()) {
            if(fileName != null && !fileName.isEmpty()) {
                String tmp = fileName.split("[.]")[1].toLowerCase();
                for(String ext : exts) {
                    if(tmp.equals(tmp)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }


    public String saveUploadFile(String uploadPath, MultipartFile multiPartFile, String prevName, boolean isDeleteFile) throws Exception {
        return saveUploadFile(uploadPath, multiPartFile, prevName, isDeleteFile, ALLOW_EXTS);
    }

    public String saveUploadFile(String uploadPath, MultipartFile multiPartFile, String prevName, boolean isDeleteFile, String[] exts) throws Exception {
        String fileName = "";
        if(multiPartFile != null && !multiPartFile.isEmpty()) {
            if(!validateUploadFile(multiPartFile, exts)) {
                throw new Exception("첨부가 불가능한 파일입니다.");
            }
            fileName = saveFile(uploadPath,multiPartFile);
        }

        return fileName;
    }

    public String saveFile(String path, MultipartFile file) throws Exception{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String name = "";
        try {
            if(file != null && !"".equals(file.getOriginalFilename())) {
                System.out.println("파일 경로 : "+path);
                File renameFile = rename(new File(path,file.getOriginalFilename()));
                file.transferTo(renameFile);
                name = renameFile.getName();

                //이미지 resize
                String[] imgcheck = name.split("[.]");
                if (imgcheck[1].equals("jpg") || imgcheck[1].equals("png") || imgcheck[1].equals("png")) {
                    try {
                        File outputfile = new File(path+File.separator+"small_"+name);
                        BufferedImage small = ImageIO.read(renameFile);
                        OutputStream os = new FileOutputStream(outputfile);
                        BufferedImage convsmall = Resize(small, 75, 75);
                        ImageIO.write(convsmall, "PNG", os);
                    }catch (IOException e) {
                        System.out.println("Image File Small Resize Error !!!");
                    }
                    try{
                        File outputfile = new File(path+File.separator+"big_"+name);
                        BufferedImage big = ImageIO.read(renameFile);
                        OutputStream os = new FileOutputStream(outputfile);
                        BufferedImage convbig = Resize(big, 500, 500);
                        ImageIO.write(convbig, "PNG", os);
                    }catch (IOException e) {
                        System.out.println("Image File Big Resize Error !!!");
                    }
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("파일 저장시 오류가 발생했습니다. " + e.getMessage());
        }
        return name;
    }

    /**
     * 파일 중복 존재 유무 확인
     * @param f
     * @return
     */
    private boolean createNewFile(File f) {
        try {
            return f.createNewFile();
        }catch (IOException e) {
            return false;
        }
    }

    //중복되는 파일명 재 설정
    private File rename(File ff) {

        File f = ff;
        if(createNewFile(f)) {
            return f;
        }

        String name = f.getName();
        String body = null;
        String ext = null;
        int dot = name.lastIndexOf(".");
        if(dot != -1) {
            body = name.substring(0,dot);
            ext = name.substring(dot);
        }else {
            body = name;
            ext = "";
        }

        //빈공간 제거
        body = body.replaceAll(" ", "");

        int count = 0;
        while(!createNewFile(f) && count < 9999) {
            count ++;
            String newName = body+count+ext;
            f = new File(f.getParent()+File.separator+newName);
        }

        return f;
    }

    /**
     * 파일 단일 삭제 메소드
     * @param path
     * @param fileName
     */
    public void deleteFile(String path, String fileName) {
        if(fileName == null || fileName.isEmpty()) {
            return ;
        }
        File deleteFile = new File(path,fileName);
        if(deleteFile.exists()) {
            deleteFile.deleteOnExit();
        }
    }

    public void deleteFile(List<File> fileList){
        for(File file : fileList){
            if(file.exists()){
                file.deleteOnExit();
            }
        }
    }

    public BufferedImage Resize(BufferedImage img, int width, int height) {
        BufferedImage outputImage = new BufferedImage(width, height, img.getType());

        Graphics2D graph2d = outputImage.createGraphics();
        graph2d.drawImage(img, 0, 0, width, height, null);
        graph2d.dispose();
        return outputImage;
    }
}
