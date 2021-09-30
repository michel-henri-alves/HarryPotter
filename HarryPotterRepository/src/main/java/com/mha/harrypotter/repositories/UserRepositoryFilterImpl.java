package com.mha.harrypotter.repositories;

/**
 * User Repository filter implementation
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mha.harrypotter.model.User;


@Repository
public class UserRepositoryFilterImpl implements UserRepositoryFilter {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<User> findByNameAndCnpj(Pageable pageable, User user){
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(User.class);
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		criteria.setFirstResult(pageable.getPageNumber());
		criteria.setMaxResults(pageable.getPageSize());

		Criteria criteriaCount = session.createCriteria(User.class);
		criteriaCount.setProjection(Projections.rowCount());

		Long count = (Long) criteriaCount.uniqueResult();

		Optional<User> opt = Optional.ofNullable(user);
		
		if(opt.isPresent()){
			if (StringUtils.isNotBlank(opt.get().getName()))
				criteria.add(Restrictions.eq("name", user.getName()));
			
			if (opt.get().getEmail() != null)
				criteria.add(Restrictions.eq("email", user.getEmail()));
			
			if (opt.get().getPassword() != null)
				criteria.add(Restrictions.eq("password", user.getPassword()));
			
			if (opt.get().getApiKey() != null)
				criteria.add(Restrictions.eq("apiKey", user.getApiKey()));
			
			if (opt.get().getId() != 0)
				criteria.add(Restrictions.eq("id", user.getId()));
		}

		return new PageImpl<User>(criteria.list(), pageable, count);
	}

}
