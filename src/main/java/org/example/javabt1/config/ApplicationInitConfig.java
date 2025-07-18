//package org.example.javabt1.config;
//
//
//import com.example.webbe.Entity.Role;
//import com.example.webbe.Entity.User;
//import com.example.webbe.Enums.RoleEnum;
//import com.example.webbe.Repository.RoleRepository;
//import com.example.webbe.Repository.UserRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Set;
//
//@RequiredArgsConstructor
//@Slf4j
//@Configuration
//public class ApplicationInitConfig {
//
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//
//    @Bean
//    public ApplicationRunner applicationRunner(UserRepo userRepo, RoleRepository roleRepository) {
//        return args -> {
//            if (userRepo.findByName("admin").isEmpty()) {
//                // Tạo vai trò ADMIN nếu chưa tồn tại trong cơ sở dữ liệu
//                Role adminRole = roleRepository.findByName(RoleEnum.ADMIN.name())
//                        .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ADMIN)));
//
//                // Khởi tạo tài khoản admin với vai trò ADMIN
//                User user = User.builder()
//                        .name("admin")
//                        .password(passwordEncoder.encode("admin"))
//                        .roles(Set.of(adminRole)) // Sử dụng Set.of() để thêm vai trò ADMIN
//                        .build();
//
//                userRepo.save(user);
//
//                log.warn("Đã tạo thành công tài khoản Admin");
//            }
//        };
//    }
//
//
//}
