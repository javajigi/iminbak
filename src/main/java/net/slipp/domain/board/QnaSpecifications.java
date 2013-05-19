package net.slipp.domain.board;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class QnaSpecifications {
	public static Specification<Board> equalsIsDelete(final boolean isDeleted) {
		return new Specification<Board>() {
			@Override
			public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(Board_.deleted), isDeleted);
			}
		};
	}
}
