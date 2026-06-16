package com.perso.fileZipperTool.controller;

import com.perso.fileZipperTool.exceptions.NoUploadedFileException;
import com.perso.fileZipperTool.utils.ZipUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filezippertool")
public class FileZipperController {

    @PostMapping("/zip")
    public ResponseEntity<byte[]> zipFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty() || files.stream().allMatch(MultipartFile::isEmpty)) {
            throw new NoUploadedFileException();
        }

        List<ZipUtils.FichierEntry> entries = new ArrayList<>();

        for (MultipartFile file : files) {
            entries.add(new ZipUtils.FichierEntry(file.getOriginalFilename(), file.getBytes()));
        }

        byte[] zipBytes = ZipUtils.zipFiles(entries);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"archive.zip\"")
                .body(zipBytes);
    }
}
