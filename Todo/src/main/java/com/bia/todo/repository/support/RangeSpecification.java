/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/repository/support/RangeSpecification.p.vm.java
 */
package com.bia.todo.repository.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.Validate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 * Helper to create {@link Specification} out of {@link Range}s. 
 */
public class RangeSpecification {

    public static <E> Specifications<E> andRangeIfSet(Specifications<E> specifications, final List<Range<E, ?>> ranges) {
        for (Range<E, ?> range : ranges) {
            if (range.isSet()) {
                specifications = specifications.and(toSpecification(range));
            }
        }
        return specifications;
    }

    public static <E, D extends Comparable<? super D>> Specification<E> toSpecification(final Range<E, D> range) {
        Validate.isTrue(range.isSet(), "You must pass an exploitable range");
        return new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate rangePredicate = null;

                if (range.isBetween()) {
                    rangePredicate = builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
                } else if (range.isFromSet()) {
                    rangePredicate = builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
                } else if (range.isToSet()) {
                    rangePredicate = builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
                }

                if (rangePredicate != null) {
                    if (!range.isIncludeNullSet() || range.getIncludeNull() == Boolean.FALSE) {
                        return rangePredicate;
                    } else {
                        return builder.or(rangePredicate, builder.isNull(root.get(range.getField())));
                    }
                }

                // no range at all
                // take the opportunity to keep only null...
                if (range.getIncludeNull() == Boolean.TRUE) {
                    return builder.isNull(root.get(range.getField()));
                }

                // ... or non-null only...
                if (range.getIncludeNull() == Boolean.FALSE) {
                    return builder.isNotNull(root.get(range.getField()));
                }

                throw new IllegalStateException("You must pass an exploitable range (should not happen here)");
            }
        };
    }
}
