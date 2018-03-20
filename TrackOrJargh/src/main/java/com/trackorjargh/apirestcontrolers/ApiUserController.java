package com.trackorjargh.apirestcontrolers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.commoncode.CommonCodeUser;
import com.trackorjargh.javaclass.DeleteElementsOfBBDD;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.UserRepository;

@RestController
@RequestMapping("/api")
public class ApiUserController {
	private final UserRepository userRepository;
	private final CommonCodeUser commonCodeUser;
	private final DeleteElementsOfBBDD deleteElementofBBDD;
		
	@Autowired
	public ApiUserController(UserRepository userRepository, CommonCodeUser commonCodeUser,
			DeleteElementsOfBBDD deleteElementofBBDD) {
		this.userRepository = userRepository;
		this.commonCodeUser = commonCodeUser;
		this.deleteElementofBBDD = deleteElementofBBDD;
	}
	
	@RequestMapping(value = "/usuarios/{name}", method = RequestMethod.GET)
	@JsonView(User.BasicInformation.class)
	public ResponseEntity<User> getUser(@PathVariable String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(HttpServletRequest request, @RequestBody User user) {
		if (userRepository.findByNameIgnoreCase(user.getName()) == null
				&& userRepository.findByEmail(user.getEmail()) == null) {
			return new ResponseEntity<>(commonCodeUser.newUser(user, request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}
	
	@RequestMapping(value = "/usuarios/{name}", method = RequestMethod.PUT)
	@JsonView(User.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> editUser(@PathVariable String name, @RequestBody User user,
			HttpServletRequest request) {
		if (userRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			User editedUser = userRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(commonCodeUser.editUser(editedUser, user.getEmail(), user.getPassword(),
					user.getRoles(), user.getImage()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/usuarios/{name}", method = RequestMethod.DELETE)
	@JsonView(User.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> deleteUser(@PathVariable("name") String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			deleteElementofBBDD.deleteUser(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/acomprobarusuario/{name}/", method = RequestMethod.GET)
	public boolean checkUser(@PathVariable String name) {
		User user = userRepository.findByNameIgnoreCase(name);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/comprobaremail/{email}/", method = RequestMethod.GET)
	public boolean checkEmail(@PathVariable String email) {
		User user = userRepository.findByEmail(email);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
}
