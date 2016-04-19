package org.dream.web.controller.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@RequestMapping(value = "/toUpload")
	public String getImg(HttpServletRequest request, HttpServletResponse response) {
		return "uploadPage";
	}

	/* 通过java IO 流上传文件 缓冲方式~ */
	@RequestMapping(value = "/uploadFile")
	public String uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("fileName: --->" + file.getOriginalFilename());

		BufferedOutputStream outputStream = null;
		BufferedInputStream inputStream = null;

		if (!file.isEmpty()) {
			try {
				outputStream = new BufferedOutputStream(
						new FileOutputStream("D:/" + System.currentTimeMillis() + file.getOriginalFilename()));

				inputStream = new BufferedInputStream(file.getInputStream());

				int num = 0;
				while ((num = inputStream.read()) != -1) {
					outputStream.write(num);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "staticFile";
	}

	/* 通过spring 提供的解析器进行上传文件 */
	@RequestMapping(value = "/uploadFileSpring")
	public String uploadFileSpring(HttpServletRequest request, HttpServletResponse response) {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (resolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartHttpServletRequest.getFileNames();
			while (it.hasNext()) {
				MultipartFile file = multipartHttpServletRequest.getFile(it.next());
				if (file != null) {
					String fileName = "uploadFileSpring" + System.currentTimeMillis() + file.getOriginalFilename();
					String path = "D:/" + fileName;
					File localFile = new File(path);

					try {
						file.transferTo(localFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "staticFile";
	}
}
