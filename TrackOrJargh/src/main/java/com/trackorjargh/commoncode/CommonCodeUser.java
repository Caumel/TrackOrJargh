package com.trackorjargh.commoncode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.GenerateURLPage;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.UserRepository;
import com.trackorjargh.mail.MailComponent;

@Service
public class CommonCodeUser {
	private final UserRepository userRepository;
	private final ListsRepository listsRepository;
	private final MailComponent mailComponent;
	private final UserComponent userComponent;
	
	@Autowired
	public CommonCodeUser(UserRepository userRepository, ListsRepository listsRepository, MailComponent mailComponent,
			UserComponent userComponent) {
		this.userRepository = userRepository;
		this.listsRepository = listsRepository;
		this.mailComponent = mailComponent;
		this.userComponent = userComponent;
	}

	public Lists addEmptyListInUser(String name) {
		if (listsRepository.findByUserAndName(userComponent.getLoggedUser(), name) == null) {
			Lists listUser = new Lists(name);
			listUser.setUser(userComponent.getLoggedUser());
			listsRepository.save(listUser);

			return listUser;
		} else {
			return new Lists("hola");
		}
	}

	public User editUser(User user, String email, String password, List<String> roles, String imageUser) {
		if (email != null) {
			user.setEmail(email);
		}
		if (password != null) {
			user.setPassword(password);
		}
		if (!roles.isEmpty()) {
			user.setRoles(roles);
		}
		if (imageUser != null) {
			user.setImage(imageUser);
		}
		userRepository.save(user);
		return user;
	}

	public User newUser(User user, HttpServletRequest request) {
		return newUser(request, user.getName(), user.getPassword(), user.getEmail(), user.getImage(),
				user.isActivatedUser(), user.getRoles().toString());
	}

	public User newUser(HttpServletRequest request, String name, String pass, String email, String image,
			boolean activate, String... role) {
		if (image != null) {
			if (image.equals("")) {
				image = "/img/default-user.png";
			}
		} else {
			image = "/img/default-user.png";
		}

		User newUser = new User(name, pass, email, image, activate, role);

		if (!activate) {
			GenerateURLPage url = new GenerateURLPage(request);
			mailComponent.sendVerificationEmail(newUser, url.generateURLActivateAccount(newUser));
		}

		userRepository.save(newUser);
		return newUser;
	}
}
