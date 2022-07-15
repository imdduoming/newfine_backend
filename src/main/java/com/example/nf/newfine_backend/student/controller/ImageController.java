package com.example.nf.newfine_backend.student.controller;

import com.example.nf.newfine_backend.student.dto.response.Result;
import com.example.nf.newfine_backend.student.service.ResponseService;
import com.example.nf.newfine_backend.student.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/image")
public class ImageController {

    private final S3Uploader s3Uploader;
    private final ResponseService responseService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return responseService.getSingleResult(s3Uploader.upload(multipartFile, "profile-image"));
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("image")String  fileName) {
        s3Uploader.delete(fileName,"profile-image");
        return responseService.getSuccessResult();
    }
}
