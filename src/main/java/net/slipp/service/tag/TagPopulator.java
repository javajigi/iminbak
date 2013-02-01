package net.slipp.service.tag;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.slipp.domain.tag.Tag;
import net.slipp.repository.tag.TagRepository;

import org.springframework.stereotype.Component;

@Component
public class TagPopulator {
	@Resource (name = "tagRepository")
	private TagRepository tagRepository;
	
	@PostConstruct
	public void populate() {
		if (tagRepository.findByName("free") == null) {
			tagRepository.save(Tag.pooledTag("free"));
			tagRepository.save(Tag.pooledTag("notice"));
		}
	}
}
