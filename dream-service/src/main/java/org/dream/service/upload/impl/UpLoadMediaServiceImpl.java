package org.dream.service.upload.impl;

import java.io.File;

import org.dream.common.ApiCode;
import org.dream.common.exception.EvcharException;
import org.dream.service.upload.IUpLoadMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.media.MediaConfiguration;
import com.alibaba.media.Result;
import com.alibaba.media.upload.UploadClient;
import com.alibaba.media.upload.UploadPolicy;
import com.alibaba.media.upload.UploadRequest;
import com.alibaba.media.upload.UploadResponse;
import com.alibaba.media.upload.UploadTokenClient;
import com.alibaba.media.upload.impl.DefaultUploadClient;
import com.alibaba.media.upload.impl.DefaultUploadTokenClient;

@Service
public class UpLoadMediaServiceImpl implements IUpLoadMediaService {

	private static final Logger logger = LoggerFactory.getLogger(UpLoadMediaServiceImpl.class);

	private static final UploadClient CLIENT;

	public static final String TOKEN;

	static {
		MediaConfiguration configuration = new MediaConfiguration();
		configuration.setAk("23333369");
		configuration.setSk("36bbe209c14c8bb6c4ae573fd9f2d80f");
		configuration.setNamespace("evcharmedia");
		UploadTokenClient tokenClient = new DefaultUploadTokenClient(configuration);

		/**
		 * 为用户指定上传策略 下面的上传策略指定了: 用户上传文件允许覆盖 该用户凭证的失效时间为当前时间之后的一个小时,
		 * 在之后的一个小时之内Token都可以作为用户的上传凭证 失效时间设置为-1时表示永不失效
		 */
		UploadPolicy uploadPolicy = new UploadPolicy();
		uploadPolicy.setInsertOnly(UploadPolicy.INSERT_ONLY_NONE);
		uploadPolicy.setExpiration(-1);

		/**
		 * 请求Token服务,为该用户申请该上传策略对应的Token
		 */
		TOKEN = tokenClient.getUploadToken(uploadPolicy);

		/**
		 * 初始化 UploadClient
		 */
		CLIENT = new DefaultUploadClient();

	}

	@Override
	public String upload(File file, String dir, String fileName) {
		UploadRequest uploadRequest = new UploadRequest(TOKEN);
		uploadRequest.setFile(file);
		uploadRequest.setDir(dir);
		uploadRequest.setName(fileName);
		Result<UploadResponse> result = CLIENT.upload(uploadRequest);
		if (result.isSuccess()) {
			// 调用接口成功,打印出上传接口的返回信息
			UploadResponse uploadResponse = result.getData();
			return uploadResponse.getDir() + "/" + fileName;
		} else {
			logger.warn(result.getMessage());
			throw new EvcharException(ApiCode.UPLOAD_ERR, "上传错误");
		}
	}

}
