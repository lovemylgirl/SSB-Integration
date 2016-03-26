package org.dream.service.car.impl;

import java.util.List;

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
}
