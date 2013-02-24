package net.slipp.repository.qna;

import net.slipp.domain.qna.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question>{

}
