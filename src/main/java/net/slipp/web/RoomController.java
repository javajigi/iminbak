package net.slipp.web;

import java.util.List;

import net.slipp.domain.room.RoomType;
import net.slipp.support.web.tags.UrlTag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	@RequestMapping("/{type}")
	public String room(@PathVariable RoomType type, Model model) {
		model.addAttribute("type", type);
		model.addAttribute("images", roomImages(type));
		model.addAttribute("thumbnailImages", roomThumbnailImages(type));
		
		if (type == RoomType.lavender) {
			return "room/room_type2"; 
		}
		if (type == RoomType.out) {
			return "room/out";
		}
		return "room/room";
	}

	private List<String> roomImages(RoomType type) {
		List<String> images = Lists.newArrayList();
		String url = "/images/room/room%d/image%d.jpg";
		for(int i=1; i<type.getImageLength(); i++) {
			String formattedUrl = String.format(url, type.getType(), i);
			images.add(UrlTag.resource(formattedUrl));
		}
		return images;
	}
	
	private List<String> roomThumbnailImages(RoomType type) {
		List<String> images = Lists.newArrayList();
		String url = "/images/room/room%d/thumb0%d.jpg";
		for(int i=1; i<type.getImageLength(); i++) {
			String formattedUrl = String.format(url, type.getType(), i);
			images.add(UrlTag.resource(formattedUrl));
		}
		return images;
	}
}
