package org.dream.web.controller.img;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/img")
public class ImageController {

	@RequestMapping(value = "/getImg")
	public String getImg(HttpServletRequest request, HttpServletResponse response) {
		return "staticFile";
	}
}
