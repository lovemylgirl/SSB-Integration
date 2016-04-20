package org.dream.web.controller.upload;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/down")
public class DownLoadController {

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {
		String path = "D:\\Backlog.xlsx";
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();

		/* 解决中文名称乱码 */
		String fileName = new String("你好.xlsx".getBytes("utf-8"), "iso-8859-1");

		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
