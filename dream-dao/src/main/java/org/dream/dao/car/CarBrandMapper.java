package org.dream.dao.car;

import java.util.List;

import org.dream.common.entity.CarBrand;

import tk.mybatis.mapper.common.Mapper;

public interface CarBrandMapper extends Mapper<CarBrand> {

	List<CarBrand> getAllWithModels(CarBrand carBrand);
	
}
