package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Player;
import service.PlayerService;
import utils.DEScrypto;
import utils.MailSender;

@Controller
@RequestMapping("/")
public class DEScryptoController {

	@Autowired
	@Qualifier("playerService")
	PlayerService Service;

	@Autowired
	@Qualifier("mailSender")
	MailSender mailSender;

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin(Model model) throws Exception {
		model.addAttribute("list", Service.getAll());
		return "admin";
	}

	@RequestMapping(value = { "/decrypt" }, method = RequestMethod.GET)
	@ResponseBody
	public String decrypt() throws Exception {
		return "Wrong format( /decrypt/{text}/{key} )";
	}

	@RequestMapping(value = { "/decrypt/{text}/{key}" }, method = RequestMethod.GET)
	public String decrypt(@PathVariable String text, @PathVariable String key, Model model) throws Exception {
		
		model.addAttribute("deCode",DEScrypto.decrypt(text, key));
		return "decrypt";
	}

	@RequestMapping(value = { "/", "/Welcome" }, method = RequestMethod.GET)
	public String welcome(Model model, Player player) {
		model.addAttribute("player", player);
		return "welcome";
	}

	@RequestMapping(value = { "/start" }, method = RequestMethod.POST)
	public String begin(HttpServletRequest request, Model model, Player player) {
		String email = player.getEmail();
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		Random rand = new Random();
		int randomNumber = rand.nextInt((999 - 9) + 1) + 9;
		String idStr = dateFormat.format(date) + randomNumber;
		int id = Integer.valueOf(idStr);
		System.out.println(id);
		System.out.println(email);

		Player playerInit = new Player();
		playerInit.setId(id);
		playerInit.setEmail(email);

		String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
		url = url + "/funtime/" + email.replace(".", "%2E");
		mailSender.send(email, "Let the game begin", url);
		Service.insert(playerInit);
		return "welcome";
	}

	@RequestMapping(value = { "/funtime/{email}" }, method = RequestMethod.GET)
	public String funTime(@PathVariable String email, Model model) throws Exception {

		Random rand = new Random();
		int randomNumber = rand.nextInt((999999 - 10000) + 1) + 10000;

		String link = "/decrypt";
		String key = randomNumber + "";
		String code = DEScrypto.encrypt("/lastStep_ConfirmYourAccount", key);

		List<Player> list = Service.getAll();
		System.out.println(list.size());

		Player player3 = Service.getPlayer(email);
		if (player3 == null) {
			System.out.println("null");
		}

		player3.setCode(code);
		Service.update(player3);

		model.addAttribute("link", link);
		model.addAttribute("key", key);
		model.addAttribute("code", code);

		return "code";
	}

	@RequestMapping(value = { "/lastStep_ConfirmYourAccount" }, method = RequestMethod.GET)
	public String lastStep() {
		return "sendmeyouremail";
	}

	@RequestMapping(value = { "/lastStep_ConfirmYourAccount" }, method = RequestMethod.POST)
	@ResponseBody
	public String lastStep(@RequestParam("email") String email, Model model) {
		Player playerSuccess = Service.getPlayer(email);
		if (playerSuccess.getCode() != null) {
			playerSuccess.setStatus(1);
			Service.update(playerSuccess);
			return "/insertInfo/" + playerSuccess.getId();
		}
		return "redirect:/lastStep_ConfirmYourAccount";
	}

	@RequestMapping(value = { "/insertInfo/{id}" }, method = RequestMethod.GET)
	public String insertInfo(@PathVariable Integer id, Model model, Player player) {
		player = Service.getPlayer(id);
		model.addAttribute("player", player);
		return "success";
	}

	@RequestMapping(value = { "/insertInfo/{id}" }, method = RequestMethod.POST)
	public String insertInfo(Model model, Player player) {
		Service.update(player);
		mailSender.send(player.getEmail(), "Congratulation",
				"You are realy smart. I will contact you to give the price");
		mailSender.send("congluat27@gmail.com", "Someone won the price",
				"Name: " + player.getName() + " Phone:" + player.getPhone());
		return "success";
	}
}
