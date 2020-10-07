package com.dao;


import com.model.common.AppUser;
import com.model.common.GenericEntity;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional
public class CommonRepository {

    private static Logger logger = LoggerFactory.getLogger(CommonRepository.class);
    @Autowired
    private JpaContext jpaContext;

    @PersistenceContext
    EntityManager em;



   public <T extends GenericEntity> T saveOrUpdate(T entity){
        T managedEntity=null;
        if(entity!=null && entity.getId()==null) {
            em.persist(entity);
       }else{
            em.merge(entity);
       }
        return entity;
    }


    public <T extends GenericEntity> void delete(T entity){

       if(entity!=null && entity.getId()!=null) {
            em.remove(entity);
        }

    }


   public <T extends GenericEntity> List<T> saveOrUpdateList(List<T> entityList){
      List<T> managedEntityList=new ArrayList<>();
       for(T entity:entityList) {
          if (entity.getId() == null) {
              em.persist(entity);
          } else {
              em.merge(entity);

          }
           managedEntityList.add(entity);
      }
       return managedEntityList;
   }


    public <T extends GenericEntity> T findById(Class<T> entity, Long id){
        return em.find(entity,id);

    }


    public AppUser getUserByUserName(String username){
        List<AppUser> list= new ArrayList<>();
        AppUser user=null;
        Query query = em.createQuery("from AppUser u where u.username=:username");
        query.setParameter("username",username);
        list=query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            user=list.get(0);
        }
     return user;
    }












}
