package com.example.bootJPA.repository;

import com.example.bootJPA.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository  extends JpaRepository<File, String> {

}
