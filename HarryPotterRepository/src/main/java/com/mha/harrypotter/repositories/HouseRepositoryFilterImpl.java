package com.mha.harrypotter.repositories;

/**
 * house Repository filter implementation
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

import com.mha.harrypotter.model.House;


@Repository
public class HouseRepositoryFilterImpl implements HouseRepositoryFilter {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<House> findByNameAndCnpj(Pageable pageable, House obj){
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(House.class);
		criteria.setFirstResult(pageable.getPageNumber());
		criteria.setMaxResults(pageable.getPageSize());

		Criteria criteriaCount = session.createCriteria(House.class);
		criteriaCount.setProjection(Projections.rowCount());

		Long count = (Long) criteriaCount.uniqueResult();

		Optional<House> opt = Optional.ofNullable(obj);
		
		if(opt.isPresent()){
			if (StringUtils.isNotBlank(opt.get().getName()))
				criteria.add(Restrictions.eq("name", obj.getName()));
			
			if (StringUtils.isNotBlank(opt.get().getHeadOfHouse()))
				criteria.add(Restrictions.eq("headOfHouse", obj.getHeadOfHouse()));
			
			if (StringUtils.isNotBlank(opt.get().getSchool()))
				criteria.add(Restrictions.eq("school", obj.getSchool()));
			
			if (StringUtils.isNotBlank(opt.get().getMascot()))
				criteria.add(Restrictions.eq("mascot", obj.getMascot()));
			
			if (StringUtils.isNotBlank(opt.get().getHouseGhost()))
				criteria.add(Restrictions.eq("houseGhost", obj.getHouseGhost()));
			
			if (StringUtils.isNotBlank(opt.get().getFounder()))
				criteria.add(Restrictions.eq("founder", obj.getFounder()));
			
			if (StringUtils.isNotBlank(opt.get().getId()))
				criteria.add(Restrictions.eq("id", obj.getId()));
		}

		return new PageImpl<House>(criteria.list(), pageable, count);
	}

}
