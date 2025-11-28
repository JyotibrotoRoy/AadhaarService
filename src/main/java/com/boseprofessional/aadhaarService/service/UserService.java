package com.boseprofessional.aadhaarService.service;

import com.boseprofessional.aadhaarService.dto.UserDtos;
import com.boseprofessional.aadhaarService.entity.User;
import com.boseprofessional.aadhaarService.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Created a User
    public UserDtos.UserResponse createUser(UserDtos.CreateUserRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }

        if(userRepository.existsByAadhaarId(request.aadhaarId())){
            throw new RuntimeException("AadhaarId already exists");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setAadhaarId(request.aadhaarId());
        user.setAddress(request.address());
        user.setPhoneNumber(request.phoneNumber());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    //To find a User by ID
    public UserDtos.UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    //Listing All Users
    @SuppressWarnings("unchecked")
    public List<UserDtos.UserResponse> getAllUsers(int page, int size, String sortBy) {
        if(page < 0) page = 0;
        if(size <= 0) size = 10;
        if(size > 100)  size = 100;

        //Preventing SQL injection
        List<String> allowedSorts = Arrays.asList("id", "name", "email");
        String safeSortBY = allowedSorts.contains(sortBy) ? sortBy : "name";

        int offset = page * size;

        String sqlQuery = "SELECT * FROM users ORDER BY " + safeSortBY + " LIMIT :limit OFFSET :offset";

        Query query = entityManager.createNativeQuery(sqlQuery, User.class);
        query.setParameter("limit", size);
        query.setParameter("offset", offset);

        List<User>  users = query.getResultList();
        return users.stream()
                .map(user -> mapToResponse(user))
                .collect(Collectors.toList());
    }



    // this is a helper method to map my response to dto
    private UserDtos.UserResponse mapToResponse(User user){
        return new UserDtos.UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAadhaarId(),
                user.getPhoneNumber(),
                user.getAddress()
        );
    }

}
