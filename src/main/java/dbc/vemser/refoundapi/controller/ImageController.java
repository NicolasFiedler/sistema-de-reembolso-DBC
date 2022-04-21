package dbc.vemser.refoundapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequestMapping("/images")
public class ImageController {

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {

    }
}
