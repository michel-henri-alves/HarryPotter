package com.mha.harrypotter.repositories;

/**
 * Characters Repository filter implementation
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mha.harrypotter.model.Character;


@Repository
public class CharacterRepositoryFilterImpl implements  CharacterRepositoryFilter {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<Character> findByNameAndCnpj(Pageable pageable, Character obj){
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Character.class);
		criteria.setFirstResult(pageable.getPageNumber());
		criteria.setMaxResults(pageable.getPageSize());

		Criteria criteriaCount = session.createCriteria(Character.class);
		criteriaCount.setProjection(Projections.rowCount());

		Long count = (Long) criteriaCount.uniqueResult();

		Optional<Character> opt = Optional.ofNullable(obj);
		
		if(opt.isPresent()){
			if (StringUtils.isNotBlank(opt.get().getName()))
				criteria.add(Restrictions.eq("name", obj.getName()));
			
			if (StringUtils.isNotBlank(opt.get().getRole()))
				criteria.add(Restrictions.eq("role", obj.getRole()));
			
			if (StringUtils.isNotBlank(opt.get().getSchool()))
				criteria.add(Restrictions.eq("school", obj.getSchool()));
			
			if (StringUtils.isNotBlank(opt.get().getPatronus()))
				criteria.add(Restrictions.eq("patronus", obj.getPatronus()));
			
			if (opt.get().getId() != 0)
				criteria.add(Restrictions.eq("id", obj.getId()));
		}

		return new PageImpl<Character>(criteria.list(), pageable, count);
	}

}
