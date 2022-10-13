package com.example.project.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.PasswordGenerator;
import com.example.project.PasswordValidator;
import com.example.project.Model.PasswordHistory;
import com.example.project.Model.Type;
import com.example.project.Model.User;
import com.example.project.Repository.PasswordHistoryRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.utility.JWTUtility;

import net.minidev.json.JSONObject;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	private final PasswordHistoryRepository passwordHistoryRepository;

	@Autowired
	public UserService(UserRepository userRepository, PasswordHistoryRepository passwordHistoryRepository) {
		this.userRepository = userRepository;
		this.passwordHistoryRepository = passwordHistoryRepository;
	}

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User findUser(int userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));
	}

	public String addNewUser(User user) {
		Optional<User> userOptional = userRepository.findUserByUsername(user.getUserName());
		System.out.println(userOptional);
		if (userOptional.isPresent()) {
			return "Username is already taken";
			// throw new IllegalStateException("Username is already taken");
		}

		String hashedPassword = "";
		String password = user.getPassword();
		if (PasswordValidator.isValid(password)) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPassword = passwordEncoder.encode(password);

			user.setPassword(hashedPassword);

			userRepository.save(user);

			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate currentDate = LocalDate.now();
			Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

			PasswordHistory passwordHistory = new PasswordHistory();

			Type type = new Type();
			type.setTypeId(16);

			User userIdUser = new User();
			userIdUser.setUserId(user.getUserId());

			passwordHistory.setUserId(userIdUser);
			passwordHistory.setDate(endDate);
			passwordHistory.setReasonType(type);
			passwordHistory.setPassword(hashedPassword);
			passwordHistoryRepository.save(passwordHistory);
			return "User Successfully Registered. \nYour hashed password is " + hashedPassword;
		}
		return "New password doesn't meet required complexity definitions. \nRequired: minimum of 8characters, 1 numerical, 1 special character, 1capital letter and 1small letter";
	}

	public void deleteUser(int userId) {
		boolean exists = userRepository.existsById(userId);
		if (!exists) {
			throw new IllegalStateException("user with id " + userId + "does not exist");
		}
		userRepository.deleteById(userId);
	}

	@Transactional
	public String updateUser(int userId, User Newuser) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));

		if (Newuser.getUserName() != null && Newuser.getUserName().length() > 0
				&& !Objects.equals(user.getUserName(), Newuser.getUserName())) {

			// checking if new username is available
			Optional<User> userOptional = userRepository.findUserByUsername(Newuser.getUserName());
			if (userOptional.isPresent()) {
				// throw new IllegalStateException("Username is already taken");
				return "Username is already taken";
			}
			user.setUserName(Newuser.getUserName());
			// return "Successfully Updated the username record";
		}

		String hashedPassword = "";

		List<String> list = userRepository.findLastPassword(Newuser.getUserName());
		int length = list.size();
		int i = 0;

		if (Newuser.getPassword() != null && Newuser.getPassword().length() > 0) {
			if (PasswordValidator.isValid(Newuser.getPassword())) {

				// updated password cannot be old password
				if (!BCrypt.checkpw(Newuser.getPassword(), user.getPassword())) {

					while (i < length) {
						if (!BCrypt.checkpw(Newuser.getPassword(), list.get(i))) {

							String password = Newuser.getPassword();
							BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
							hashedPassword = passwordEncoder.encode(password);

							user.setPassword(hashedPassword);

							ZoneId defaultZoneId = ZoneId.systemDefault();
							LocalDate currentDate = LocalDate.now();
							Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

							PasswordHistory passwordHistory = new PasswordHistory();

							Type type = new Type();
							type.setTypeId(6);

							User userIdUser = new User();
							userIdUser.setUserId(userId);

							passwordHistory.setUserId(userIdUser);
							passwordHistory.setDate(endDate);
							passwordHistory.setReasonType(type);
							passwordHistory.setPassword(hashedPassword);
							passwordHistoryRepository.save(passwordHistory);
							// return "Successfully Updated the password record";
						}
						i++;
					}
				} else {
					return "New password cannot be same as old password";
				}
			} else {
				return "New password doesn't meet required complexity definitions. \nRequired: minimum of 8characters, 1 numerical, 1 special character, 1capital letter and 1small letter";
			}
		}

		if (Newuser.getUserStatus() != null) {
			user.setUserStatus(Newuser.getUserStatus());
			// return "Successfully Updated the status record";
		}

		if (Newuser.getPasswordExpiryMonths() != null) {
			user.setPasswordExpiryMonths(Newuser.getPasswordExpiryMonths());
			// return "Successfully Updated the expiry records";
		}

		return "Updation Successful. \nYour new password is " + hashedPassword;
	}

	@Transactional
	public String LoginHashedPassword(String userName, String password) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate currentDate = LocalDate.now();
		Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

		Optional<User> userOptional = userRepository.findUserByUsername(userName);

		if (userOptional.isPresent()) {
			// username valid cha vane login garna dine
			String passwordString = userRepository.findPassword(userName);

			if (password.equals(passwordString)) {
				int userId = userRepository.findUserId(userName);
				int PasswordExpiry = userRepository.findPasswordExpiry(userName);

				if (PasswordExpiry == 0) {
					return "Successful Login !! You chose not to expire your password";
				} else {
					Date expiryDate = userRepository.findExpiryDate(PasswordExpiry, userId);
					System.out.println(expiryDate);
					if (expiryDate.compareTo(endDate) <= 0) {
						return "Your password has expired on " + expiryDate + ". \nPlease update it and login again";
					}
					return "Successful Login !! \nYour password will expire on " + expiryDate;
				}
			}
		}
		return "UserName or Password is invalid";
	}

	@Transactional
	public String resetPassword(int userId, String userName, String oldPassword, String newPassword) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));

		if (userName != null && userName.length() > 0) {

			Optional<User> userOptional = userRepository.findUserByUsername(userName);
			if (userOptional.isPresent()) {
				return "Username is already taken";
			}

			user.setUserName(userName);
		}

		if (oldPassword != null && oldPassword.length() > 0 && !Objects.equals(user.getPassword(), oldPassword)) {

			// null aairacha
			String usernameString = "'" + userName + "'";
			System.out.println(usernameString);
			Optional<User> userOptional = userRepository.findLastPasswords(usernameString);
			System.out.println(userOptional);

			if (BCrypt.checkpw(oldPassword, user.getPassword())) {

				if (newPassword != null && newPassword.length() > 0 && !Objects.equals(oldPassword, newPassword)) {

					if (PasswordValidator.isValid(newPassword)) {

						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						String hashedPassword = passwordEncoder.encode(newPassword);

						user.setPassword(hashedPassword);

						return "Successfully Updated the records";
					}
					return "New password doesn't meet required complexity definitions.\nRequired: minimum of 8characters, 1 numerical, 1 special character, 1capital letter and 1small letter";
				}
				return "New Password cannot be old password";
			}
			return "Username or password is incorrect";
		}
		return "Password Reset Failed";
	}

	@Transactional
	public String forgotPassword(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));

		String generatedPassword = PasswordGenerator.generateStrongPassword();
		String newPassword = PasswordGenerator.shuffleString(generatedPassword);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(newPassword);

		user.setPassword(hashedPassword);

		return "Successful !! Your new password is " + hashedPassword;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Transactional
	public JSONObject Login(String userName, String password) {
		JSONObject jsonObject = new JSONObject();

		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate currentDate = LocalDate.now();
		Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

		Optional<User> userOptional = userRepository.findUserByUsername(userName);
		if (userOptional.isPresent()) {
			// username valid cha vane login garna dine
			String passwordString = userRepository.findPassword(userName);
			System.out.println(passwordString);

			int userId;
			int PasswordExpiry;
			Date expiryDate;

			if (BCrypt.checkpw(password, passwordString)) {

				userId = userRepository.findUserId(userName);
				System.out.println(userId);
				PasswordExpiry = userRepository.findPasswordExpiry(userName);
				System.out.println(PasswordExpiry);

				try {
					authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(userName, passwordString));
				} catch (BadCredentialsException e) {
					jsonObject.put("error", "INVALID CREDENTIALS " + e);
					return jsonObject;
				}

				if (PasswordExpiry == 0) {
					UserDetails userDetails = loadUserByUsername(userName);
					final String token = jwtUtility.generateToken(userDetails);

					jsonObject.put("message", "Successful Login !! You chose not to expire your password ");

					jsonObject.put("id", userId);
					jsonObject.put("user", userName);
					jsonObject.put("token", token);

					return jsonObject;

				} else {
					expiryDate = userRepository.findExpiryDate(PasswordExpiry, userId);
					System.out.println(expiryDate);
					if (expiryDate.compareTo(endDate) <= 0) {
						jsonObject.put("message",
								"Your password has expired on " + expiryDate + ". Please update it and login again");

						return jsonObject;
					}

					UserDetails userDetails = loadUserByUsername(userName);
					final String token = jwtUtility.generateToken(userDetails);

					jsonObject.put("message", "Successful Login !! Your password will expire on " + expiryDate);

					jsonObject.put("id", userId);
					jsonObject.put("user", userName);
					jsonObject.put("token", token);

					return jsonObject;
				}
			}
		}
		jsonObject.put("error", "UserName or Password is invalid");

		return jsonObject;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByusername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		String name = user.getUserName();
		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(name, password, new ArrayList<>());
	}
}
