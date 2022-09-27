package com.example.project.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.PasswordGenerator;
import com.example.project.PasswordValidator;
import com.example.project.Model.PasswordHistory;
import com.example.project.Model.Type;
import com.example.project.Model.User;
import com.example.project.Repository.PasswordHistoryRepository;
import com.example.project.Repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordHistoryRepository passwordHistoryRepository;

	@Autowired
	public UserService(UserRepository userRepository, PasswordHistoryRepository passwordHistoryRepository) {
		this.userRepository = userRepository;
		this.passwordHistoryRepository = passwordHistoryRepository;
	}

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

		String hashedPassword="";
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
			return "User Successfully Registered. \nYour hashed password is "+hashedPassword;
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
		if (Newuser.getPassword() != null && Newuser.getPassword().length() > 0) {
			if (PasswordValidator.isValid(Newuser.getPassword())) {

				// updated password cannot be old password
				if (!BCrypt.checkpw(Newuser.getPassword(), user.getPassword())) {

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
		System.out.println("Login");
		Optional<User> userOptional = userRepository.findUserByUsername(userName);
		System.out.println(userOptional);
		if (userOptional.isPresent()) {
			// username valid cha vane login garna dine
			String passwordString = userRepository.findPassword(userName);
			System.out.println(passwordString);
			System.out.println(password);
			System.out.println(password.equals(passwordString));
			if (password.equals(passwordString)) {
				System.out.println("Equal");
				int userId = userRepository.findUserId(userName);
				System.out.println(userId);
				int PasswordExpiry = userRepository.findPasswordExpiry(userName);
				System.out.println(PasswordExpiry);

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

		return "Successful !! Your new password is " + newPassword;
	}

	@Transactional
	public String Login(String userName, String password) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate currentDate = LocalDate.now();
		Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

		Optional<User> userOptional = userRepository.findUserByUsername(userName);
		if (userOptional.isPresent()) {
			// username valid cha vane login garna dine
			String passwordString = userRepository.findPassword(userName);
			System.out.println(passwordString);

			if (BCrypt.checkpw(password, passwordString)) {

				int userId = userRepository.findUserId(userName);
				System.out.println(userId);
				int PasswordExpiry = userRepository.findPasswordExpiry(userName);
				System.out.println(PasswordExpiry);

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

}
