package net.slipp.web;

import java.util.List;

import net.slipp.support.web.tags.UrlTag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	@RequestMapping("/room1")
	public String room1(Model model) {
		model.addAttribute("images", roomImages(1, 12));
		model.addAttribute("thumbnailImages", roomThumbnailImages(1, 12));
		return "room/room";
	}

	@RequestMapping("/room2")
	public String room2(Model model) {
		model.addAttribute("images", roomImages(2, 12));
		model.addAttribute("thumbnailImages", roomThumbnailImages(2, 12));
		return "room/room";
	}

	@RequestMapping("/room3")
	public String room3(Model model) {
		model.addAttribute("images", roomImages(3, 11));
		model.addAttribute("thumbnailImages", roomThumbnailImages(3, 11));
		return "room/room";
	}

	@RequestMapping("/room4")
	public String room4(Model model) {
		model.addAttribute("images", roomImages(4, 11));
		model.addAttribute("thumbnailImages", roomThumbnailImages(4, 11));
		return "room/room";
	}

	@RequestMapping("/room5")
	public String room5(Model model) {
		model.addAttribute("images", roomImages(5, 11));
		model.addAttribute("thumbnailImages", roomThumbnailImages(5, 11));
		return "room/room";
	}
	
	private List<String> roomImages(int roomNo, int max) {
		List<String> images = Lists.newArrayList();
		String url = "/images/room/room%d/image%d.jpg";
		for(int i=1; i<max; i++) {
			String formattedUrl = String.format(url, roomNo, i);
			images.add(UrlTag.resource(formattedUrl));
		}
		return images;
	}
	
	private List<String> roomThumbnailImages(int roomNo, int max) {
		List<String> images = Lists.newArrayList();
		String url = "/images/room/room%d/thumb0%d.jpg";
		for(int i=1; i<max; i++) {
			String formattedUrl = String.format(url, roomNo, i);
			images.add(UrlTag.resource(formattedUrl));
		}
		return images;
	}
}
