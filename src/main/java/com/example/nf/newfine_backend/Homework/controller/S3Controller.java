package com.example.nf.newfine_backend.Homework.controller;

import com.example.nf.newfine_backend.Homework.CommonUtils.CommonUtils;
import com.example.nf.newfine_backend.Homework.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("category") String category,
                                             @RequestPart(value = "file") MultipartFile file) {
        return new ResponseEntity<>(s3service.uploadFile(category, file), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("resourcePath") String resourcePath) {
        byte[] data = s3service.downloadFile(resourcePath);
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = buildHeaders(resourcePath, data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .headers(headers)
                .body(resource);
    }

    private HttpHeaders buildHeaders(String resourcePath, byte[] data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(CommonUtils.createContentDisposition(resourcePath));
        return headers;

    }

    @GetMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(s3service.deleteFile(fileName), HttpStatus.OK);
    }
}
