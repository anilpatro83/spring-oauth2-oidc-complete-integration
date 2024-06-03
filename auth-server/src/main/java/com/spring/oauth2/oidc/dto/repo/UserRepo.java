package com.spring.oauth2.oidc.dto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.oauth2.oidc.dto.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Query("Select user.email, user.password, auth.authority from User user "
			+ "join UserAuthority ur on user.id=ur.userId join  "
			+ "Authority auth on auth.id=ur.authorityId "
			+ "where user.email=:email")
	List<Object[]> findByUserEmail(@Param("email") String email);
}
