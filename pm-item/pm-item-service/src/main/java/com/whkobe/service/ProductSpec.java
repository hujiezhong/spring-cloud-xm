package com.whkobe.service;

import com.mysql.fabric.xmlrpc.base.Param;
import com.whkobe.pojo.*;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.JoinTable;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSpec {
    public static Specification<Product> toFenLei9(Product product){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (product.getInventory() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("inventory"), 0));
                }
                predicates.add(criteriaBuilder.equal(root.get("cid"),Integer.valueOf(product.getCid())));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<Product> toFenLei(Product product){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (product.getInventory() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("inventory"), 0));
                }
                Join<Product, Edition> editionJoin=root.join("edition",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(editionJoin.get("pid"),root.get("pid")));
                if (product.getRateid() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(editionJoin.get("eyuanprice"), editionJoin.get("eshopprice")));
                }

                predicates.add(criteriaBuilder.equal(root.get("cid"),Integer.valueOf(product.getCid())));
                criteriaQuery.groupBy(root.get("pid"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<Product> toFenLei2(Product product) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<Product, Edition> editionJoin=root.join("edition",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(editionJoin.get("pid"),root.get("pid")));
                if (product.getInventory() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("inventory"), 0));
                }
                if (product.getRateid() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(editionJoin.get("eyuanprice"), editionJoin.get("eshopprice")));
                }
               if(product.getPostage()!=0){
                    Join<Product, Comment> commentJoin=root.join("comment",JoinType.INNER);
                  /*  predicates.add(criteriaBuilder.equal(commentJoin.get("pid"),product.getPid()));*/
                    Join<Comment, User> userJoin=commentJoin.join("user", JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(userJoin.get("uid"),commentJoin.get("uid")));
               }

                predicates.add(criteriaBuilder.equal(root.get("cid"), Integer.valueOf(product.getCid())));
               criteriaQuery.groupBy(root.get("pid"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<Product> toFenLei3(Product product) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (product.getInventory() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("inventory"), 0));
                }
                Join<Product, Edition> editionJoin=root.join("edition",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(editionJoin.get("pid"),root.get("pid")));
                if (product.getRateid() != 0) {
                    predicates.add(criteriaBuilder.greaterThan(editionJoin.get("eyuanprice"), editionJoin.get("eshopprice")));
                }
                if(!product.getPname().equals("")){
                    predicates.add(criteriaBuilder.like(root.get("pname"),"%"+product.getPname()+"%"));
                }
                if(product.getPostage()!=0){
                    Join<Product, Comment> commentJoin=root.join("comment",JoinType.INNER);
                    /*  predicates.add(criteriaBuilder.equal(commentJoin.get("pid"),product.getPid()));*/
                    Join<Comment, User> userJoin=commentJoin.join("user", JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(userJoin.get("uid"),commentJoin.get("uid")));
                }

                criteriaQuery.groupBy(root.get("pid"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<Category> toFenLei4(Product product) {
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                    Join<Category, Product> commentJoin=root.join("product",JoinType.INNER);
                    predicates.add(criteriaBuilder.like(commentJoin.get("pname"),"%"+product.getPname()+"%"));

                criteriaQuery.groupBy(root.get("cid"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<Edition> toFenLei5(int eid) {
        return new Specification<Edition>() {
            @Override
            public Predicate toPredicate(Root<Edition> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<Edition, Product> commentJoin=root.join("product",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(root.get("eid"),eid));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
