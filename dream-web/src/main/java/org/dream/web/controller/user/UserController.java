package org.dream.web.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dream.common.ApiCode;
import org.dream.common.entity.User;
import org.dream.common.requestparams.CheckUserParam;
import org.dream.service.user.IUserService;
import org.dream.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

	@Resource
	private IUserService userService;

	@Resource
	private Validator validator;

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getUserInfo(CheckUserParam param, HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		validator.validate(param, errors);
		handleValidFieldError(errors);
		param.setId(52L);
		User user = userService.getUserById(param.getId());
		return createJsonRespone(ApiCode.SUCCESS, user, "");
	}
}
