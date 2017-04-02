package bk.tailfile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/files")
public class TailController {
	private SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyyMMdd");
	@RequestMapping("/home")
	public String home(Model model) {
//		String filePath= "D:/tmp/";
		String filePath= "/home/firefly/log/10.1.31.21/Log/";
		model.addAttribute("filename", filePath+ "messages-"+simpleDateFormat.format(new Date())+".log");
		return "files/home";
	}

}
