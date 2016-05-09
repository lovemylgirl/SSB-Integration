package org.dream.service.car.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dream.common.entity.CarBrand;
import org.dream.dao.car.CarBrandMapper;
import org.dream.service.car.ICarBrandService;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl implements ICarBrandService {

	@Resource
	private CarBrandMapper brandMapper;

	@Override
	public List<CarBrand> getAll() {
		CarBrand carBrand = new CarBrand();
		return brandMapper.select(carBrand);
	}

	@Override
	public List<CarBrand> getAllWithModels(int pageNum, int pageSize) {
		CarBrand brand = new CarBrand();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("start", (pageNum - 1) * pageSize);
		paramMap.put("end", pageSize);
		brand.setParamMap(paramMap);
		List<CarBrand> list = brandMapper.getAllWithModels(brand);
		return list;
	}
}
