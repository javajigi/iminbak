package net.slipp.repository.attachment;

import net.slipp.domain.board.Attachment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String>{

}
