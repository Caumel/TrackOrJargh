package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long>{

}
