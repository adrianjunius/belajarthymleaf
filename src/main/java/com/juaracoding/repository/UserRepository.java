package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
@Query(value="Select * from `user`where nama like %?1%", nativeQuery = true)

List<UserModel> getUserByName(String user);
@Query(value="Select * FROM `user` WHERE `tanggal` Like %?1%",nativeQuery = true)
List<UserModel> getUserByTanggal(String user);
}
