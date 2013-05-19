package net.slipp.domain.board;

import static org.springframework.data.jpa.domain.Specifications.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class BoardSpecifications {
	public static Specification<Board> equalsIsDelete(final boolean isDeleted) {
		return new Specification<Board>() {
			@Override
			public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(Board_.deleted), isDeleted);
			}
		};
	}
	
	public static Specification<Board> equalsBoardType(final BoardType boardType) {
		return new Specification<Board>() {
			@Override
			public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(Board_.boardType), boardType);
			}
		};
	}
	
	public static Specification<Board> findBoards(final BoardType boardType, final boolean isDeleted) {
		Specifications<Board> specs = where(equalsBoardType(boardType));
		return specs.and(equalsIsDelete(isDeleted));
	}
}
