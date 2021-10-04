package com.mha.harrypotter.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert object to DTO
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvertEntityToDTO {

	/**
	 * object to DTO
	 * 
	 * @param Object   - object to be converted
	 * @param Class<E> - dto for conversion
	 * @return <E> E
	 */
	public <E> E mappingObjects(Object entity, Class<E> dto) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		  .setFieldMatchingEnabled(true);
		return modelMapper.map(entity, dto);
	}

	/**
	 * List<Object> to List<DTO>
	 * 
	 * @param List<?>   - list of object to be converted
	 * @param Class<E> - dto for conversion
	 * @return <E> List<E>
	 */
	public <E> List<E> mappingLists(List<?> lst, Class<E> cls) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		  .setFieldMatchingEnabled(true);
		List<E> result = new ArrayList<E>();
		for (Object obj : lst) {
			result.add((E) modelMapper.map(obj, cls));
		}
		return result;
	}

}
