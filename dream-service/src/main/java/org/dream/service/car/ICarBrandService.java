package org.dream.service.car;

import java.util.List;

import org.dream.common.entity.CarBrand;

public interface ICarBrandService {

	List<CarBrand> getAll();
	
	List<CarBrand> getAllWithModels(int pageNum,int pageSize );
}
