package org.dream.service.upload;

import java.io.File;

public interface IUpLoadMediaService {
	
	String upload(File file, String dir, String fileName);
}
