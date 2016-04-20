package org.dream.web.controller.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dream.common.entity.User;
import org.dream.common.util.ExportExcel;
import org.dream.service.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/export")
public class ExportExcelController {

	@Resource
	private IUserService userService;

	@RequestMapping(value = "/exportExcel")
	public void getImg(HttpServletRequest request, HttpServletResponse response) {
		String[] headers = { "Serial", "ID", "token", "昵称", "微信号", "姓名", "头像地址", "电话", "mac_id", "创建时间", "更新时间" };
		List<User> list = userService.findAll();
		ExportExcel<User> excel = new ExportExcel<>();
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=User.xls");
		try {
			out = response.getOutputStream();
			workbook = excel.exportExcel("测试", headers, list, out, "yyyy-MM-dd HH:dd:ss");
			workbook.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
