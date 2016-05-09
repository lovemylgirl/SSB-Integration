package org.dream.service.car;

import java.util.List;

import javax.annotation.Resource;

import org.dream.common.entity.CarBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class CarBrandTest {

	@Resource
	private ICarBrandService brandService;

	@Test
	public void getAll() {
		List<CarBrand> list = brandService.getAll();
		for (CarBrand cb : list) {
			System.out.println(cb.getBrandCode() + " : " + cb.getBrandName());
		}
	}

	@Test
	public void getAllWithModels() {
		List<CarBrand> list = brandService.getAllWithModels(1, 20);
		System.out.println(list.size());
	}
}
