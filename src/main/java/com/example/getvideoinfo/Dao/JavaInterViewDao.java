package com.example.getvideoinfo.Dao;

import com.example.getvideoinfo.Entity.JavaBaseInfo;
import com.example.getvideoinfo.Entity.SaveVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface JavaInterViewDao extends JpaRepository<JavaBaseInfo, String> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `javaBaseInfo`(`id`, `title`, `content`) VALUES (?1,?2,?3)")
    int insertJavaInterviewInfo(String id, String title, String content);
    @Query(nativeQuery = true, value = "select * from `javaBaseInfo` ")
    List<JavaBaseInfo> selectJavaInterviewInfo();

}
